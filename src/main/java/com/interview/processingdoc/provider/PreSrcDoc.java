package com.interview.processingdoc.provider;

import com.interview.processingdoc.common.dto.PendingDocDto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author jing.c
 * @Date: 18-4-14
 */
public class PreSrcDoc {
    private static String CSV ="csv";
    /**
     * 形成待处理文档
     *
     * @param dataFilePath 数据文件存放文件夹路径
     * @return 待处理文档列表
     */
    public static List<PendingDocDto> makeDoc(String dataFilePath) {
        File f = new File(dataFilePath);
        if (!f.exists()) {
            try {
                throw new Exception("data file path not exist!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        File[] files = f.listFiles();
        List<PendingDocDto> docList = new ArrayList<>();//文档列表
        for (File file : files) {
            if (!file.isDirectory()) {
                String fileName = file.getName();
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                if(suffix.equals(CSV)) {
                    PendingDocDto pendingDocVo = new PendingDocDto(fileName, file.getAbsolutePath());
                    docList.add(pendingDocVo);
                }
            }
        }
        return docList;
    }
}
