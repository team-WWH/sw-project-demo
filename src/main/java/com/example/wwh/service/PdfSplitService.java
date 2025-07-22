package com.example.wwh.service;


import com.example.wwh.Config.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PdfSplitService {

    private static final int CHUNK_SIZE = 3;
    @Autowired
    private  MinioService minioService;

    public List<String> splitPdf(byte[] fileData, int currentPage, String originalName) throws Exception {
        List<String> chunkPaths = new ArrayList<>();

        try (PDDocument document = Loader.loadPDF(fileData)){
            int totalPages = document.getNumberOfPages();
            validateCurrentPage(currentPage, totalPages, "PDF");
            // 创建只包含已讲页面的文档
            Splitter splitter = new Splitter();
            splitter.setStartPage(1);
            splitter.setEndPage(currentPage);
            List<PDDocument> presentedPages = splitter.split(document);

            // 每10页分块处理
            int chunkIndex = 1;
            for (int i = 0; i < presentedPages.size(); i += CHUNK_SIZE) {
                int end = Math.min(i + CHUNK_SIZE, presentedPages.size());
                PDDocument chunkDoc = new PDDocument();

                for (int j = i; j < end; j++) {
                    chunkDoc.addPage(presentedPages.get(j).getPage(0));
                }

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                chunkDoc.save(outputStream);
                chunkDoc.close();

                String chunkName = FileUtils.generateChunkName(originalName, "pdf", chunkIndex);
                minioService.uploadChunk(outputStream.toByteArray(), chunkName, "application/pdf");
                chunkPaths.add(chunkName);

                chunkIndex++;
            }

            //log.info("Split PDF into {} chunks", chunkPaths.size());
        } catch (IOException e) {
            //log.error("PDF processing error", e);
            throw new Exception("PDF processing failed", e);
        }
        return chunkPaths;
    }

    private void validateCurrentPage(int currentPage, int totalPages, String fileType) {
        if (currentPage < 1 || currentPage > totalPages) {
            throw new IllegalArgumentException(
                    "Invalid current page for " + fileType + ". Total pages: " + totalPages
            );
        }
    }
}
