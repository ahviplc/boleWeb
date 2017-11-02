/*
package com.baorenai.bole.service;


import com.alibaba.fastjson.JSONObject;
import com.baorenai.bole.model.JobParam;
import com.baorenai.bole.service.impl.PaChongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;


public class DailyJobThread {

    @Autowired
    PaChongService paChongService;

    public DailyJobThread(String threadName)
    {
        super(threadName);
    }


    @Override
    public void run()
    {
        String Params = getName();
        JobParam jobParam = buildJobParamFromParams(Params);
        try {
            paChongService.doSerch(jobParam);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static JobParam buildJobParamFromParams(String Params)
    {
        JobParam jobParam = new JobParam();
        JSONObject jsonObject =JSONObject.parseObject(Params);

        jobParam.setWorkType(jsonObject.getString("WorkType"));
        jobParam.setJobBigType(jsonObject.getString("JobBigType"));
        jobParam.setWorkPlace(jsonObject.getString("WorkPlace"));
        return jobParam;
    }

}
*/
