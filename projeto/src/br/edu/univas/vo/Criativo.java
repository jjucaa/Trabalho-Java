package br.edu.univas.vo;

public class Criativo {
	
	private String name;
	private float valor;
	private String data;
	private float UPvalor;
	private String UPnome;
	private String UPdata;
	private String login;
	private String senha;
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public float getUPvalor() {
		return UPvalor;
	}

	public void setUPvalor(float uPvalor) {
		UPvalor = uPvalor;
	}

	public String getUPnome() {
		return UPnome;
	}

	public void setUPnome(String uPnome) {
		UPnome = uPnome;
	}

	public String getUPdata() {
		return UPdata;
	}

	public void setUPdata(String uPdata) {
		UPdata = uPdata;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public float getValor() {
		return valor;
	}
	
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
}
