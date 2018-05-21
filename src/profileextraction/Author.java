/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profileextraction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author Adiel
 */
public class Author {
    private String name;
    private List<Document> docList = new ArrayList<Document>();
    private String feature;
    public Author(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public void addDocument(Document doc){
        getDocList().add(doc);
    }
   
    public List<Document> getDocList() {
        return docList;
    }
    public String generateFeature(Integer max){
        this.feature="";
        this.feature+=getName()+"\n";
        this.feature+=getAverageWordLength()+"\n";
        this.feature+=getAverageWordEachSentence()+"\n";
        this.feature+=getAverageSentenceEachParagraph()+"\n";
        this.feature+=getMostFrequentWord()+"\n";
        this.feature+=getAverageWordFrequencyClass(max)+"\n";
        return this.feature;
    }
    
    private String getAverageWordEachSentence(){
        int jumlahKalimat=0;
        int jumlahKata=0;
        for (Document document : docList) {
            for (Paragraf paragraf : document.getKumpulanParagraf()) {
                jumlahKalimat+=paragraf.getKalimat().size();
                for (Kalimat kalimat : paragraf.getKalimat()) {
                    jumlahKata+=kalimat.getListKata().size();
                }
            }
        }
        return "Average Word each Sentence(Average Length of Sentence): "+jumlahKata*1.00/jumlahKalimat*1.00;
    }

        
    private String getAverageSentenceEachParagraph(){
        int jumlahKalimat=0;
        int jumlahParagraf=0;
        for (Document document : docList) {
            jumlahParagraf+=document.getKumpulanParagraf().size();
            for (Paragraf paragraf : document.getKumpulanParagraf()) {
                jumlahKalimat+=paragraf.getKalimat().size();
            }
        }
        return "Average Sentence each Paragraph(Average Length of Paragraph): "+jumlahKalimat*1.00/jumlahParagraf*1.00;
    }

    private String getAverageWordLength() {
        int panjangKata=0;
        int jumlahKata=0;
        for (Document document : docList) {
            for (Paragraf paragraf : document.getKumpulanParagraf()) {
                for (Kalimat kalimat : paragraf.getKalimat()) {
                    jumlahKata+=kalimat.getListKata().size();
                    for (String string : kalimat.getListKata()) {
                        panjangKata+=string.length();
                    }
                }
            }
        }
        return "Average Length of Word : "+panjangKata*1.00/jumlahKata*1.00;
    }
    
    public Map<String,Integer> getMapOfWord(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
        for (Document document : getDocList()) {
            for (Paragraf paragraf : document.getKumpulanParagraf()) {
                for (Kalimat kalimat1 : paragraf.getKalimat()) {
                    for (String string : kalimat1.getListKata()) {
                        if(map.get(string)==null){
                            map.put(string, 1);
                        }else{
                            map.put(string, map.get(string)+1);
                        }
                    }
                }
            }
        }
        
        Map<String,Integer> sortedMap = sortByValues(map); 

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        return sortedMap;
    }
    
    
    
     private static HashMap sortByValues(HashMap map) { 
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
             public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                   .compareTo(((Map.Entry) (o1)).getValue());
             }
        });

       
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
               Map.Entry entry = (Map.Entry) it.next();
               sortedHashMap.put(entry.getKey(), entry.getValue());
        } 
        return sortedHashMap;
    }
     
    public String getAverageWordFrequencyClass(Integer max){
        int counter = 0;
        double sum = 0;
        for (Map.Entry<String, Integer> entry : getMapOfWord().entrySet()) {
            Integer temp = entry.getValue();
            System.out.println(max*1.00/temp*1.00);
            System.out.println(Math.log10((double)max*1.00/temp*1.00) / Math.log10(2.)+"\n");
            sum+=Math.log10((double)max*1.00/temp*1.00) / Math.log10(2.);
            counter++;
        }
        return "Average Word Frequency Class : "+sum/counter*1.00;
    }
    
    private String getMostFrequentWord(){
        Map<String,Integer> map = getMapOfWord();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            return "Most Frequent Word "+entry.getKey()+":"+entry.getValue();
        }
        return null;
    }
}
