package entities;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import application.Main;

public class User {

	private Integer id;
	private String username;
	private String email;
	private String password;
	private String role;
	
	private Address address;
	
	private List<Product> cart = new ArrayList<>();
	
	private List<Order> orders = new ArrayList<>();
	
	public User() {
	}
	
	public User(String username, String email ,String password, String role, Address address) {
		this.id = Main.generateId();
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.address = address;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Order> getOrders() {
		return orders;
	}
	
	public void addOrder(Order order) {
		orders.add(order);
	}
	
	public List<Product> getCart() {
		return cart;
	}
	
	public void addItemInCart(Product product) {
		cart.add(product);
	}

	public void listOrders() {
		
		Main.cleanScreen();
		System.out.println("**************************************************");
		System.out.println("*                    PEDIDOS                     *");
		
		getOrders().forEach(x -> {
			System.out.println("ID: " + x.getId());
			System.out.println("Aberta: " + x.getOrderOpen());
			System.out.println("Valor Total: " + x.getTotalValue());
			System.out.println("**************************************************");
		});
	
		System.out.println("*                1 - Voltar                      *");
		System.out.println("**************************************************");
		System.out.print("-> ");
		
		Integer choice = Main.sc.nextInt();
		
		switch(choice) {
			case 1:
				Main.dashboardClient();
				break;
			default:
				System.out.println("Opção inválida!");
				listOrders();
				break;
		}

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
	
	public void showRegistry() {
		
		System.out.println("**************************************************");
		System.out.println("*                   SEUS DADOS                   *");
		
		System.out.println("NOME DE USUÁRIO: " + getUsername());
		System.out.println("EMAIL: " + getEmail());
		System.out.println("SENHA: " + getPassword());
		
		System.out.println("*                1 - Alterar Username            *");
		System.out.println("*                2 - Alterar Email               *");
		System.out.println("*                3 - Alterar Senha               *");
		System.out.println("*                4 - Voltar                      *");
		System.out.println("**************************************************");
		System.out.print("-> ");
		
		int choice = Main.sc.nextInt();
		
		changeRegistry(choice);
	}

	private void changeRegistry(Integer option) {

		switch (option) {
			case 1:
				System.out.println("Digite seu novo username: ");
				System.out.print("-> ");
				String username = Main.sc.next();
				setUsername(username);
				showRegistry();
				break;
			case 2:
				System.out.println("Digite seu novo email: ");
				System.out.print("-> ");
				String email = Main.sc.next();
				setEmail(email);
				showRegistry();
				break;
			case 3:
				System.out.println("Digite sua nova senha: ");
				System.out.print("-> ");
				String password = Main.sc.next();
				setPassword(password);
				showRegistry();
				break;
			case 4: 
				Main.dashboardClient();
				break;
			default:
				System.out.println("Opção inválida!");
				showRegistry();
				break;				
		}
		
	}
	
}
