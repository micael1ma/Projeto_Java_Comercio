package entities;

public class Produto {

	private String nomeProduto;
	private int codProduto;
	private int quantProduto;

	public Produto(String nomeProduto, int codProduto, int quantProduto) {
		super();
		this.nomeProduto = nomeProduto;
		this.codProduto = codProduto;
		this.quantProduto = quantProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public int getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}

	public int getQuantProduto() {
		return quantProduto;
	}

	public void setQuantProduto(int quantProduto) {
		this.quantProduto = quantProduto;
	}

	public void imprimirProduto() {
		System.out.println("Nome do Produto: " + nomeProduto);
		System.out.println("Código do Produto: " + codProduto);
		System.out.println("Quantidade do Produto: " + quantProduto);
	}

}
