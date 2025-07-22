package com.example.wwh.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileSplitService {
    @Autowired
    private  PdfSplitService pdfSplitService;
    @Autowired
    private  PptSplitService pptSplitService;
    @Autowired
    private  MinioService minioService;

    public List<String> splitFile(String objectName, int currentPage) throws Exception {
        byte[] fileData = minioService.downloadFile(objectName);
        String fileName = objectName.toLowerCase();

        if (fileName.endsWith(".pdf")) {
            return pdfSplitService.splitPdf(fileData, currentPage, objectName);
        } else if (fileName.endsWith(".pptx") || fileName.endsWith(".ppt")) {
            return pptSplitService.splitPpt(fileData, currentPage, objectName);
        } else {
            throw new UnsupportedOperationException("Unsupported file format: " + fileName);
        }
    }
}