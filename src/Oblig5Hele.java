import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Oblig5Hele {
    static CountDownLatch LatchSyk;
    static CountDownLatch LatchHelthy;
    Monitor2 MonitorSyke;
    Monitor2 MonitorHelthy;
    public static void main(String[] args) throws Exception {
        Thread[] tradForSyke= new Thread[8];
        Thread[] tradForHelthy= new Thread[8];

        //lager monitor
        Monitor2 MonitorSyke = new Monitor2();
        Monitor2 MonitorHelthy = new Monitor2();

        //tar fil inn
        File fil1 = new File("metadata.csv");
        Scanner scc = new Scanner(fil1);

        //leser fil
        while (scc.hasNextLine()){
            String filen = scc.nextLine();
            String[] fileType = filen.split(",");
            filen = fileType[0];
            boolean TrueOrFalse = Boolean.parseBoolean(fileType[1]); 
            if (TrueOrFalse){
                LeseTrad lese = new LeseTrad(filen, MonitorSyke);
                Thread tradSyke = new Thread(lese);
                tradSyke.start();
                tradSyke.join();

            } else{
                LeseTrad lese = new LeseTrad(filen, MonitorHelthy);
            Thread tradHelthy = new Thread(lese);
            tradHelthy.start();
            tradHelthy.join();
            
            }
        }

        
        
        //lager latch
        int syk = MonitorSyke.hentSubsekvensRegisterMoni2().HvorMangeHashMaps()-1;
        int Helthy= MonitorHelthy.hentSubsekvensRegisterMoni2().HvorMangeHashMaps()-1;
        LatchSyk = new CountDownLatch(syk);
        LatchHelthy = new CountDownLatch(Helthy);
        
    //fletter syke
        for(int i = 0; i<8;i++){
            FletteTrad RunSyk = new FletteTrad(LatchSyk,MonitorSyke);
            Thread trad = new Thread(RunSyk);
            tradForSyke[i]= trad;
            trad.start();
            trad.join();
            LatchSyk.await();
        }
                
        //fletter Helthy
        for(int i = 0; i<8;i++){
            FletteTrad RunHelthy = new FletteTrad(LatchHelthy,MonitorHelthy);
            Thread traden = new Thread(RunHelthy);
            tradForHelthy[i]= traden;
            traden.start();
            traden.join();
            LatchHelthy.await();
        }

        scc.close();
   
        HashMap<String,Subsekvens> hashSyk = MonitorSyke.hentSubsekvensRegisterMoni2().ListOfHashMaps.get(0);
        HashMap<String,Subsekvens> hashHelthy = MonitorHelthy.hentSubsekvensRegisterMoni2().ListOfHashMaps.get(0);

        //printe ut
         for (String i : hashSyk.keySet()){
            if (!hashHelthy.keySet().contains(i)){
                if(hashSyk.get(i).hentAntForekomster()>4){
                    System.out.println(i +"-gen kommer med "+hashSyk.get(i).hentAntForekomster()+" ganger mer");
                }
            }
            else{
                if(hashSyk.get(i).hentAntForekomster()> hashHelthy.get(i).hentAntForekomster()){
                    int diff = hashSyk.get(i).hentAntForekomster()- hashHelthy.get(i).hentAntForekomster();
                    if (diff> 5){
                        System.out.println(i +"-gen kommer med "+ diff+ " ganger mer");
                    }
                    
                }
            }
         }
        
    }

}
