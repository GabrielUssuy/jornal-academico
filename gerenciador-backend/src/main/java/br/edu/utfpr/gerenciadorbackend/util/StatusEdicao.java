package br.edu.utfpr.gerenciadorbackend.util;

public enum StatusEdicao {
	
	EXCLUIDO("AGUARDANDO"), PUBLICADO("PUBLICADO");
	
	private String value;

	private StatusEdicao(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
