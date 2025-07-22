package com.example.wwh.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class   Pdfparser {
    public String parse(PDDocument document) throws IOException {

            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);

    }
}
