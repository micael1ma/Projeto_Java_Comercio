package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Venda extends Produto {

	private float valorDeVendaTotal;
	private int quantidadeVendida;

	public Venda() {
	}

	public Venda(int codProduto, String nomeProduto, int quantidadeVendida, float valorDeVendaTotal) {

		super(codProduto, nomeProduto, "", 0.0f, 0.0f);

		this.valorDeVendaTotal = valorDeVendaTotal;
		this.quantidadeVendida = quantidadeVendida;
	}

	public float getvalorDeVendaTotal() {
		return valorDeVendaTotal;
	}

	public int getQuantidadeVendida() {
		return quantidadeVendida;
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

//
//	
//
	public String toFileStringVenda() {
		return getCodProduto() + "," + getNomeProduto() + "," + quantidadeVendida + "," + valorDeVendaTotal;
	}

	public void salvarProdutosVendidos(List<Venda> listaProdutosVendidos, String caminhoArquivoVenda) {
		try {
			FileWriter fileWriter = new FileWriter(caminhoArquivoVenda);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (Venda Venda : listaProdutosVendidos) {
				bufferedWriter.write(Venda.toFileStringVenda());
				bufferedWriter.newLine();
			}

			bufferedWriter.close();
			System.out.println("Produtos salvos com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//
//	
//	
	public static Venda fromFileStringVenda(String linha) {

		String[] partes = linha.split(",");

		int codProduto = Integer.parseInt(partes[0]);
		String nomeProduto = partes[1];
		int quantidadeVendida = Integer.parseInt(partes[2]);
		float valorDeVendaTotal = Float.parseFloat(partes[3]);

		return new Venda(codProduto, nomeProduto, quantidadeVendida, valorDeVendaTotal);
	}

	public List<Venda> carregarProdutosVendidos(String caminhoArquivoVenda) {
		List<Venda> vendas = new ArrayList<>();

		try {
			FileReader fileReader = new FileReader(caminhoArquivoVenda);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String linha;
			while ((linha = bufferedReader.readLine()) != null) {
				Venda venda = entities.Venda.fromFileStringVenda(linha);
				vendas.add(venda);
			}

			bufferedReader.close();
			System.out.println("Produtos carregados com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return vendas;
	}

}
