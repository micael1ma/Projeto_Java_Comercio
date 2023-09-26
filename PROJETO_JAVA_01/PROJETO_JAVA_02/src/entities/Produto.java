package entities;

public class Produto {

	private String nomeProduto;
	private int codProduto;
	private int quantProduto;
	private String categoria;
	private float custoDeCompra;
	private float valorDeVenda;

	public Produto(String nomeProduto, int codProduto, int quantProduto, String categoria, float custoDeCompra,
			float valorDeVenda) {
		super();
		this.nomeProduto = nomeProduto;
		this.codProduto = codProduto;
		this.quantProduto = quantProduto;
		this.categoria = categoria;
		this.custoDeCompra = custoDeCompra;
		this.valorDeVenda = valorDeVenda;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public float getCustoDeCompra() {
		return custoDeCompra;
	}

	public void setCustoDeCompra(float custoDeCompra) {
		this.custoDeCompra = custoDeCompra;
	}

	public float getValorDeVenda() {
		return valorDeVenda;
	}

	public void setValorDeVenda(float valorDeVenda) {
		this.valorDeVenda = valorDeVenda;
	}

}
