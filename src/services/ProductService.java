package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import entities.Product;
import entities.Store;

public class ProductService {

	private Store store;

	public void findAll() {

		try (BufferedReader br = new BufferedReader(new FileReader("stock_products.txt"))) {

			String line = br.readLine();

			while (line != null) {
				String[] field = line.split(",");

				Product prod = new Product();

				prod.setId(Integer.parseInt(field[0]));
				prod.setName(field[1]);
				prod.setPrice(Double.parseDouble(field[2]));
				prod.setQuantity(Integer.parseInt(field[3]));

				store.getProducts().add(prod);

				line = br.readLine();
			}

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}

	}

	public void addProduct(Product prod) {

		String path = "stock_products.txt";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {

			String product = prod.getName() + "," + prod.getPrice() + "," + prod.getQuantity();

			bw.write(product);
			bw.newLine();

		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}

	}

	public Product findProductById(Integer id) {

		for (Product prod : store.getProducts()) {
			
			if (prod.getId().equals(id)) {
				Product selectedProduct = prod;
				return selectedProduct;
			}
			
		}
		
		//Product selectedProduct = products.stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList()).get(0);
		
		return null;
	}

}
