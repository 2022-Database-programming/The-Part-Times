package model.dto;

import java.sql.Timestamp;

public class EmployerWorkplaceDto {
    private int id;
    private int memberId;
    private int workplaceId;
    private String salaryForm;
    private int salaryDay;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public EmployerWorkplaceDto(int memberId, int workplaceId, String salaryForm, int salaryDay) {
        this.memberId = memberId;
        this.workplaceId = workplaceId;
        this.salaryForm = salaryForm;
        this.salaryDay = salaryDay;
    }

    public int getId() {
        return id;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getWorkplaceId() {
        return workplaceId;
    }

    public String getSalaryForm() {
        return salaryForm;
    }

    public int getSalaryDay() {
        return salaryDay;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setWorkplaceId(int workplaceId) {
        this.workplaceId = workplaceId;
    }

    public void setSalaryForm(String salaryForm) {
        this.salaryForm = salaryForm;
    }

    public void setSalaryDay(int salaryDay) {
        this.salaryDay = salaryDay;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
