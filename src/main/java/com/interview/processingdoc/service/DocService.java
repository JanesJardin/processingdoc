package com.interview.processingdoc.service;

import com.interview.processingdoc.common.persistence.dao.TimeSeriesDataMapper;
import com.interview.processingdoc.common.persistence.model.TimeSeriesData;
import com.interview.processingdoc.common.dto.PendingDocDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @Description 文档处理服务
 * @Author jing.c
 * @Date: 18-4-14
 */
@Service
public class DocService {
    protected final static Logger logger = LoggerFactory.getLogger(DocService.class);

    @Autowired
    TimeSeriesDataMapper timeSeriesDataMapper;

    /**
     * 入库文档到数据库
     */
    public String loadDoc(PendingDocDto doc) {

        List<TimeSeriesData> list = doc.getDataList();
        for (TimeSeriesData data : list)
            timeSeriesDataMapper.insert(data);
        return doc.getDocPath();
    }

    /**
     * 异步并行处理文档中的data
     *
     * @param pendingDocDto
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public PendingDocDto makeAsyn(PendingDocDto pendingDocDto) throws ExecutionException, InterruptedException {
        logger.info("开始处理文档：" + pendingDocDto.getDocPath());
        File f = new File(pendingDocDto.getDocPath());
        try {
            if (!f.exists()) {
                throw new FileNotFoundException("data file path [" + pendingDocDto.getDocName() + "] not exist!");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "GBK"));
            String line = reader.readLine();
            pendingDocDto.setColList(Arrays.asList(line.split(",")));
            List<TimeSeriesData> dataList = new ArrayList<>();
            pendingDocDto.setDataList(dataList);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmmss");
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields == null || fields.length == 0) continue;
                TimeSeriesData data = new TimeSeriesData();
                data.setStockCode(fields[0]);
                data.setItemValue(Double.valueOf(fields[1]));
                data.setTradingDate(sdf.parse(pendingDocDto.getDocName()));
                dataList.add(data);
//                logger.info(data.toString());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return pendingDocDto;

    }
}
