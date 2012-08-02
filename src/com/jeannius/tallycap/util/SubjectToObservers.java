package com.jeannius.tallycap.util;

public interface SubjectToObservers {
	
	
	void notifyObserver();
	void registerObserver(ObserverOfSubject obs);
	void removeObserver();
}
