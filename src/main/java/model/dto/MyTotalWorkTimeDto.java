package model.dto;

import java.sql.Timestamp;
import java.sql.Date;

public class MyTotalWorkTimeDto {
    private int id;
    private int partTimerWorkplaceId;
    private int totalWorkHourOfMonth;
    private int totalWorkMinuteOfMonth;
    private Date workDateOfMonth;
    private int salary;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public MyTotalWorkTimeDto(int id, int partTimerWorkplaceId, int totalWorkHourOfMonth, int totalWorkMinuteOfMonth, Date workDateOfMonth, int salary, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.partTimerWorkplaceId = partTimerWorkplaceId;
        this.totalWorkHourOfMonth = totalWorkHourOfMonth;
        this.totalWorkMinuteOfMonth = totalWorkMinuteOfMonth;
        this.workDateOfMonth = workDateOfMonth;
        this.salary = salary;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public MyTotalWorkTimeDto(int partTimerWorkplaceId, int totalWorkHourOfMonth, int totalWorkMinuteOfMonth, Date workDateOfMonth, int salary, Timestamp createdAt, Timestamp updatedAt) {
        this.partTimerWorkplaceId = partTimerWorkplaceId;
        this.totalWorkHourOfMonth = totalWorkHourOfMonth;
        this.totalWorkMinuteOfMonth = totalWorkMinuteOfMonth;
        this.workDateOfMonth = workDateOfMonth;
        this.salary = salary;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPartTimerWorkplaceId() {
        return partTimerWorkplaceId;
    }

    public void setPartTimerWorkplaceId(int partTimerWorkplaceId) {
        this.partTimerWorkplaceId = partTimerWorkplaceId;
    }

    public int getTotalWorkHourOfMonth() {
        return totalWorkHourOfMonth;
    }

    public void setTotalWorkHourOfMonth(int totalWorkHourOfMonth) {
        this.totalWorkHourOfMonth = totalWorkHourOfMonth;
    }

    public int getTotalWorkMinuteOfMonth() {
        return totalWorkMinuteOfMonth;
    }

    public void setTotalWorkMinuteOfMonth(int totalWorkMinuteOfMonth) {
        this.totalWorkMinuteOfMonth = totalWorkMinuteOfMonth;
    }

    public Date getWorkDateOfMonth() {
        return workDateOfMonth;
    }

    public void setWorkDateOfMonth(Date workDateOfMonth) {
        this.workDateOfMonth = workDateOfMonth;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
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
        return "MyTotalWorkTimeDto{" +
                "id=" + id +
                ", partTimerWorkplaceId=" + partTimerWorkplaceId +
                ", totalWorkHourOfMonth=" + totalWorkHourOfMonth +
                ", totalWorkMinuteOfMonth=" + totalWorkMinuteOfMonth +
                ", workDateOfMonth=" + workDateOfMonth +
                ", salary=" + salary +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}