package model.dto;

import java.time.LocalDateTime;

public class PartTimerWorkplaceDto {
	private int id;
	private MemberDto memberDto;
	private String salaryForm;
	private int salaryDay;
	private LocalDateTime totalWorkTimeOfMonth;
	private int salary;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public PartTimerWorkplaceDto(int id, MemberDto memberDto, String salaryForm, int salaryDay, int salary, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.memberDto = memberDto;
		this.salaryForm = salaryForm;
		this.salaryDay = salaryDay;
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

	public String getSalaryForm() {
		return salaryForm;
	}

	public void setSalaryForm(String salaryForm) {
		this.salaryForm = salaryForm;
	}

	public int getSalaryDay() {
		return salaryDay;
	}

	public void setSalaryDay(int salaryDay) {
		this.salaryDay = salaryDay;
	}

	public LocalDateTime getTotalWorkTimeOfMonth() {
		return totalWorkTimeOfMonth;
	}

	public void setTotalWorkTimeOfMonth(LocalDateTime totalWorkTimeOfMonth) {
		this.totalWorkTimeOfMonth = totalWorkTimeOfMonth;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public MemberDto getMemberDto() {
		return memberDto;
	}

	public void setMemberDto(MemberDto memberDto) {
		this.memberDto = memberDto;
	}

	@Override
	public String toString() {
		return "PartTimerWorkplaceDto [id=" + id + ", salaryForm=" + salaryForm + ", salaryDay=" + salaryDay
				+ ", salary=" + salary + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}