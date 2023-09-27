package entities;

import java.util.List;

public class Compra extends Produto {

	private float valorDeCompraTotal;
	private int quantidadeComprada;

	public Compra(int codProduto, String nomeProduto, int quantidadeComprada, float valorDeCompraTotal) {

		super(codProduto, nomeProduto, "", 0.0f, 0.0f);

		this.valorDeCompraTotal = valorDeCompraTotal;
		this.quantidadeComprada = quantidadeComprada;
	}

	public Compra() {
	}

	public float getValorDeCompraTotal() {
		return valorDeCompraTotal;
	}

	public void setValorDeCompraTotal(float valorDeCompraTotal) {
		this.valorDeCompraTotal = valorDeCompraTotal;
	}

	public int getQuantidadeComprada() {
		return quantidadeComprada;
	}
  
	
	
	public void setQuantidadeComprada(int quantidadeComprada) {
		this.quantidadeComprada = quantidadeComprada;
	}
	
	public void listarProdutosComprados(List<Compra> listaProdutosComprado) {

		if (listaProdutosComprado.isEmpty()) {
			System.out.println("Não houve compras na sessão.");
		}

		else {
			int N = 0;
			for (Compra Compra : listaProdutosComprado) {
				N = N + 1;
				System.out.printf("%d) %s (cód.: %d | Quantidade comprada: %d | Valor da compra: %.2f)%n", N,
						Compra.getNomeProduto(), Compra.getCodProduto(), Compra.getQuantidadeComprada(),
						Compra.getValorDeCompraTotal());

			}
		}
	}

}