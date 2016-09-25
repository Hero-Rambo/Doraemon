package io.usa.doraemon.commons.api.transport.common;

public class ResponseWrapper {
	private Long id;
	private String response;

	public ResponseWrapper(){
		super();
	}
	public ResponseWrapper(Long id) {
		this();
		this.id = id;
	}

	public ResponseWrapper(Long id, String response) {
		this(id);
		this.response = response;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

 

 
}
