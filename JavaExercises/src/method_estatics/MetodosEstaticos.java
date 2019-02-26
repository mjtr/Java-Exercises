package method_estatics;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import oro_inciensoyjava.Carroza;
import oro_inciensoyjava.Niño;
import oro_inciensoyjava.Regalo;
import oro_inciensoyjava.TipoRegalo;

public class MetodosEstaticos {

	/*
	 * método estático llamado niñosMalos, que reciba una lista de niños y diga
	 * cuántos niños van a recibir carbón
	 */

	public static Integer NiñosMalo(List<Niño> lista) {
		Integer res = 0;

		for (Niño n : lista) {

			if (n.Carbon())
				res++;

		}

		return res;

	}

	/*
	 * regaloMasCaro, que reciba una lista de regalos y un regalo y devuelva el
	 * primer regalo que sea más caro que el recibido por parámetro, siempre y
	 * cuando se encuentre en stock
	 */

	public static Regalo regaloMasCaro(List<Regalo> lista, Regalo r) {

		Regalo res = null;
		for (Regalo re : lista) {
			if (r.getPrecio().compareTo(re.getPrecio()) == -1 && re.enStock()) {
				res = re;
				break;
			}
		}

		return res;

	}

	/*
	 * carrozasMasLlenas que, dado un conjunto de carrozas y una carroza en
	 * concreto, me devuelva aquellas que tienen más asistentes que la recibida como
	 * parámetro. No está permitido la utilización de bucles tipo for o while
	 * 
	 */

	public static Set<Carroza> carrozasMasLlenas(Carroza car, Set<Carroza> conjunto) {

		return conjunto.stream().filter(x -> x.getAsistentes() > car.getAsistentes()).collect(Collectors.toSet());

	}

	/*
	 * regalosEnComun que, dado tres conjuntos de regalos, me devuelva aquellos
	 * regalos que están en los dos primeros conjuntos, pero no en el tercero. No
	 * puedes usar bucles
	 * 
	 */

	public static Set<Regalo> regalosEnComun(Set<Regalo> c1, Set<Regalo> c2, Set<Regalo> c3) {
		Set<Regalo> res = new HashSet<Regalo>(c1);

		res.retainAll(c2);
		res.removeAll(c3);

		return res;

	}

	/*
	 * regalosDeReyes que, dada una lista de Niños, me devuelva un conjunto ordenado
	 * por precios de regalos de aquellos niños que se han portado bien, siempre y
	 * cuando el regalo no sea un vale de descuento
	 */

	public static SortedSet<Regalo> regalosDeReyes(List<Niño> lista) {
		Set<Regalo> res = new HashSet<Regalo>();
		SortedSet<Regalo> res2 = new TreeSet<Regalo>();

		res = lista.stream().filter(x -> !x.Carbon()).flatMap(x -> x.getListaRegalos().stream())
				.filter(x -> !x.getTipoRegalo().equals(TipoRegalo.VALE_DESCUENTO))
				.sorted((p1, p2) -> p1.getPrecio().compareTo(p2.getPrecio())).collect(Collectors.toSet());

		res2.addAll(res);
		return res2;
	}

}
