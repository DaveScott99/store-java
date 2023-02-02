package entities;

public class Admin extends User {
	
	private Boolean changeStock;
	private Boolean changeCategory;
	private Boolean changeUsers;
	
	public Admin(String username, String email, String password, String role, Address address) {
		super(username, email, password, role, address);
		this.changeStock = true;
		this.changeCategory = true;
		this.changeUsers = true;
	}

	public Boolean getChangeStock() {
		return changeStock;
	}

	public Boolean getChangeCategory() {
		return changeCategory;
	}

	public Boolean getChangeUsers() {
		return changeUsers;
	}

}
