package br.com.mariapuri.mydom.enums;

import java.util.Arrays;
import java.util.List;

public enum ReportPaginationType {

	WITH_PAGINATRION(false, "With Pagination"), 
	PAGINATIONLESS(true, "Paginationlass");

	private boolean value;
	private String description;
	

	ReportPaginationType(boolean value, String description) {
		this.value = value;
		this.description = description;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ReportPaginationType OfName(String name) {
		List<ReportPaginationType> types = Arrays.asList(ReportPaginationType.values());
		return types.stream()
				.filter(item -> item.name().equals(name))
				.findFirst()
				.orElse(null);
	}

}
