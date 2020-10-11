package testdocs.application.dto;

public class CreateTestRequestDTO {
	private String version;
	private long projectId;
	private long requestingUserId;
	private long testerUserId;

	public String getVersion() {
		return version;
	}
	
	public long getProjectId() {
		return projectId;
	}

	public long getRequestingUserId() {
		return requestingUserId;
	}

	public long getTesterUserId() {
		return testerUserId;
	}
}
