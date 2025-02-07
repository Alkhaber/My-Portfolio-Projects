
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.management.monitor.Monitor;


public class Monitor2 {
    
    SubsekvensRegister registerMonitor2 = new SubsekvensRegister();
    
     Lock locken = new ReentrantLock();

     Condition ikkeInholderTo = locken.newCondition();

    public SubsekvensRegister hentSubsekvensRegisterMoni2(){
        return registerMonitor2;
    }
    
    

    ArrayList<HashMap<String, Subsekvens>> TaUtHashmapMonito2()  {
        locken.lock();
        try{
            ArrayList <HashMap<String, Subsekvens>> ArrayfortwoHashes = new ArrayList<>();
            while (registerMonitor2.HvorMangeHashMaps() <=1) {
                ikkeInholderTo.await();
            }
            
            HashMap<String, Subsekvens> hashmapp1= registerMonitor2.TaUtHashMap();
            HashMap<String, Subsekvens> hashmapp2= registerMonitor2.TaUtHashMap();

            ArrayfortwoHashes.add(hashmapp1);
            ArrayfortwoHashes.add(hashmapp2);
            return ArrayfortwoHashes;
        }catch(Exception e){
            return null;
        }
        finally{locken.unlock();}
    }

   

        

    public void SettInnhashmapSlaat(HashMap<String, Subsekvens> hash){
        locken.lock();
        try{
            registerMonitor2.Settinn(hash);
            ikkeInholderTo.signalAll();
            
        }
        finally{locken.unlock();}
    }

    
}



