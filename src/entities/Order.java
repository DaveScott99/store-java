package entities;

import java.util.ArrayList;
import java.util.List;

public class Order {
	
	private Integer id;
	private Double totalValue;
	private Boolean orderOpen = true;
	
	private User user;
	
	private List<Product> products = new ArrayList<>();
	
	public Order(Integer id, Double totalValue, User user) {
		this.id = id;
		this.totalValue = totalValue;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public Boolean getOrderOpen() {
		return orderOpen;
	}
	
	public void addProduct(Product product) {
		products.add(product);
		calcTotalValue();
	}
	
	public void removeProduct(Integer id) {
		for (Product prod : products) {
			if (prod.getId().equals(id)) {
				products.remove(id);
				System.out.println("Produto removido");
			}
			else {
				System.out.println("Produto não encontrado");
			}
		}
	}
	
	public void closeOrder() {
		orderOpen = false;
	}
	
	public void calcTotalValue() {
		/*
		for (Product prod : getProducts()) {
			totalValue =+ prod.getPrice();
		}
		*/
		
		totalValue = getProducts().stream().mapToDouble(Product::getPrice).sum();
		
	}
	
}
