package mx.unam.ciencias.icc;

/**
 * Clase para bases de datos de estudiantes.
 */
public class BaseDeDatosEstudiantes extends BaseDeDatos {

    /**
     * Crea un estudiante en blanco.
     * @return un estudiante en blanco.
     */
    @Override public Registro creaRegistro() {
        Estudiante e = new Estudiante(null,0,0,0);
	return e;
    }

    /**
     * Busca estudiantes por un campo específico.
     * @param campo el campo del registro por el cuál buscar.
     * @param texto el texto a buscar.
     * @return una lista con los estudiantes tales que en el campo especificado
     *         contienen el texto recibido, o una lista vacía si el texto es
     *         <code>null</code> o la cadena vacía.
     * @throws IllegalArgumentException si el campo no es instancia de
     *         {@link CampoEstudiante}.
     */
    @Override public Lista buscaRegistros(Enum campo, String texto) throws IllegalArgumentException{
        if (!(campo instanceof CampoEstudiante)){
            throw new IllegalArgumentException("El campo debe ser " +
                                               "CampoEstudiante");
	}
        CampoEstudiante c = (CampoEstudiante)campo;
	Lista nueva = new Lista();
	if(this.registros.esVacia()){
	    return nueva;
	}
	if(texto==null){
	    return nueva;
	}
	if(texto==""){
	    return nueva;
	}
	Lista.Nodo n=this.registros.getCabeza();
	switch (c){
	case NOMBRE:
	    while(n!=null){
		Estudiante e =(Estudiante)n.get();
		String s = e.getNombre();
		if(s.contains(texto)){
		    nueva.agregaFinal(e);
		}
		n=n.getSiguiente();
	    }
	    return nueva;
	    
	case CUENTA:
	    while(n!=null){
		Estudiante e =(Estudiante)n.get();
		String s = String.valueOf(e.getCuenta());
		if(s.contains(texto)){
		    nueva.agregaFinal(e);
		}
		n=n.getSiguiente();
	    }
	    return nueva;

	case PROMEDIO:
	    while(n!=null){
		Estudiante e =(Estudiante)n.get();
		String s = String.format("%2.2f", e.getPromedio());
		if(s.contains(texto)){
		    nueva.agregaFinal(e);
		}
		n=n.getSiguiente();
	    }
	    return nueva;
	   	     
	default:
	     while(n!=null){
		Estudiante e =(Estudiante)n.get();
		String s = String.valueOf(e.getEdad());
		if(s.contains(texto)){
		    nueva.agregaFinal(e);
		}
		n=n.getSiguiente();
	    }
	    return nueva;
	    
	}
	
    }
}
