package br.edu.utfpr.gerenciadorweb.enums;

public enum TipoArquivo {

	NOTICIA("NOTICIA"),
	IMAGEM("IMAGEM");
	
	private String value;

	private TipoArquivo(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
