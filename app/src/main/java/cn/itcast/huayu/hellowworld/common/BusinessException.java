package cn.itcast.huayu.hellowworld.common;

import java.io.Serializable;

/**
 * 业务异常类
 * 
 * @author gaojia
 * 
 */
public class BusinessException extends RuntimeException implements IException,
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -98857410022907304L;
	protected String errCode;
	private String errMsg;
	private Object[] arguments;

	public BusinessException() {
		
	}

	public BusinessException(String msg) {
		super(msg);
        this.errMsg = msg;
	}

	public BusinessException(String msg, Throwable cause) {
		super(msg, cause);
        this.errMsg = msg;
	}

	public BusinessException(String code, String msg) {
		super(msg);
        this.errMsg = msg;
		this.errCode = code;
	}

    public BusinessException(String code, String msg, Throwable cause){
        super(msg, cause);
        this.errMsg = msg;
        this.errCode = code;
    }

	public BusinessException(String code, Object... args) {
		this.errCode = code;
		this.arguments = args;
	}

	public BusinessException(String code, String msg, Object... args) {
		super(msg);
        this.errMsg = msg;
		this.errCode = code;
		this.arguments = args;
	}

	@Override
	public String getErrorCode() {
		// TODO Auto-generated method stub
		return errCode;
	}

    public String getErrMsg(){
       return errMsg;
    }

	@Override
	public void setErrorArguments(Object... paramVarArgs) {
		this.arguments = paramVarArgs;

	}

	@Override
	public Object[] getErrorArguments() {

		return this.arguments;
	}

}
