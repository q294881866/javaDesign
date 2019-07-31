package javase.other.reusable;

import java.util.Observer;
import java.util.Vector;

public class Observable extends java.util.Observable {
    private boolean changed = false;
    private Vector<Observer> obs;

    public Observable() {
        obs = new Vector<>();
    }

    @Override
    public synchronized void addObserver(Observer o) {
        if (!obs.contains(o)) {
            obs.addElement(o);
        }
    }

    @Override
    public void notifyObservers(Object arg) {
        Object[] arrLocal;
        synchronized (this) {
            if (!changed) {
                return;
            }
            arrLocal = obs.toArray();
            clearChanged();
        }
        for (int i = arrLocal.length - 1; i >= 0; i--) {
            ((Observer) arrLocal[i]).update(this, arg);
        }
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        obs.removeElement(o);
    }

    @Override
    protected synchronized void setChanged() {
        changed = true;
    }

    @Override
    protected synchronized void clearChanged() {
        changed = false;
    }

    @Override
    public synchronized boolean hasChanged() {
        return changed;
    }

}

