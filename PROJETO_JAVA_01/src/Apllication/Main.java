package Apllication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.*;

import management.Gerenciador;
import txt.*;

public class Main {

	public static void main(String[] args) {

		String caminhoArquivoProduto = "C:\\Users\\micae\\OneDrive\\Área de Trabalho\\PRODUTO.txt";
		String caminhoArquivoSaldo = "C:\\Users\\micae\\OneDrive\\Área de Trabalho\\SALDO.txt";
		String caminhoArquivoCompra = "C:\\Users\\micae\\OneDrive\\Área de Trabalho\\COMPRA.txt";
		String caminhoArquivoVenda = "C:\\Users\\micae\\OneDrive\\Área de Trabalho\\VENDA.txt";

		Scanner sc = new Scanner(System.in);

		ProdutoManeger PM = new ProdutoManeger();
		Gerenciador Gerenciador = new Gerenciador();

		Saldo Saldo = new Saldo();
		Venda Venda = new Venda();
		Compra Compra = new Compra();

		float saldo = Saldo.puxar(caminhoArquivoSaldo);

		float saldoSessaoComprado = 0;
		float saldoSessaoVendido = 0;

		List<Produto> listaProdutos = PM.carregarProdutos(caminhoArquivoProduto);

		List<Venda> listaTodosProdutosVendidos = Venda.carregarProdutosVendidos(caminhoArquivoVenda);
		List<Venda> listaProdutosVendidoSessao = new ArrayList<>();

		List<Compra> listaTodosProdutosComprados = Compra.carregarProdutosComprados(caminhoArquivoCompra);
		List<Compra> listaProdutosCompradoSessao = new ArrayList<>();

		int menu = 0;
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
				Gerenciador.listarProdutos(listaProdutos); // LISTA PRODUTOS
			}

			else if (menu == 2) { // ADICIONAR NOVO PRODUTO

				System.out.println("Digite o codigo do produto");
				int codigo = sc.nextInt();

				System.out.println("Digite o produto: ");
				String produto = sc.next();

				System.out.println("Digite a categoria do produto: ");
				String categoria = sc.next();

				System.out.println("Digite custo do produto: ");
				float custoDeCompra = sc.nextFloat();

				System.out.println("Digite valor de venda do produto: ");
				float valorDeVenda = sc.nextFloat();
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
				int quantidade = 0;
				if (choice.equals("y")) {

					System.out.println("Digite a quantidade do produto"); // TRATAMENTO PARA OBETER NUMERO POSITIVO (+)
					quantidade = Gerenciador.obeterNumeroPositivo(sc, 0);

					Produto novoProduto = new Produto(codigo, produto, categoria, custoDeCompra, valorDeVenda,
							quantidade);
					listaProdutos.add(novoProduto);
					System.out.println(
							produto + " cadastrado com sucesso. Código: " + codigo + ", Estoque: " + quantidade + ".");

				} else {
					Produto novoProduto = new Produto(codigo, produto, categoria, custoDeCompra, valorDeVenda,
							quantidade);
					listaProdutos.add(novoProduto);
					System.out.println(produto + " cadastrado com sucesso. Código: " + codigo + ", Estoque: 0");
				}
			}

