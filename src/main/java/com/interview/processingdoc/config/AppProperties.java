package com.interview.processingdoc.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author jing.c
 * @Date: 18-4-14
 */
@Component
@Data
@ConfigurationProperties(prefix = AppProperties.PREFIX)
public class AppProperties {
    public static final String PREFIX = "processingdoc";

}
