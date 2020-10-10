package testdocs.domain.entities;

public class TestCase {
	private long id;
	private String title;
	private String description;
	private long testSuiteId;

	public TestCase(long id, String title, String description, long testSuiteId) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.testSuiteId = testSuiteId;
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

	public long getTestSuiteId() {
		return testSuiteId;
	}
}
