/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeratasa.webservice;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author dardo
 */
@Path("DukeEdad")
public class DukeEdadRecurso {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DukeEdadRecurso
         */
    public DukeEdadRecurso() {
    }

    /**
     * Retrieves representation of an instance of primeratasa.webservice.DukeEdadRecurso
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getText() {
        // Creamos un nuevo calendario para el cumpleaños de Duke
        Calendar dukesBirthday=new GregorianCalendar(1995, Calendar.MAY, 23);
        // Creamos un nuevo calendario para hoy
        Calendar now=GregorianCalendar.getInstance();
        // Restamos el año de hoy con el de Duke
        int dukeEdad=now.get(Calendar.YEAR) - dukesBirthday.get(Calendar.YEAR);
        dukesBirthday.add(Calendar.YEAR,dukeEdad);
        // Si la fecha de hoy es anterior a Mayo 23, restamos un año de la edad de Duke
        if (now.before(dukesBirthday)){
            dukeEdad--;
        }
        // Retornamos un String que representa la edad de Duke
        return ""+dukeEdad;
    }

/**
     * PUT method for updating or creating an instance of DukeEdadRecurso
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public void putText(String content) {
    }
}
