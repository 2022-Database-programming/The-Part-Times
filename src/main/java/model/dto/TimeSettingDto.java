package model.dto;

import java.sql.Time;

public class TimeSettingDto {
    private int workStartHour;
    private int workStartMinute;
    private int workFinishHour;
    private int workFinishMinute;
    private int breakStartHour;
    private int breakStartMinute;
    private int breakFinishHour;
    private int breakFinishMinute;
    private int totalWorkHour;
    private int totalWorkMinute;
    private int totalBreakHour;
    private int totalBreakMinute;
    private Time workStartTime;
    private Time workFinishTime;
    private Time breakStartTime;
    private Time breakFinishTime;
    private Time totalWorkTime;
    private Time totalBreakTime;

    public TimeSettingDto(int workStartHour, int workStartMinute, int workFinishHour, int workFinishMinute, int breakStartHour, int breakStartMinute, int breakFinishHour, int breakFinishMinute) {
        this.workStartHour = workStartHour;
        this.workStartMinute = workStartMinute;
        this.workFinishHour = workFinishHour;
        this.workFinishMinute = workFinishMinute;
        this.breakStartHour = breakStartHour;
        this.breakStartMinute = breakStartMinute;
        this.breakFinishHour = breakFinishHour;
        this.breakFinishMinute = breakFinishMinute;
        this.totalBreakHour = breakFinishHour - breakStartHour;
        this.totalBreakMinute = breakFinishMinute - breakStartMinute;
        this.totalWorkHour = (workFinishHour - workStartHour) - totalBreakHour;
        this.totalWorkMinute = (workFinishMinute - workStartMinute) - totalBreakMinute;


        this.workStartTime = new Time(workStartHour, workStartMinute, 0);
        this.workFinishTime = new Time(workFinishHour, workFinishMinute, 0);
        this.breakStartTime = new Time(breakStartHour, breakStartMinute, 0);
        this.breakFinishTime = new Time(breakFinishHour, breakFinishMinute, 0);
        this.totalWorkTime = new Time(totalWorkHour, totalWorkMinute, 0);
        this.totalBreakTime = new Time(totalBreakHour, totalBreakMinute, 0);
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

    public Time getTotalWorkTime() {
        return totalWorkTime;
    }

    public Time getTotalBreakTime() {
        return totalBreakTime;
    }
}
