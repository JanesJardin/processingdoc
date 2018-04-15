package com.interview.processingdoc;

import com.interview.processingdoc.rpc.RpcMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutionException;

/**
 * SpringBoot方式启动类
 */
@SpringBootApplication
public class ProcDocApplication {
	protected final static Logger logger = LoggerFactory.getLogger(ProcDocApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProcDocApplication.class, args);
	}
}
