package com.example.restfirst.utils;

import com.example.restfirst.model.communicationentities.UniversityFile;
import com.example.restfirst.repo.FileRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileManager {
    private final FileRepo fileRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public FileManager(FileRepo fileRepo) {
        this.fileRepo = fileRepo;
    }

    public void fileLoader(MultipartFile file) throws IOException {
        UniversityFile fileMy = new UniversityFile();
        if (!file.isEmpty()) {
            File uploadD = new File(uploadPath);
            if (!uploadD.exists()) {
                uploadD.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            fileMy.setFilename(resultFilename);
            fileRepo.save(fileMy);
        }
    }

}
