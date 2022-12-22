package model.dto;

import java.sql.Timestamp;

public class WorkplaceDto {
    private int id;
    private String workplaceName;
    private String address;
    private String phoneNumber;
    private String businessNumber;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public WorkplaceDto(String workplace_name, String address, String phoneNumber, String businessNumber) {
        this.workplaceName = workplace_name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.businessNumber = businessNumber;
    }

    public WorkplaceDto(int id, String workplace_name, String address, String phoneNumber, String businessNumber, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.workplaceName = workplace_name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.businessNumber = businessNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getWorkplaceName() {
        return workplaceName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBusinessNumber() {
        return businessNumber;
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

    public void setWorkplaceName(String workplaceName) {
        this.workplaceName = workplaceName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "WorkplaceDto{" +
                "id=" + id +
                ", workplace_name='" + workplaceName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", businessNumber='" + businessNumber + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}