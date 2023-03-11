package br.com.mariapuri.mydom.app.domain.dto.standard;

public class JsonMapDTO {
	
	private Integer order;
	private String key;
	private Object value;
	
		
	public JsonMapDTO(int order, String key, Object value) {
		this.order = Integer.valueOf(order);
		this.key = key;
		this.value = value;
	}
	
	public JsonMapDTO(String key, Object value) {
		this.key = key;
		this.value = value;
	}
	
	public JsonMapDTO(){}
	
	
	
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
