package model.dto;

import java.time.LocalDateTime;
import java.sql.Date;

public class MemberDto {
	private int id;		//유저번호
	private String member_id;	//아이디
	private String password;	//비밀번호
	private String name;	//이름
	private Date date;	//생년월일  Date타입으로 해도 되는지 아니면 String으로 받고 jsp 페이지에서 바꿀건지
	private String phone_number;	//전화번호
	private String type;	//멤버타입
	private int is_active;	//계정 활성화 여부
	private LocalDateTime createdAt;	//생성 날짜
	private LocalDateTime updatedAt;	//수정 날짜
	
	public MemberDto() {
		
	}
	
	public MemberDto(String member_id, String password, String name, Date date, String phone_number,
			String type, int is_active) {
		super();
		this.member_id = member_id;
		this.password = password;
		this.name = name;
		this.date = date;
		this.phone_number = phone_number;
		this.type = type;
		this.is_active = is_active;
	}

	public int getId() {
		return id;
	}
	
	public String getMember_id() {
		return member_id;
	}
	
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getPhone_number() {
		return phone_number;
	}
	
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getIs_active() {
		return is_active;
	}
	
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	
	public LocalDateTime getCreated_at() {
		return createdAt;
	}
	
	public void setCreated_at(LocalDateTime updatedAt) {
		this.createdAt = updatedAt;
	}
	
	public LocalDateTime getUpdated_at() {
		return updatedAt;
	}
	
	public void setUpdated_at(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}