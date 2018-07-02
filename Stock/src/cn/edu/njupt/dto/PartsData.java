package cn.edu.njupt.dto;

/*
 * 零件返回的数据
 */
public class PartsData<T> {
	private Boolean success;
	private T data;
	private String reason;
	
	public PartsData() {
		super();
	}

	public PartsData(Boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}
	
	public PartsData(Boolean success, String reason) {
		super();
		this.success = success;
		this.reason = reason;
	}

	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "PartsData [success=" + success + ", data=" + data + ", reason=" + reason + "]";
	}
	
}
