package com.ystartor.crm.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * CREATE TABLE `cst_customer` (
 * `cust_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '客户编号(主键)',
 * `cust_name` varchar(32) NOT NULL COMMENT '客户名称(公司名称)',
 * `cust_source` varchar(32) DEFAULT NULL COMMENT '客户信息来源',
 * `cust_industry` varchar(32) DEFAULT NULL COMMENT '客户所属行业',
 * `cust_level` varchar(32) DEFAULT NULL COMMENT '客户级别',
 * `cust_phone` varchar(64) DEFAULT NULL COMMENT '固定电话',
 * `cust_mobile` varchar(16) DEFAULT NULL COMMENT '移动电话',
 * PRIMARY KEY (`cust_id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
 */
public class Customer {
    private Long cust_id;
    private String cust_name;
    /*private Long cust_source;
    private Long cust_industry;
    private Long cust_level;*/
    private String cust_phone;
    private String cust_mobile;
    private String cust_image;
    private Set<LinkMan> linkManSet = new HashSet<LinkMan>();
//    private Set<SaleVisit> saleVisit = new HashSet<SaleVisit>();

    @Override
    public String toString() {
        return "Customer{" +
                "cust_id=" + cust_id +
                ", cust_name='" + cust_name + '\'' +
                ", cust_phone='" + cust_phone + '\'' +
                ", cust_mobile='" + cust_mobile + '\'' +
                ", cust_image='" + cust_image + '\'' +
                ", linkManSet=" + linkManSet +
//                ", saleVisit=" + saleVisit +
                ", baseDictSource=" + baseDictSource +
                ", baseDictIndustry=" + baseDictIndustry +
                ", baseDictLevel=" + baseDictLevel +
                '}';
    }

/*    public Set<SaleVisit> getSaleVisit() {
        return saleVisit;
    }

    public void setSaleVisit(Set<SaleVisit> saleVisit) {
        this.saleVisit = saleVisit;
    }*/

    public Set<LinkMan> getLinkManSet() {
        return linkManSet;
    }

    public void setLinkManSet(Set<LinkMan> linkManSet) {
        this.linkManSet = linkManSet;
    }

    public String getCust_image() {
        return cust_image;
    }

    public void setCust_image(String cust_image) {
        this.cust_image = cust_image;
    }

    private BaseDict baseDictSource;
    private BaseDict baseDictIndustry;
    private BaseDict baseDictLevel;

    public BaseDict getBaseDictSource() {
        return baseDictSource;
    }

    public void setBaseDictSource(BaseDict baseDictSource) {
        this.baseDictSource = baseDictSource;
    }

    public BaseDict getBaseDictIndustry() {
        return baseDictIndustry;
    }

    public void setBaseDictIndustry(BaseDict baseDictIndustry) {
        this.baseDictIndustry = baseDictIndustry;
    }

    public BaseDict getBaseDictLevel() {
        return baseDictLevel;
    }

    public void setBaseDictLevel(BaseDict baseDictLevel) {
        this.baseDictLevel = baseDictLevel;
    }



    public Long getCust_id() {
        return cust_id;
    }

    public void setCust_id(Long cust_id) {
        this.cust_id = cust_id;
    }


    /*public Long getCust_source() {
        return cust_source;
    }

    public void setCust_source(Long cust_source) {
        this.cust_source = cust_source;
    }

    public Long getCust_industry() {
        return cust_industry;
    }

    public void setCust_industry(Long cust_industry) {
        this.cust_industry = cust_industry;
    }

    public Long getCust_level() {
        return cust_level;
    }

    public void setCust_level(Long cust_level) {
        this.cust_level = cust_level;
    }*/

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_phone() {
        return cust_phone;
    }

    public void setCust_phone(String cust_phone) {
        this.cust_phone = cust_phone;
    }

    public String getCust_mobile() {
        return cust_mobile;
    }

    public void setCust_mobile(String cust_mobile) {
        this.cust_mobile = cust_mobile;
    }
}
