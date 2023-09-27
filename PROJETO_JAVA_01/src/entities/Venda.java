package entities;

import java.util.List;

public class Venda extends Produto {

	private float valorDeVendaTotal;
	private int quantidadeVendida;

	public Venda(int codProduto, String nomeProduto, String categoria, float custoDeCompra, float valorDeVenda,
			int quantProduto, float valorDeVendaTotal, int quantidadeVendida) {

		super(codProduto, nomeProduto, categoria, custoDeCompra, valorDeVenda, quantProduto);

		this.valorDeVendaTotal = valorDeVendaTotal;
		this.quantidadeVendida = quantidadeVendida;
	}

	public Venda(int codProduto, String nomeProduto, int quantidadeVendida, float valorDeVendaTotal) {

		super(codProduto, nomeProduto, "", 0.0f, 0.0f);

		this.valorDeVendaTotal = valorDeVendaTotal;
		this.quantidadeVendida = quantidadeVendida;
	}

	public Venda() {
	}

	public float getvalorDeVendaTotal() {
		return valorDeVendaTotal;
	}

	public void setvalorDeVendaTotal(float valorDeVenda) {
		this.valorDeVendaTotal = valorDeVenda;
	}

	public int getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public void setQuantidadeVendida(int quantidadeVendida) {
		this.quantidadeVendida = quantidadeVendida;
	}

	public void listarProdutosVendidos(List<Venda> listaProdutosVendido) {

		if (listaProdutosVendido.isEmpty()) {
			System.out.println("Não houve vendas na sessão.");
		}

		else {
			int N = 0;
			for (Venda Venda : listaProdutosVendido) {
				N = N + 1;
				System.out.printf("%d) %s (cód.: %d | Quantidade vendida: %d | Valor Vendido: %.2f)%n", N,
						Venda.getNomeProduto(), Venda.getCodProduto(), Venda.getQuantidadeVendida(),
						Venda.getvalorDeVendaTotal());

			}
		}
	}
}
