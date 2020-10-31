package br.ufc.crateus.testdocs.domain.erros;

public class Left<D, E> extends Either<D, E>{

	private E error;
	
	public Left(E error) {
		this.error = error;
	}

	@Override
	public E getError() {
		return error;
	}

}
