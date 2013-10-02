package com.jeannius.tallycap.util;

public interface ObserverOfSubject {
	
	void update(Object o);
	int getCurrentMode();

	void update(String s);

}
