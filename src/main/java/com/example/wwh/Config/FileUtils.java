package com.example.wwh.Config;


import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FileUtils {

    public static String generateChunkName(String originalName, String extension, int chunkIndex) {
        String baseName = originalName.contains(".") ?
                originalName.substring(0, originalName.lastIndexOf('.')) :
                originalName;

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return String.format("chunks/%s_%s_chunk_%d.%s", baseName, timestamp, chunkIndex, extension);
    }
}