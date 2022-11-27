package model.dto;

import java.sql.Timestamp;
import java.sql.Date;

public class MyTotalIncomeDto {
	private int id;		//PK 근무정보 번호
	private int employerWorkplaceId;	//FK 나의 근무지 번호
	private Date incomeDateOfMonth;		//연월일, 월별 수익 저장
	private int income;		//이번달 수익
	private Timestamp createdAt;	//생성 날짜
	private Timestamp updatedAt;	//수정 날짜
	
	public MyTotalIncomeDto() {
		
	}

	public MyTotalIncomeDto(int employerWorkplaceId, Date incomeDateOfMonth, int income) {
		super();
		this.employerWorkplaceId = employerWorkplaceId;
		this.incomeDateOfMonth = incomeDateOfMonth;
		this.income = income;
	}

	public MyTotalIncomeDto(int id, int employerWorkplaceId, Date incomeDateOfMonth, int income, Timestamp createdAt,
			Timestamp updatedAt) {
		super();
		this.id = id;
		this.employerWorkplaceId = employerWorkplaceId;
		this.incomeDateOfMonth = incomeDateOfMonth;
		this.income = income;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployerWorkplaceId() {
		return employerWorkplaceId;
	}

	public void setEmployerWorkplaceId(int employerWorkplaceId) {
		this.employerWorkplaceId = employerWorkplaceId;
	}

	public Date getIncomeDateOfMonth() {
		return incomeDateOfMonth;
	}

	public void setIncomeDateOfMonth(Date incomeDateOfMonth) {
		this.incomeDateOfMonth = incomeDateOfMonth;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
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
		return "MyTotalIncomeDto [id=" + id + ", employerWorkplaceId=" + employerWorkplaceId + ", incomeDateOfMonth="
				+ incomeDateOfMonth + ", income=" + income + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ "]";
	}
	
}