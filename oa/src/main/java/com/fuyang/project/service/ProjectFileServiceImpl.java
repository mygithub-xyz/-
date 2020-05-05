package com.fuyang.project.service;

import com.fuyang.bean.Attachment;
import com.fuyang.bean.AttachmentExtends;
import com.fuyang.project.dao.ProjectFileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProjectFileServiceImpl implements ProjectFileService {
    @Autowired
    private ProjectFileDao projectFileDao;
    @Override
    public List<AttachmentExtends> queryFileList() {
        return projectFileDao.selectFileList();
    }

    //保存功能
    @Transactional
    @Override
    public void save(Attachment attachment) {
        projectFileDao.insert(attachment);
    }
}
