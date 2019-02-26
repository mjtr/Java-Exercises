package oro_inciensoyjava;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exepciones.ExcepcionRegaloNoValido;

public class NiñoImpl extends PersonaImpl implements Niño {

	private Integer castigos;
	private List<Regalo> listaRegalos;
	private Set<Carroza> carrozas;

	public NiñoImpl(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email,
			Integer castigos, List<Regalo> regalos, Set<Carroza> carrozas) {

		super(dni, nombre, apellidos, fechaNacimiento, email);

		checkRegalos(listaRegalos);
		checkCabalgatas(carrozas);

		this.castigos = castigos;
		listaRegalos = regalos;
		this.carrozas = carrozas;
	}

	public Integer getNumeroCastigos() {
		return castigos;
	}

	public Boolean Carbon() {

		boolean res = false;

		if (this.getNumeroCastigos() > 5)
			res = true;

		return res;

	}

	public List<Regalo> getListaRegalos() {

		return new ArrayList<Regalo>(this.listaRegalos);

	}

	public Set<Carroza> getCabalgata() {
		return new HashSet<Carroza>(this.carrozas);
	}

	public void añadeRegalo(Regalo r) {

		this.listaRegalos.add(r);

		checkRegalos(listaRegalos);
	}

	public void eliminaRegalo(Regalo r) {

		if (listaRegalos.contains(r))
			this.listaRegalos.remove(r);

		else
			throw new ExcepcionRegaloNoValido("El regalo " + r + "no está en la lista de regalos");

	}

	public void añadeCarroza(Carroza c) {

		this.carrozas.add(c);
		checkCabalgatas(carrozas);

	}

	public void checkRegalos(List<Regalo> lista) {
		if (lista.size() > 10)
			throw new ExcepcionRegaloNoValido("La lista de regalos no puede superar los 10 objetos");

	}

	public void checkCabalgatas(Set<Carroza> conjunto) {

		if (conjunto.size() > 12)
			throw new ExcepcionRegaloNoValido("Las cabalgatas no pueden ser de más de doce carrozas");

	}

	///// ****EQUALS****/////

	public boolean equals(Object o) {

		boolean res = false;

		if (o instanceof Niño) {
			Niño n = (Niño) o;

			if (this.castigos.equals(n.getNumeroCastigos()))
				res = true;

		}

		return res;

	}

	public int hashCode() {

		return super.hashCode() + this.getNumeroCastigos() * 31 * 31 * 31;

	}

	public String toString() {

		return super.toString() + "(" + this.getListaRegalos() + ")";

	}

}