package cn.edu.njupt.bean;

public class SearchParts {
	private String partsNumber;//库存零件编号
	private String partsMaterial;//库存零件名字
	private Integer partsRemain;//库存剩余
	private Integer partsLimit;//库存临界值
	private Integer partsDefaultAppend;//库存默认追加
	
	public SearchParts() {
		super();
	}
	public SearchParts(String partsNumber) {
		super();
		this.partsNumber = partsNumber;
	}
	public SearchParts(String partsNumber, String partsMaterial) {
		super();
		this.partsNumber = partsNumber;
		this.partsMaterial = partsMaterial;
	}
	public SearchParts(String partsNumber, String partsMaterial, Integer partsLimit) {
		super();
		this.partsNumber = partsNumber;
		this.partsMaterial = partsMaterial;
		this.partsLimit = partsLimit;
	}
	public SearchParts(String partsNumber, String partsMaterial, Integer partsRemain, Integer partsLimit,
			Integer partsDefaultAppend) {
		super();
		this.partsNumber = partsNumber;
		this.partsMaterial = partsMaterial;
		this.partsRemain = partsRemain;
		this.partsLimit = partsLimit;
		this.partsDefaultAppend = partsDefaultAppend;
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
	public Integer getPartsRemain() {
		return partsRemain;
	}
	public void setPartsRemain(Integer partsRemain) {
		this.partsRemain = partsRemain;
	}
	public Integer getPartsLimit() {
		return partsLimit;
	}
	public void setPartsLimit(Integer partsLimit) {
		this.partsLimit = partsLimit;
	}
	public Integer getPartsDefaultAppend() {
		return partsDefaultAppend;
	}
	public void setPartsDefaultAppend(Integer partsDefaultAppend) {
		this.partsDefaultAppend = partsDefaultAppend;
	}
	@Override
	public String toString() {
		return "SearchParts [partsNumber=" + partsNumber + ", partsMaterial=" + partsMaterial + ", partsRemain="
				+ partsRemain + ", partsLimit=" + partsLimit + ", partsDefaultAppend=" + partsDefaultAppend + "]";
	}
	
	
}
