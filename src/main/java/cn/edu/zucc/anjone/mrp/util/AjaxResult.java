package cn.edu.zucc.anjone.mrp.util;

public class AjaxResult {
	private String state;
	private String message;
	
	
	public AjaxResult(){}
	/*
	 *  state 状态  message信息
	 */
	public AjaxResult(String state,String message){
		this.state = state;
		this.message = message;
	}
	
	/*
	 *  res : state,message  状态,信息
	 */
	public AjaxResult(String res){
		String[] str = res.split(",,");
		this.state = str[0].equals("0") ? "error" : "success";
		this.message = str[1];
	}

	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
