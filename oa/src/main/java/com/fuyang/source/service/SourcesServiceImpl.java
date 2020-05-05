package com.fuyang.source.service;
import com.fuyang.bean.Sources;
import com.fuyang.bean.SourcesExtends;
import com.fuyang.source.dao.SourcesDao;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class SourcesServiceImpl implements SourcesService {
    @Autowired
    private SourcesDao sourcesDao;

    @Override
    public List<SourcesExtends> querySource() {
        List<SourcesExtends> ses = sourcesDao.selectSource(0);
        ses=show(ses);
        return ses;
    }

    @Override
    @Transactional
    public void add(Sources sources) {
        sourcesDao.insert(sources);
    }

    @Override
    public Sources selectSourceById(int id) {
        return sourcesDao.querySourceById(id);
    }

    @Override
    @Transactional
    public void edit(Sources sources) {
        sourcesDao.update(sources);
    }

    @Override
    @Transactional
    public void delete(int id) {
        sourcesDao.deleteById(id);
    }

    private List<SourcesExtends> show(List<SourcesExtends> ses) {
        if(ses != null && ses.size() > 0){
            for(int i = 0;i < ses.size();i++){
                SourcesExtends mgr = ses.get(i);
                Integer id = mgr.getId();
                List<SourcesExtends> childs = sourcesDao.selectSource(id);
                //递归：由上一级找下一级
                childs = show(childs);
                mgr.setChildren(childs);
            }
        }
        return ses;
    }
}