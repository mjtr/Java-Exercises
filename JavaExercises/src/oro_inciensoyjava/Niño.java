package oro_inciensoyjava;

import java.util.List;
import java.util.Set;

public interface Niño extends Persona {

	
	Integer getNumeroCastigos();
	Boolean Carbon();
	List<Regalo> getListaRegalos();
	Set<Carroza> getCabalgata();
	
	void añadeRegalo(Regalo r);
	void eliminaRegalo(Regalo r);
	void añadeCarroza(Carroza c);
}
