package com.baorenai.bole.service;

import com.alibaba.fastjson.JSONObject;
import com.baorenai.bole.model.JobParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DailyJobService {

    @Autowired
    PaChongService paChongService;

    public int doDailyThreadJob() throws Exception {
        int res = 0;
        List<String> workPlace = new ArrayList<>();
        workPlace.add("1");
        workPlace.add("2");
        workPlace.add("138");
        workPlace.add("229");


        List<String> jobBigType = new ArrayList<>();
        jobBigType.add("01");
        jobBigType.add("06");
        jobBigType.add("11");
        jobBigType.add("16");
        jobBigType.add("26");
        jobBigType.add("36");
        jobBigType.add("46");
        jobBigType.add("41");

        JobParam jobParam = new JobParam();

        for (String jbt : jobBigType) {
            jobParam.setJobBigType(jbt);

            for (String wp : workPlace) {
                jobParam.setWorkPlace(wp);
                for (int i = 0; i < 2; i++) {
                    jobParam.setWorkType(String.valueOf(i));
                    for (int j =1;j<11;j++)
                    {
                        jobParam.setCurrentPage(String.valueOf(j));
                        res+=paChongService.doAddJob(jobParam);
                    }
                }
            }
        }
        return res;
    }

    public static String buildJsonObjectFromClass(JobParam j) {
        JSONObject object = new JSONObject();
        object.put("WorkType", j.getWorkType());
        object.put("WorkPlace", j.getWorkPlace());
        object.put("JobBigType", j.getJobBigType());
        object.put("CurrentPage", j.getCurrentPage());
        return object.toJSONString();
    }
}
