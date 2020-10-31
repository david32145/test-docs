package br.ufc.crateus.testdocs.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.ufc.crateus.testdocs.domain.enums.TestCaseStatus;

@Entity
@Table(name = "test_cases")
public class TestCase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String description;
	private TestCaseStatus testCaseStatus;
	
	@ManyToOne
	@JoinColumn(name="test_suite_id", nullable=false)
	private TestSuite testSuite;
	
	protected TestCase() {
		
	}
	
	public TestCase(long id, String title, String description, TestSuite testSuite) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.testSuite = testSuite;
	}
	
	public void approve() {
		this.testCaseStatus = TestCaseStatus.PASSED;
	}
	
	public void disapprove() {
		this.testCaseStatus = TestCaseStatus.NOT_PASSED;
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
	public TestSuite getTestSuite() {
		return testSuite;
	}
	
	public TestCaseStatus getTestCaseStatus() {
		return testCaseStatus;
	}
}
