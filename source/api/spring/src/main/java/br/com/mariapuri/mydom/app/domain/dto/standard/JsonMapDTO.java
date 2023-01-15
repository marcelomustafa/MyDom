package br.com.mariapuri.mydom.app.domain.dto.standard;

public class JsonMapDTO {
	
	private String key;
	private Object value;
	
	public JsonMapDTO(String key, Object value) {
		this.key = key;
		this.value = value;
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
