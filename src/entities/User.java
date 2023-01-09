package entities;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class User {

	private Integer id;
	private String username;
	private String email;
	private String password;
	private String role;
	
	private Address address;
	
	private List<Order> orders = new ArrayList<>();
	
	public User() {
	}
	
	public User(Integer id, String username, String email ,String password, String role, Address address) {
		this.id = id;
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
		
		String data = "Seus dados";
		
		data += "\n\nUsername: " + getUsername();
		data += "\nEmail: " + getEmail();
		data += "\nSenha: " + getPassword();
		
		int option = Integer.parseInt(JOptionPane.showInputDialog(data+ "\n\n1 - Alterar username" + "\n2 - Alterar email" + "\n3 - Alterar senha\n"));
		
		changeRegistry(option);
	}

	private void changeRegistry(Integer option) {

		switch (option) {
			case 1:
				String username = JOptionPane.showInputDialog("Digite seu novo username: ");
				setUsername(username);
				showRegistry();
				break;
			case 2:
				String email = JOptionPane.showInputDialog("Digite seu novo email: ");
				setEmail(email);
				showRegistry();
				break;
			case 3:
				String password = JOptionPane.showInputDialog("Digite sua nova senha: ");
				setPassword(password);
				showRegistry();
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção inválida!");
				showRegistry();
				break;				
		}
		
	}
	
	
}
