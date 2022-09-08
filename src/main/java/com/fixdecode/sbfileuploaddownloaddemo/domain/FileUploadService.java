package com.fixdecode.sbfileuploaddownloaddemo.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class FileUploadService {
    private FileUploadRepository uploadRepository;

    public FileUpload saveFile(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            try {
                if (fileName.contains("..")) {
                    throw new Exception("The file name is invalid" + fileName);
                }
                FileUpload fileUpload = new FileUpload(fileName, file.getContentType(), file.getBytes());
                return uploadRepository.save(fileUpload);
            } catch (Exception e) {
                throw new Exception("File could not be save");
            }
        }

        public FileUpload downloadFile(String fileId) throws Exception {
        return uploadRepository.findById(fileId)
                .orElseThrow(() -> new Exception("A file with Id : "+ fileId + " could not be found"));
        }
    }

