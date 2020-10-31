package br.ufc.crateus.testdocs.domain.erros;

public class DomainError {
	private String type;
	private String message;
	
	public DomainError(String type, String message) {
		this.type = type;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getType() {
		return type;
	}
}
