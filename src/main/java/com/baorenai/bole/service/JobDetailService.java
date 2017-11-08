package com.baorenai.bole.service;


import com.baorenai.bole.dao.JobDetailDao;
import com.baorenai.bole.model.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobDetailService {

    @Autowired
    JobDetailDao jobDetailDao;

    public JobDetail getJobDetail(String id)
    {
        return jobDetailDao.selectByPrimaryKey(id);
    }

    public int addJobDetail(JobDetail jobDetail)
    {
        return jobDetailDao.insertByPrimaryKey(jobDetail);
    }

    public List<JobDetail> getJobDetailByCondition(JobDetail jobDetail)
    {
        return jobDetailDao.selectByJobDetail(jobDetail);
    }

    public List<JobDetail> getJobDetailByConditionLimit(JobDetail jobDetail) {
        return jobDetailDao.selectByJobDetailLimit(jobDetail);
    }
}
