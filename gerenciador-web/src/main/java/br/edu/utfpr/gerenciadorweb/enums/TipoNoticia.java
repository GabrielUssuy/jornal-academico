package br.edu.utfpr.gerenciadorweb.enums;

public enum TipoNoticia {
	
	PADRAO("PADRAO"),
	PDF("PDF");
	
	private String value;

	private TipoNoticia(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
