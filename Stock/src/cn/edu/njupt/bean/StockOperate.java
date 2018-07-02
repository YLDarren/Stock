package cn.edu.njupt.bean;

import java.util.Date;

public class StockOperate {
	private Integer operateId;//库存操作id
	private String userNumber;//库存用户编号
	private String userName;//库存用户名
	private Byte userLevel;//库存用户等级 1：管理员 2：采购员 3：车间员
	private String partsNumber;//库存零件编号
	private String partsMaterial;//库存零件名字
	private Integer operateAdd;//manager: append , buyer: buy , employ: need
	private Integer operateTake;//employ: take
	private Byte operateActive;//操作的状态 -1：停止  1：正常
	private Date createTime;//操作初始时间
	private Integer operateNumber;//操作的次数,只能操作一次
	public Integer getOperateId() {
		return operateId;
	}
	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
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
	public Byte getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(Byte userLevel) {
		this.userLevel = userLevel;
	}
	public String getPartsNumber() {
		return partsNumber;
	}
	public void setPartsNumber(String partsNumber) {
		this.partsNumber = partsNumber;
	}
	public String getPartsMaterial() {
		return partsMaterial;
	}
	public void setPartsMaterial(String partsMaterial) {
		this.partsMaterial = partsMaterial;
	}
	public Integer getOperateAdd() {
		return operateAdd;
	}
	public void setOperateAdd(Integer operateAdd) {
		this.operateAdd = operateAdd;
	}
	public Integer getOperateTake() {
		return operateTake;
	}
	public void setOperateTake(Integer operateTake) {
		this.operateTake = operateTake;
	}
	public Byte getOperateActive() {
		return operateActive;
	}
	public void setOperateActive(Byte operateActive) {
		this.operateActive = operateActive;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getOperateNumber() {
		return operateNumber;
	}
	public void setOperateNumber(Integer operateNumber) {
		this.operateNumber = operateNumber;
	}
	@Override
	public String toString() {
		return "StockOperate [operateId=" + operateId + ", userNumber=" + userNumber + ", userName=" + userName
				+ ", userLevel=" + userLevel + ", partsNumber=" + partsNumber + ", partsMaterial=" + partsMaterial
				+ ", operateAdd=" + operateAdd + ", operateTake=" + operateTake + ", operateActive=" + operateActive
				+ ", createTime=" + createTime + ", operateNumber=" + operateNumber + "]";
	}
	
}
