package com.fuyang.task.service;

import com.fuyang.bean.Task;
import com.fuyang.task.dao.TaskDao;
import com.fuyang.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDao taskDao;

    //保存功能
    @Transactional
    @Override
    public void save(Task task) {
        taskDao.insert(task);
    }
}
