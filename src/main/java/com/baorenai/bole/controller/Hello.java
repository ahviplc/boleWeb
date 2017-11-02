package com.baorenai.bole.controller;


import com.alibaba.druid.util.StringUtils;
import com.baorenai.bole.model.JobDetail;
import com.baorenai.bole.model.JobParam;
import com.baorenai.bole.service.DailyJobService;
import com.baorenai.bole.service.JobDetailService;
import com.baorenai.bole.service.PaChongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hello")
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

    @RequestMapping(value = "/job", method = RequestMethod.POST)
    @ResponseBody
    public List<JobDetail> doJob(@RequestBody JobDetail jobDetail) throws Exception {
        List<JobDetail> response = paChongService.doSerch(jobDetail);
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


}
