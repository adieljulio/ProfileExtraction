package profileextraction;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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
public class Document {
    private String documentId;
    private int jumlahParagraf = 0;
    private String isiDocument;
    private List<Paragraf> kumpulanParagraf = new ArrayList();
    private String namaDocument = "";
    public Document(String docId){
        this.documentId = docId;
        
    }
    public void addParagraph(Paragraf p){
        this.setJumlahParagraf(this.getJumlahParagraf() + 1);
        getKumpulanParagraf().add(p);
    }
    public void cetakParagraf(){
        for(int i = 0; i < getKumpulanParagraf().size(); i++){
            System.out.println("Isi Semua Kalimat");
            getKumpulanParagraf().get(i).cetakSemuaKalimat();
            System.out.println("Isi Per Kalimat");
            getKumpulanParagraf().get(i).cetakPerKalimat();
            System.out.println("Isi Per Kata");
            getKumpulanParagraf().get(i).cetakPerKata();
        }
    }
    public void cetak(){
        System.out.println("Isi dokumen : " + getIsiDocument());
    }
    public void addContent(String content){
        this.setIsiDocument(content);
    }
    public int getJmlParagraf(){
        return getJumlahParagraf();
    }

    /**
     * @return the documentId
     */
    public String getDocumentId() {
        return documentId;
    }

    /**
     * @param documentId the documentId to set
     */
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    /**
     * @return the jumlahParagraf
     */
    public int getJumlahParagraf() {
        return jumlahParagraf;
    }

    /**
     * @param jumlahParagraf the jumlahParagraf to set
     */
    public void setJumlahParagraf(int jumlahParagraf) {
        this.jumlahParagraf = jumlahParagraf;
    }

    /**
     * @return the isiDocument
     */
    public String getIsiDocument() {
        return isiDocument;
    }

    /**
     * @param isiDocument the isiDocument to set
     */
    public void setIsiDocument(String isiDocument) {
        this.isiDocument = isiDocument;
    }

    /**
     * @return the kumpulanParagraf
     */
    public List<Paragraf> getKumpulanParagraf() {
        return kumpulanParagraf;
    }

    /**
     * @param kumpulanParagraf the kumpulanParagraf to set
     */
    public void setKumpulanParagraf(List<Paragraf> kumpulanParagraf) {
        this.kumpulanParagraf = kumpulanParagraf;
    }

    /**
     * @return the namaDocument
     */
    public String getNamaDocument() {
        return namaDocument;
    }

    /**
     * @param namaDocument the namaDocument to set
     */
    public void setNamaDocument(String namaDocument) {
        this.namaDocument = namaDocument;
    }
    
    
}
class ValueComparator implements Comparator<String>{
 
	HashMap<String, Integer> map = new HashMap<String, Integer>();
 
	public ValueComparator(HashMap<String, Integer> map){
		this.map.putAll(map);
	}
 
	@Override
	public int compare(String s1, String s2) {
		if(map.get(s1) >= map.get(s2)){
			return -1;
		}else{
			return 1;
		}	
	}
}