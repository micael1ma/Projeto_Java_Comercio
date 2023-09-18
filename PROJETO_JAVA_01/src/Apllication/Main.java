package Apllication;

import java.util.ArrayList;
import java.util.Scanner;
import management.*;
import entities.*;

public class Main {

	public static void main(String[] args) {

		ArrayList<Produto> listaProdutos = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		Gerenciador g = new Gerenciador();
		int menu = 0;

		while (menu != 6) {
			System.out.println("_____________________________________________");
			System.out.println("Bem vindo ao menu");
			System.out.println();
			System.out.println("1) Listar todos os produtos");
			System.out.println("2) Cadastrar um novo produto");
			System.out.println("3) Editar estoque de produtos");
			System.out.println("4) Remover um produto do comércio");
			System.out.println("5) Vender algum produto existente");
			System.out.println("6) Sair do programa");
			System.out.println();
			System.out.println("Digite a opção desejada: ");
			System.out.println("_____________________________________________");
			menu = sc.nextInt();

			if (menu == 1) {
				g.listarProdutos(listaProdutos);
			}

			else if (menu == 2) {
				System.out.println("Digite o produto: ");
				String produto = sc.next();
				System.out.println("Digite o codigo do produto");
				int codigo = sc.nextInt();
				sc.nextLine();

				for (Produto produto5 : listaProdutos) { // TRATAMENTO CODIGO REPETIDO
					while (produto5.getCodProduto() == codigo) {
						System.out.println("Codigo de produto ja existe tente novamente:");
						codigo = sc.nextInt();
						sc.nextLine();
					}
				}

				System.out.println("Deseja adicionar estoque(y/n)");
				String choice = sc.nextLine();

				if (choice.equals("y")) {

					int quantidade = -1;
					System.out.println("Digite a quantidade do produto");

					while (quantidade < 0) {
						quantidade = sc.nextInt();
						if (quantidade < 0) {
							System.out.println("Quantidade negativa, tente novamente com um numero positvo.");
						}
					}
					Produto novoProduto = new Produto(produto, codigo, quantidade);
					listaProdutos.add(novoProduto);
				} else {
					Produto novoProduto = new Produto(produto, codigo, 0);
					listaProdutos.add(novoProduto);
				}

			}

			else if (menu == 3) { // EDITAR ESTOQUE

				if (g.listaProdutosVazia(listaProdutos)) {
				} else {

					g.listarProdutos(listaProdutos);

					System.out.println("Digite o código do produto:");
					int cod = g.obterCodigoProdutoValido(listaProdutos, sc);

					for (Produto produto : listaProdutos) {

						if (produto.getCodProduto() == cod) {
							int newQuant = -1;

							System.out.println("Digite a quantidade a ser adicionada/removida do sistema:");

							while (newQuant < 0) { // LOOP PARA CASO newQuant VENHA SER (-) NEGATIVO
								int addQuant = sc.nextInt();

								int oldQuant = produto.getQuantProduto(); // ACESSAR QUANTIDADE EXISTENTE
								newQuant = oldQuant + addQuant; // SOMAR QUANTIDADE EXISTENTE E QUANTIDADE FORNECIDA

								if (newQuant < 0) { // FEEDBAACK CASO newQuant SEJA NEGATIVO
									System.out.println("Quantidade de estoque negativa, tente novamente:");

								} else { // BLOCO PARA ATUALIZAR A QUANTIDADE
									String nameP = produto.getNomeProduto();
									System.out.println("Unidades de " + nameP + " foi atualizado de " + oldQuant
											+ " para " + newQuant);
									produto.setQuantProduto(newQuant);

								}
							}
							break;
						}

					}

				}
			} else if (menu == 4) { // REMOVER PRODUTO

				if (g.listaProdutosVazia(listaProdutos)) {
				} else {

					g.listarProdutos(listaProdutos);

					System.out.println("Digite o codiogo do produto a ser removido:");
					int codigoRemover = g.obterCodigoProdutoValido(listaProdutos, sc);

					sc.nextLine();

					for (Produto produto : listaProdutos) {
						if (produto.getCodProduto() == codigoRemover) {

							String produtoPrint = produto.getNomeProduto();
							System.out.println("Tem certeza que deseja excluir " + produtoPrint + "do sistema? ");
							String choice = sc.nextLine();

							if (choice.equals("y")) {
								if (produto.getCodProduto() == codigoRemover) {
									listaProdutos.remove(produto); // Remove o produto da lista
									System.out.println(produtoPrint + " foi removido do sistema com sucesso.");

								}
								break;
							} else {
								System.out.println("Operação cancelada.");
							}
						}
					}
				}
			}

			else if (menu == 5) { // VENDER ESTOQUE

				if (g.listaProdutosVazia(listaProdutos)) {
				} else {

					g.listarProdutos(listaProdutos);

					System.out.println("Digite o código do produto:");
					int cod = g.obterCodigoProdutoValido(listaProdutos, sc);

					for (Produto produto : listaProdutos) {

						if (produto.getCodProduto() == cod) {
							int newQuant = -1;

							System.out.println("Digite a quantidade a de produtos:");

							while (newQuant < 0) { // LOOP PARA CASO newQuant VENHA SER (-) NEGATIVO
								int addQuant = sc.nextInt();

								int oldQuant = produto.getQuantProduto(); // ACESSAR QUANTIDADE EXISTENTE
								newQuant = oldQuant - addQuant; // SOMAR QUANTIDADE EXISTENTE E QUANTIDADE FORNECIDA

								if (newQuant < 0) { // FEEDBAACK CASO newQuant SEJA NEGATIVO
									System.out.println("Estoque indisponivel, tente novamente:");

								} else { // BLOCO PARA ATUALIZAR A QUANTIDADE
									String nameP = produto.getNomeProduto();
									System.out.println("Unidades de " + nameP + " foi atualizada de " + oldQuant
											+ " para " + newQuant);
									produto.setQuantProduto(newQuant);

								}
							}
							break;
						}

					}
				}
			}
		}

		sc.close();
		System.out.println("Sistema encerrado.");
	}
}