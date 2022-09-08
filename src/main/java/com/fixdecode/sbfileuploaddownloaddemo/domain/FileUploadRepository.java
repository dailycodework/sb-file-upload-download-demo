package com.fixdecode.sbfileuploaddownloaddemo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepository extends JpaRepository<FileUpload, String> {
}
