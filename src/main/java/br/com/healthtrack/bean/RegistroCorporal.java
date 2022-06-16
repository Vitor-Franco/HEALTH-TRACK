package br.com.healthtrack.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegistroCorporal {

	private int id;

	private double peso;

	private Calendar data;

	private Usuario usuario;

	public RegistroCorporal(int id, double peso, Calendar data) {
		this.id = id;
		this.peso = peso;
		this.data = data;
	}

	public RegistroCorporal() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public void setUsuario(Usuario u) {
		this.usuario = u;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return "Registro Corporal: [código = " + id + ", peso = " + peso + ", data registro = "
				+ sdf.format(data.getTime()) + ", Nome Usuário: " + usuario.getNome() + "]";
	}
}
