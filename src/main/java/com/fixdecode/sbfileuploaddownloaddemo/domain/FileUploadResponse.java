package com.fixdecode.sbfileuploaddownloaddemo.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadResponse {
    private String fileName;
    private String downloadURL;
    private String fileType;
    private long fileSize;
}
