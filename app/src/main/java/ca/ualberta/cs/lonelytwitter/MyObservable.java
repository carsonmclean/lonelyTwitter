package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

/**
 * Created by carsonmclean on 7/10/15.
 */
public interface MyObservable {
    void addObserver(MyObserver o);
    void notifyObservers();
}
