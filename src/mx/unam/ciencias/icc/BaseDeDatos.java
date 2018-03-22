package mx.unam.ciencias.icc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Clase abstracta para bases de datos. Provee métodos para agregar y eliminar
 * registros, y para guardarse y cargarse de una entrada y salida dados.
 *
 * Las clases que extiendan a BaseDeDatos deben implementar el método {@link
 * #creaRegistro}, que crea un registro en blanco. También deben implementar el
 * método {@link #buscaRegistros} para hacer consultas en la base de datos.
 */
public abstract class BaseDeDatos {

    /** Lista de registros en la base de datos. */
    protected Lista registros;

    /**
     * Constructor único.
     */
    public BaseDeDatos() {
        registros  = new Lista();
    }

    /**
     * Regresa el número de registros en la base de datos.
     * @return el número de registros en la base de datos.
     */
    public int getNumRegistros() {
        return this.getLongitud();
    }

    /**
     * Regresa una lista con los registros en la base de datos. Modificar esta
     * lista no cambia a la información en la base de datos.
     * @return una lista con los registros en la base de datos.
     */
    public Lista getRegistros() {
        return this.copia();
    }

    /**
     * Agrega el registro recibido a la base de datos.
     * @param registro el registro que hay que agregar a la base de datos.
     */
    public void agregaRegistro(Registro registro) {
        this.agregaFinal(registro);
    }

    /**
     * Elimina el registro recibido de la base de datos.
     * @param registro el registro que hay que eliminar de la base de datos.
     */
    public void eliminaRegistro(Registro registro) {
        this.elimina(registro);
    }

    /**
     * Guarda todos los registros en la base de datos en la salida recibida.
     * @param out la salida donde hay que guardar los registos.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void guarda(BufferedWriter out) throws IOException {
        Lista.Nodo n= registros.getCabeza();
	while(n != null){
	    registros r =(registros) n.get();
	    r.guarda(out);
	    n=n.getSiguiente();
	}
    }

    /**
     * Carga los registros de la entrada recibida en la base de datos. Si antes
     * de llamar el método había registros en la base de datos, estos son
     * eliminados.
     * @param in la entrada de donde hay que cargar los registos.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void carga(BufferedReader in) throws IOException {
        registros.limpia();
	Registro r =this.creaRegistro();
	while(r.carga(in)){
	    registros.agregaFinal(r);
	    r=this.creaRegistro();	    
	}
    }

    /**
     * Crea un registro en blanco.
     * @return un registro en blanco.
     */
    public abstract Registro creaRegistro();

    /**
     * Busca registros por un campo específico.
     * @param campo el campo del registro por el cuál buscar.
     * @param texto el texto a buscar.
     * @return una lista con los registros tales que en el campo especificado
     *         contienen el texto recibido.
     * @throws IllegalArgumentException si el campo no es válido.
     */
    public abstract Lista buscaRegistros(Enum campo, String texto);
}
