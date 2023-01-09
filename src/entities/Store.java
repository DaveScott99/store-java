package entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

public class Store {
	
	private Integer id = 1; 
	private String name = "JDKClean";
	private String cnpj = "010101010";
	
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
		
		int choice = Integer.parseInt(JOptionPane.showInputDialog(catalogProducts+ "\n\nEscolha uma opção: "));
		
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
