package fi.hjhamala.model;

public abstract class Temperature {
	
	
	public static double measurementValueToCelsiusValue(int value){
		return value*0.2222-61.111;
	}
}
