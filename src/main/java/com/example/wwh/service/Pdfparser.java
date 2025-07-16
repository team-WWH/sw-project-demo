package com.example.wwh.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class Pdfparser {
    public String parse(File file) throws IOException {
        try (PDDocument document = Loader.loadPDF(new File("C://Users//王镜然//Desktop//任务1代码说明文档.pdf"))) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }
}
