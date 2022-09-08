package com.fixdecode.sbfileuploaddownloaddemo.domain;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@AllArgsConstructor
public class FileUploadController {
    private FileUploadService uploadService;

    @PostMapping("/upload")
    public FileUploadResponse uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
        FileUpload attachment = null;
        String downloadUrl = "";
        attachment = uploadService.saveFile(file);
        downloadUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getId())
                .toUriString();
        return new FileUploadResponse(attachment.getFileName(), downloadUrl, file.getContentType(), file.getSize());

    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> download(@PathVariable("fileId")String fileId) throws Exception {
        FileUpload fileUpload = null;
        fileUpload = uploadService.downloadFile(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileUpload.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "fileUpload; filename=\""+ fileUpload.getFileName()
                        +"\"").body(new ByteArrayResource(fileUpload.getData()));
    }

}
