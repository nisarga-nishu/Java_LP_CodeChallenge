package com.code;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.util.List;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;

public final class App {  
        public static void main(String[] args) throws Exception {

            String userHome = System.getProperty("user.dir");
            String fileName = userHome+"\\src\\main\\java\\com\\code\\Input.csv";
            String outFileName = userHome+"\\src\\main\\java\\com\\code\\Output.csv";
            File file = new File(outFileName);
            file.delete(); 
            //DEFINING OUTPUT HEADER OR COLUMN NAMES
            String[] header = {"Started","Finished","DurationSecs","FromStopId","ToStopId","ChargeAmount","CompanyId","BusID","PAN","Status" };
            CSVWriter csvWrite = new CSVWriter(new FileWriter(outFileName,true));
            csvWrite.writeNext(header);
            csvWrite.close();

            List<Input> beans = new CsvToBeanBuilder(new FileReader(fileName)).withType(Input.class).build().parse();

            CompletedTrip ct = new CompletedTrip();
            try {
                  ct.getCompTripCost(beans);
                } catch (ParseException e) {
                     e.printStackTrace();
                }    
        }
}

