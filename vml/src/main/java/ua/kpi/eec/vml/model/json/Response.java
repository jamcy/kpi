package ua.kpi.eec.vml.model.json;

public class Response {
	private String response;
	private Object data;
	
	public Response(String response) {
		this.response = response;
	}
	
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
