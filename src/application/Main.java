package application;

import java.util.Random;
import java.util.Scanner;

import entities.Store;
import entities.User;

public class Main {

	public static Store store = new Store();

	public static User loginUser;

	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {		
		
		User user = new User("Davi", "davi@gmail.com", "1234", "admin", null);
		store.users.add(user);
		
		cleanScreen();
		authenticationMenu();
		
	}

	public static Integer generateId() {

		Random random = new Random();

		Integer id = random.nextInt(1000000) * 10;

		return id;

	}

	public static void menuEntry() {

		store.header();
		System.out.println("*                  1 - Catálogo                  *");
		System.out.println("*                  2 - Conta                     *");
		System.out.println("*                  3 - Sair                      *");
		System.out.println("**************************************************");
		System.out.print("-> ");

		int choice = sc.nextInt();

		switch (choice) {
		case 1:
			cleanScreen();
			store.catalog();
			break;
		case 2:

			if (loginUser.getRole().equals("admin")) {
				cleanScreen();
				dashboardAdmin();
			}
			cleanScreen();
			dashboardClient();
			
			break;
		case 3:
			System.exit(0);
			break;
		default:
			System.out.println("Opção inválida");
			menuEntry();
		}

	}

	public static void dashboardClient() {
		
		store.header();
		System.out.println("*               1 - Adicionar endereço           *");
		System.out.println("*               2 - Seus dados                   *");
		System.out.println("*               3 - Pedidos                      *");
		System.out.println("*               4 - Voltar                       *");
		System.out.println("**************************************************");
		System.out.print("-> ");

		int choice = sc.nextInt();

		switch (choice) {
		case 1:
			loginUser.addAddress();
			dashboardClient();
			break;
		case 2:
			loginUser.showRegistry();
			break;
		case 3:
			loginUser.listOrders();
			break;
		case 4:
			menuEntry();
			break;
		}

	}

	public static void dashboardAdmin() {
		
		cleanScreen();
		if (loginUser.getRole().equals("admin")) {

			store.header();
			System.out.println("*               1 - Adicionar produtos           *");
			System.out.println("*               2 - Seus dados                   *");
			System.out.println("*               3 - Listar usuários              *");
			System.out.println("*               4 - Pedidos                      *");
			System.out.println("*               5 - Voltar                       *");
			System.out.println("**************************************************");
			System.out.print("-> ");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				cleanScreen();
				store.addProducts();
				dashboardAdmin();
				break;
			case 2:
				cleanScreen();
				store.listUsers();
				dashboardAdmin();
				break;
			case 4:
				cleanScreen();
				loginUser.listOrders();
				break;
			case 5:
				menuEntry();
				break;
			}
		} 
		else {
			System.out.println("ERRO: Usuário não autorizado");
		}

	}

	public static void authenticationMenu() {

		store.entryStore();
		System.out.println("*                  1 - Login                     *");
		System.out.println("*                  2 - Cadastrar                 *");
		System.out.println("*                  3 - Sair                      *");
		System.out.println("**************************************************");
		System.out.print("-> ");

		int choice = sc.nextInt();

		switch (choice) {
		case 1:
			cleanScreen();
			loginUser = store.login();
			if (loginUser != null) {
				System.out.println("Login realizado com sucesso!");
				menuEntry();
			}
			else {
				System.out.println("ERRO: Email ou Senha incorretos!");
				authenticationMenu();
			}
			break;
		case 2:
			cleanScreen();
			store.registry();
			menuEntry();
			break;
		case 3:
			System.exit(0);
			break;
		default:
			System.out.println("Opção inválida!");
			authenticationMenu();
		}
	}
	
	public static void cleanScreen() {
		for (int i = 0; i <= 100; i++) {
			System.out.println();
		}
	}

}