package br.ufc.crateus.testdocs.domain.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "test_suites")
public class TestSuite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String description;
	
	@ManyToOne
	@JoinColumn(name="test_request_id", nullable=false)
	private TestRequest testRequest;
	
	@OneToMany(mappedBy = "testSuite")
	private List<TestCase> testCases;
	
	protected TestSuite() {
		
	}

	public TestSuite(long id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
	
	@Transient
	@JsonProperty(access = Access.WRITE_ONLY)
	public TestRequest getTestRequest() {
		return testRequest;
	}
	
	public List<TestCase> getTestCases() {
		return testCases;
	}
}
