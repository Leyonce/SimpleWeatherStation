/*
 * Eyog Yvon Leonce Fe15P011
 */
package sensorapp.appinterfaces;

/**
 *
 * @author leo
 */
public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o); 
    public void notifyObservers();
    
}
