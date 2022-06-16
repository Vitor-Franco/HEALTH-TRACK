package br.com.healthtrack.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class IngestaoAgua {
	private int id;

	private Usuario usuario;

	private Calendar data;

	private double quantidadeAgua;

	public IngestaoAgua(int id, Calendar data, double quantidadeAgua) {
		super();
		this.id = id;
		this.data = data;
		this.quantidadeAgua = quantidadeAgua;
	}

	public IngestaoAgua() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public double getQuantidadeAgua() {
		return quantidadeAgua;
	}

	public void setQuantidadeAgua(double quantidadeAgua) {
		this.quantidadeAgua = quantidadeAgua;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return "Ingestão água: [código = " + id + ", qtdÁgua = " + quantidadeAgua + ", data = "
				+ sdf.format(data.getTime()) + ", Nome Usuário: " + usuario.getNome() + "]";
	}

}
