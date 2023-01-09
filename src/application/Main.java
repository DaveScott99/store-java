package application;

import javax.swing.JOptionPane;

import entities.Store;
import entities.User;

public class Main {
	
	public static Store store = new Store();
		
	public static User loginUser;

	public static void main(String[] args) {
		
		menu();
				
	}
	
	public static void menu() {
		
		int choice = Integer.parseInt(JOptionPane.showInputDialog("--- Olá seja bem-vindo à JDKClean! ---" 
				+ "\n\nEscolha uma opção: "
				+ "\n1 - Catálogo"
				+ "\n2 - Conta\n\n"));
		
		switch (choice) {
			case 1:
				store.catalog();
				break;
			case 2:
				if (loginUser == null) {
					authenticationMenu();
				}
				else {
					if (loginUser.getRole().equals("admin")) {
						dashboardAdmin();
					}
					dashboardClient();
				}
				break;
		}
	}
	
	public static void dashboardClient() {
		
		if (loginUser == null) {
			JOptionPane.showInternalMessageDialog(null, "Faça login primeiro.");
			menu();
		}
		else {
			int choice = Integer.parseInt(JOptionPane.showInputDialog("Olá, " + loginUser.getUsername() 
					+ "\n\nConfigurações de conta: "
					+ "\n1 - Adicionar endereço"
					+ "\n2 - Seus dados"
					+ "\n3 - Pedidos\n\n"));
			
			switch (choice) {
				case 1:
					loginUser.addAddress();
					dashboardClient();
					break;
				case 2:
					loginUser.showRegistry();
			}
		}
		
	}
	
	public static void dashboardAdmin() {
		
		if (loginUser != null && loginUser.getRole().equals("admin")) {
			int choice = Integer.parseInt(JOptionPane.showInputDialog("Olá, " + loginUser.getUsername() 
			+ "\n\nConfigurações da loja: "
			+ "\n1 - Adicionar produtos"
			+ "\n2 - Listar usuários"
			+ "\n3 - Listar pedidos"
			+ "\n4 - Voltar\n\n"));
	
			switch (choice) {
				case 1:
					store.addProducts();
					dashboardAdmin();
					break;
				case 2: 
					store.listUsers();
					dashboardAdmin();
					break;
				case 4:
					menu();
					break;
			}
		}
		else {
			JOptionPane.showInternalMessageDialog(null, "ERRO: Usuário não autorizado");
		}
		
	}
	
	public static void authenticationMenu() {
		
		int choice = Integer.parseInt(JOptionPane.showInputDialog("Faça login ou Cadastre-se em nossa loja" 
				+ "\n\nEscolha uma opção: "
				+ "\n1 - Login"
				+ "\n2 - Cadastrar\n\n"));
		
		switch (choice) {
			case 1:
				loginUser = store.login();
				if (loginUser.getRole().equals("admin")) {
					dashboardAdmin();
				}
				else {
					dashboardClient();
				}
				break;
			case 2:
				store.registry();
				menu();
				break;
		}
		
	}
	
}
