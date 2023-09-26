package txt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Saldo {

	public void salvar(float saldo, String caminhoArquivoSaldo) {

		try {
			// Cria um objeto FileWriter para escrever no arquivo
			FileWriter fileWriter = new FileWriter(caminhoArquivoSaldo);

			// Cria um objeto BufferedWriter para escrever os dados de forma mais eficiente
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// Escreve o valor do saldo no arquivo
			bufferedWriter.write(Float.toString(saldo));

			// Fecha o BufferedWriter
			bufferedWriter.close();

			System.out.println("Saldo salvo com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public float puxar(String caminhoArquivoSaldo) {

		try {
			// Cria um objeto FileReader para ler o arquivo
			FileReader fileReader = new FileReader(caminhoArquivoSaldo);

			// Cria um objeto BufferedReader para ler os dados de forma mais eficiente
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// Lê o valor do saldo do arquivo
			String saldoStr = bufferedReader.readLine();

			// Converte o valor lido para float
			float saldo = Float.parseFloat(saldoStr);

			// Fecha o BufferedReader
			bufferedReader.close();

			// Retorna o valor saldo
			return saldo;

		} catch (IOException e) {
			e.printStackTrace();
			return -1; // Retorna um valor padrão em caso de erro
		}
	}
}
