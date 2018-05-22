package profileextraction;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rexy
 */
public class Paragraf {
    private String isiParagraf;
    private int parId = 0;
    private List<Kalimat> kalimat = new ArrayList();
    
    public Paragraf(String sentence, int id){
        this.isiParagraf = sentence;
        this.parId = id;
        this.segmentationKalimat();
        
    }
    public int getParId(){
        return this.parId;
    }
    public void segmentationKalimat(){
        /*String lower = isiParagraf.toLowerCase(); //lowercase
        String pattern = "[^\\w\\s]";
        String newString = lower.replaceAll(pattern, "");*/
        String kalimat2[] = getIsiParagraf().split("\\.|\\?|\\!");
        for (int i = 0; i < kalimat2.length; i++) {
            if(kalimat2[i].length()<10){
            }
        }
        for(int i = 0; i < kalimat2.length ; i++){
            //String temp = kalimat2[i].replaceAll("\\.", "");
            if(kalimat2[i].length()<20){
                int c=1;
                String s = kalimat2[i];
                
                while (s.length()<20) {     
                    if(kalimat2.length<i+c){
                        s+=" "+kalimat2[i+c];
                        c++;
                    }else{
                        break;
                    }
                }
                getKalimat().add(new Kalimat(s));
                i++;
            }else{
                getKalimat().add(new Kalimat(kalimat2[i]));
            }
        }
    }
    
    public void cetakSemuaKalimat(){
        System.out.println(getIsiParagraf());
    }
    public void cetakPerKata(){
        for (Kalimat kalimat1 : getKalimat()) {
            for (String string : kalimat1.getListKata()) {
                System.out.println(string);
            }
        }
        
        
    }
    public void cetakPerKalimat(){
        for (Kalimat kalimat1 : getKalimat()) {
            System.out.println(kalimat1.getKalimat());
        }
    }

    /**
     * @return the isiParagraf
     */
    public String getIsiParagraf() {
        return isiParagraf;
    }

    /**
     * @param isiParagraf the isiParagraf to set
     */
    public void setIsiParagraf(String isiParagraf) {
        this.isiParagraf = isiParagraf;
    }

    /**
     * @param parId the parId to set
     */
    public void setParId(int parId) {
        this.parId = parId;
    }

    public List<Kalimat> getKalimat() {
        return kalimat;
    }
    
    
}
