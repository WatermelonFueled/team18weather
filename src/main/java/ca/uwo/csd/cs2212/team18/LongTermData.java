package ca.uwo.csd.cs2212.team18;

public class LongTermData{
    private String[] temperatureMin;
    private String[] temperatureMax;
    private String[] skyCondition;
    
    LongTermData(){
        temperatureMin = new String[7];
        temperatureMax = new String[7];
        skyCondition = new String[7];
    }

    public String[] getTemperatureMin() {
        return temperatureMin;
    }
    
    public String[] getTemperatureMax() {
        return temperatureMax;
    }

    public String[] getSkyCondition() {
        return skyCondition;
    }   
}