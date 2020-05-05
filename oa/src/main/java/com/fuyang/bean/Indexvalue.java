package com.fuyang.bean;

import java.io.Serializable;

/**
 * (Indexvalue)实体类
 *
 * @author makejava
 * @since 2020-03-26 19:15:10
 */
public class Indexvalue implements Serializable {
    private static final long serialVersionUID = -21868509564817541L;
    /**
    * 对标的具体内容id
    */
    private Integer inId;
    /**
    * 目标营业额
    */
    private Object inTurnover;
    /**
    * 主要业务方向
    */
    private String inBusiness;
    /**
    * 对比企业名称
    */
    private Integer comnameFk;
    /**
    * 简单说明
    */
    private String inRemark;
    /**
    * 附件
    */
    private String inFile;
    /**
    * 指标制定人
    */
    private Integer empFk5;
    /**
    * 开始时间
    */
    private Object inStarttime;
    /**
    * 截止时间
    */
    private Object inEndtime;
    /**
    * 更新时间
    */
    private Object inUpdatetime;


    public Integer getInId() {
        return inId;
    }

    public void setInId(Integer inId) {
        this.inId = inId;
    }

    public Object getInTurnover() {
        return inTurnover;
    }

    public void setInTurnover(Object inTurnover) {
        this.inTurnover = inTurnover;
    }

    public String getInBusiness() {
        return inBusiness;
    }

    public void setInBusiness(String inBusiness) {
        this.inBusiness = inBusiness;
    }

    public Integer getComnameFk() {
        return comnameFk;
    }

    public void setComnameFk(Integer comnameFk) {
        this.comnameFk = comnameFk;
    }

    public String getInRemark() {
        return inRemark;
    }

    public void setInRemark(String inRemark) {
        this.inRemark = inRemark;
    }

    public String getInFile() {
        return inFile;
    }

    public void setInFile(String inFile) {
        this.inFile = inFile;
    }

    public Integer getEmpFk5() {
        return empFk5;
    }

    public void setEmpFk5(Integer empFk5) {
        this.empFk5 = empFk5;
    }

    public Object getInStarttime() {
        return inStarttime;
    }

    public void setInStarttime(Object inStarttime) {
        this.inStarttime = inStarttime;
    }

    public Object getInEndtime() {
        return inEndtime;
    }

    public void setInEndtime(Object inEndtime) {
        this.inEndtime = inEndtime;
    }

    public Object getInUpdatetime() {
        return inUpdatetime;
    }

    public void setInUpdatetime(Object inUpdatetime) {
        this.inUpdatetime = inUpdatetime;
    }

}