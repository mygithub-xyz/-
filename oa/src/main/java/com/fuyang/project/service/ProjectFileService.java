package com.fuyang.project.service;

import com.fuyang.bean.Attachment;
import com.fuyang.bean.AttachmentExtends;

import java.util.List;

public interface ProjectFileService {
    List<AttachmentExtends> queryFileList();

    void save(Attachment attachment);
}
