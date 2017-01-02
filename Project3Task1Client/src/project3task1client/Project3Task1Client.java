/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3task1client;

import java.util.Date;

/**
 *
 * @author Jiaming
 */
public class Project3Task1Client {

    private static Signature singnature = new Signature();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // first high temperature
        String s1 = sendTemperature("1", "23");
        System.out.println("s1 : " + s1);

        // second low temperature
        String s2 = sendTemperature("2", "3");
        System.out.println("s2 : " + s2);

        // third high temperature
        String s3 = sendTemperature("1", "24");
        System.out.println("s3 : " + s3);

        // fourth high temperature
        String s4 = sendWrongHighTemperature( "100");
        System.out.println("s4 : " + s4);
        
        // Get Last Temperature from Sensor 1
        String LastTempSensor1 = getLastTemperature("1");
        System.out.println("LastTempSensor1 : " + LastTempSensor1);
        
        // Get Last Temperature from Sensor 2
        String LastTempSensor2 = getLastTemperature("2");
        System.out.println("LastTempSensor2 : " + LastTempSensor2);
        
        // Get all Temperature 
        String allTemp = getTemperatures();
        System.out.println("allTemp : " + allTemp);
    }
    
    public static String sendWrongHighTemperature(String temperature){
         Date date = new Date();
        String timeStamp = date.getTime() + "";
        String type = "Celsius";
        String sign = "13513453246541235134512351251";
        
        return highTemperature("1", timeStamp, type, temperature, sign);
    }

    public static String sendTemperature(String sensorID, String temperature) {
        Date date = new Date();
        String timeStamp = date.getTime() + "";
        String type = "Celsius";
        String sign;
        if (sensorID.equals("1")) {
            sign = singnature.signSensor1(sensorID, timeStamp, type, temperature);
            return highTemperature(sensorID, timeStamp, type, temperature, sign);
        } else {
            sign = singnature.signSensor2(sensorID, timeStamp, type, temperature);
            return lowTemperature(sensorID, timeStamp, type, temperature, sign);
        }
    }

    private static String highTemperature(java.lang.String sensorID, java.lang.String timeStamp, java.lang.String type, java.lang.String temperature, java.lang.String signature) {
        project3task1server.Project3Task1Server_Service service = new project3task1server.Project3Task1Server_Service();
        project3task1server.Project3Task1Server port = service.getProject3Task1ServerPort();
        return port.highTemperature(sensorID, timeStamp, type, temperature, signature);
    }

    private static String lowTemperature(java.lang.String sensorID, java.lang.String timeStamp, java.lang.String type, java.lang.String temperature, java.lang.String signature) {
        project3task1server.Project3Task1Server_Service service = new project3task1server.Project3Task1Server_Service();
        project3task1server.Project3Task1Server port = service.getProject3Task1ServerPort();
        return port.lowTemperature(sensorID, timeStamp, type, temperature, signature);
    }

    private static String getLastTemperature(java.lang.String sensorID) {
        project3task1server.Project3Task1Server_Service service = new project3task1server.Project3Task1Server_Service();
        project3task1server.Project3Task1Server port = service.getProject3Task1ServerPort();
        return port.getLastTemperature(sensorID);
    }

    private static String getTemperatures() {
        project3task1server.Project3Task1Server_Service service = new project3task1server.Project3Task1Server_Service();
        project3task1server.Project3Task1Server port = service.getProject3Task1ServerPort();
        return port.getTemperatures();
    }

}
