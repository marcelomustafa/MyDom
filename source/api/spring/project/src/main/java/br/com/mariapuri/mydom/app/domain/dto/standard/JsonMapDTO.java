package br.com.mariapuri.mydom.app.domain.dto.standard;

public record JsonMapDTO(
		int order, 
		String key, 
		Object value
) {

	public JsonMapDTO(
			String key, 
			Object value
	) {
		this(0, key, value);
	}

}
