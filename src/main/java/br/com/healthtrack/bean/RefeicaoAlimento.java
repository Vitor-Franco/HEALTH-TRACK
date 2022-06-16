package br.com.healthtrack.bean;

public class RefeicaoAlimento {
	private int id;

	private Refeicao refeicao;

	private Alimento alimento;

	private int quantidade;

	private String medida;

	public RefeicaoAlimento() {
		super();
	}

	public RefeicaoAlimento(int id, int quantidade, String medida) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.medida = medida;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Refeicao getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(Refeicao refeicao) {
		this.refeicao = refeicao;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	@Override
	public String toString() {

		return "Refeição Alimento: [código = " + id + ", quantidade = " + quantidade + ", medida: " + medida + ", Refeicao: "
				+ refeicao.getNomeRefeicao() + ", Alimento = " +  alimento.getNomeAlimento()  + "]";
	}
}
