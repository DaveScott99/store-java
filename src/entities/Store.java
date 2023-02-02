package entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import application.Main;
import services.ProductService;

public class Store {

	private Integer id = 1;
	private String name = "JDKClean";
	private String cnpj = "010101010";

	private Address address;

	public Set<User> users = new HashSet<>();

	private List<Product> products = new ArrayList<>();
	
	private ProductService productService;

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

	public Set<User> getUsers() {
		return users;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void entryStore() {
		System.out.println("**************************************************");
		System.out.println("*               Bem vindo à JDKClean             *");
	}

	public void header() {
		System.out.println("**************************************************");
		System.out.println("*             DIGITE OPERAÇÃO DESEJADA           *");

	}

	public void catalog() {
		
		Integer choice = 1;
		
		productService.findAll();
		
		Order order = new Order(Main.generateId(), null, Main.loginUser);
		
		while (choice != 0) {
			System.out.println("**************************************************");
			System.out.println("*              ESCOLHA SEUS PRODUTOS             *");
			System.out.println();

			System.out.println("ID               NOME");

			products.forEach(x -> {
				System.out.println(x.getId() + "         " + x.getName());
			});
			
			System.out.println();
			System.out.println("*              0 - Finalizar Compra              *");
			System.out.println("**************************************************");
			System.out.print("-> ");
			
			choice = Main.sc.nextInt();
			
			Product product = productService.findProductById(choice);
			
			if (product != null) {
				order.addProduct(product);
			}
		}				
		System.out.println();		
		
		showItensInCart(order);
		
		order.closeOrder();
		
		Main.loginUser.addOrder(order);
		
		newOrder();
	}
	
	public void showItensInCart(Order order) {
		System.out.println("**************************************************");
		System.out.println("*                 RESUMO DO PEDIDO               *");
		
		order.getProducts().stream().forEach(x -> {
			if (x != null) {
				System.out.println(x.getId() + " - " + x.getName() + "  R$" + x.getPrice());				
			}
		});
		
		System.out.println("Total: R$ " + order.getTotalValue());
		System.out.println("**************************************************");
	
	}
	
	public void newOrder() {
		System.out.println("Deseja realizar uma nova compra? (S - Sim / N - Não) ");
		System.out.print("-> ");
		
		char choice = Main.sc.next().toUpperCase().charAt(0);
		
		switch (choice) {
		case 'S':
			catalog();
			break;
		case 'N':
			Main.menuEntry();
			break;
		default:
			System.out.println("Opção inválida.");
			newOrder();
			break;
		}
		
	}

	public void registry() {
		
		System.out.println("**************************************************");
		System.out.println("*                    REGISTRO                    *");

		User user;
		
		System.out.print("NOME DE USUÁRIO: ");
		String username = Main.sc.next();
		
		System.out.print("EMAIL: ");
		String email = Main.sc.next();
		
		System.out.print("SENHA: ");
		String password = Main.sc.next();
		
	
		if (username == null) {
			System.out.println("ERRO: Username não pode estar vazio!");
			registry();
			if (email == null) {
				System.out.println("ERRO: Email não pode estar vazio!");
				registry();
				if (password == null) {
					System.out.println("ERRO Senha não pode estar vazio!");
					registry();
				}
			}
		}
		else {
			user = new User(username, email, password, "client", null);
			users.add(user);
			Main.loginUser = user;
		}
	
		System.out.println("USUÁRIO REGISTRADO COM SUCESSO!");
	}

	public User login() {
		
		System.out.println("**************************************************");
		System.out.println("*                     LOGIN                      *");

		System.out.print("EMAIL: ");
		String email = Main.sc.next();
		
		System.out.print("SENHA: ");
		String password = Main.sc.next();
		
		for (User user : users) {
			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {	
				return user;
			}
		}
		
		return null;
		
		/*
		User user = users.stream().filter(x ->  x.getEmail().equals(email)).filter(z -> z.getPassword().equals(password)).collect(Collectors.toList()).get(0);
		
		
		*/
	}

	public void addProducts() {
		
		System.out.println("**************************************************");
		System.out.println("*                ADICIONAR PRODUTO               *");

		System.out.print("NOME DO PRODUTO: ");
		String name = Main.sc.next();
		
		System.out.print("PREÇO: ");
		Double price = Main.sc.nextDouble();
		
		System.out.print("QUANTIDADE: ");
		Integer quantity = Main.sc.nextInt();
	
		Product product = new Product(name, price, quantity);

		productService.addProduct(product);
	}

	public void listUsers() {
		
		System.out.println("**************************************************");
		System.out.println("*              USUÁRIOS CADASTRADOS              *");
		
		System.out.println("ID           NOME           EMAIL           SENHA          ROLE");

		users.forEach(x -> {
			System.out.println(x.getId() + "               " + 
							   x.getUsername() + "               " + 
							   x.getEmail() + "               " + 
							   x.getPassword() + "               " + 
							   x.getRole());
		});
		
		System.out.println("*                  1 - Voltar                    *");
		System.out.println("**************************************************");
		System.out.print("-> ");
		
		Integer choice = Main.sc.nextInt();
		
		if (choice == 1 ) {
			Main.dashboardAdmin();
		}
		
	}

}
