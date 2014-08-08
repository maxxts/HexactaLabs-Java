package ar.com.hexacta.tpl.model;

public class User extends Entity {
	private static final long serialVersionUID = -2599013520313365015L;
	
	private String username;
	private String email;
	private String password;
	
	// Hibernate needs
	public User() {
		super();
	}
	
	
	public User(String username, String pass){
		super();
		this.username = username;
		this.password = pass;
	}
	
	public User(String username, String pass, String email){
		super();
		this.username = username;
		this.password = pass;
		this.email = email;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setUsername(String user){
		this.username = user;
	}
	
	public void setPassword(String pass){
		this.password = pass;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
}
