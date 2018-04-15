package com.interview.processingdoc.core;

import com.interview.processingdoc.rpc.RpcMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Description 服务启动执行
 * @Author jing.c
 * @Date: 18-4-15
 */
@Component
@Order(value=5)
public class MyStartupRunner implements CommandLineRunner {
    protected final static Logger logger = LoggerFactory.getLogger(MyStartupRunner.class);
    @Autowired
    RpcMode rpcMode;

    @Override
    public void run(String... args) throws Exception {
        logger.info(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作 <<<<<<<<<<<<<");
        rpcMode.start(args[0]);
    }
}
