package ViaCep;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


// Nome: Paulo Henrique Almeida
//RA: 0025195

public class Cep {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite o CEP: ");
		String cep = sc.next();

		String url = "https://viacep.com.br/ws/" + cep + "/json/";
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(url)) 
					.GET() 
					.build(); 

			HttpResponse<String> response = client.send(
					request,
					HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != 200) {
				System.out.println("CEP inválido. Digite 8 números.");
			} else if (response.body().contains("\"erro\"")) {
				System.out.println("CEP não encontrado.");
			} else {
				System.out.println(response.body());
			}

		} catch (Exception e) {
			System.out.println("Erro ao consultar o CEP: " + e.getMessage());
		}

		sc.close();
	}

}
