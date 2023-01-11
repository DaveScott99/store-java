package entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import application.Main;

public class Store {
	
	private Integer id = 1; 
	private String name = "JDKClean";
	private String cnpj = "010101010";

	private Main main = new Main();
	
	private Address address;
	
	public static Set<User> users = new HashSet<>();
	
	public static List<Product> products = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public static Set<User> getUsers() {
		return users;
	}

	public static List<Product> getProducts() {
		return products;
	}
	
	public void catalog() {
		
		String catalogProducts = "---------- Catálogo de produtos ----------\n\n";
		
		catalogProducts += "ID           NOME           R$           QTD\n";
		
		for (Product prod : products) {
			catalogProducts += prod.getId() + "               " + prod.getName() + "            " + prod.getPrice() + "       " + prod.getQuantity();
		}
	
		catalogProducts += "\n0 - Voltar";
		
		int choice = Integer.parseInt(JOptionPane.showInputDialog(catalogProducts+ "\n\nSelecione o produto pelo seu id: "));
		
		if (choice == 0) {
			main.menu();
		}
		
		pageProduct(choice);
		
	}
	
	public void pageProduct(Integer id) {
		Product product = selectProductById(id);
		
		String infoProduct = "Nome do produto: " + product.getName();
		infoProduct += "\nPreço: R$ " + product.getPrice();
		infoProduct += "\nQuantidade: " + product.getQuantity();
		
		infoProduct += "\n\n1 - Adicionar ao pedido";
		infoProduct += "\n2 - Voltar";
		
		int choice = Integer.parseInt(JOptionPane.showInputDialog(infoProduct + "\n\nSelecione uma opção: "));
		
		switch (choice) {
			case 1:
				//JOptionPane.showMessageDialog(null, "Produto adicionado ao pedido!");
				addProductInOrder(product);
				catalog();
				break;
			case 2:
				catalog();
				break;
		}
	}
	
	public void addProductInOrder(Product product) {
		
		if (main.loginUser != null) {
			Order order = new Order(null, null, main.loginUser);
			order.addProducts(product);
			main.loginUser.addOrder(order);
		}
		else {
			JOptionPane.showMessageDialog(null, "Faça login primeiro!!");
		}
		
	}
	
	public Product selectProductById(Integer id) {
		Product selectedProduct;
		for (Product prod : products) {
			if (prod.getId().equals(id)) {
				selectedProduct = prod;
				return selectedProduct;
			}
		}
		return null;
	}
	
	public void registry() {
		
		User user;
		
		Integer id = Integer.parseInt(JOptionPane.showInputDialog("ID: "));
		String username = JOptionPane.showInputDialog("Username: ");
		String email = JOptionPane.showInputDialog("Email: ");
		String password = JOptionPane.showInputDialog("Senha: ");
		String role = JOptionPane.showInputDialog("Role: ");
		
		if (role == "admin") {
			user = new Admin(id, username, email, password, role, null);
			users.add(user);
		}
		else {
			user = new User(id, username, email, password, role, null);
			users.add(user);
		}		
		JOptionPane.showMessageDialog(null, "Usuário registrado com sucesso!");
	}
	
	public User login() {
		
		String email = JOptionPane.showInputDialog("Email: ");
		String password = JOptionPane.showInputDialog("Senha: ");
		
		for(User user : users) {
			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
				JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
				return user;
			}
			else {
				JOptionPane.showMessageDialog(null, "ERRO: Email ou senha incorretos!");
			}
		}
		
		return null;
		
	}
	
	public void addProducts() {
		
		Integer id = Integer.parseInt(JOptionPane.showInputDialog("ID: "));
		String name = JOptionPane.showInputDialog("Nome: ");
		Double price = Double.parseDouble(JOptionPane.showInputDialog("Preço: "));
		Integer quantity = Integer.parseInt(JOptionPane.showInputDialog("Quantidade: "));
		
		Product product = new Product(id, name, price, quantity);
		
		products.add(product);
		
	}
	
	public void addAddress() {
		
		String zipCode = JOptionPane.showInputDialog("Cep: ");
		String street = JOptionPane.showInputDialog("Rua: ");
		Integer number = Integer.parseInt(JOptionPane.showInputDialog("Número: "));
		String district = JOptionPane.showInputDialog("Bairro: ");
		String city = JOptionPane.showInputDialog("Cidade: ");
		
		Address address = new Address(zipCode, street, district, city, number);
		
		setAddress(address);
		
		JOptionPane.showInputDialog("Endereço cadastrado com sucesso!");
	}
	
	public void listUsers() {
		String listUsers = "---------- Usuários cadastrados ----------\n\n";
		
		listUsers += "ID           NOME           EMAIL           SENHA          ROLE\n";
		
		for (User user : users) {
			listUsers += user.getId() + "               " 
						+ user.getUsername() + "            " 
						+ user.getEmail() + "       " 
						+ user.getPassword() + "       " 
						+ user.getRole();
		}
		
		int choice = Integer.parseInt(JOptionPane.showInputDialog(listUsers+ "\n\nEscolha uma opção: "));
	}

}