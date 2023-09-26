package Apllication;

import java.util.ArrayList;
import java.util.Scanner;

import entities.Produto;
import management.Gerenciador;
import txt.Saldo;

public class Main {

	public static void main(String[] args) {

		ArrayList<Produto> listaProdutos = new ArrayList<>();

		Scanner sc = new Scanner(System.in);
		Gerenciador G = new Gerenciador();
		Saldo Saldo = new Saldo();

		String caminhoArquivoSaldo = "C:\\Users\\micae\\OneDrive\\Área de Trabalho\\SALDO.txt";
		int menu = 0;

		float saldo = Saldo.puxar(caminhoArquivoSaldo);
		System.out.println(saldo);

		while (menu != 7) {
			System.out.println("_____________________________________________");
			System.out.println("Bem vindo ao menu");
			System.out.println();
			System.out.println("1) Listar todos os produtos");
			System.out.println("2) Cadastrar um novo produto");
			System.out.println("3) Adicionar estoque de produtos");
			System.out.println("4) Remover um produto do comércio");
			System.out.println("5) Vender algum produto existente");
			System.out.println("6) Relatório de compra e vendas");
			System.out.println("7) Sair do programa");
			System.out.println();
			System.out.println("Digite a opção desejada: ");
			System.out.println("_____________________________________________");
			menu = sc.nextInt();

			if (menu == 1) { // LISTAR PRODUTOS
				G.listarProdutos(listaProdutos); // LISTA PRODUTOS
			}

			else if (menu == 2) { // ADICIONAR NOVO PRODUTO

				System.out.println("Digite o produto: ");
				String produto = sc.next();

				System.out.println("Digite a categoria do produto: ");
				String categoria = sc.next();

				System.out.println("Digite custo do produto: ");
				float custoDeCompra = sc.nextFloat();

				System.out.println("Digite valor de venda do produto: ");
				float valorDeVenda = sc.nextFloat();

				System.out.println("Digite o codigo do produto");
				int codigo = sc.nextInt();
				sc.nextLine();

				for (Produto produto5 : listaProdutos) {

					while (produto5.getCodProduto() == codigo) { // TRATAMENTO CODIGO REPETIDO
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
					quantidade = G.obeterNumeroPositivo(sc, 0);

					Produto novoProduto = new Produto(produto, codigo, quantidade, categoria, custoDeCompra,
							valorDeVenda);
					listaProdutos.add(novoProduto);

				} else {
					Produto novoProduto = new Produto(produto, codigo, 0, categoria, custoDeCompra, valorDeVenda);
					listaProdutos.add(novoProduto);
				}
			}

			else if (menu == 3) { // ADICIONAR ESTOQUE

				if (G.listaProdutosVazia(listaProdutos)) { // TRATAMENTO CASO LISTA ESTEJA VAZIA
				} else {

					G.listarProdutos(listaProdutos); // LISTA PRODUTOS CADASTRADOS

					System.out.println();
					System.out.println("Digite o código do produto:");
					int cod = G.obterCodigoProdutoValido(listaProdutos, sc); // TRATAMENTO PARA OBETER CODIGO EXISTENTE
																				// NA LISTA
					for (Produto produto : listaProdutos) {
						if (produto.getCodProduto() == cod) {

							System.out.println("Digite a quantidade a ser adicionada do sistema:");

							int addQuant = G.obeterNumeroPositivo(sc, 1); // OBTER NUMERO VALIDO
							int oldQuant = produto.getQuantProduto(); // ACESSAR QUANTIDADE EXISTENTE
							int newQuant = oldQuant + addQuant; // SOMAR QUANTIDADE EXISTENTE E QUANTIDADE FORNECIDA

							float valorDaCompra = produto.getCustoDeCompra() * addQuant;
							float saldoCheck = saldo - valorDaCompra;

							if (saldoCheck >= 0) {
								saldo = saldo - valorDaCompra;
								String nameP = produto.getNomeProduto(); // BLOCO PARA ATUALIZAR A QUANTIDADE
								System.out.println("Unidades de " + nameP + " foi atualizado de " + oldQuant + " para "
										+ newQuant);
								produto.setQuantProduto(newQuant);

								break;
							} else {
								System.out.println("Saldo insuficente para compra.");
							}
						}

					}
				}

			} else if (menu == 4) { // REMOVER PRODUTO

				if (G.listaProdutosVazia(listaProdutos)) { // TRATAMENTO CASO LISTA ESTEJA VAZIA
				} else {

					G.listarProdutos(listaProdutos); // LISTA PRODUTOS CADASTRADOS

					System.out.println("Digite o codiogo do produto a ser removido:");
					int codigoRemover = G.obterCodigoProdutoValido(listaProdutos, sc); // TRATAMENTO PARA OBTER CODIGO V
					sc.nextLine();

					for (Produto produto : listaProdutos) { // CONFIRMAÇAO PARA DELETAR CODIGO
						if (produto.getCodProduto() == codigoRemover) {

							String produtoNome = produto.getNomeProduto(); // OBTER NOME DO PRODUTO

							if (produto.getQuantProduto() > 0) { // CHECAR SE A ESTOQUE
								System.out.println(
										"Tem certeza que deseja excluir " + produtoNome + " do sistema? (y/n) ");
								String choice = sc.nextLine();

								if (choice.equals("y")) { // REMOVER CASO HAJA ESTOQUE
									listaProdutos.remove(produto);
									System.out.println(produtoNome + " foi removido do sistema com sucesso.");

								} else {
									System.out.println("Operação cancelada.");
								}
							} else {
								listaProdutos.remove(produto); // REMOVER CASO NAO HAJA ESTOQUE
								System.out.println(produtoNome + " foi removido do sistema com sucesso.");

							}
						}

					}
				}
			}

			else if (menu == 5) { // VENDER ESTOQUE //CONSERTA CASO ESTOQUE SEJA 0 // TRATAMENTO PARA ENTRADA

				if (G.listaProdutosVazia(listaProdutos)) {
				} else {

					G.listarProdutos(listaProdutos);

					System.out.println("Digite o código do produto a ser vendido:");
					int cod = G.obterCodigoProdutoValido(listaProdutos, sc);

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
			} else if (menu == 6) {

				System.out.println(saldo);

			}
		}

		Saldo.salvar(saldo, caminhoArquivoSaldo);
		sc.close();

		System.out.println("Sistema encerrado.");
	}
}