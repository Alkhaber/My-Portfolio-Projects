
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor1 {
    SubsekvensRegister register= new SubsekvensRegister();

    Lock lockk = new ReentrantLock();

    public  void Settinn(HashMap<String, Subsekvens> hashMap){
        
        lockk.lock();
        try{
            register.Settinn(hashMap);
            
        }finally{
            lockk.unlock();
        }
    }

    public synchronized HashMap<String, Subsekvens> TaUtHashMap(){
        return register.TaUtHashMap();
    }
}
