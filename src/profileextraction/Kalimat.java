/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profileextraction;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adiel
 */
public class Kalimat {
    private String kalimat = "";
    private List<String> listKata = new ArrayList<String>();

    public Kalimat(String kalimat) {
        this.kalimat=kalimat;
        segmentationKata();
    }

    public void segmentationKata(){
        String kata2[] = kalimat.split(" ");
        /*for(int i = 0; i < kata2.length; i++){
            if(kata2[i].matches("(.+)(\\W)")){
                System.out.println(kata2[i]);
            }
            
        }*/
        for(int i = 0; i < kata2.length ; i++){
            /*
            if(kata2[i].matches("(.+)(\\W)")){
                getKata().add(kata2[i].substring(0,kata2[i].length()-1));
                getKata().add(kata2[i].substring(kata2[i].length()-1,kata2[i].length()));
            }
            else{
                getKata().add(kata2[i]);
            }
            */
            if(!kata2[i].replaceAll("[^a-zA-Z0-9']+", "").equals("")){
                getListKata().add(kata2[i].replaceAll("[^a-zA-Z0-9']+", "").toLowerCase());
            }
        }
    }

    public List<String> getListKata() {
        return listKata;
    }

    public String getKalimat() {
        return kalimat;
    }

    
    
    
    
}
