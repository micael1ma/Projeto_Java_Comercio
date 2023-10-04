package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Produto {

	private int codProduto;
	private String nomeProduto;
	private String categoria;
	private float custoDeCompra;
	private float valorDeVenda;
	private int quantProduto;

	public Produto() {
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

	public void setQuantProduto(int quantProduto) {
		this.quantProduto = quantProduto;
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

	public void salvarProdutos(List<Produto> produtos, String caminhoArquivoProduto) {
		try {
			FileWriter fileWriter = new FileWriter(caminhoArquivoProduto);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (Produto produto : produtos) {
				bufferedWriter.write(produto.toFileString());
				bufferedWriter.newLine();
			}

			bufferedWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Produto> carregarProdutos(String caminhoArquivo) {
		List<Produto> produtos = new ArrayList<>();

		try {
			FileReader fileReader = new FileReader(caminhoArquivo);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String linha;
			while ((linha = bufferedReader.readLine()) != null) {
				Produto produto = entities.Produto.fromFileString(linha);
				produtos.add(produto);
			}

			bufferedReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return produtos;
	}

}
