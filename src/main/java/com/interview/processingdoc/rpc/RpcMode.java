package com.interview.processingdoc.rpc;

import com.interview.processingdoc.common.constance.Consts;
import com.interview.processingdoc.common.dto.PendingDocDto;
import com.interview.processingdoc.config.AppProperties;
import com.interview.processingdoc.provider.PreSrcDoc;
import com.interview.processingdoc.service.DocService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.*;

/**
 * @Description
 * @Author jing.c
 * @Date: 18-4-14
 */
@Component
public class RpcMode {
    protected final static Logger logger = LoggerFactory.getLogger(RpcMode.class);

    @Autowired
    DocService docService;
    @Autowired
    AppProperties appProperties;
    //读取文档的线程池
    private ExecutorService docReaderService
            = Executors.newFixedThreadPool(Consts.THREAD_COUNT_BASE*2);
    //入库数据的线程池
    private ExecutorService docDataService
            = Executors.newFixedThreadPool(Consts.THREAD_COUNT_BASE*2);

    private CompletionService readerCompletionService
            = new ExecutorCompletionService(docReaderService);
    private CompletionService dataCompletionService
            = new ExecutorCompletionService(docDataService);


    private class ReadDocTask implements Callable<PendingDocDto> {
        private PendingDocDto pendingDocVo;

        public ReadDocTask(PendingDocDto pendingDocVo) {
            this.pendingDocVo = pendingDocVo;
        }

        @Override
        public PendingDocDto call() throws Exception {
            long start = System.currentTimeMillis();
            PendingDocDto file= docService.makeAsyn(pendingDocVo);
            logger.info("文档["+file.getDocPath()+"]读取耗时："
                    +(System.currentTimeMillis()-start)+"ms");
            return file;
        }
    }


    private class dbIOTask implements Callable<String>{
        private PendingDocDto localName;

        public dbIOTask(PendingDocDto localName) {
            this.localName = localName;
        }

        @Override
        public String call() throws Exception {
            long  start = System.currentTimeMillis();
            String fileurl = docService.loadDoc(localName);
            logger.info("["+fileurl+"]已存入数据库耗时："
                    +(System.currentTimeMillis()-start)+"ms");
            return fileurl;
        }
    }

    public void start(String folderPath)
            throws InterruptedException, ExecutionException {
        logger.info("数据文件开始初始化...........");
        List<PendingDocDto> docList = PreSrcDoc.makeDoc(folderPath);
        logger.info("初始化完成。");

        long startTotal = System.currentTimeMillis();

        for(PendingDocDto doc:docList){
            readerCompletionService.submit(new ReadDocTask(doc));
        }

        for(PendingDocDto doc:docList){
            Future<PendingDocDto> f = readerCompletionService.take();
            dataCompletionService.submit(new dbIOTask(f.get()));
        }

        for(PendingDocDto doc:docList){
            //把存入数据库的文件拿到
            dataCompletionService.take().get();
        }
        logger.info("共耗时："+(System.currentTimeMillis()-startTotal)+"ms");
    }
}
