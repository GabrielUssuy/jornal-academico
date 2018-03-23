package br.edu.utfpr.gerenciadorweb.util;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "url")
public class UrlProperties implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String arquivo_salvar;
	private String arquivo_listar_imagens_por_noticia;
	private String edicao_salvar;
	private String edicao_deletar;
	private String edicao_listar;
	private String edicao_buscar_por_id;
	private String noticia_salvar;
	private String noticia_listar;
	private String noticia_deletar;
	private String token_solicitar;
	
	public String getArquivo_salvar() {
		return arquivo_salvar;
	}
	public void setArquivo_salvar(String arquivo_salvar) {
		this.arquivo_salvar = arquivo_salvar;
	}
	public String getArquivo_listar_imagens_por_noticia() {
		return arquivo_listar_imagens_por_noticia;
	}
	public void setArquivo_listar_imagens_por_noticia(String arquivo_listar_imagens_por_noticia) {
		this.arquivo_listar_imagens_por_noticia = arquivo_listar_imagens_por_noticia;
	}
	public String getEdicao_salvar() {
		return edicao_salvar;
	}
	public void setEdicao_salvar(String edicao_salvar) {
		this.edicao_salvar = edicao_salvar;
	}
	public String getEdicao_deletar() {
		return edicao_deletar;
	}
	public void setEdicao_deletar(String edicao_deletar) {
		this.edicao_deletar = edicao_deletar;
	}
	public String getEdicao_listar() {
		return edicao_listar;
	}
	public void setEdicao_listar(String edicao_listar) {
		this.edicao_listar = edicao_listar;
	}
	public String getEdicao_buscar_por_id() {
		return edicao_buscar_por_id;
	}
	public void setEdicao_buscar_por_id(String edicao_buscar_por_id) {
		this.edicao_buscar_por_id = edicao_buscar_por_id;
	}
	public String getNoticia_salvar() {
		return noticia_salvar;
	}
	public void setNoticia_salvar(String noticia_salvar) {
		this.noticia_salvar = noticia_salvar;
	}
	public String getNoticia_listar() {
		return noticia_listar;
	}
	public void setNoticia_listar(String noticia_listar) {
		this.noticia_listar = noticia_listar;
	}
	public String getNoticia_deletar() {
		return noticia_deletar;
	}
	public void setNoticia_deletar(String noticia_deletar) {
		this.noticia_deletar = noticia_deletar;
	}
	public String getToken_solicitar() {
		return token_solicitar;
	}
	public void setToken_solicitar(String token_solicitar) {
		this.token_solicitar = token_solicitar;
	}
	
}
