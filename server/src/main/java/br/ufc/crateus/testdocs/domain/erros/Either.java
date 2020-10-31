package br.ufc.crateus.testdocs.domain.erros;

abstract public class Either<D, E> {
	
	public D getData() {
		return null;
	}
	public E getError() {
		return null;
	}
	
	public boolean hasData() {
		return this.getData() != null;
	}
	
	public boolean hasError() {
		return this.getError() != null;
	}
}
