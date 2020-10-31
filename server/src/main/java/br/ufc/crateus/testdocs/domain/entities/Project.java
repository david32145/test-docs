package br.ufc.crateus.testdocs.domain.entities;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "projects")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank(message = "O nome é obrigatório")
	@Size(message = "O nome deve ter entre 3 e 30 caracteres", min = 3, max = 30)
	private String name;
	private String description;
	private String githubUri;
	private String imageUri;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name="project_members", 
			joinColumns= @JoinColumn(name="project_id"), 
			inverseJoinColumns= @JoinColumn(name="user_id"))
	private List<User> members;
	
	@OneToOne
	private User owner;
	
	@OneToMany(mappedBy = "project")
	private List<TestRequest> testRequests;
	
	protected Project() {
		
	}

	public Project(long id, String name, String description, String githubUri,
			String imageUri, User owner) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.githubUri = githubUri;
		this.imageUri = imageUri;
		this.owner = owner;
	}

	public void addOwner(User owner) {
		this.owner = owner;
	}
	
	public void deleteByMemberId(Long id) {
		this.members = this.members.stream().filter(u -> u.getId() != id)
					.collect(Collectors.toList());
	}
	
	public void addMember(User user) {
		this.members.add(user);
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
	
	public String getImageUri() {
		return imageUri;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public List<User> getMembers() {
		return members;
	}

	public void changeImageUri(String newImageUri) {
		this.imageUri = newImageUri;
	}
}
