package com.hpe.findlover.model;

import javax.persistence.*;
@Table(name = "taocan")
public class Taocan {
    @Id
    @Column(name = "taocan_id")
    private Integer taocanId;

    @Column(name = "search_display_image")
    private String searchDisplayImage;

    @Column(name = "display_image_id")
    private Integer displayImageId;

    private String title;

    private String introduce;

    @Column(name = "low_price")
    private Integer lowPrice;

    @Column(name = "high_price")
    private Integer highPrice;

    /**
     * 适用场景
     */
    @Column(name = "activity_form")
    private String activityForm;

    /**
     * 场地
     */
    @Column(name = "activity_place")
    private String activityPlace;

    @Column(name = "enable_area")
    private String enableArea;

    @Column(name = "taocan_type_a")
    private String taocanTypeA;

    @Column(name = "taocan_type_a_price")
    private Integer taocanTypeAPrice;

    @Column(name = "taocan_type_b")
    private String taocanTypeB;

    @Column(name = "taocan_type_b_price")
    private Integer taocanTypeBPrice;

    @Column(name = "taocan_type_c")
    private String taocanTypeC;

    @Column(name = "taocan_type_c_price")
    private Integer taocanTypeCPrice;

    @Column(name = "introduce_image_id")
    private Integer introduceImageId;

    /**
     * @return taocan_id
     */
    public Integer getTaocanId() {
        return taocanId;
    }

    /**
     * @param taocanId
     */
    public void setTaocanId(Integer taocanId) {
        this.taocanId = taocanId;
    }

    /**
     * @return search_display_image
     */
    public String getSearchDisplayImage() {
        return searchDisplayImage;
    }

    /**
     * @param searchDisplayImage
     */
    public void setSearchDisplayImage(String searchDisplayImage) {
        this.searchDisplayImage = searchDisplayImage;
    }

    /**
     * @return display_image_id
     */
    public Integer getDisplayImageId() {
        return displayImageId;
    }

    /**
     * @param displayImageId
     */
    public void setDisplayImageId(Integer displayImageId) {
        this.displayImageId = displayImageId;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return introduce
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * @param introduce
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * @return low_price
     */
    public Integer getLowPrice() {
        return lowPrice;
    }

    /**
     * @param lowPrice
     */
    public void setLowPrice(Integer lowPrice) {
        this.lowPrice = lowPrice;
    }

    /**
     * @return high_price
     */
    public Integer getHighPrice() {
        return highPrice;
    }

    /**
     * @param highPrice
     */
    public void setHighPrice(Integer highPrice) {
        this.highPrice = highPrice;
    }

    /**
     * 获取适用场景
     *
     * @return activity_form - 适用场景
     */
    public String getActivityForm() {
        return activityForm;
    }

    /**
     * 设置适用场景
     *
     * @param activityForm 适用场景
     */
    public void setActivityForm(String activityForm) {
        this.activityForm = activityForm;
    }

    /**
     * 获取场地
     *
     * @return activity_place - 场地
     */
    public String getActivityPlace() {
        return activityPlace;
    }

    /**
     * 设置场地
     *
     * @param activityPlace 场地
     */
    public void setActivityPlace(String activityPlace) {
        this.activityPlace = activityPlace;
    }

    /**
     * @return enable_area
     */
    public String getEnableArea() {
        return enableArea;
    }

    /**
     * @param enableArea
     */
    public void setEnableArea(String enableArea) {
        this.enableArea = enableArea;
    }

    /**
     * @return taocan_type_a
     */
    public String getTaocanTypeA() {
        return taocanTypeA;
    }

    /**
     * @param taocanTypeA
     */
    public void setTaocanTypeA(String taocanTypeA) {
        this.taocanTypeA = taocanTypeA;
    }

    /**
     * @return taocan_type_a_price
     */
    public Integer getTaocanTypeAPrice() {
        return taocanTypeAPrice;
    }

    /**
     * @param taocanTypeAPrice
     */
    public void setTaocanTypeAPrice(Integer taocanTypeAPrice) {
        this.taocanTypeAPrice = taocanTypeAPrice;
    }

    /**
     * @return taocan_type_b
     */
    public String getTaocanTypeB() {
        return taocanTypeB;
    }

    /**
     * @param taocanTypeB
     */
    public void setTaocanTypeB(String taocanTypeB) {
        this.taocanTypeB = taocanTypeB;
    }

    /**
     * @return taocan_type_b_price
     */
    public Integer getTaocanTypeBPrice() {
        return taocanTypeBPrice;
    }

    /**
     * @param taocanTypeBPrice
     */
    public void setTaocanTypeBPrice(Integer taocanTypeBPrice) {
        this.taocanTypeBPrice = taocanTypeBPrice;
    }

    /**
     * @return taocan_type_c
     */
    public String getTaocanTypeC() {
        return taocanTypeC;
    }

    /**
     * @param taocanTypeC
     */
    public void setTaocanTypeC(String taocanTypeC) {
        this.taocanTypeC = taocanTypeC;
    }

    /**
     * @return taocan_type_c_price
     */
    public Integer getTaocanTypeCPrice() {
        return taocanTypeCPrice;
    }

    /**
     * @param taocanTypeCPrice
     */
    public void setTaocanTypeCPrice(Integer taocanTypeCPrice) {
        this.taocanTypeCPrice = taocanTypeCPrice;
    }

    /**
     * @return introduce_image_id
     */
    public Integer getIntroduceImageId() {
        return introduceImageId;
    }

    /**
     * @param introduceImageId
     */
    public void setIntroduceImageId(Integer introduceImageId) {
        this.introduceImageId = introduceImageId;
    }
}