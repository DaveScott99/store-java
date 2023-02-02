package entities;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

public class Product {
	
	private Integer id;
	private String name;
	private Double price;
	private Integer quantity = 0;
	public Boolean inStock = false;
	
	private Set<Category> categories = new HashSet<>();
	
	public Product() {
	}
	
	public Product(String name, Double price, Integer quantity) {
		this.name = name;
		this.price = price;
		this.quantity = increaseStock(quantity);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Set<Category> getCategories() {
		return categories;
	}
	
	public void addCategory(Category category) {
		categories.add(category);
		JOptionPane.showMessageDialog(null, "Produto adicionado a categoria com sucesso!");
	}

	public Integer increaseStock(Integer quantity) {
		this.quantity =+ quantity;
		inStock = true;
		return quantity;
	}
	
	public void decreaseStock(Integer quantity) {
		if (this.quantity > 0) {
			this.quantity =- quantity;
		}
		else {
			inStock = false;
			System.out.println("Produto esgotado!");
		}
	}
	
}
