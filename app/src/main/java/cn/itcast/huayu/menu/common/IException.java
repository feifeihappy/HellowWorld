package cn.itcast.huayu.menu.common;

public abstract interface IException {
	  public abstract String getErrorCode();
	  

	  public abstract void setErrorArguments(Object... paramVarArgs);
	  
	  public abstract Object[] getErrorArguments();
	  
}
