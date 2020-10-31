package br.ufc.crateus.testdocs.application.dto;

public class CreateUserDTO {
	private String email;
	private String password;
	private String name;
	private String office;
	private String description;
	private String avatarUri;

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
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
