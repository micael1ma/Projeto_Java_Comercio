package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Compra extends Produto {

	private float valorDeCompraTotal;
	private int quantidadeComprada;

	public Compra() {
	}

	public Compra(int codProduto, String nomeProduto, int quantidadeComprada, float valorDeCompraTotal) {

		super(codProduto, nomeProduto, "", 0.0f, 0.0f);

		this.valorDeCompraTotal = valorDeCompraTotal;
		this.quantidadeComprada = quantidadeComprada;
	}

	public float getValorDeCompraTotal() {
		return valorDeCompraTotal;
	}

	public int getQuantidadeComprada() {
		return quantidadeComprada;
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

//
//	
//
	public String toFileStringVenda() {
		return getCodProduto() + "," + getNomeProduto() + "," + quantidadeComprada + "," + valorDeCompraTotal;
	}

	public void salvarProdutosComprados(List<Compra> listaProdutosComprado, String caminhoArquivoVenda) {
		try {
			FileWriter fileWriter = new FileWriter(caminhoArquivoVenda);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (Compra Compra : listaProdutosComprado) {
				bufferedWriter.write(Compra.toFileStringVenda());
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
	public static Compra fromFileStringCompra(String linha) {

		String[] partes = linha.split(",");

		int codProduto = Integer.parseInt(partes[0]);
		String nomeProduto = partes[1];
		int quantidadeComprada = Integer.parseInt(partes[2]);
		float valorDeCompraTotal = Float.parseFloat(partes[3]);

		return new Compra(codProduto, nomeProduto, quantidadeComprada, valorDeCompraTotal);
	}

	public List<Compra> carregarProdutosComprados(String caminhoArquivoVenda) {
		List<Compra> compras = new ArrayList<>();

		try {
			FileReader fileReader = new FileReader(caminhoArquivoVenda);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String linha;
			while ((linha = bufferedReader.readLine()) != null) {
				Compra compra = entities.Compra.fromFileStringCompra(linha);
				compras.add(compra);
			}

			bufferedReader.close();
			System.out.println("Produtos carregados com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return compras;
	}

}