package oro_inciensoyjava;

import java.time.LocalDate;

public interface Persona extends Comparable<Persona> {
   
	String getDNI();
	String getNombre();
	String getApellidos();
	LocalDate getFechaNacimiento();
	String getEmail();
	Integer getEdad();//Derivada
	
	void setDNI(String DNI);
	void setNombre(String Nombre);
	void setApellidos(String Apellidos);
	void setFechaNacimiento(LocalDate FechaNacimiento);
	void setEmail(String Email);
	
	
}
