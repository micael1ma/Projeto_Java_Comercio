package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Saldo {

	private float saldoTotal;
	private float vendaTotal;;
	private float compraTotal;;

	public Saldo() {

	}

	public Saldo(float saldoTotal, float vendaTotal, float compraTotal) {
		super();
		this.saldoTotal = saldoTotal;
		this.vendaTotal = vendaTotal;
		this.compraTotal = compraTotal;

	}

	public float getSaldoTotal() {
		return saldoTotal;
	}

	public float getVendaTotal() {
		return vendaTotal;
	}

	public float getCompraTotal() {
		return compraTotal;
	}

	public String toFileStringSaldo() {
		return saldoTotal + "," + vendaTotal + "," + compraTotal;
	}

	public void salvarSaldo(List<Saldo> listaSaldo, String caminhoArquivoSaldo) {
		try {

			FileWriter fileWriter = new FileWriter(caminhoArquivoSaldo);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (Saldo Valor : listaSaldo) {
				bufferedWriter.write(Valor.toFileStringSaldo());
				bufferedWriter.newLine();
			}

			bufferedWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//
//	
//	

	public static Saldo fromFileStringSaldo(String linha) {

		String[] partes = linha.split(",");

		float saldoTotal = Float.parseFloat(partes[0]);
		float vendaTotal = Float.parseFloat(partes[1]);
		float compraTotal = Float.parseFloat(partes[2]);

		return new Saldo(saldoTotal, vendaTotal, compraTotal);
	}

	public List<Saldo> carregarSaldo(String caminhoArquivoSaldo) {
		List<Saldo> valores = new ArrayList<>();

		try {
			FileReader fileReader = new FileReader(caminhoArquivoSaldo);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String linha;
			while ((linha = bufferedReader.readLine()) != null) {
				Saldo valor = entities.Saldo.fromFileStringSaldo(linha);
				valores.add(valor);
			}

			bufferedReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return valores;
	}

}
