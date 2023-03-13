package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import service.Sale;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Entre o caminho do arquivo: ");
		String past = sc.nextLine();

		try (BufferedReader bf = new BufferedReader(new FileReader(past))) {
			String line;
			List<Sale> sale = new ArrayList<>();

			while ((line = bf.readLine()) != null) {
				String[] vec = line.split(",");
				int month = Integer.parseInt(vec[0]);
				int year = Integer.parseInt(vec[1]);
				String seller = vec[2];
				int items = Integer.parseInt(vec[3]);
				double total = Double.parseDouble(vec[4]);

				sale.add(new Sale(month, year, seller, items, total));

			}

			List<Sale> precoMedio = sale.stream().filter(p -> p.getYear() == 2016)
					.sorted((s1, s2) -> s2.averagePrice().compareTo(s1.averagePrice())).limit(5)
					.collect(Collectors.toList());

			double total = sale.stream().filter(p -> p.getSaller().equals("Logan"))
					.filter(p -> p.getMonth() == 1 || p.getMonth() == 7).map(p -> p.getTotal())
					.reduce((double) 0, (x, y) -> x + y);

			System.out.println("\nCinco primeiras vendas de 2016 de maior preço médio");
			precoMedio.forEach(System.out::println);
			System.out.printf("\nValor total vendido pelo vendedor Logan nos meses 1 e 7 = %.2f ", total);

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}

		sc.close();

	}

}
