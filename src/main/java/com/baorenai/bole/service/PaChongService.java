package com.baorenai.bole.service;


import com.baorenai.bole.model.JobDetail;
import com.baorenai.bole.model.JobModel;
import com.baorenai.bole.model.JobParam;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.util.List;

public interface PaChongService {
    public Boolean addJobDetailToDatabase();

    public Boolean addJobDetailByDetail(JobParam jobParam);

    public JobDetail generateText(CloseableHttpClient httpclient, String uri) throws IOException;

    public List<JobDetail> doSerch(JobModel jobModel) throws Exception;

    public int doAddJob(JobParam jobParam) throws Exception;

}
