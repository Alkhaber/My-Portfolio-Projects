import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

public class SubsekvensRegister {
     ArrayList<HashMap<String, Subsekvens>> ListOfHashMaps = new ArrayList<HashMap<String, Subsekvens>>();;
    static int antallHashLeset=0;
    
    public  void Settinn(HashMap<String, Subsekvens> hashMap){
        ListOfHashMaps.add(hashMap);
    }
    
    
    public HashMap<String, Subsekvens> TaUtHashMap(){
        if (ListOfHashMaps.isEmpty()) {
            return null; 
        }
        else{
            /// det står i oppgaven at jeg må velge et vilkårlig hashmap, men jeg synes det er letter sånn og fortsatt effektiv. 
        HashMap<String, Subsekvens> valgteHashMapp = ListOfHashMaps.get(0);
        ListOfHashMaps.remove(0);
        return valgteHashMapp;
        
        }
    
    }

    public Integer HvorMangeHashMaps(){
        return ListOfHashMaps.size();
    }

     public String toString(){
        return ListOfHashMaps.toString();
     }

    public static HashMap<String, Subsekvens> leser(String fileString) {
            HashMap<String, Subsekvens> hashMappen = new HashMap<String, Subsekvens>();
            
            
            
            try{
            File filen = new File(fileString);
            Scanner FilLinjer = new Scanner(filen);
            
            while (FilLinjer.hasNextLine()){
                String Linjen = FilLinjer.nextLine();

                ArrayList<Character> listeAvLinjen = new ArrayList<>();

                for (int i =0; i < Linjen.length(); i++){
                    char ch = Linjen.charAt(i);
                    listeAvLinjen.add(ch);
                }

                
                int antGanger = 0;

                for (char i : listeAvLinjen){
                   String stringen = "";
                   stringen += i;
                    for (int z =0; z< 2; z++){
                        antGanger++;
                        if (antGanger>= listeAvLinjen.size()){
                            break;
                        }
                        stringen += listeAvLinjen.get(antGanger);
                    }
                    if (stringen.length()== 3){
                        
                        Subsekvens sek = new Subsekvens(stringen, 1);
                        hashMappen.put(stringen, sek);
                        
                    }

                    antGanger -=1;
                }
                
            }
            FilLinjer.close();
        }catch(FileNotFoundException e){
            System.out.println("no file");
            return null;
        }
        return hashMappen;
            
    }


    public static HashMap<String, Subsekvens> SlaaSammen(HashMap<String, Subsekvens> hashMap1, HashMap<String, Subsekvens> hashMap2){
        HashMap<String,Subsekvens> sluttHashMap = new HashMap<>();
        sluttHashMap.putAll(hashMap2);

        for (String i : hashMap1.keySet()) {
            if (!hashMap2.keySet().contains(i)){
                sluttHashMap.put(i, hashMap1.get(i));
            }
            else{
                Subsekvens sub= sluttHashMap.get(i);
                Subsekvens sub2 = hashMap1.get(i);
                sub.PlussForekomster(sub.hentAntForekomster()+sub2.hentAntForekomster());
            } 
        }
        
        
        return sluttHashMap;
    }

    
}


