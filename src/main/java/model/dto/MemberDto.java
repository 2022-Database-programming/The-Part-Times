package model.dto;

import java.sql.Timestamp;
import java.sql.Date;

public class MemberDto {
	private int id;		//유저번호
	private String memberId;	//아이디
	private String password;	//비밀번호
	private String name;	//이름
	private Date birth;	//생년월일  Date타입으로 해도 되는지 아니면 String으로 받고 jsp 페이지에서 바꿀건지
	private String phoneNumber;	//전화번호
	private String type;	//멤버타입
	private int isActive;	//계정 활성화 여부 -> 따로 작업할 것 뭐가 있나..?
	private Timestamp createdAt;	//생성 날짜
	private Timestamp updatedAt;	//수정 날짜

	public MemberDto() {

	}

	public MemberDto(String memberId, String password, String name, Date birth, String phoneNumber,
					 String type) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.phoneNumber = phoneNumber;
		this.type = type;
	}

	public MemberDto(int id, String memberId, String password, String name, Date birth, String phoneNumber,
					 String type) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.phoneNumber = phoneNumber;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
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

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp updatedAt) {
		this.createdAt = updatedAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

//	/* 비밀번호 검사 */
//	public boolean matchPassword(String password) {
//		if (password == null) {
//			return false;
//		}
//		return this.password.equals(password);
//	}
//
//	public boolean isSameMember(String memberid) {
//        return this.memberId.equals(memberid);
//    }

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", memberId=" + memberId + ", password=" + password + ", name=" + name
				+ ", birth=" + birth + ", phoneNumber=" + phoneNumber + ", type=" + type + ", isActive=" + isActive
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}



}