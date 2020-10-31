package br.ufc.crateus.testdocs.domain.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.ufc.crateus.testdocs.domain.enums.TestCaseStatus;
import br.ufc.crateus.testdocs.domain.enums.TestRequestStatus;

@Entity
@Table(name = "test_requests")
public class TestRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String version;
	private TestRequestStatus status;
	private long releasedTimestamp;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="project_id", nullable=false)
	private Project project;
	
	@OneToOne
	@JoinColumn(name = "tester_user_id", referencedColumnName = "id")
	private User testerUser;
	
	@OneToOne
	@JoinColumn(name = "requesting_user_id", referencedColumnName = "id")
	private User requestingUser;
	
	@OneToMany(mappedBy = "testRequest")
	private List<TestSuite> testSuites;
	
	protected TestRequest() { 
		this.status = TestRequestStatus.REQUESTED;
	}

	public TestRequest(long id, String version, Project project, TestRequestStatus status, User requestingUser,
			User testerUser, long releasedTimestamp) {
		this.id = id;
		this.version = version;
		this.project = project;
		this.status = status;
		this.requestingUser = requestingUser;
		this.testerUser = testerUser;
		this.releasedTimestamp = releasedTimestamp;
	}

	public static TestRequest create(String version, Project project, User requestingUser, User testerUser) {
		TestRequest testRequest = new TestRequest();
		testRequest.version = version;
		testRequest.project = project;
		testRequest.requestingUser = requestingUser;
		testRequest.testerUser = testerUser;
		testRequest.status = TestRequestStatus.REQUESTED;
		return testRequest;
	}

	public long getId() {
		return id;
	}
	
	public void accept() {
		this.status = TestRequestStatus.ACCEPTED;
	}
	
	public void reject() {
		this.status = TestRequestStatus.REJECTED;
	}

	public String getVersion() {
		return version;
	}
	
	public TestRequestStatus getStatus() {
		return status;
	}

	public long getReleasedTimestamp() {
		return releasedTimestamp;
	}
	
	public Project getProject() {
		return project;
	}
	
	public User getRequestingUser() {
		return requestingUser;
	}
	
	public User getTesterUser() {
		return testerUser;
	}
}
