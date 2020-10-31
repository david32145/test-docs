package br.ufc.crateus.testdocs.data;

public class ProjectListDTO {
	private Long id;
	private String name;
	private Long memberSize;
	private String ownerName;
	private String imageUri;
	
	public ProjectListDTO(Long id, String name, Long memberSize, String ownerName,
			String imageUri) {
		this.id = id;
		this.name = name;
		this.memberSize = memberSize;
		this.ownerName = ownerName;
		this.imageUri = imageUri;
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getMemberSize() {
		return memberSize;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public String getImageUri() {
		return imageUri;
	}
}
