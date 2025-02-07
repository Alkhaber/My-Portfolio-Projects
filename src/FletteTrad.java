import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;


public class FletteTrad implements Runnable{
    CountDownLatch count;
    Monitor2 monitor;
    FletteTrad(CountDownLatch counten, Monitor2 moni){
        count= counten;
        monitor= moni;
    }
    
    public void run(){
        while(count.getCount() != 0){
           
            count.countDown();
            ArrayList<HashMap<String,Subsekvens>> hash1 = monitor.TaUtHashmapMonito2();
            
            
                if (hash1 !=null){
                    monitor.SettInnhashmapSlaat(SubsekvensRegister.SlaaSammen(hash1.get(0), hash1.get(1)));
                }

        
            
        }
    }
}
