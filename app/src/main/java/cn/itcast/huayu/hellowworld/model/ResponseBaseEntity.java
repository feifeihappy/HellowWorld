package cn.itcast.huayu.hellowworld.model;

import java.io.Serializable;

public class ResponseBaseEntity<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String resultcode;
	
	private String reason;
	
	private T result;

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	
	

}
