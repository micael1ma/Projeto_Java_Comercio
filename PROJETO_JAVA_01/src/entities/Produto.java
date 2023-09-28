package entities;

public class Produto {

	private int codProduto;
	private String nomeProduto;
	private String categoria;
	private float custoDeCompra;
	private float valorDeVenda;
	private int quantProduto;

	public Produto() {
	}

	public void setQuantProduto(int quantProduto) {
		this.quantProduto = quantProduto;
	}

	public Produto(int codProduto, String nomeProduto, String categoria, float custoDeCompra, float valorDeVenda,
			int quantProduto) {
		super();
		this.codProduto = codProduto;
		this.nomeProduto = nomeProduto;
		this.categoria = categoria;
		this.custoDeCompra = custoDeCompra;
		this.valorDeVenda = valorDeVenda;
		this.quantProduto = quantProduto;
	}

	public Produto(int codProduto, String nomeProduto, String categoria, float custoDeCompra, float valorDeVenda) {
		this.codProduto = codProduto;
		this.nomeProduto = nomeProduto;
		this.categoria = categoria;
		this.custoDeCompra = custoDeCompra;
		this.valorDeVenda = valorDeVenda;

	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public int getCodProduto() {
		return codProduto;
	}

	public int getQuantProduto() {
		return quantProduto;
	}

	public String getCategoria() {
		return categoria;
	}

	public float getCustoDeCompra() {
		return custoDeCompra;
	}

	public float getValorDeVenda() {
		return valorDeVenda;
	}

	public String toFileString() {
		return codProduto + "," + nomeProduto + "," + categoria + "," + custoDeCompra + "," + valorDeVenda + ","
				+ quantProduto;
	}

	public static Produto fromFileString(String linha) {

		String[] partes = linha.split(",");

		int codProduto = Integer.parseInt(partes[0]);
		String nomeProduto = partes[1];
		String categoria = partes[2];
		float custoDeCompra = Float.parseFloat(partes[3]);
		float valorDeVenda = Float.parseFloat(partes[4]);
		int quantProduto = Integer.parseInt(partes[5]);

		return new Produto(codProduto, nomeProduto, categoria, custoDeCompra, valorDeVenda, quantProduto);
	}

}
