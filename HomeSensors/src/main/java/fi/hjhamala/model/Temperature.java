package fi.hjhamala.model;

public interface Temperature {
	
	default double measurementValueToCelsiusValue(int value){
		return value*0.2222-61.111;
	}
	
	default int CelsiusValueToMeasurementValue(double celsiusValue){
		return new Double((celsiusValue+61.111)/0.2222).intValue();
	}
	
	
	
	
}
