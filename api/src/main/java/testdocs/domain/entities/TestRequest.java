package testdocs.domain.entities;

import testdocs.infra.data.dao.enums.TestRequestStatus;

public class TestRequest {
	private long id;
	private String version;
	private long projectId;
	private TestRequestStatus status;
	private long requestingUserId;
	private long testerUserId;
	private long releasedTimestamp;
	
	protected TestRequest() { }

	public TestRequest(long id, String version, long projectId, TestRequestStatus status, long requestingUserId,
			long testerUserId, long releasedTimestamp) {
		this.id = id;
		this.version = version;
		this.projectId = projectId;
		this.status = status;
		this.requestingUserId = requestingUserId;
		this.testerUserId = testerUserId;
		this.releasedTimestamp = releasedTimestamp;
	}

	public static TestRequest create(String version, long projectId, long requestingUserId, long testerUserId) {
		TestRequest testRequest = new TestRequest();
		testRequest.version = version;
		testRequest.projectId = projectId;
		testRequest.requestingUserId = requestingUserId;
		testRequest.testerUserId = testerUserId;
		testRequest.status = TestRequestStatus.REQUESTED;
		return testRequest;
	}

	public long getId() {
		return id;
	}

	public String getVersion() {
		return version;
	}
	
	public long getProjectId() {
		return projectId;
	}
	
	public TestRequestStatus getStatus() {
		return status;
	}

	public long getRequestingUserId() {
		return requestingUserId;
	}

	public long getTesterUserId() {
		return testerUserId;
	}

	public long getReleasedTimestamp() {
		return releasedTimestamp;
	}
}
