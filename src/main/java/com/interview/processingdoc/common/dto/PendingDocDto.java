package com.interview.processingdoc.common.dto;

import com.interview.processingdoc.common.persistence.model.TimeSeriesData;

import java.util.List;

/**
 * @Description 待处理文档实体类
 * @Author jing.c
 * @Date: 18-4-14
 */
public class PendingDocDto {
    //待处理文档名称
    private final String docName;
    //待处理文档名称
    private final String docPath;
    //待处理文档中title列表
    private List<String> colList;
    //待处理文档中data
    private List<TimeSeriesData> dataList;

    public PendingDocDto(String docName, String docPath) {
        this.docName = docName;
        this.docPath = docPath;
    }

    public String getDocName() {
        return docName;
    }

    public String getDocPath() {
        return docPath;
    }

    public List<String> getColList() {
        return colList;
    }

    public List<TimeSeriesData> getDataList() {
        return dataList;
    }

    public void setColList(List<String> list) {
        this.colList = list;
    }

    public void setDataList(List<TimeSeriesData> list) {
        dataList = list;
    }
}
