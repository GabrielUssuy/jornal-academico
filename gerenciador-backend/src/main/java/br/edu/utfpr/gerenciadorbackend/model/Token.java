package br.edu.utfpr.gerenciadorbackend.model;

import java.io.Serializable;
import java.util.List;

public class Token implements Serializable {

	private static final long serialVersionUID = 4483751249391797042L;
	
	private String exp;
	private String user_name;
	private List<String> authorities;
	private String jti;
	private String client_id;
	private List<String> scope;
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public List<String> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	public String getJti() {
		return jti;
	}
	public void setJti(String jti) {
		this.jti = jti;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public List<String> getScope() {
		return scope;
	}
	public void setScope(List<String> scope) {
		this.scope = scope;
	}

	
	
}
