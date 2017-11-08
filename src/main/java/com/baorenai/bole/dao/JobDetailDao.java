package com.baorenai.bole.dao;

import com.baorenai.bole.mapper.JobDetailMapper;
import com.baorenai.bole.model.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobDetailDao {

    @Autowired
    private JobDetailMapper jobDetailMapper;

    public JobDetail selectByPrimaryKey(String id) {
        return jobDetailMapper.selectByPrimaryKey(id);
    }

    public int insertByPrimaryKey(JobDetail jobDetail) {
        return jobDetailMapper.insert(jobDetail);
    }

    public List<JobDetail> selectByJobDetail(JobDetail jobDetail) {
        return jobDetailMapper.selectByJobDetail(jobDetail);
    }

    public List<JobDetail> selectByJobDetailLimit(JobDetail jobDetail) {
        return jobDetailMapper.selectByJobDetailLimit(jobDetail);
    }
}
