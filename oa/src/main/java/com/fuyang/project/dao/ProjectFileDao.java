package com.fuyang.project.dao;

import com.fuyang.bean.Attachment;
import com.fuyang.bean.AttachmentExtends;

import java.util.List;

public interface ProjectFileDao {
    List<AttachmentExtends> selectFileList();

    void insert(Attachment attachment);
}
