package cn.edu.njupt.bean;

import java.util.Date;

public class StockParts {
	private Integer partsId;//库存零件id
	private String partsNumber;//库存零件编号
	private String partsMaterial;//库存零件名字
	private Integer partsTotal;//库存总量
	private Integer partsRemain;//库存剩余
	private Integer partsUsed;//库存已用
	private Integer partsLimit;//库存临界值
	private Integer partsAppend;//库存追加
	private Integer partsDefaultAppend;//库存默认追加
	private Byte partsActive;//库存零件的状态 -1：停购   1：正常生产
	private Date createTime;//库存材料初始添加时间
	public Integer getPartsId() {
		return partsId;
	}
	public void setPartsId(Integer partsId) {
		this.partsId = partsId;
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
	public Integer getPartsTotal() {
		return partsTotal;
	}
	public void setPartsTotal(Integer partsTotal) {
		this.partsTotal = partsTotal;
	}
	public Integer getPartsRemain() {
		return partsRemain;
	}
	public void setPartsRemain(Integer partsRemain) {
		this.partsRemain = partsRemain;
	}
	public Integer getPartsUsed() {
		return partsUsed;
	}
	public void setPartsUsed(Integer partsUsed) {
		this.partsUsed = partsUsed;
	}
	public Integer getPartsLimit() {
		return partsLimit;
	}
	public void setPartsLimit(Integer partsLimit) {
		this.partsLimit = partsLimit;
	}
	public Integer getPartsAppend() {
		return partsAppend;
	}
	public void setPartsAppend(Integer partsAppend) {
		this.partsAppend = partsAppend;
	}
	public Integer getPartsDefaultAppend() {
		return partsDefaultAppend;
	}
	public void setPartsDefaultAppend(Integer partsDefaultAppend) {
		this.partsDefaultAppend = partsDefaultAppend;
	}
	public Byte getPartsActive() {
		return partsActive;
	}
	public void setPartsActive(Byte partsActive) {
		this.partsActive = partsActive;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "StockParts [partsId=" + partsId + ", partsNumber=" + partsNumber + ", partsMaterial=" + partsMaterial
				+ ", partsTotal=" + partsTotal + ", partsRemain=" + partsRemain + ", partsUsed=" + partsUsed
				+ ", partsLimit=" + partsLimit + ", partsAppend=" + partsAppend + ", partsDefaultAppend="
				+ partsDefaultAppend + ", partsActive=" + partsActive + ", createTime=" + createTime + "]";
	}
	
}
