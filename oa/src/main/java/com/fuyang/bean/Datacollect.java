package com.fuyang.bean;

import java.io.Serializable;

/**
 * (Datacollect)实体类
 *
 * @author makejava
 * @since 2020-03-26 19:14:18
 */
public class Datacollect implements Serializable {
    private static final long serialVersionUID = -49109901125847215L;
    
    private Integer daid;
    /**
    * 目标公司名称
    */
    private String dacname;
    /**
    * 营业额
    */
    private Object daturnover;
    /**
    * 年度
    */
    private Object datime;
    /**
    * 主要业务
    */
    private String dabusiness;
    /**
    * 优势
    */
    private String dasuperiority;
    /**
    * 劣势
    */
    private String dainforiority;
    /**
    * 行业排名
    */
    private Integer dasort;
    /**
    * 员工数量
    */
    private Integer empcount;
    /**
    * 企业创建时间
    */
    private Object buildtime;
    /**
    * 简单描述
    */
    private String remark;
    /**
    * 其他
    */
    private String daother;


    public Integer getDaid() {
        return daid;
    }

    public void setDaid(Integer daid) {
        this.daid = daid;
    }

    public String getDacname() {
        return dacname;
    }

    public void setDacname(String dacname) {
        this.dacname = dacname;
    }

    public Object getDaturnover() {
        return daturnover;
    }

    public void setDaturnover(Object daturnover) {
        this.daturnover = daturnover;
    }

    public Object getDatime() {
        return datime;
    }

    public void setDatime(Object datime) {
        this.datime = datime;
    }

    public String getDabusiness() {
        return dabusiness;
    }

    public void setDabusiness(String dabusiness) {
        this.dabusiness = dabusiness;
    }

    public String getDasuperiority() {
        return dasuperiority;
    }

    public void setDasuperiority(String dasuperiority) {
        this.dasuperiority = dasuperiority;
    }

    public String getDainforiority() {
        return dainforiority;
    }

    public void setDainforiority(String dainforiority) {
        this.dainforiority = dainforiority;
    }

    public Integer getDasort() {
        return dasort;
    }

    public void setDasort(Integer dasort) {
        this.dasort = dasort;
    }

    public Integer getEmpcount() {
        return empcount;
    }

    public void setEmpcount(Integer empcount) {
        this.empcount = empcount;
    }

    public Object getBuildtime() {
        return buildtime;
    }

    public void setBuildtime(Object buildtime) {
        this.buildtime = buildtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDaother() {
        return daother;
    }

    public void setDaother(String daother) {
        this.daother = daother;
    }

}