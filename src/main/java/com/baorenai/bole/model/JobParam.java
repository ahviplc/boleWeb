package com.baorenai.bole.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 入参：
 * 岗位的基本属性
 * 1.工作地点
 * 2.工作类型-大类
 * 3.工作类型-小类
 * 4.工作性质-全职、实习
 */

@Getter
@Setter
@ToString
public class JobParam {
    public String WorkPlace="";
    public String JobBigType="";
    public String JobSmallType="";
    public String WorkType="";
    public String CurrentPage = "1";
}
