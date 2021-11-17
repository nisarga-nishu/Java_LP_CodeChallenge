package com.code;
import java.text.ParseException;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class Input {


    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "id")  
              
    private int Id;

    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "DateTime")
    private String dateTime;

    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "tap Type")
    private String tapType;

    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "stop id")
    private String stopId;

    @CsvBindByPosition(position = 4)
    @CsvBindByName(column = "company id")
    private String companyId;


    @CsvBindByPosition(position = 5)
    @CsvBindByName(column = "bus id")
    private String busId;

    @CsvBindByPosition(position = 6)
    @CsvBindByName(column = "PAN")
    private long pan;

    //  getters, setters, toString

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDateTime() {
       
        return dateTime;
    }

    public void setDateTime(String dateTime) throws ParseException {
        
        this.dateTime = dateTime;
    }

    public String getTapType() {
        return tapType;
    }

    public void setTapType(String tapType) {
        this.tapType = tapType;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public long getPan() {
        return pan;
    }

    public void setPan(long pan) {
        this.pan = pan;
    }

    @Override
    public String toString() {
        return "Input [ busId=" + busId + ", companyId=" + companyId + ", dateTime=" + dateTime 
         + ", stopId=" + stopId + ", tapType=" + tapType + "]";
    }


    
}