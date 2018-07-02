package cn.edu.njupt.bean;

import java.util.Date;

public class StockUser {
	private Integer userId;//库存用户id
	private String userNumber;//库存用户编号
	private String userName;//库存用户名
	private String userPassword;//库存用户密码
	private Date createTime;//库存用户入职时间
	private Byte userLevel;//库存用户等级 1：管理员 2：采购员 3：车间员
	private Byte userActive;//库存用户的状态 -1：注销   1：在职员工
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Byte getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(Byte userLevel) {
		this.userLevel = userLevel;
	}
	public Byte getUserActive() {
		return userActive;
	}
	public void setUserActive(Byte userActive) {
		this.userActive = userActive;
	}
	@Override
	public String toString() {
		return "StockUser [userId=" + userId + ", userNumber=" + userNumber + ", userName=" + userName
				+ ", userPassword=" + userPassword + ", createTime=" + createTime + ", userLevel=" + userLevel
				+ ", userActive=" + userActive + "]";
	}
	
}
