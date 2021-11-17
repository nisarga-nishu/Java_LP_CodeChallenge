package com.code;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IncompleteTrip {

    public void getIncompleteTripCost(long panId, String busId, Date date, String stopId, String companyId) throws IOException, InterruptedException{
        
        CompletedTrip ct = new CompletedTrip();
        DateFormat df = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");

        //CALCULATING THE HIGHER VALUE TO CHARGE FOR AN INCOMPLETE TRIP

        if(stopId.equals("Stop1")){
            ct.chargeAmount = ct.Stop3_1;
        }
        else if(stopId.equals("Stop2")){
            ct.chargeAmount = ct.Stop2_3;
        }
        else if(stopId.equals("Stop3")){
            ct.chargeAmount = ct.Stop3_1;
        }
        ct.status = "INCOMPLETE";
        ct.formattedDate2 = "N/A";
        ct.stopId2 = "N/A";
        String date1 = df.format(date);;
        Output op = new Output();

        //SENDING OUTPUT TO WRITE TO CSV FILE

        op.writeOutput(date1, ct.formattedDate2, ct.duration, stopId, ct.stopId2, ct.chargeAmount, companyId, busId, panId, ct.status);

    }
    
}
