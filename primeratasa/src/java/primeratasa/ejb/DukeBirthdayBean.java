/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeratasa.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import primeratasa.entity.FirstcupUser;

/**
 *
 * @author dardo
 */
@Stateless
public class DukeBirthdayBean {
    @PersistenceContext
    private EntityManager em;
    private static final Logger logger =
        Logger.getLogger("primeratasa.ejb.DukeBirthdayBean");

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Double getAverangeAgeDifference(){
        Double avgAgeDiff = 
                (Double) em.createNamedQuery("findAverangeAgeDifferenceOfAllPrimeraTasaUsers").getSingleResult();
        logger.info("Desde el Bean La diferencia promedio de edad es: "+avgAgeDiff);
        return avgAgeDiff;
    }
    public int getAgeDifference(Date date){
        int ageDifference;
        
        Calendar theirBirthday = new GregorianCalendar();
        Calendar dukesBirthday = new GregorianCalendar(1995,Calendar.MAY,23);
        // Seteamos el objeto calendario con el pasado en Date
        theirBirthday.setTime(date);
        // Restamos la edad del usuario con la de Duke
        ageDifference=dukesBirthday.get(Calendar.YEAR)-
                theirBirthday.get(Calendar.YEAR);
        logger.info("Desde el Bean La diferencia de edad en bruto es: "+ageDifference);
        // Chequeamos para ver si el cumple de Duke ocurre antes que el del usuario.
        if (dukesBirthday.before(theirBirthday)&&(ageDifference>0)) {
            ageDifference--;
        }
        //Creamos y grabamos al usuario en la base de datos.
        FirstcupUser user=new FirstcupUser(date, ageDifference);
        em.persist(user);
        logger.info("Desde el Bean La diferencia de edad final es: "+ageDifference);
        return ageDifference;
    }
}
