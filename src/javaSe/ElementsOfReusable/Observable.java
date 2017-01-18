package ElementsOfReusable;

import java.util.Observer;
import java.util.Vector;

public class Observable extends java.util.Observable{
    private boolean changed = false;
    private Vector<Observer> obs;
    public Observable() {  obs = new Vector<>(); }

    public synchronized void addObserver(Observer o) {
        if (o == null)  throw new NullPointerException();
        if (!obs.contains(o)) {
            obs.addElement(o);
        }
    }

    public void notifyObservers(Object arg) {
        Object[] arrLocal;
        synchronized (this) {
            if (!changed)
                return;
            arrLocal = obs.toArray();
            clearChanged();
        }
        for (int i = arrLocal.length-1; i>=0; i--)
            ((Observer)arrLocal[i]).update(this, arg);
    }

    public synchronized void deleteObserver(Observer o) {  obs.removeElement(o); }
    protected synchronized void setChanged() {  changed = true; }
    protected synchronized void clearChanged() {  changed = false; }
    public synchronized boolean hasChanged() {  return changed;  }

}

