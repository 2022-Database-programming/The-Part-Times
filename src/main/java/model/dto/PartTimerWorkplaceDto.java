package model.dto;

import java.sql.Time;
import java.sql.Timestamp;

public class PartTimerWorkplaceDto {
	// Primary key field
	private int id;

	//Foreign key field
	private MemberDto memberDto;
	private WorkplaceDto workplaceDto;

	private String salaryForm;
	private int salaryDay;
	private Time totalWorkTimeOfMonth;
	private int salary;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public PartTimerWorkplaceDto(int id, MemberDto memberDto, WorkplaceDto workplaceDto, String salaryForm, int salaryDay, Time totalWorkTimeOfMonth, int salary, Timestamp createdAt, Timestamp updatedAt) {
		this.id = id;
		this.memberDto = memberDto;
		this.workplaceDto = workplaceDto;
		this.salaryForm = salaryForm;
		this.salaryDay = salaryDay;
		this.totalWorkTimeOfMonth = totalWorkTimeOfMonth;
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

	public MemberDto getMemberDto() {
		return memberDto;
	}

	public void setMemberDto(MemberDto memberDto) {
		this.memberDto = memberDto;
	}

	public WorkplaceDto getWorkplaceDto() {
		return workplaceDto;
	}

	public void setWorkplaceDto(WorkplaceDto workplaceDto) {
		this.workplaceDto = workplaceDto;
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

	public Time getTotalWorkTimeOfMonth() {
		return totalWorkTimeOfMonth;
	}

	public void setTotalWorkTimeOfMonth(Time totalWorkTimeOfMonth) {
		this.totalWorkTimeOfMonth = totalWorkTimeOfMonth;
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
		return "PartTimerWorkplaceDto{" +
				"id=" + id +
				", memberDto=" + memberDto +
				", workplaceDto=" + workplaceDto +
				", salaryForm='" + salaryForm + '\'' +
				", salaryDay=" + salaryDay +
				", totalWorkTimeOfMonth=" + totalWorkTimeOfMonth +
				", salary=" + salary +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				'}';
	}
}