package model.dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class MyTodayWorkTimeDto {
    private int id;
    private int myTotalWorkTimeId;
    private Time workStartTime;
    private Time workFinishTime;
    private Time breakStartTime;
    private Time breakFinishTime;
    private Time totalWorkTimeOfDay;
    private Time totalBreakTimeOfDay;
    private Date workDate;
    private int minimumWage;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public MyTodayWorkTimeDto(int myTotalWorkTimeId, TimeSettingDto timeSettingDto, Date workDate, int minimumWage, Timestamp createdAt, Timestamp updatedAt) {
        this.myTotalWorkTimeId = myTotalWorkTimeId;
        this.workStartTime = timeSettingDto.getWorkStartTime();
        this.workFinishTime = timeSettingDto.getWorkFinishTime();
        this.breakStartTime = timeSettingDto.getBreakStartTime();
        this.breakFinishTime = timeSettingDto.getBreakFinishTime();
        this.totalWorkTimeOfDay = timeSettingDto.getTotalWorkTime();
        this.totalBreakTimeOfDay = timeSettingDto.getTotalBreakTime();
        this.workDate = workDate;
        this.minimumWage = minimumWage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public MyTodayWorkTimeDto(int id, int myTotalWorkTimeId, Time workStartTime, Time workFinishTime, Time breakStartTime, Time breakFinishTime, Time totalWorkTimeOfDay, Time totalBreakTimeOfDay, Date workDate, int minimumWage, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.myTotalWorkTimeId = myTotalWorkTimeId;
        this.workStartTime = workStartTime;
        this.workFinishTime = workFinishTime;
        this.breakStartTime = breakStartTime;
        this.breakFinishTime = breakFinishTime;
        this.totalWorkTimeOfDay = totalWorkTimeOfDay;
        this.totalBreakTimeOfDay = totalBreakTimeOfDay;
        this.workDate = workDate;
        this.minimumWage = minimumWage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Time getWorkStartTime() {
        return workStartTime;
    }
    public Time getWorkFinishTime() {
        return workFinishTime;
    }
    public Time getBreakStartTime() {
        return breakStartTime;
    }
    public Time getBreakFinishTime() {
        return breakFinishTime;
    }
    public Time getTotalWorkTimeOfDay() {
        return totalWorkTimeOfDay;
    }
    public Time getTotalBreakTimeOfDay() {
        return totalBreakTimeOfDay;
    }
    public Date getWorkDate() {
        return workDate;
    }
    public int getMinimumWage() {
        return minimumWage;
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
        return "MyTodayWorkTimeDto{" +
                "id=" + id +
                ", myTotalWorkTimeId=" + myTotalWorkTimeId +
                ", workStartTime=" + workStartTime +
                ", workFinishTime=" + workFinishTime +
                ", breakStartTime=" + breakStartTime +
                ", breakFinishTime=" + breakFinishTime +
                ", totalWorkTimeOfDay=" + totalWorkTimeOfDay +
                ", totalBreakTimeOfDay=" + totalBreakTimeOfDay +
                ", workDate=" + workDate +
                ", minimumWage=" + minimumWage +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
