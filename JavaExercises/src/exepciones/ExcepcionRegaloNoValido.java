package exepciones;

public class ExcepcionRegaloNoValido extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcepcionRegaloNoValido() {
		super();
	}

	public ExcepcionRegaloNoValido(String r) {
		super(r);
	}

}
