package testdocs.domain.entities;

public class User {
	private long id;
	private String email;
	private String passwordHash;
	private String name;
	private String office;
	private String description;
	private String avatarUri;
	
	protected User() {
		
	}

	public User(long id, String email, String passwordHash, String name, String office, String description,
			String avatarUri) {
		this.id = id;
		this.email = email;
		this.passwordHash = passwordHash;
		this.name = name;
		this.office = office;
		this.description = description;
		this.avatarUri = avatarUri;
	}
	
	public static User fromName(String name) {
		User user = new User();
		user.name = name;
		return user;
	}
	 
	public void makePasswordHash() {
		
	}
	
	public boolean passwordNotMatches(String password) {
		return !this.passwordHash.equals(password);
	}

	public long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public String getName() {
		return name;
	}

	public String getOffice() {
		return office;
	}

	public String getDescription() {
		return description;
	}

	public String getAvatarUri() {
		return avatarUri;
	}
}
