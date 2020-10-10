package testdocs.domain.erros;

public class Rigth<D, E> extends Either<D, E> {
	private D data;
	
	public Rigth() {
		this(null);
	}
	
	public Rigth(D data) {
		this.data = data;
	}

	@Override
	public D getData() {
		return data;
	}
}
