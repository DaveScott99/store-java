package entities;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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

	@Override
	public String toString() {
		return id + "          " +  orderOpen + "             R$ " + totalValue;
	}

	public void addProducts(Product product) {
		products.add(product);
		calcTotalValue();
		JOptionPane.showMessageDialog(null, "Produto adicionado ao pedido.");
	}
	
	public void removeProduct(Integer id) {
		for (Product prod : products) {
			if (prod.getId().equals(id)) {
				products.remove(id);
				JOptionPane.showConfirmDialog(null, "Produto removido com sucesso!");
			}
			else {
				JOptionPane.showConfirmDialog(null, "Produto não encontrado!");
			}
		}
	}
	
	public void closeOrder() {
		orderOpen = false;
	}
	
	public void calcTotalValue() {
		for (Product prod : products) {
			totalValue =+ prod.getPrice();
		}
	}
	
}
