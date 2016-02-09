/*
 * Code Written By Eyog Yvon Leonce -FE15P011

 */
package sensorapp.sensors.pojo;

/**
 *
 * @author leo
 */
public class ComboItem {

    private String key;
    private String value;

    public ComboItem(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
     public ComboItem( String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return key;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
