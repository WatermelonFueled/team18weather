package ca.uwo.csd.cs2212.team18;

public class ShortTermData{
    private String[] temperature;
    private String[] skyCondition;
    
    ShortTermData(){
        temperature = new String[9];
        skyCondition = new String[9];
    }

    public String[] getTemperature() {
        return temperature;
    }

    public String[] getSkyCondition() {
        return skyCondition;
    }   
}