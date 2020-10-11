package testdocs.domain.entities;

public class Project {
	private long id;
	private String name;
	private String description;
	private String githubUri;
	private long ownerId;
	private String imageUri;
	
	private User user;
	
	public Project(long id, String name, String description, String githubUri, long ownerId, String imageUri) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.githubUri = githubUri;
		this.ownerId = ownerId;
		this.imageUri = imageUri;
	}
	
	public Project(long id, String name, String description, String githubUri, User user, String imageUri) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.githubUri = githubUri;
		this.user = user;
		this.imageUri = imageUri;
	}

	public void relationshipWithOwnerId(long userId) {
		this.ownerId = userId;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getGithubUri() {
		return githubUri;
	}
	
	public long getOwnerId() {
		return ownerId;
	}
	
	public String getImageUri() {
		return imageUri;
	}
	
	public User getUser() {
		return user;
	}
}
