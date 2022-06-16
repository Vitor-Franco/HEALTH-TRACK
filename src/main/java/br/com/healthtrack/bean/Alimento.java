package br.com.healthtrack.bean;

public class Alimento {

	private int id;

	private String nomeAlimento;

	private int calorias;

	private String macroNutrientes;

	public Alimento() {
		super();
	}

	public Alimento(int id, String nome_alimento, int calorias, String macroNutrientes) {
		super();
		this.id = id;
		this.nomeAlimento = nome_alimento;
		this.calorias = calorias;
		this.macroNutrientes = macroNutrientes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeAlimento() {
		return nomeAlimento;
	}

	public void setNomeAlimento(String nome_alimento) {
		this.nomeAlimento = nome_alimento;
	}

	public int getCalorias() {
		return calorias;
	}

	public void setCalorias(int calorias) {
		this.calorias = calorias;
	}

	public String getMacroNutrientes() {
		return macroNutrientes;
	}

	public void setMacroNutrientes(String macroNutrientes) {
		this.macroNutrientes = macroNutrientes;
	}

	@Override
	public String toString() {
		return "Alimento: [código = " + id + ", calorias = " + calorias + ", nome alimento: " + nomeAlimento
				+ ", macro nutrientes = " + macroNutrientes + "]";
	}
}
