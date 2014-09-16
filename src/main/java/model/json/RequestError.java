package model.json;

public class RequestError {
	private String error;
	private int code;
	
	public RequestError(String err, int c) {
		this.error = err;
		this.code = c;
	}
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
	
}
