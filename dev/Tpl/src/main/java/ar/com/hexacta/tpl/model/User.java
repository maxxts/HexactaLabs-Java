package ar.com.hexacta.tpl.model;

public class User extends Entidad {
	private static final long serialVersionUID = -2599013520313365015L;

	private String username;
	private String email;
	private String password;

	// Hibernate needs
	public User() {
		super();
	}

	public User(final String username, final String pass) {
		super();
		this.username = username;
		password = pass;
	}

	public User(final String username, final String pass, final String email) {
		super();
		this.username = username;
		password = pass;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setUsername(final String user) {
		username = user;
	}

	public void setPassword(final String pass) {
		password = pass;
	}

	public void setEmail(final String email) {
		this.email = email;
	}
}
