package com.lautalfs.blogapi.service.impl;

import com.lautalfs.blogapi.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImp implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        String randomID = UUID.randomUUID().toString();
        String filename = randomID.concat(name.substring(name.lastIndexOf(".")));
        String filePath = path + File.separator + filename;
        File newFile = new File(path);
        if(!newFile.exists()){
            newFile.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return filename;
    }

    @Override
    public InputStream getResource(String path, String filename) throws FileNotFoundException {
        String fullPath = path +File.separator+filename;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }
}
