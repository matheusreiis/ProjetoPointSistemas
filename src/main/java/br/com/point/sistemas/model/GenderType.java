package br.com.point.sistemas.model;

public enum GenderType {

	Masculino("Masculino"), Feminino("Feminino");

	private String description;

	GenderType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
