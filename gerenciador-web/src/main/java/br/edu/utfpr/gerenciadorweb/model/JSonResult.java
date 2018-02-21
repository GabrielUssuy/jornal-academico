package br.edu.utfpr.gerenciadorweb.model;

import java.io.Serializable;

public class JSonResult implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer idGerado;
	private String mensagem;
	private Object resultObject;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdGerado() {
		return idGerado;
	}
	public void setIdGerado(Integer idGerado) {
		this.idGerado = idGerado;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Object getResultObject() {
		return resultObject;
	}
	public void setResultObject(Object resultObject) {
		this.resultObject = resultObject;
	}
	
	

}
