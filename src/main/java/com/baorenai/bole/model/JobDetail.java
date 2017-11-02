package com.baorenai.bole.model;

import lombok.ToString;

import java.util.Date;

@ToString
public class JobDetail {
    private String jobid;

    private String jobtitle;

    private String jobdate;

    private String hireCount;

    private String jobbigtype;

    private String joblittletype;

    private String academic;

    private String workplace;

    private String worktype;

    private String worktime;

    private Date createTime = new Date();

    private Date updateTime = new Date();

    private String jobDetail;

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid == null ? null : jobid.trim();
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle == null ? null : jobtitle.trim();
    }

    public String getJobdate() {
        return jobdate;
    }

    public void setJobdate(String jobdate) {
        this.jobdate = jobdate == null ? null : jobdate.trim();
    }

    public String getHireCount() {
        return hireCount;
    }

    public void setHireCount(String hireCount) {
        this.hireCount = hireCount == null ? null : hireCount.trim();
    }

    public String getJobbigtype() {
        return jobbigtype;
    }

    public void setJobbigtype(String jobbigtype) {
        this.jobbigtype = jobbigtype == null ? null : jobbigtype.trim();
    }

    public String getJoblittletype() {
        return joblittletype;
    }

    public void setJoblittletype(String joblittletype) {
        this.joblittletype = joblittletype == null ? null : joblittletype.trim();
    }

    public String getAcademic() {
        return academic;
    }

    public void setAcademic(String academic) {
        this.academic = academic == null ? null : academic.trim();
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace == null ? null : workplace.trim();
    }

    public String getWorktype() {
        return worktype;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype == null ? null : worktype.trim();
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime == null ? null : worktime.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getJobDetail() {
        return jobDetail;
    }

    public void setJobDetail(String jobDetail) {
        this.jobDetail = jobDetail == null ? null : jobDetail.trim();
    }
}