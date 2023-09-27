package modelo;

public class TipoVeiculo {

	private String nome;
	private int id;

	public TipoVeiculo(String nome) {
		super();
		this.nome = nome;
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
	
	@Override
	public String toString() {
		return "TipoVeiculo: nome=" + nome;
	}
	
}