			else if (menu == 3) { // ADICIONAR ESTOQUE

				if (Gerenciador.listaProdutosVazia(listaProdutos)) { // TRATAMENTO CASO LISTA ESTEJA VAZIA
				} else {

					Gerenciador.listarProdutos(listaProdutos); // LISTA PRODUTOS CADASTRADOS

					System.out.println();
					System.out.println("Digite o código do produto:");
					int cod = Gerenciador.obterCodigoProdutoValido(listaProdutos, sc); // TRATAMENTO PARA OBETER CODIGO
																						// EXISTENTE
					// NA LISTA
					for (Produto produto : listaProdutos) {
						if (produto.getCodProduto() == cod) {

							System.out.println("Digite a quantidade a ser adicionada do sistema:");

							int addQuant = Gerenciador.obeterNumeroPositivo(sc, 1); // OBTER NUMERO VALIDO
							int oldQuant = produto.getQuantProduto(); // ACESSAR QUANTIDADE EXISTENTE
							int newQuant = oldQuant + addQuant; // SOMAR QUANTIDADE EXISTENTE E QUANTIDADE FORNECIDA

							float valorTotalDaCompra = produto.getCustoDeCompra() * addQuant;
							float saldoCheck = saldo - valorTotalDaCompra;

							if (saldoCheck >= 0) {

								String nameP = produto.getNomeProduto(); // BLOCO PARA ATUALIZAR A QUANTIDADE
								System.out.println("Unidades de " + nameP + " foi atualizado de " + oldQuant + " para "
										+ newQuant);
								produto.setQuantProduto(newQuant);

								saldoSessaoComprado = saldoSessaoComprado + valorTotalDaCompra;
								saldo = saldo - valorTotalDaCompra;

								Compra NovaCompra = new Compra(cod, nameP, addQuant, valorTotalDaCompra);
								listaProdutosCompradoSessao.add(NovaCompra);
								listaTodosProdutosComprados.add(NovaCompra);

								break;
							} else {
								System.out.println("Saldo insuficente para compra.");
							}
						}

					}
				}

			} else if (menu == 4) { // REMOVER PRODUTO

				if (Gerenciador.listaProdutosVazia(listaProdutos)) { // TRATAMENTO CASO LISTA ESTEJA VAZIA
				} else {

					Gerenciador.listarProdutos(listaProdutos); // LISTA PRODUTOS CADASTRADOS

					System.out.println("Digite o codiogo do produto a ser removido:");
					int codigoRemover = Gerenciador.obterCodigoProdutoValido(listaProdutos, sc); // TRATAMENTO PARA
																									// OBTER CODIGO V
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

				if (Gerenciador.listaProdutosVazia(listaProdutos)) {
				} else {

					Gerenciador.listarProdutos(listaProdutos);

					System.out.println("Digite o código do produto a ser vendido:");
					int cod = Gerenciador.obterCodigoProdutoValido(listaProdutos, sc);

					for (Produto produto : listaProdutos) {

						if (produto.getCodProduto() == cod) {
							int newQuant = -1;

							System.out.println("Digite a quantidade a de produtos:");

							while (newQuant < 0) { // LOOP PARA CASO newQuant VENHA SER (-) NEGATIVO
								int addQuant = Gerenciador.obeterNumeroPositivo(sc, 1);

								int oldQuant = produto.getQuantProduto(); // ACESSAR QUANTIDADE EXISTENTE
								newQuant = oldQuant - addQuant; // SOMAR QUANTIDADE EXISTENTE E QUANTIDADE FORNECIDA

								if (newQuant < 0) { // FEEDBAACK CASO newQuant SEJA NEGATIVO
									System.out.println("Estoque indisponivel, tente novamente:");

								} else { // BLOCO PARA ATUALIZAR A QUANTIDADE
									String nameP = produto.getNomeProduto();
									System.out.println("Unidades de " + nameP + " foi atualizada de " + oldQuant
											+ " para " + newQuant);
									produto.setQuantProduto(newQuant);

									float ValordeVenda = produto.getValorDeVenda();
									float ValorTotalDeVendaTotal = addQuant * ValordeVenda;

									saldoSessaoVendido = saldoSessaoVendido + ValorTotalDeVendaTotal;
									saldo = saldo + ValorTotalDeVendaTotal;

									Venda novaVenda = new Venda(cod, nameP, addQuant, ValorTotalDeVendaTotal);
									listaProdutosVendidoSessao.add(novaVenda);
									listaTodosProdutosVendidos.add(novaVenda);

								}
							}
							break;
						}

					}
				}
			} else if (menu == 6) {
				int opcao = 0;
				System.out.println("1) Relatorio da sessão");
				System.out.println("2) Relatorio completo");
				System.out.println("");
				System.out.println("Digite a opção desejada:");
				opcao = sc.nextInt();

				if (opcao == 1) {
					System.out.println("           Relatorio da sessão");
					System.out.println("_____________________________________________");
					System.out.println("");
					System.out.println("Saldo R$: " + saldo);
					System.out.println("Saldo arrecadado R$:" + saldoSessaoVendido);
					System.out.println("Saldo gasto R$:" + saldoSessaoComprado);
					System.out.println("_____________________________________________");
					System.out.println("           Relatorio de vendas");
					System.out.println("");
					Venda.listarProdutosVendidos(listaProdutosVendidoSessao);
					System.out.println("_____________________________________________");
					System.out.println("           Relatorio de compras");
					System.out.println("");
					Compra.listarProdutosComprados(listaProdutosCompradoSessao);

				} else if (opcao == 2) {
					System.out.println("           Relatorio da sessão");
					System.out.println("_____________________________________________");
					System.out.println("");
					System.out.println("Saldo R$: " + saldo);
					System.out.println("Saldo arrecadado R$:" + saldoSessaoVendido);
					System.out.println("Saldo gasto R$:" + saldoSessaoComprado);
					System.out.println("_____________________________________________");
					System.out.println("           Relatorio de vendas");
					System.out.println("");
					Venda.listarProdutosVendidos(listaTodosProdutosVendidos);
					System.out.println("_____________________________________________");
					System.out.println("           Relatorio de compras");
					System.out.println("");
					Compra.listarProdutosComprados(listaTodosProdutosComprados);

				}
			}
		}
		Compra.salvarProdutosComprados(listaTodosProdutosComprados, caminhoArquivoCompra);
		Venda.salvarProdutosVendidos(listaTodosProdutosVendidos, caminhoArquivoVenda);

		PM.salvarProdutos(listaProdutos, caminhoArquivoProduto);
		Saldo.salvar(saldo, caminhoArquivoSaldo);
		sc.close();

		System.out.println("Sistema encerrado.");
	}
}