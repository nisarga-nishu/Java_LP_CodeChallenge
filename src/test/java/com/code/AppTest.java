package com.code;

import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.List;
import java.util.stream.IntStream;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

class AppTest {
    /**
     * Rigorous UNIT TEST.
     */
    CompletedTrip ct ;
    Output op;
    IncompleteTrip it;
    Input ip ;
   
    @BeforeEach
    public void initiateAll() throws Exception  {

        String userHome = System.getProperty("user.dir");
        ct = new CompletedTrip();
        op = new Output();
        it = new IncompleteTrip();
        ip = new Input();

        String outFileName = userHome+"\\src\\main\\java\\com\\code\\Output.csv";
        File file = new File(outFileName);
        //DEFINING OUTPUT HEADER OR COLUMN NAMES
        file.delete(); 
        String[] header = {"Started","Finished","DurationSecs","FromStopId","ToStopId","ChargeAmount","CompanyId","BusID","PAN","Status" };
        CSVWriter csvWrite = new CSVWriter(new FileWriter(outFileName,true));
        csvWrite.writeNext(header);
        csvWrite.close();      
    }

    @Test
    public void testCompletedTrip() throws Exception {

        String userHome = System.getProperty("user.dir");

        String fileName = userHome+"\\src\\main\\java\\com\\code\\Input.csv";
        List<Input> beans = new CsvToBeanBuilder(new FileReader(fileName)).withType(Input.class).build().parse();
        
        ct.getCompTripCost(beans);

        String testFileName = userHome+"\\src\\test\\java\\com\\code\\CompleteTripTestOutput.csv";

        CSVReader reader1 = new CSVReader(new FileReader(testFileName));
            List<String[]> testoutput = reader1.readAll();
         
    // EXPECTED V/S ACTUAL RESULT COMPARISON

        Assertions.assertAll(
             IntStream.range(0, op.getfList().size())
             .mapToObj(i -> () -> Assertions.assertArrayEquals(testoutput.get(i),
             op.getfList().get(i)))
);
    }

@Test
public void testCancelledTrip() throws Exception {

    String userHome = System.getProperty("user.dir");

    String fileName = userHome+"\\src\\main\\java\\com\\code\\Input.csv";
    List<Input> beans = new CsvToBeanBuilder(new FileReader(fileName)).withType(Input.class).build().parse();
    
    ct.getCompTripCost(beans);


    String testFileName = userHome+"\\src\\test\\java\\com\\code\\CancelledTripTestOutput.csv";

    CSVReader reader1 = new CSVReader(new FileReader(testFileName));
        List<String[]> testoutput = reader1.readAll();
     
            // EXPECTED V/S ACTUAL RESULT COMPARISON

    Assertions.assertAll(
         IntStream.range(0, op.getfList().size())
         .mapToObj(i -> () -> Assertions.assertArrayEquals(testoutput.get(i),
         op.getfList().get(i)))
);
    }

    @Test
public void testIncompleteTrip() throws Exception {

    String userHome = System.getProperty("user.dir");

    String fileName = userHome+"\\src\\main\\java\\com\\code\\Input.csv";
    List<Input> beans = new CsvToBeanBuilder(new FileReader(fileName)).withType(Input.class).build().parse();

    ct.getCompTripCost(beans);

    String testFileName = userHome+"\\src\\test\\java\\com\\code\\IncompleteTripTestOutput.csv";

    CSVReader reader1 = new CSVReader(new FileReader(testFileName));
        List<String[]> testoutput = reader1.readAll();
     
    // EXPECTED V/S ACTUAL RESULT COMPARISON
    
    Assertions.assertAll(
         IntStream.range(0, op.getfList().size())
         .mapToObj(i -> () -> Assertions.assertArrayEquals(testoutput.get(i),
         op.getfList().get(i)))
);
    }




}
