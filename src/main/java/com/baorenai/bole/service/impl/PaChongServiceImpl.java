package com.baorenai.bole.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baorenai.bole.model.JobDetail;
import com.baorenai.bole.model.JobModel;
import com.baorenai.bole.model.JobParam;
import com.baorenai.bole.service.JobDetailService;
import com.baorenai.bole.service.PaChongService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PaChongServiceImpl implements PaChongService {
    private static CloseableHttpResponse response;
    BasicCookieStore cookieStore = new BasicCookieStore();
    CloseableHttpClient httpclient = HttpClients.custom()
            .setDefaultCookieStore(cookieStore).setMaxConnTotal(200)
            .build();

    @Autowired
    JobDetailService jobDetailService;


    @Override
    public Boolean addJobDetailToDatabase() {
        return null;
    }

    @Override
    public Boolean addJobDetailByDetail(JobParam jobParam) {
        return null;
    }


    @Override
    public List<JobDetail> doSerch(JobModel jobModel) throws Exception {

        /*Assert.notNull(jobParam.getWorkType());
        String url = buildUrl(jobParam);
        List<String> urls = getUrisFromCurre(httpclient, url);

        for (String job_url : urls) {
            String jobid = job_url.split("=")[1];
            JobDetail existDetail = jobDetailService.getJobDetail(jobid);
            if (existDetail == null || existDetail.equals(null)) {
                JobDetail jobDetail = generateText(httpclient, job_url);
                jobs.add(jobDetail);
                jobDetail.setJobid(jobid);
//                System.out.println(jobDetail);
                Integer i = jobDetailService.addJobDetail(jobDetail);
                if(i!=1)
                {
                    throw new Exception("add new JobDetail error , size is not equals to 1");
                }
            }
            else {
                jobs.add(existDetail);
            }
        }*/
        JobDetail jobDetail = new JobDetail();
        if (!StringUtils.equals(jobModel.workplace,"") && jobModel.workplace != null) {
            jobDetail.setWorkplace(jobModel.workplace);
        }
        jobDetail.setJobbigtype(jobModel.jobbigtype);
        if (!StringUtils.equals(jobModel.joblittletype,"不限") && jobModel.joblittletype != null) {
            jobDetail.setJoblittletype(jobModel.joblittletype);
        }
        jobDetail.setWorktype(jobModel.worktype);
        return jobDetailService.getJobDetailByConditionLimit(jobDetail);
    }

    @Override
    public int doAddJob(JobParam jobParam) throws Exception {
        int res = 0;
        List<JobDetail> jobs = new ArrayList<JobDetail>();
        Assert.notNull(jobParam.getWorkType());
        String url = buildUrl(jobParam);
        List<String> urls = getUrisFromCurre(httpclient, url);

        for (String job_url : urls) {
            String jobid = job_url.split("=")[1];
            JobDetail existDetail = jobDetailService.getJobDetail(jobid);
            if (existDetail == null || existDetail.equals(null)) {
                JobDetail jobDetail = generateText(httpclient, job_url);
                jobs.add(jobDetail);
                jobDetail.setJobid(jobid);
                try {
                    Integer i = jobDetailService.addJobDetail(jobDetail);
                    res += i;
                    if (i != 1) {
                        throw new Exception("add new JobDetail error , size is not equals to 1");
                    }
                } catch (DuplicateKeyException e) {
                    log.error("jobdetail is {}, jobId is {}", jobDetail.toString(), jobid);
                }

            }
        }

        return res;

    }

    //从currentPage当前页面获取所有职位细节的链接
    public static List<String> getUrisFromCurre(CloseableHttpClient httpclient, String currentPage) throws IOException {
        List<String> uris = new ArrayList<>();
        HttpGet httpget = new HttpGet(currentPage);
        CloseableHttpResponse response = httpclient.execute(httpget);
        String html = EntityUtils.toString(response.getEntity());
        // System.out.println("++++++++   "+html+"   ++++++++++");
        Document doc = Jsoup.parse(html);
        // System.out.println(html);
        // System.out.println("--------------------------------------");
        // System.out.println(doc);
        Elements eles = doc.select(".position-tb tbody tr td a");
        // System.out.println(eles.size());
        //System.out.println("++++  "+eles);
        for (int i = 0; i < eles.size(); i++) {
            if (eles.get(i).attr("href").contains("detail.do")) {
                //  uris.add("http://bole.netease.com"+eles.get(i).attr("href"));
                uris.add("http://hr.163.com" + eles.get(i).attr("href"));
            }
        }
        return uris;
    }

    @Override
    public JobDetail generateText(CloseableHttpClient httpclient, String uri) throws IOException {
        JobDetail jobDetail = new JobDetail();
        HttpGet httpget = new HttpGet(uri);
        CloseableHttpResponse response = httpclient.execute(httpget);
        String html = EntityUtils.toString(response.getEntity());
        Document doc = Jsoup.parse(html);

        String jobTitle = doc.select(".job-title").text() + "\n"; //怎么查找对应的cssQuery
//        System.out.println("jobtitle : " + jobTitle);
        jobDetail.setJobtitle(jobTitle.replace("\\n", ""));

        String postDate = doc.select(".post-date").text() + "\n";
//        System.out.println("postDate : " + postDate);
        jobDetail.setJobdate(postDate.replace("发布时间：", "").replace("\\n", ""));

        String jobParams = "";
        Elements jobParamsTh = doc.select(".job-params tbody tr th"); //key
        Elements jobParamsTd = doc.select(".job-params tbody tr td"); //value
        for (int i = 0; i < jobParamsTh.size(); i++) {
            jobDetail = setJobDetailByJobParams(jobDetail, jobParamsTd.get(i), jobParamsTh.get(i));
            jobParams += jobParamsTh.get(i).text() + jobParamsTd.get(i).text() + "\n";
        }


        Elements sectionTitle = doc.select(".detail-section .section-title");
        Elements sectionContent = doc.select(".detail-section .section-content");
        String detailSection = "";
//        System.out.println("jobParams is : " + jobParams);
        for (int i = 0; i < sectionTitle.size(); i++) {
            if (!sectionTitle.get(i).text().contains("伯乐奖说明"))
                detailSection += sectionTitle.get(i).text() + "\n" + sectionContent.get(i).text() + "\n";
        }
        jobDetail.setJobDetail(detailSection);
//        System.out.println("detailSection is : " + detailSection);

        response.close();
        return jobDetail;
    }

    public static String buildUrl(JobParam jobParam) {
        if (StringUtils.equals(jobParam.getJobSmallType(), "")) {
            return "http://hr.163.com/position/list.do?&workType=" + jobParam.getWorkType() + "&workPlaceStr=" + jobParam.getWorkPlace()
                    + "&currentPage=" + jobParam.getCurrentPage() + "&postType=" + jobParam.getJobBigType();
        } else {
            return "http://hr.163.com/position/list.do?&workType=" + jobParam.getWorkType() + "&workPlaceStr=" + jobParam.getWorkPlace()
                    + "&currentPage=" + jobParam.getCurrentPage() + "&postType=" + jobParam.getJobSmallType();
        }
    }

    public static JobDetail setJobDetailByJobParams(JobDetail jobDetail, Element jobParamTd, Element jobParamTh) {
        String key = jobParamTh.text();
        String value = jobParamTd.text();
        switch (key) {
            case "职位类别：":
                String[] types = value.split(">");
                jobDetail.setJobbigtype(types[0]);
                jobDetail.setJoblittletype(types[1]);
                break;
            case "最低学历：":
                jobDetail.setAcademic(value);
                break;
            case "工作地点：":
                jobDetail.setWorkplace(value);
                break;
            case "工作年限：":
                jobDetail.setWorktime(value);
            case "招聘人数：":
                jobDetail.setHireCount(value);
                break;
            case "工作类型：":
                jobDetail.setWorktype(value);
                break;
        }
        return jobDetail;

    }


}

/*    static static void main(String[] args) throws Exception {
        JobParam j = new JobParam();
        j.setWorkPlace("1");
        j.setJobSmallType("0108");
        j.setWorkType("0");

        PaChongService c = new PaChongServiceImpl();
        c.doSerch(j);
    }*/



