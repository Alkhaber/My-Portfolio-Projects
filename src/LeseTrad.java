
public class LeseTrad implements Runnable{
    String filen;
    Monitor2 moni;
    public LeseTrad(String fil, Monitor2 mon){
        filen = fil;
        moni= mon;
    }
    public void run() {
       
    moni.SettInnhashmapSlaat(SubsekvensRegister.leser(filen));

        
    }

}
