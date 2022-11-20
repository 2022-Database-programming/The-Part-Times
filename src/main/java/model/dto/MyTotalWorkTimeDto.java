package model.dto;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class MyTotalWorkTimeDto {
    // Primary key field
    private int id;

    // Foreign key field
    private int partTimerWorkplaceId;

    private Time totalWorkTimeOfDay;
    private Date date;
    private int salary;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public MyTotalWorkTimeDto(int id, int partTimerWorkplaceId, Time totalWorkTimeOfDay, Date date, int salary, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.partTimerWorkplaceId = partTimerWorkplaceId;
        this.totalWorkTimeOfDay = totalWorkTimeOfDay;
        this.date = date;
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

    public Time getTotalWorkTimeOfDay() {
        return totalWorkTimeOfDay;
    }

    public void setTotalWorkTimeOfDay(Time totalWorkTimeOfDay) {
        this.totalWorkTimeOfDay = totalWorkTimeOfDay;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
                ", totalWorkTimeOfDay=" + totalWorkTimeOfDay +
                ", date=" + date +
                ", salary=" + salary +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
