package com.example.wwh.service;

import org.apache.poi.hslf.usermodel.*;
import org.apache.poi.xslf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * 修复版PPT解析器，支持PPT和PPTX格式
 * 将PowerPoint文件转换为包含所有幻灯片文本内容的字符串
 */
@Service
public class PPTParser {

    public String parse(byte[] file, String filename) throws IOException {
        if (filename == null) {
            throw new IllegalArgumentException("文件名不能为空");
        }

        try (InputStream inputStream = new ByteArrayInputStream(file)) {
            if (filename.toLowerCase().endsWith(".pptx")) {
                return parsePPTX(inputStream);
            } else if (filename.toLowerCase().endsWith(".ppt")) {
                return parsePPT(inputStream);
            } else {
                throw new UnsupportedOperationException("不支持的文件格式: " + filename);
            }
        }
    }

    private String parsePPTX(InputStream inputStream) throws IOException {
        StringBuilder result = new StringBuilder();
        try (XMLSlideShow pptx = new XMLSlideShow(inputStream)) {
            List<XSLFSlide> slides = pptx.getSlides();

            for (int i = 0; i < slides.size(); i++) {
                XSLFSlide slide = slides.get(i);
                result.append("--- 幻灯片 ").append(i + 1).append(" ---\n");

                // 提取标题
                String title = slide.getTitle();
                if (title != null && !title.isEmpty()) {
                    result.append("标题: ").append(title).append("\n");
                }

                // 提取正文内容
                for (XSLFShape shape : slide.getShapes()) {
                    if (shape instanceof XSLFTextShape) {
                        XSLFTextShape textShape = (XSLFTextShape) shape;
                        String text = textShape.getText();
                        if (text != null && !text.trim().isEmpty()) {
                            result.append(text).append("\n");
                        }
                    }
                }

                // 提取备注 - 修复版
                XSLFNotes notes = slide.getNotes();
                if (notes != null) {
                    String noteText = extractNoteTextPPTX(notes);
                    if (!noteText.isEmpty()) {
                        result.append("备注: ").append(noteText).append("\n");
                    }
                }

                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     * 修复版备注提取 (PPTX)
     */
    private String extractNoteTextPPTX(XSLFNotes notes) {
        StringBuilder noteText = new StringBuilder();
        for (XSLFShape shape : notes) {
            if (shape instanceof XSLFTextShape) {
                XSLFTextShape textShape = (XSLFTextShape) shape;
                String text = textShape.getText();
                if (text != null && !text.isEmpty()) {
                    noteText.append(text).append(" ");
                }
            }
        }
        return noteText.toString().trim();
    }

    /**
     * 修复版PPT解析 (97-2003格式)
     */
    private String parsePPT(InputStream inputStream) throws IOException {
        StringBuilder result = new StringBuilder();
        try (HSLFSlideShow ppt = new HSLFSlideShow(inputStream)) {
            List<HSLFSlide> slides = ppt.getSlides();

            for (int i = 0; i < slides.size(); i++) {
                HSLFSlide slide = slides.get(i);
                result.append("--- 幻灯片 ").append(i + 1).append(" ---\n");

                // 提取标题和正文 - 修复版
                for (List<HSLFTextParagraph> textParagraphs : slide.getTextParagraphs()) {
                    for (HSLFTextParagraph textParagraph : textParagraphs) {
                        String text = HSLFTextParagraph.getText(Collections.singletonList(textParagraph));
                        if (text != null && !text.trim().isEmpty()) {
                            result.append(text).append("\n");
                        }
                    }
                }

                // 提取备注 - 修复版
                HSLFNotes notes = slide.getNotes();
                if (notes != null) {
                    String noteText = extractNoteTextPPT(notes);
                    if (!noteText.isEmpty()) {
                        result.append("备注: ").append(noteText).append("\n");
                    }
                }

                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     * 修复版备注提取 (PPT)
     */
    private String extractNoteTextPPT(HSLFNotes notes) {
        StringBuilder noteText = new StringBuilder();
        for (List<HSLFTextParagraph> noteParagraphs : notes.getTextParagraphs()) {
            for (HSLFTextParagraph paragraph : noteParagraphs) {
                String text = HSLFTextParagraph.getText(Collections.singletonList(paragraph));
                if (text != null && !text.isEmpty()) {
                    noteText.append(text).append(" ");
                }
            }
        }
        return noteText.toString().trim();
    }
}
