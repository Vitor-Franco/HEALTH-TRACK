package br.com.healthtrack.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AtividadeFisica {
	private int id;

	private Usuario usuario;

	private String tipoAtividade;

	private Calendar data;

	private Calendar duracao;

	private String nome;

	private int calorias;

	private String descricao;

	public AtividadeFisica(int id, String tipoAtividade, Calendar data, Calendar duracao, String nome, int calorias,
			String descricao) {
		super();
		this.id = id;
		this.tipoAtividade = tipoAtividade;
		this.data = data;
		this.duracao = duracao;
		this.nome = nome;
		this.calorias = calorias;
		this.descricao = descricao;
	}

	public AtividadeFisica() {
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

	public String getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(String tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Calendar getDuracao() {
		return duracao;
	}

	public void setDuracao(Calendar duracao) {
		this.duracao = duracao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCalorias() {
		return calorias;
	}

	public void setCalorias(int calorias) {
		this.calorias = calorias;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return "Atividade física: [código = " + id + ", exercício = " + nome + ", data = " + sdf.format(data.getTime())
				+ ", Calorias: " + calorias + ", descrição: " + descricao + ", duração: " + sdf.format(duracao.getTime())
				+ ", tipo de atividade: " + tipoAtividade + ", Nome Usuário: " + usuario.getNome() + "]";

	}
}
