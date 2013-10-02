package com.jeannius.tallycap.util;

import java.util.ArrayList;

public interface SubjectToObservers {
	
	public static final int VALUE_DEPENDENT =0;
	public static final int MAX_CODEPENDENT =1;
	
	void notifyObserver();
	void registerObserver(ObserverOfSubject obs);
	int getObserverCount();
	
	void removeObserver(ObserverOfSubject obs);
	void removeObserver();
}
