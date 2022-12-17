package model.dto;

import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Date;

public class MyTotalWorkTimeDto {
    private int id;
    private int partTimerWorkplaceId;
    private Time totalWorkTimeOfMonth;
    private Date workDateOfMonth;
    private int salary;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public MyTotalWorkTimeDto(int id, int partTimerWorkplaceId, Time totalWorkTimeOfMonth, Date workDateOfMonth, int salary, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.partTimerWorkplaceId = partTimerWorkplaceId;
        this.totalWorkTimeOfMonth = totalWorkTimeOfMonth;
        this.workDateOfMonth = workDateOfMonth;
        this.salary = salary;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public MyTotalWorkTimeDto(int partTimerWorkplaceId, Time totalWorkTimeOfMonth, Date workDateOfMonth, int salary, Timestamp createdAt, Timestamp updatedAt) {
        this.partTimerWorkplaceId = partTimerWorkplaceId;
        this.totalWorkTimeOfMonth = totalWorkTimeOfMonth;
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
    public Time getTotalWorkTimeOfMonth() {
        return totalWorkTimeOfMonth;
    }

    public void setTotalWorkTimeOfMonth(Time totalWorkTimeOfMonth) {
        this.totalWorkTimeOfMonth = totalWorkTimeOfMonth;
    }
    public Date getWorkDateOfMonth() {
        return workDateOfMonth;
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
                "partTimerWorkplaceId=" + partTimerWorkplaceId +
                ", totalWorkTimeOfMonth=" + totalWorkTimeOfMonth +
                ", workDateOfMonth=" + workDateOfMonth +
                ", date=" + workDateOfMonth +
                ", salary=" + salary +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
