/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3task1server;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Jiaming
 */
@WebService(serviceName = "project3task1server")
public class project3task1server {

    private Authentication auth = new Authentication();
    List<Temperature> list1 = new ArrayList<>();
    List<Temperature> list2 = new ArrayList<>();

    /**
     * Web service operation
     */
    @WebMethod(operationName = "highTemperature")
    public String highTemperature(@WebParam(name = "sensorID") String sensorID, @WebParam(name = "timeStamp") String timeStamp, @WebParam(name = "type") String type, @WebParam(name = "temperature") String temperature, @WebParam(name = "signature") String signature) {
        //TODO write your implementation code here:
        boolean authResult = auth.authenticateSensor1(sensorID, timeStamp, type, temperature, signature);

        if (authResult) {
            list1.add(new Temperature(temperature, timeStamp));
            return "success! Insert high temperature " +temperature ;
        } else {
            return "invalid signature method";
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "lowTemperature")
    public String lowTemperature(@WebParam(name = "sensorID") String sensorID, @WebParam(name = "timeStamp") String timeStamp, @WebParam(name = "type") String type, @WebParam(name = "temperature") String temperature, @WebParam(name = "signature") String signature) {
        //TODO write your implementation code here:
        boolean authResult = auth.authenticateSensor2(sensorID, timeStamp, type, temperature, signature);
        if (authResult) {
            list2.add(new Temperature(temperature, timeStamp));
            return "success! Insert low temperature " +temperature ;
        } else {
            return "invalid signature method";
        }
    }

    /**
     * If ID is 1, we get the last high temperature
     * If ID is 2, we get the last low temperature
     * Web service operation
     */
    @WebMethod(operationName = "getLastTemperature")
    public String getLastTemperature(@WebParam(name = "sensorID") String sensorID) {
        //TODO write your implementation code here:
        if (sensorID.equals("1")) {
            if (list1.size() > 0) {
                return list1.get(list1.size() - 1).toString();
            }
            return "No record";

        } else {
            if (list2.size() > 0) {
                return list2.get(list2.size() - 1).toString();
            }
            return "No record";
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getTemperatures")
    public String getTemperatures() {
        //TODO write your implementation code here:
        List<Temperature> result = new ArrayList<>();
        result.addAll(list1);
        result.addAll(list2);
        // Temperature are sorted by the time when inserting
        Collections.sort(result);
        return result.toString();
    }

}
