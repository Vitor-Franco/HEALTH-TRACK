package br.com.healthtrack.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.healthtrack.util.CriptografiaUtils;

public class Usuario {
	private int id;

	private String nome;

	private Calendar dataNascimento;

	private char sexo;

	private double altura;

	private int idade;

	private String email;

	private String password;

	public Usuario(int id, String nome, Calendar dataNascimento, char sexo, double altura, int idade, String email,
			String password) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.altura = altura;
		this.idade = idade;
		this.email = email;
		setPassword(password);
	}
	
	public Usuario(String email, String password) {
		super();
		this.email = email;
		setPassword(password);
	}

	public Usuario() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		try {
			this.password = CriptografiaUtils.criptografar(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return "Usuário: [código = " + id + ", nome = " + nome + ", sexo = " + sexo + ", altura = " + altura
				+ ", idade = " + idade + ", email = " + email + ", data Nascimento = "
				+ sdf.format(dataNascimento.getTime()) + "]";
	}
}
