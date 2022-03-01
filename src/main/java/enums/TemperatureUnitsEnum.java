package enums;

public enum TemperatureUnitsEnum {

    STANDARD("standard"), METRIC("metric"), IMPERIAL("imperial");

    private String description;

    TemperatureUnitsEnum(String desc){
        this.description = desc;
    }
}
