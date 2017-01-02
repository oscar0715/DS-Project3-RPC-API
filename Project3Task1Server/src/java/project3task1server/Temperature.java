/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3task1server;

/**
 *
 * @author Jiaming
 */
public class Temperature implements Comparable<Object>{

    String temperature;
    String timeStamp;

    public Temperature(String temperature, String timeStamp) {
        this.temperature = temperature;
        this.timeStamp = timeStamp;
    }

    /**
     * Temperature will be ordered by time
     * @param o
     * @return 
     */
    @Override
    public int compareTo(Object o) {
        Temperature obj = (Temperature)o;
        return this.timeStamp.compareTo(obj.timeStamp);
    }

    @Override
    public String toString() {
        return temperature;
    }
}
