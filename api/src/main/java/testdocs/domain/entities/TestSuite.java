package testdocs.domain.entities;

public class TestSuite {
	private long id;
	private String title;
	private String description;
	private long testRequestId;

	public TestSuite(long id, String title, String description, long testRequestId) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.testRequestId = testRequestId;
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

	public long getTestRequestId() {
		return testRequestId;
	}
}
