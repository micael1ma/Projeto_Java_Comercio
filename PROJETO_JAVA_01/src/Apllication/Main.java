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

			if (menu == 1) { // LISTAR PRODUTOS
				g.listarProdutos(listaProdutos); // LISTA PRODUTOS
			}

			else if (menu == 2) { // ADICIONAR NOVO PRODUTO

				System.out.println("Digite o produto: ");
				String produto = sc.next();

				System.out.println("Digite o codigo do produto");
				int codigo = sc.nextInt();
				sc.nextLine();

				for (Produto produto5 : listaProdutos) {

					while (produto5.getCodProduto() == codigo) { // TRATAMENTO CODIGO REPETIDO (USADO APENAS 1 VEZ EM
																	// TODO CODIGO)
						System.out.println("Codigo de produto ja existe tente novamente:");
						codigo = sc.nextInt();
						sc.nextLine();
					}
				}

				System.out.println("Deseja adicionar estoque(y/n)"); // OPÇÃO DE ADICIONAR ESTOQUE
				String choice = sc.nextLine();

				if (choice.equals("y")) {
					int quantidade = -1;

					System.out.println("Digite a quantidade do produto"); // TRATAMENTO PARA OBETER NUMERO POSITIVO (+)
					quantidade = g.obeterNumeroPositivo(sc, 0);

					Produto novoProduto = new Produto(produto, codigo, quantidade);
					listaProdutos.add(novoProduto);

				} else {
					Produto novoProduto = new Produto(produto, codigo, 0);
					listaProdutos.add(novoProduto);
				}
			}

			else if (menu == 3) { // EDITAR ESTOQUE

				if (g.listaProdutosVazia(listaProdutos)) { // TRATAMENTO CASO LISTA ESTEJA VAZIA
				} else {

					g.listarProdutos(listaProdutos); // LISTA PRODUTOS CADASTRADOS

					int cod = g.obterCodigoProdutoValido(listaProdutos, sc); // TRATAMENTO PARA OBETER CODIGO EXISTENTE
																				// NA LISTA

					System.out.println("Digite o código do produto:");
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

				if (g.listaProdutosVazia(listaProdutos)) { // TRATAMENTO CASO LISTA ESTEJA VAZIA
				} else {

					g.listarProdutos(listaProdutos); // LISTA PRODUTOS CADASTRADOS

					System.out.println("Digite o codiogo do produto a ser removido:");
					int codigoRemover = g.obterCodigoProdutoValido(listaProdutos, sc); // TRATAMENTO PARA OBETER CODIGO
																						// EXISTENTE NA LISTA
					sc.nextLine();

					for (Produto produto : listaProdutos) { // CONFIRMAÇAO PARA DELETAR CODIGO
						if (produto.getCodProduto() == codigoRemover) {
							String produtoPrint = produto.getNomeProduto();
							System.out.println("Tem certeza que deseja excluir " + produtoPrint + "do sistema? ");
							String choice = sc.nextLine();

							if (choice.equals("y")) { // REMOÇAO DO PRODUTO
								if (produto.getCodProduto() == codigoRemover) {
									listaProdutos.remove(produto);
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

			else if (menu == 5) { // VENDER ESTOQUE //CONSERTA CASO ESTOQUE SEJA 0 // TRATAMENTO PARA ENTRADA

				if (g.listaProdutosVazia(listaProdutos)) {
				} else {

					g.listarProdutos(listaProdutos);

					System.out.println("Digite o código do produto a ser vendido:");
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
			} else if (menu == 7) {
				System.out.println("digite:");
				int cod = 0;
				int newcode = g.obeterNumeroPositivo(sc, 0);
				cod = newcode;
				System.out.println(cod);

			}
		}

		sc.close();
		System.out.println("Sistema encerrado.");
	}
}