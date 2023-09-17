package management;

import java.util.ArrayList;
import java.util.Scanner;

import entities.Produto;

public class Gerenciador {

	public void listarProdutos(ArrayList<Produto> listaProdutos) {
		System.out.println("Lista de produtos cadastrados.");
		System.out.println();

		if (listaProdutos.isEmpty()) {
			System.out.println("Não existem produtos cadastrados.");
		}

		else {
			int N = 0;
			for (Produto produto : listaProdutos) {
				N = N + 1;
				System.out.printf("%d ) %s (cód.: %d | estoque: %d%n", N, produto.getNomeProduto(),
						produto.getCodProduto(), produto.getQuantProduto());
			}
		}
	}

	public boolean listaProdutosVazia(ArrayList<Produto> listaProdutos) {
		if (listaProdutos.isEmpty()) {
			System.out.println("Não há produtos cadastrados no sistema.");
			return true;
		}
		return false;
	}

	public int obterCodigoProdutoValido(ArrayList<Produto> listaProdutos, Scanner sc) {
		int cod = -1;

		
		while (true) {

			cod = sc.nextInt();

			boolean produtoEncontrado = false;

			for (Produto produto : listaProdutos) {
				if (produto.getCodProduto() == cod) {
					produtoEncontrado = true;
					break;
				}
			}

			if (produtoEncontrado) {
				break;
			} else {
				System.out.println("Produto não encontrado, tente novamente:");
			}
		}

		return cod;
	}

}
