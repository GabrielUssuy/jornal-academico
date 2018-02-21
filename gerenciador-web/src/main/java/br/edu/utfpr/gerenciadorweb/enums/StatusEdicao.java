package br.edu.utfpr.gerenciadorweb.enums;

public enum StatusEdicao {
	
	AGUARDANDO("AGUARDANDO"), PUBLICADO("PUBLICADO");
	
	private String value;

	private StatusEdicao(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
