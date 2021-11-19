package com.shunli.bookingmeeting.models;

import java.util.Date;

public class MeetingInfoModel {

    private int startTime;
    private int stopTime;
    private String startTimeHHmm;
    private String stopTimeHHmm;
    private Date meetingDate;
    private String roomsName;
    private long id;
    private int roomsId;
    private String pager;
    private String email;
    private String booker;
    private String subject;

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStopTime(int stopTime) {
        this.stopTime = stopTime;
    }

    public int getStopTime() {
        return stopTime;
    }

    public void setStartTimeHHmm(String startTimeHHmm) {
        this.startTimeHHmm = startTimeHHmm;
    }

    public String getStartTimeHHmm() {
        return startTimeHHmm;
    }

    public void setStopTimeHHmm(String stopTimeHHmm) {
        this.stopTimeHHmm = stopTimeHHmm;
    }

    public String getStopTimeHHmm() {
        return stopTimeHHmm;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setRoomsName(String roomsName) {
        this.roomsName = roomsName;
    }

    public String getRoomsName() {
        return roomsName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setRoomsId(int roomsId) {
        this.roomsId = roomsId;
    }

    public int getRoomsId() {
        return roomsId;
    }

    public void setPager(String pager) {
        this.pager = pager;
    }

    public String getPager() {
        return pager;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setBooker(String booker) {
        this.booker = booker;
    }

    public String getBooker() {
        return booker;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

}