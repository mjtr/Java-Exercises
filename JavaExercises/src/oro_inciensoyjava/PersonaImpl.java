package oro_inciensoyjava;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

//import fp.grados.excepciones.ExcepcionPersonaNoValida;

public class PersonaImpl implements Persona {

	/******************************* ATRIBUTOS **********************************/

	private String dni;
	private String nombre;
	private String apellidos;
	private String email;
	private LocalDate fechanacimiento;

	/*********************************
	 * CONSTRUCTORES
	 ******************************************/

	public PersonaImpl(String dni, String nombre, String apellidos, LocalDate fechanacimiento, String email) {

		CheckDNI(dni);
		CheckEmail(email);

		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechanacimiento = fechanacimiento;
		this.email = email;

	}

	public PersonaImpl(String d, String n, String a, LocalDate f) {

		CheckDNI(d);
		dni = d;
		nombre = n;
		apellidos = a;
		fechanacimiento = f;
		email = "";

	}

	public PersonaImpl(String s) {

		// �12345678H,Juan,L�pez Garc�a,20/01/1998,juan@acmemail.com�

		String[] res = s.split(",");

		if (res.length != 5) {

			throw new IllegalArgumentException("Cadena introducida incorrecta");
		}

		String d = res[0].trim();
		String nom = res[1].trim();
		String ap = res[2].trim();
		String em = res[4].trim();

		CheckDNI(d);
		CheckEmail(em);

		LocalDate fecha = LocalDate.parse(res[3].trim(), DateTimeFormatter.ofPattern("d/M/yyyy"));

		dni = d;
		nombre = nom;
		apellidos = ap;
		email = em;

		fechanacimiento = fecha;

	}

	// M�TODOS

	public String getDNI() {
		return this.dni;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public LocalDate getFechaNacimiento() {
		return this.fechanacimiento;
	}

	public String getEmail() {
		return this.email;
	}

	public Integer getEdad() {

		Period tiempoquepasa = Period.between(getFechaNacimiento(), LocalDate.now());
		return tiempoquepasa.getYears();

	}

	public void setDNI(String dni) {

		CheckDNI(dni);

		this.dni = dni;
	}

	public void setNombre(String nombre) {

		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setFechaNacimiento(LocalDate fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public void setEmail(String email) {

		CheckEmail(email);

		this.email = email;
	}

	/******************************* EQUALS ***************************************/

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Persona) {
			Persona p = (Persona) o;

			if (this.getDNI().equals(p.getDNI()) && this.getNombre().equals(p.getNombre())
					&& this.getApellidos().equals(p.getApellidos())) {
				res = true;
			}

		}
		return res;
	}

	/********************************
	 * HASHCODE
	 *************************************/

	public int hashCode() {
		return this.getDNI().hashCode() + this.getNombre().hashCode() * 31 + this.getApellidos().hashCode() * 31 * 31;
	}

	/***************************************
	 * COMPARETO
	 *****************************/

	public int compareTo(Persona p) {

		int persona = this.getApellidos().compareTo(p.getApellidos());
		if (persona == 0) {

			persona = this.getNombre().compareTo(p.getNombre());
			if (persona == 0) {
				persona = this.getDNI().compareTo(p.getDNI());
			}
		}
		return persona;

	}

	/*******************************
	 * TOSTRING
	 ***************************************/

	public String toString() {

		LocalDate fechanacimiento = getFechaNacimiento();
		String fechaModificada = fechanacimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		return this.getDNI() + "-" + this.getApellidos() + "," + getNombre() + "-" + fechaModificada;
	}

	/*****************************
	 * M�TODOS PRIVADOS
	 *************************************/

	private void CheckDNI(String dni) {
		Integer s = dni.length();

		if (s.equals(9)) {

			for (int i = 0; i < dni.length() - 1; i++) {
				if (!Character.isDigit(dni.charAt(i))) {

					// throw new ExcepcionPersonaNoValida("Los 8 primeros d�gitos del DNI tienen que
					// ser n�meros");

				} else if (!Character.isLetter(dni.charAt(dni.length() - 1))) {

					// throw new ExcepcionPersonaNoValida("El �ltimo d�gito del DNI tiene que ser
					// una letra ");}
				}

				if (!ComprobandoLetter(dni)) {

					// throw new ExcepcionPersonaNoValida("Letra introducida incorrecta");

				}

			}
		} else {

			// throw new ExcepcionPersonaNoValida("El tama�o del DNI tiene que ser 9 ");

		}
	}

	private Boolean ComprobandoLetter(String dni) {

		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
		Integer numDNI = new Integer(dni.substring(0, 8));
		Character s = dni.charAt(8);
		return s.equals(letras.charAt(numDNI % 23));
	}

	private void CheckEmail(String correo) {
		if (!(correo.isEmpty() || correo.contains("@"))) {

			// throw new ExcepcionPersonaNoValida("Email introducido no v�lido");
		}
	}

}
