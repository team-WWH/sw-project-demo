package com.example.wwh.service;

import com.example.wwh.Config.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PptSplitService {

    private static final int CHUNK_SIZE = 5;
    @Autowired
    private  MinioService minioService;

    public List<String> splitPpt(byte[] fileData, int currentPage, String originalName) throws Exception {
        List<String> chunkPaths = new ArrayList<>();

        try (XMLSlideShow presentation = new XMLSlideShow(new ByteArrayInputStream(fileData))) {
            int totalSlides = presentation.getSlides().size();
            validateCurrentPage(currentPage, totalSlides, "PPT");

            // 每10页分块处理
            int chunkIndex = 1;
            for (int i = 0; i < currentPage; i += CHUNK_SIZE) {
                int start = i;
                int end = Math.min(i + CHUNK_SIZE, currentPage);

                XMLSlideShow chunkShow = new XMLSlideShow();
                chunkShow.setPageSize(presentation.getPageSize());

                // 添加幻灯片
                for (int j = start; j < end; j++) {
                    XSLFSlide sourceSlide = presentation.getSlides().get(j);
                    XSLFSlide newSlide = chunkShow.createSlide(sourceSlide.getSlideLayout());
                    newSlide.importContent(sourceSlide);
                }

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                chunkShow.write(outputStream);
                chunkShow.close();

                String ext = originalName.substring(originalName.lastIndexOf('.') + 1);
                String chunkName = FileUtils.generateChunkName(originalName, ext, chunkIndex);
                minioService.uploadChunk(outputStream.toByteArray(), chunkName,
                        "application/vnd.openxmlformats-officedocument.presentationml.presentation");
                chunkPaths.add(chunkName);

                chunkIndex++;
            }

            //log.info("Split PPTX into {} chunks", chunkPaths.size());
        } catch (IOException e) {
           // log.error("PPT processing error", e);
            throw new Exception("PPT processing failed", e);
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
