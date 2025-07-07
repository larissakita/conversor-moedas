import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Scanner;

public class HttpClient {
    public static void main(String[] args) throws Exception {
        java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/ea1234f4cacc2bd3c1cf37e5/latest/USD"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonObject taxas = jsonObject.getAsJsonObject("conversion_rates");

        Scanner scanner = new Scanner(System.in);

        int opcao = 0;

        while (opcao != 7) {

            System.out.println("=== Conversor de Moedas ===");
            System.out.println("1 - Dólar (USD) --> Peso Argentino (ARS)");
            System.out.println("2 - Peso Argentino (ARS) --> Dólar (USD)");
            System.out.println("3 - Dólar (USD) --> Real Brasileiro (BRL)");
            System.out.println("4 - Real Brasileiro (BRL) --> Dólar (USD)");
            System.out.println("5 - Dólar (USD) --> Peso Colombiano (COP)");
            System.out.println("6 - Peso Colombiano (COP) --> Dólar (USD)");
            System.out.println("7 - Sair");

            System.out.print("Escolha a opção: ");
            opcao = scanner.nextInt();

            if (opcao == 7) {
                System.out.println("Programa encerrado.");
                break;
            }

            System.out.print("Digite o valor a ser convertido: ");
            double valor = scanner.nextDouble();

            double resultado = 0;

            switch (opcao) {
                case 1:
                    resultado = valor * taxas.get("ARS").getAsDouble();
                    System.out.printf("%.2f USD convertido para Peso Argentino (ARS) é %.2f ARS%n", valor, resultado);
                    break;
                case 2:
                    resultado = valor / taxas.get("ARS").getAsDouble();
                    System.out.printf("%.2f ARS convertido para Dólar (USD) é %.2f USD%n", valor, resultado);
                    break;
                case 3:
                    resultado = valor * taxas.get("BRL").getAsDouble();
                    System.out.printf("%.2f USD convertido para Real Brasileiro (BRL) é %.2f BRL%n", valor, resultado);
                    break;
                case 4:
                    resultado = valor / taxas.get("BRL").getAsDouble();
                    System.out.printf("%.2f BRL convertido para Dólar (USD) é %.2f USD%n", valor, resultado);
                    break;
                case 5:
                    resultado = valor * taxas.get("COP").getAsDouble();
                    System.out.printf("%.2f USD convertido para Peso Colombiano (COP) é %.2f COP%n", valor, resultado);
                    break;
                case 6:
                    resultado = valor / taxas.get("COP").getAsDouble();
                    System.out.printf("%.2f COP convertido para Dólar (USD) é %.2f USD%n", valor, resultado);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            System.out.println();
        }
        scanner.close();

    }
}
