package com.baorenai.bole.mapper;

import com.baorenai.bole.model.JobDetail;

import java.util.List;

public interface JobDetailMapper {
    int deleteByPrimaryKey(String jobid);

    int insert(JobDetail record);

    int insertSelective(JobDetail record);

    JobDetail selectByPrimaryKey(String jobid);

    int updateByPrimaryKeySelective(JobDetail record);

    int updateByPrimaryKey(JobDetail record);

    List<JobDetail> selectByJobDetail(JobDetail record);

    List<JobDetail> selectByJobDetailLimit(JobDetail record);



}