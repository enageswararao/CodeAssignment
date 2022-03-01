package beans;

import enums.TemperatureUnitsEnum;

import java.text.DecimalFormat;
import java.text.ParseException;

public class Temperature implements Comparable{
    private String units;
    private double temperature;

    private static final double VARIANCE = 0.25d;
    private static final String ROUND_OFF_FORMAT = "###.00";

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public int compareTo(Object o) {
        if(o == null && this !=null)
            return -1;
        else if(o!=null && this==null)
            return 1;
        else if(o==null && this==null)
            return 1;
        else {
            Temperature t1 = (Temperature) o;

            //If two units are same
            if(t1.getUnits().equals(this.getUnits())){
                if(roundOffTemperature(Math.abs(t1.getTemperature() - this.getTemperature())) <= VARIANCE ){
                    return 0;
                }
                return Double.compare(t1.getTemperature(), this.getTemperature());
            }

            //If two units are different
            //Converting to standard (Kelvin) format as per API spec
            double temp1InStdFormat = getTemperatureInStandardFormat(t1);
            double temp2InStdFormat = getTemperatureInStandardFormat(this);

            System.out.println("After conversion >> "+temp1InStdFormat + " "+temp2InStdFormat);
            double tempDiff = Math.abs(temp1InStdFormat - temp2InStdFormat);
            if(roundOffTemperature(tempDiff) <= VARIANCE ){
                return 0;
            }
            return Double.compare(temp1InStdFormat, temp2InStdFormat);
        }
    }

    private double getTemperatureInStandardFormat(Temperature temp){
        switch(TemperatureUnitsEnum.valueOf(temp.getUnits().toUpperCase())){
            case METRIC: return temp.getTemperature() + 273.15d;
            case IMPERIAL: return  (temp.getTemperature() + 459.67) * 5/9;
            default: return temp.getTemperature();
        }
    }

    private double roundOffTemperature(double temperature){
        DecimalFormat df = new DecimalFormat(ROUND_OFF_FORMAT);
        String format = df.format(temperature);
        try {
            temperature = (Double)df.parse(format) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return temperature;
    }
}
