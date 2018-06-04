/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profileextraction;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author Adiel
 */
public class View extends javax.swing.JFrame {
    private List<Author> authorList = new ArrayList<Author>();
    private String feature = "";
    /**
     * Creates new form View
     */
    public View() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtPath = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        btnRun = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txvResult = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Path :");

        txtPath.setEditable(false);

        btnBrowse.setText("Browse");
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });

        btnRun.setText("Run");
        btnRun.setEnabled(false);
        btnRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunActionPerformed(evt);
            }
        });

        txvResult.setEditable(false);
        txvResult.setColumns(20);
        txvResult.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txvResult.setRows(5);
        jScrollPane1.setViewportView(txvResult);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPath, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBrowse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRun)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowse)
                    .addComponent(btnRun))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Select folder of root of all author");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
            System.out.println("getCurrentDirectory(): " 
               +  chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " 
               +  chooser.getSelectedFile());
            txtPath.setText(chooser.getSelectedFile().toString());
            btnRun.setEnabled(true);
        }
        else {
            btnRun.setEnabled(false);
          System.out.println("No Selection ");
          }
       
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void btnRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunActionPerformed
        // TODO add your handling code here:
        List<Author> authorList = new ArrayList<Author>();
        String feature = "";
        String path = txtPath.getText().toString();
        File rootdirectory = new File(path);
        int pMinChar = 60;

        File[] fList = rootdirectory.listFiles();
        for (File file : fList) {
            if (file.isDirectory()) {
                System.out.println(file.getAbsolutePath());
                Author authortemp = new Author(file.getName());
                File[] filesOfAuthor = file.listFiles();
                for (File authorfile : filesOfAuthor) {
                    if (authorfile.isFile()){
                        System.out.println(authorfile.getAbsolutePath());
                        Document d1 = new Document(file.getName());
                        List <String> tempParagraf = new ArrayList<String>();
                        String hasil = "";
                        try{
                            FileReader reader = new FileReader(authorfile);
                            Scanner input = new Scanner(reader);
                            while (input.hasNext()){
                                String num = input.nextLine();
                                while(num.length()<pMinChar){
                                    if(input.hasNext()){
                                        num+=" "+input.nextLine();
                                    }else{
                                        break;
                                    }
                                }
                                if(num.matches("")){
                                }
                                else{
                                    tempParagraf.add(num);
                                }
                                hasil += num + " ";
                            }
                            System.out.println(tempParagraf.size());
                            /*
                            for(int i = 0; i < tempParagraf.size(); i++){         
                                Paragraf p = new Paragraf(tempParagraf.get(i), d1.getJmlParagraf() + 1);                
                                d1.addParagraph(p);
                            }
                            */
                        }
                        catch(Exception e){
                            System.out.println(e.toString());
                        }
                        for (String string : tempParagraf) {
                            Paragraf p = new Paragraf(string, d1.getJmlParagraf()+1);
                            d1.addParagraph(p);
                        }
                        authortemp.addDocument(d1);
                        //d1.cetakParagraf();
                    }
                }
                authorList.add(authortemp);
            }
        }
        Integer max = 0;
        
        for (Author author : authorList) {
            Map<String,Integer> map = author.getMapOfWord();
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                Integer value = entry.getValue();
                if(value>max){
                    max=value;
                }
                break;
            }
        }
        //find maximum of all corpus
        Map<String,Integer> newmap = new HashMap<String,Integer>();
        
        for (Author author : authorList) {
            Map<String,Integer> map = author.getMapOfWord();
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                Integer value = entry.getValue();
                String key = entry.getKey();
                //System.out.println(value+"!"+key);
                if(newmap.get(key)!=null){
                    newmap.put(key, newmap.get(key)+value);
                    
                }else{
                    newmap.put(key, value);
                }
                
            }
        }
        
        Map<String,Integer> sortedMap = Author.sortByValues((HashMap) newmap);
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            //System.out.println(key+"~"+value);
        }
        Integer maxAllCorpus=0;
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            System.out.println(entry.getValue());
            maxAllCorpus = entry.getValue();
            break;
        }
        
        System.out.println(max);
        for (Author author : authorList) {
            feature+=author.generateFeature(max,maxAllCorpus)+"\n\n======================================\n";
        }
        txvResult.setText(feature);
    }//GEN-LAST:event_btnRunActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnRun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtPath;
    private javax.swing.JTextArea txvResult;
    // End of variables declaration//GEN-END:variables
}
