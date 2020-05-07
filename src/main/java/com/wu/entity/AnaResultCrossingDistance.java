package com.wu.entity;

import com.wu.common.BaseModel;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
public class AnaResultCrossingDistance extends BaseModel<Integer> {
    private Integer id;

    private String lkbh;

    private String lkmc;

    private Short jkdfxh;

    private Integer faGreen;

    private Integer lkGreen;

    private Short gzxr;

    private Short jg;

    private Integer schemalId;

    private Integer startHour;

    private Integer startMinute;

    private Integer endHour;

    private Integer endMinute;

    private String scheduleName;

    private Integer scheduleWeek;

    private Integer scheduleMonth;

    private Long scheduleDate;

    private Integer dayplanId;

    private Date createTime;

    private Date editTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLkbh() {
        return lkbh;
    }

    public void setLkbh(String lkbh) {
        this.lkbh = lkbh == null ? null : lkbh.trim();
    }

    public String getLkmc() {
        return lkmc;
    }

    public void setLkmc(String lkmc) {
        this.lkmc = lkmc == null ? null : lkmc.trim();
    }

    public Short getJkdfxh() {
        return jkdfxh;
    }

    public void setJkdfxh(Short jkdfxh) {
        this.jkdfxh = jkdfxh;
    }

    public Integer getFaGreen() {
        return faGreen;
    }

    public void setFaGreen(Integer faGreen) {
        this.faGreen = faGreen;
    }

    public Integer getLkGreen() {
        return lkGreen;
    }

    public void setLkGreen(Integer lkGreen) {
        this.lkGreen = lkGreen;
    }

    public Short getGzxr() {
        return gzxr;
    }

    public void setGzxr(Short gzxr) {
        this.gzxr = gzxr;
    }

    public Short getJg() {
        return jg;
    }

    public void setJg(Short jg) {
        this.jg = jg;
    }

    public Integer getSchemalId() {
        return schemalId;
    }

    public void setSchemalId(Integer schemalId) {
        this.schemalId = schemalId;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    public Integer getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(Integer startMinute) {
        this.startMinute = startMinute;
    }

    public Integer getEndHour() {
        return endHour;
    }

    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }

    public Integer getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(Integer endMinute) {
        this.endMinute = endMinute;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName == null ? null : scheduleName.trim();
    }

    public Integer getScheduleWeek() {
        return scheduleWeek;
    }

    public void setScheduleWeek(Integer scheduleWeek) {
        this.scheduleWeek = scheduleWeek;
    }

    public Integer getScheduleMonth() {
        return scheduleMonth;
    }

    public void setScheduleMonth(Integer scheduleMonth) {
        this.scheduleMonth = scheduleMonth;
    }

    public Long getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Long scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Integer getDayplanId() {
        return dayplanId;
    }

    public void setDayplanId(Integer dayplanId) {
        this.dayplanId = dayplanId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}