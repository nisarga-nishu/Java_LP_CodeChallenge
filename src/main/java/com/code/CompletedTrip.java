package com.code;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CompletedTrip {

    public double Stop1_2 = 3.25;
    public double Stop2_3 = 5.50;
    public double Stop3_1 = 7.30;
    public double chargeAmount =0.0;
    public long duration =0;
    public String stopId1 = null;
    public String stopId2 = null;
    public Date date1 ;
    public Date date2 ;
    public String formattedDate1;
    public String formattedDate2;
    public String companyId = null;
    public String busId = null;
    public long panId = 0;
    public int id ;
    public String status = null;
    public int id1;
    public int id2;
    public HashSet<Integer> hs = new HashSet<Integer>();


    public void getCompTripCost(List<Input> li) throws Exception{
        
        List<Input> list = li;
        CompletedTrip ct = new CompletedTrip();
        IncompleteTrip it = new IncompleteTrip();
        Output op = new Output();
        DateFormat df = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
            for(int i =0; i<list.size();i++){
                if(list.get(0).getTapType().equals("OFF")){
                    throw new Exception("First tap cannot be OFF") ;
                }
                    String tap = list.get(i).getTapType();
                    ct.panId = list.get(i).getPan();
                    ct.busId = list.get(i).getBusId();
                    ct.date1 = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss").parse(list.get(i).getDateTime());
                    ct.stopId1 = list.get(i).getStopId();
                    ct.companyId = list.get(i).getCompanyId();
                    ct.id = list.get(i).getId();
                    String date1, date2 ;

                    for(int j=i+1;j<list.size();j++){
                        ct.date2 = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss").parse(list.get(j).getDateTime());

                            // FIND COMPLETE TRANSACTION

                        if(tap.equals("ON") && ct.panId == list.get(j).getPan() && ct.busId.equals(list.get(j).getBusId()) 
                            && list.get(j).getTapType().equals("OFF") && ct.companyId.equals(list.get(j).getCompanyId())){

                            ct.id1 = list.get(i).getId();
                            ct.id2 = list.get(j).getId();
                            ct.hs.add(ct.id1); //ADDING MATCHED IDs TO HASHSET
                            ct.hs.add(ct.id2);

                            ct.stopId2 = list.get(j).getStopId();
                            long duration = ct.date2.getTime()-ct.date1.getTime();
                            ct.duration = TimeUnit.MILLISECONDS.toSeconds(duration);

                            //CHARGE AMOUNT b/w STOP 1 & 2
                            
                            if(ct.stopId1.equals("Stop1") && ct.stopId2.equals("Stop2")|| ct.stopId1.equals("Stop2") && ct.stopId2.equals("Stop1")){
                                ct.chargeAmount = ct.Stop1_2;
                                ct.status = "COMPLETED";
                                date1 = df.format(ct.date1);
                                date2 = df.format(ct.date2);
                                op.writeOutput(date1, date2, ct.duration, ct.stopId1, ct.stopId2, ct.chargeAmount, ct.companyId, ct.busId, ct.panId, ct.status);
                            }
                            //CHARGE AMOUNT b/w STOP 2 & 3
                            
                            else if(ct.stopId1.equals("Stop2") && ct.stopId2.equals("Stop3")||ct.stopId1.equals("Stop3") && ct.stopId2.equals("Stop2")){
                                ct.chargeAmount = ct.Stop2_3;
                                ct.status = "COMPLETED";
                                date1 = df.format(ct.date1);
                                date2 = df.format(ct.date2);
                                op.writeOutput(date1, date2, ct.duration, ct.stopId1, ct.stopId2, ct.chargeAmount, ct.companyId, ct.busId, ct.panId, ct.status);
                            }
                            //CHARGE AMOUNT b/w STOP 3 & 1

                            else if(ct.stopId1.equals("Stop3") && ct.stopId2.equals("Stop1")|| ct.stopId1.equals("Stop1") && ct.stopId2.equals("Stop3")){
                                ct.chargeAmount = ct.Stop3_1;
                                ct.status = "COMPLETED";
                                date1 = df.format(ct.date1);
                                date2 = df.format(ct.date2);
                                op.writeOutput(date1, date2, ct.duration, ct.stopId1, ct.stopId2, ct.chargeAmount, ct.companyId, ct.busId, ct.panId, ct.status);
                            } 
                            // NO CHARGE FOR CANCELLATION OF TRIP

                            else if(ct.stopId1.equals(ct.stopId2)){
                                ct.chargeAmount = 0.0;
                                ct.status = "CANCELLED";
                                date1 = df.format(ct.date1);
                                date2 = df.format(ct.date2);

                            //SENDING OUTPUT TO WRITE TO CSV FILE
                                op.writeOutput(date1, date2, ct.duration, ct.stopId1, ct.stopId2, ct.chargeAmount, ct.companyId, ct.busId, ct.panId, ct.status);
                            }
                            break;
                        }
                            //FIND DUPLICATE TAP ONs DUE TO SYSTEM/CARD ERROR

                        else if (ct.panId == list.get(j).getPan() && ct.busId.equals(list.get(j).getBusId()) && 
                            list.get(j).getTapType().equals(tap) && ct.companyId.equals(list.get(j).getCompanyId()) 
                            && ct.date1.equals(ct.date2)){
                                try{
                                ct.id1 = list.get(i).getId();
                                ct.id2 = list.get(j).getId();
                                ct.hs.add(ct.id1); //ADDING DUPLICATE TAP ON IDs TO HASHSET
                                ct.hs.add(ct.id2);
                                throw new Exception();
                                }
                                catch(Exception e)
                                {
                                System.out.println("Exception: Duplicate TAP ON found BY transaction ID: " + ct.id1 + " & " + ct.id2 + ". CARD ERROR");
                                }
                            }
                        }
            }
            for(int j=0;j<list.size();j++){
                if( !ct.hs.contains(list.get(j).getId())&& list.get(j).getTapType().equals("OFF"))
                {   // For STALE TAP OFF, Without ANY TAP ONs THROW EXCEPTION 
                    try{
                        throw new Exception();}
                        catch(Exception e){
                        System.out.println("Exception: Stale TAP OFF by transaction ID: " + list.get(j).getId() + ". CARD ERROR"); }
                }
            }
            for(int j=0;j<list.size();j++){
                if( !ct.hs.contains(list.get(j).getId())&& list.get(j).getTapType().equals("ON"))
                {
                    // For INCOMPLETE  TAP ONs                      
                    ct.panId = list.get(j).getPan();
                    ct.busId = list.get(j).getBusId();
                    ct.date1 = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss").parse(list.get(j).getDateTime());
                    ct.stopId1 = list.get(j).getStopId();
                    ct.companyId = list.get(j).getCompanyId(); 
                    it.getIncompleteTripCost(ct.panId, ct.busId, ct.date1, ct.stopId1, ct.companyId);                       
                }
            }
        }
        
    }
    
