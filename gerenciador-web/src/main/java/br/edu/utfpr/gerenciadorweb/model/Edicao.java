package br.edu.utfpr.gerenciadorweb.model;

import java.io.Serializable;

public class Edicao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer ano;
	private Integer mes;
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
