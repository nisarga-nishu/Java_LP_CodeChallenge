package com.code;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVWriter;

public  class Output {
    public List<String[]> fList= new ArrayList<String[]>();  



    public List<String[]> getfList() {
        return fList;
    }



    public void setfList(List<String[]> fList) {
        this.fList = fList;
    }



    public void writeOutput(String started, String finished, long duration, String fromStop, String toStop, double chargeAmount, String company, String bus, long pan, String status) throws IOException, InterruptedException {
     
        String sDuration = Long.toString(duration);
        String amount = Double.toString(chargeAmount);
        String sPan = Long.toString(pan);
        List<String[]> list= new ArrayList<String[]>();      
        String [] store = {started,finished,sDuration,fromStop,toStop,amount,company,bus,sPan,status};
        list.add(store);
        String userHome = System.getProperty("user.dir");
        String outFileName = userHome+"\\src\\main\\java\\com\\code\\Output.csv";
        CSVWriter csvWrite = new CSVWriter(new FileWriter(outFileName,true));
        setfList(list);
        csvWrite.writeAll(list);
        csvWrite.close();
    }
    
}
