package com.hpe.findlover.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_order")
public class UserOrder {
    @Id
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "taocan_id")
    private Integer taocanId;

    @Column(name = "taocan_image")
    private String taocanImage;

    @Column(name = "taocan_name")
    private String taocanName;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "activity_theme")
    private String activityTheme;

    @Column(name = "order_time")
    private Date orderTime;

    @Column(name = "version_id")
    private Integer versionId;

    @Column(name = "version_display_name")
    private String versionDisplayName;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "prepay_price")
    private Integer prepayPrice;

    @Column(name = "paid_price")
    private Integer paidPrice;

    @Column(name = "not_pay_price")
    private Integer notPayPrice;

    @Column(name = "activity_area")
    private String activityArea;

    private String idea;

    private String nickname;

    @Column(name = "tel_phone")
    private String telPhone;

    @Column(name = "wechat_num")
    private String wechatNum;

    private Integer state;

    /**
     * @return order_id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

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
     * @return taocan_image
     */
    public String getTaocanImage() {
        return taocanImage;
    }

    /**
     * @param taocanImage
     */
    public void setTaocanImage(String taocanImage) {
        this.taocanImage = taocanImage;
    }

    /**
     * @return taocan_name
     */
    public String getTaocanName() {
        return taocanName;
    }

    /**
     * @param taocanName
     */
    public void setTaocanName(String taocanName) {
        this.taocanName = taocanName;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return activity_theme
     */
    public String getActivityTheme() {
        return activityTheme;
    }

    /**
     * @param activityTheme
     */
    public void setActivityTheme(String activityTheme) {
        this.activityTheme = activityTheme;
    }

    /**
     * @return order_time
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * @param orderTime
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * @return version_id
     */
    public Integer getVersionId() {
        return versionId;
    }

    /**
     * @param versionId
     */
    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    /**
     * @return version_display_name
     */
    public String getVersionDisplayName() {
        return versionDisplayName;
    }

    /**
     * @param versionDisplayName
     */
    public void setVersionDisplayName(String versionDisplayName) {
        this.versionDisplayName = versionDisplayName;
    }

    /**
     * @return total_price
     */
    public Integer getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice
     */
    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return prepay_price
     */
    public Integer getPrepayPrice() {
        return prepayPrice;
    }

    /**
     * @param prepayPrice
     */
    public void setPrepayPrice(Integer prepayPrice) {
        this.prepayPrice = prepayPrice;
    }

    /**
     * @return paid_price
     */
    public Integer getPaidPrice() {
        return paidPrice;
    }

    /**
     * @param paidPrice
     */
    public void setPaidPrice(Integer paidPrice) {
        this.paidPrice = paidPrice;
    }

    /**
     * @return not_pay_price
     */
    public Integer getNotPayPrice() {
        return notPayPrice;
    }

    /**
     * @param notPayPrice
     */
    public void setNotPayPrice(Integer notPayPrice) {
        this.notPayPrice = notPayPrice;
    }

    /**
     * @return activity_area
     */
    public String getActivityArea() {
        return activityArea;
    }

    /**
     * @param activityArea
     */
    public void setActivityArea(String activityArea) {
        this.activityArea = activityArea;
    }

    /**
     * @return idea
     */
    public String getIdea() {
        return idea;
    }

    /**
     * @param idea
     */
    public void setIdea(String idea) {
        this.idea = idea;
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return tel_phone
     */
    public String getTelPhone() {
        return telPhone;
    }

    /**
     * @param telPhone
     */
    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    /**
     * @return wechat_num
     */
    public String getWechatNum() {
        return wechatNum;
    }

    /**
     * @param wechatNum
     */
    public void setWechatNum(String wechatNum) {
        this.wechatNum = wechatNum;
    }

    /**
     * @return state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(Integer state) {
        this.state = state;
    }
}