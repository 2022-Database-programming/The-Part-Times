package model.dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class MyTodayWorkTimeDto {
    // Primary key field
    private int id;

    // Foreign key field
    private int myTotalWorkTimeId;

    private Time workStartTime;
    private Time workFinishTime;
    private Time breakStartTime;
    private Time breakFinishTime;
    private Time totalWorkTimeOfDay;
    private Time totalBreakTimeOfDay;
    private Date workDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public MyTodayWorkTimeDto(int id, int myTotalWorkTimeId, Time workStartTime, Time workFinishTime, Time breakStartTime, Time breakFinishTime, Time totalWorkTimeOfDay, Time totalBreakTimeOfDay, Date workDate, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.myTotalWorkTimeId = myTotalWorkTimeId;
        this.workStartTime = workStartTime;
        this.workFinishTime = workFinishTime;
        this.breakStartTime = breakStartTime;
        this.breakFinishTime = breakFinishTime;
        this.totalWorkTimeOfDay = totalWorkTimeOfDay;
        this.totalBreakTimeOfDay = totalBreakTimeOfDay;
        this.workDate = workDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMyTotalWorkTimeId() {
        return myTotalWorkTimeId;
    }

    public void setMyTotalWorkTimeId(int myTotalWorkTimeId) {
        this.myTotalWorkTimeId = myTotalWorkTimeId;
    }

    public Time getWorkStartTime() {
        return workStartTime;
    }

    public void setWorkStartTime(Time workStartTime) {
        this.workStartTime = workStartTime;
    }

    public Time getWorkFinishTime() {
        return workFinishTime;
    }

    public void setWorkFinishTime(Time workFinishTime) {
        this.workFinishTime = workFinishTime;
    }

    public Time getBreakStartTime() {
        return breakStartTime;
    }

    public void setBreakStartTime(Time breakStartTime) {
        this.breakStartTime = breakStartTime;
    }

    public Time getBreakFinishTime() {
        return breakFinishTime;
    }

    public void setBreakFinishTime(Time breakFinishTime) {
        this.breakFinishTime = breakFinishTime;
    }

    public Time getTotalWorkTimeOfDay() {
        return totalWorkTimeOfDay;
    }

    public void setTotalWorkTimeOfDay(Time totalWorkTimeOfDay) {
        this.totalWorkTimeOfDay = totalWorkTimeOfDay;
    }

    public Time getTotalBreakTimeOfDay() {
        return totalBreakTimeOfDay;
    }

    public void setTotalBreakTimeOfDay(Time totalBreakTimeOfDay) {
        this.totalBreakTimeOfDay = totalBreakTimeOfDay;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "MyTodayWorkTime{" +
                "id=" + id +
                ", myTotalWorkTime=" + myTotalWorkTimeId +
                ", workStartTime=" + workStartTime +
                ", workFinishTime=" + workFinishTime +
                ", breakStartTime=" + breakStartTime +
                ", breakFinishTime=" + breakFinishTime +
                ", totalWorkTimeOfDay=" + totalWorkTimeOfDay +
                ", totalBreakTimeOfDay=" + totalBreakTimeOfDay +
                ", workDate=" + workDate +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
