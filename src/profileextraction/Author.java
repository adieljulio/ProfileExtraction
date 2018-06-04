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
    public String generateFeature(Integer max,Integer maxAllCorpus){
        this.feature="";
        this.feature+=getName()+"\n";
        this.feature+=getAverageWordLength()+"\n";
        this.feature+=getAverageWordEachSentence()+"\n";
        this.feature+=getAverageSentenceEachParagraph()+"\n";
        this.feature+=getMostFrequentWord()+"\n";
        this.feature+=getAverageWordFrequencyClass(max,maxAllCorpus)+"\n";
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
        String output = "";
        output+="Word Count : "+jumlahKata+"\n";
        output+="Sentence Count : "+jumlahKalimat+"\n";
        output+="Average Word each Sentence(Average Length of Sentence): "+jumlahKata*1.00/jumlahKalimat*1.00;
   
        return output;
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
        String output = "";
        output+="Sentence Count : "+jumlahKalimat+"\n";
        output+="Paragraph Count : "+jumlahParagraf+"\n";
        output+="Average Sentence each Paragraph(Average Length of Paragraph): "+jumlahKalimat*1.00/jumlahParagraf*1.00;
   
        return output;
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
        String output = "";
        output+="Character count (without symbol) : "+panjangKata+"\n";
        output+="Word Count : "+jumlahKata+"\n";
        output+="Average Length of Word : "+panjangKata*1.00/jumlahKata*1.00;
        return output;
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
        /*
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        */
        return sortedMap;
    }
    
    
    
    protected static HashMap sortByValues(HashMap map) { 
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
     
    public String getAverageWordFrequencyClass(Integer max,Integer maxAllCorpus){
        
        HashMap<String,Integer> newmap = new LinkedHashMap();
        int temp=0;
        String temps="";
        
        //for di bawah ini digunakan untuk menggabungkan kata dengan jumlah yang sama akan di jadikan satu class.
        for (Map.Entry<String, Integer> entry : getMapOfWord().entrySet()) {
            if(temp==0){
                temp=entry.getValue();
                temps+=entry.getKey();
                continue;
            }
            Integer tempvalue = entry.getValue();
            if(temp!=tempvalue){
                newmap.put(temps, temp);
                temp = tempvalue;
                temps = entry.getKey();
                 
            }else{
                temps+=","+entry.getKey();
            }
            
            
            //System.out.println(max*1.00/temp*1.00);
            //System.out.println(Math.log10((double)max*1.00/temp*1.00) / Math.log10(2.)+"\n");
        }
        
        //selesai penggabungan
        
        System.out.println(this.name);
        for (Map.Entry<String, Integer> entry : newmap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key+':'+value);
        }
        Integer maxThisAuthor=0;
        for (Map.Entry<String, Integer> entry : newmap.entrySet()) {
            Integer value = entry.getValue();
            if(value>maxThisAuthor){
                maxThisAuthor=value;
            }
            break;
        }
        String output="";
        output+="\nMaximum Count Corpus this author : "+maxThisAuthor+"\n";
        int counter = 0;
        double sum = 0;
        for (Map.Entry<String, Integer> entry : newmap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            sum+=Math.log10((double)maxThisAuthor*1.00/value*1.00) / Math.log10(2.);
            counter++;
        }
        output+= "Average Word Frequency Class(use only this Author corpus) : "+sum/counter*1.00+"\n";
        
        
        output+="\nMaximum Count of Maximum Count Corpus each author : "+max+"\n";
        counter=0;
        sum=0;
        for (Map.Entry<String, Integer> entry : newmap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            sum+=Math.log10((double)max*1.00/value*1.00) / Math.log10(2.);
            counter++;
        }
        output+= "Average Word Frequency Class(use Maximum of Maximum each author) : "+sum/counter*1.00+"\n";
        output+="\nMaximum Count of All Corpus : "+maxAllCorpus+"\n";
        counter=0;
        sum=0;
        for (Map.Entry<String, Integer> entry : newmap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            sum+=Math.log10((double)maxAllCorpus*1.00/value*1.00) / Math.log10(2.);
            counter++;
        }
        output+= "Average Word Frequency Class(use Maximum of All Corpus) : "+sum/counter*1.00+"\n";
        return output;
        
    }
    
    private String getMostFrequentWord(){
        Map<String,Integer> map = getMapOfWord();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            return "Most Frequent Word "+entry.getKey()+":"+entry.getValue();
        }
        return null;
    }
}
