/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeratasa.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import primeratasa.ejb.DukeBirthdayBean;

/**
 *
 * @author dardo
 */
@ManagedBean
@SessionScoped
public class DukesBDay {

    @EJB
    private DukeBirthdayBean dukeBirthdayBean;

    protected int age;
    protected Date yourBD;
    protected int ageDiff;
    protected int absAgeDiff;
    protected Double averageAgeDifference;
    private static Logger logger = Logger.getLogger("firstcup.web.DukesBDay");
    protected String mensaje;
    /**
     * Creates a new instance of DukesBDay
     */
    public DukesBDay() {
        age = -1;
        yourBD = null;
        ageDiff = -1;
        absAgeDiff = -1;
        mensaje = "";
    }
    /**
     * @return the logger
     */
    public static Logger getLogger() {
        return logger;
    }

    /**
     * @param aLogger the logger to set
     */
    public static void setLogger(Logger aLogger) {
        logger = aLogger;
    }
    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the yourBD
     */
    public Date getYourBD() {
        return yourBD;
    }

    /**
     * @param yourBD the yourBD to set
     */
    public void setYourBD(Date yourBD) {
        this.yourBD = yourBD;
    }

    /**
     * @return the ageDiff
     */
    public int getAgeDiff() {
        return ageDiff;
    }

    /**
     * @param ageDiff the ageDiff to set
     */
    public void setAgeDiff(int ageDiff) {
        this.ageDiff = ageDiff;
    }

    /**
     * @return the absAgeDiff
     */
    public int getAbsAgeDiff() {
        return absAgeDiff;
    }

    /**
     * @param absAgeDiff the absAgeDiff to set
     */
    public void setAbsAgeDiff(int absAgeDiff) {
        this.absAgeDiff = absAgeDiff;
    }
    
    /**
     * @return the averageAgeDifference
     */
    public Double getAverageAgeDifference() {
        return averageAgeDifference;
    }

    /**
     * @param averageAgeDifference the averageAgeDifference to set
     */
    public void setAverageAgeDifference(Double averageAgeDifference) {
        this.averageAgeDifference = averageAgeDifference;
    }
    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public String processBirthday(){
        try {
            this.setAbsAgeDiff((dukeBirthdayBean.getAgeDifference((yourBD))));
            this.ageDiff=this.getAbsAgeDiff();
            logger.info("age diff from dukesbday" + ageDiff);
            this.setAbsAgeDiff(Math.abs(this.getAbsAgeDiff()));
            logger.info("absAgeDiff" + absAgeDiff);
            this.setAverageAgeDifference(dukeBirthdayBean.getAverangeAgeDifference());
            logger.info("absAgeDiff" + averageAgeDifference);
            return "/response.xhtml";   
        } catch (Exception e) {
            logger.info("Error del controlador" + e.getMessage());
            this.setMensaje("El formato de fecha no es valido");
            return "/greeting.xhtml";
        }
    }
    public int getAge() {
        // Use the java.net.* APIs to access the Duke's Age RESTful web service
        HttpURLConnection connection = null;
        BufferedReader rd = null;
        StringBuilder sb = null;
        String line = null;
        URL serverAddress = null;

        try {
            serverAddress = new URL(
                    "http://localhost:8080/DukeEdadService/webresources/DukeEdad");
            connection = (HttpURLConnection) serverAddress.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setReadTimeout(10000);

            // Make the connection to Duke's Age
            connection.connect();

            // Read in the response
            rd = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            sb = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            // Convert the response to an int
            age = Integer.parseInt(sb.toString());
        } catch (MalformedURLException e) {
            getLogger().warning("A MalformedURLException occurred.");
            e.printStackTrace();
        } catch (ProtocolException e) {
            getLogger().warning("A ProtocolException occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            getLogger().warning("An IOException occurred");
            e.printStackTrace();
        }

        return age;
    }


}
