package br.ufc.crateus.testdocs.domain.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "O email é obrigatório")
	@Email(message = "Email inválido")
	private String email;
	@NotBlank(message = "A senha é obrigatória")
	@Size(message = "A senha deve ter entre 7 e 15 caracteres", min = 7, max = 15)
	private String passwordHash;
	@NotBlank(message = "O nome é obrigatório")
	@Size(message = "O nome deve ter entre 3 e 30 caracteres", min = 3, max = 30)
	private String name;
	private String office;
	private String description;
	private String avatarUri;

	@ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
	private List<Project> projects;

	protected User() {

	}

	public static User create(String email, String password, String name,
			String office, String description, String avatarUri) {
		User user = new User();
		user.email = email;
		user.passwordHash = password;
		user.name = name;
		user.office = office;
		user.description = description;
		user.avatarUri = avatarUri;
		return user;
	}

	public void makePasswordHash() {

	}

	public boolean passwordNotMatches(String password) {
		return !this.passwordHash.equals(password);
	}

	public Long getId() {
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

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", passwordHash="
				+ passwordHash + ", name=" + name + ", office=" + office
				+ ", description=" + description + ", avatarUri=" + avatarUri
				+ ", projects=" + projects + "]";
	}
}
