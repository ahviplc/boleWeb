package com.baorenai.bole.controller;


import com.alibaba.druid.util.StringUtils;
import com.baorenai.bole.model.JobDetail;
import com.baorenai.bole.model.JobModel;
import com.baorenai.bole.model.JobParam;
import com.baorenai.bole.service.DailyJobService;
import com.baorenai.bole.service.JobDetailService;
import com.baorenai.bole.service.PaChongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/job")
public class Hello {
    @Autowired
    JobDetailService jobDetailService;

    @Autowired
    PaChongService paChongService;

    @Autowired
    DailyJobService dailyJobService;

    @RequestMapping(value = "/say")
    static String sayHello() {
        return "hello dabaoge";
    }

    @RequestMapping(value = "/database")
    public String test(@RequestParam String jobID) {
        JobDetail jobDetail = new JobDetail();
        jobDetail = jobDetailService.getJobDetail(jobID);
        return jobDetail.getJobtitle();
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public List<JobDetail> doJob(@RequestBody JobModel jobModel) throws Exception {
        List<JobDetail> response = paChongService.doSerch(jobModel);
        return response;
    }


    @RequestMapping(value = "/dailyJob", method = RequestMethod.GET)
    @ResponseBody
    public int doJob(@RequestParam String Code) throws Exception {
        if (StringUtils.equals(Code, "woaiyaoyao")) {
            return dailyJobService.doDailyThreadJob();
        }
        return 666666;
    }

    @RequestMapping(value = "/newestUpdateTime",method = RequestMethod.GET)
    @ResponseBody
    public String selectUpdate()
    {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date().getTime());
        return date;
    }
}
