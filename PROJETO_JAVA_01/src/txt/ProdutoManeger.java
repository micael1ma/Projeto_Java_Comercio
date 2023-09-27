package txt;

import entities.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoManeger {

	public void salvarProdutos(List<Produto> produtos, String caminhoArquivoProduto) {
		try {
			FileWriter fileWriter = new FileWriter(caminhoArquivoProduto);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (Produto produto : produtos) {
				bufferedWriter.write(produto.toFileString());
				bufferedWriter.newLine();
			}

			bufferedWriter.close();
			System.out.println("Produtos salvos com sucesso!");
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
			System.out.println("Produtos carregados com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return produtos;
	}
}