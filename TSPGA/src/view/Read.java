/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Desktop;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import tsp.City;
import tsp.Data;
import tsp.GA;
import tsp.Population;
import tsp.TourManager;

/**
 *
 * @author Slayer
 */
public class Read extends javax.swing.JFrame {

    /**
     * Creates new form FileReader
     */
    public Read() {
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

        btnSelectFile = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(300, 150, 0, 0));

        btnSelectFile.setText("Select data");
        btnSelectFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectFileActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(102, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("                     EMVRP Solver - Genetic Algorithm");
        jLabel1.setOpaque(true);

        jLabel2.setText("Select text files with VRP data to apply GA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSelectFile, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSelectFile, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectFileActionPerformed
        try{
            String fileName = "";
            String line = null;

            //String path = "C:\\Users\\Slayer\\Desktop\\Data";
            String path = "D:\\Education\\Research project\\Clustering\\Clustered Data\\Unclustered";
            JFileChooser chooser = new JFileChooser(path);
            int result = chooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {

                File filePath = chooser.getSelectedFile();

                if(filePath != null ){
                    //Desktop.getDesktop().open(f);
                    fileName = filePath.getName();
                    BufferedReader in = new BufferedReader(new FileReader(filePath));

                    Pattern p = Pattern.compile("(\\d+)\\s+(\\d+)\\s+(\\d+)");
                    Pattern p2=Pattern.compile("(\\d+)\\s+(\\d+)");

                    Boolean isContinue=true;
                    Data data=new Data();
                    while ((line = in.readLine()) != null && isContinue){
                        String[] array=line.split("\\s+[:]\\s+");
                        switch (array[0])
                        {
                            case "NAME":data.setName(array[1]);
                            // System.out.println(array[1]+" data"+array[0]);
                            break;
                            case "COMMENT":data.setComment(array[1]);
                            //System.out.println(array[1]+" data"+array[0]);
                            break;
                            case "TYPE": data.setType(array[1]);
                            //System.out.println(array[1]+" data"+array[0]);
                            break;
                            case "DIMENSION":data.setDimension(array[1]);
                            //System.out.println(array[1]+" data "+array[0]);
                            break;
                            case "CAPACITY":data.setCapacity(array[1]);
                            //System.out.println(array[1]+" data "+array[0]);
                            isContinue=false;
                            break;
                        }

                    }
                    Map<Integer,City> cities=new TreeMap<Integer, City>();
                    City city=null;
                    while ((line = in.readLine()) != null) {

                        Matcher m = p.matcher(line);
                        if (m.matches()) {
                            city=new City();
                            city.setName(Integer.parseInt(m.group(1).trim()));
                            city.setX(Integer.parseInt(m.group(2)));
                            city.setY(Integer.parseInt(m.group(3)));
                            cities.put(city.getName(), city);
                        }
                        else{
                            break;
                        }
                    }
                    //System.out.println("Before while");

                    while ((line = in.readLine()) != null) {
                        Matcher m2=p2.matcher(line);
                        if (m2.find()) {
                            city=cities.get(Integer.parseInt(m2.group(1).trim()));
                            city.setDemand(Integer.parseInt(m2.group(2)));
                            cities.put(city.getName(), city);
                        }

                    }
                    in.close();

                    //Adding cities to tour manager
                    for (Integer cityID : cities.keySet()) {
                        //System.out.println(cities.get(cityID));
                        TourManager.addCity(cities.get(cityID));
                    }

                    for(int i=0;i<TourManager.numberOfCities();i++){
                            System.out.println("Tour manager - " + TourManager.getCity(i));
                    }

                    //Adding vehicle capacity
                    TourManager.setVehicleCapacity(data.getCapacity());

                    Population pop = new Population(50, true);
                    double initialEnergy = pop.getFittest().getEnergy();
                    System.out.println("Initial energy: " + initialEnergy);
                    System.out.println(pop.getFittest());

                    // Evolve population for 100 generations
                    pop = GA.evolvePopulation(pop);
                    for (int i = 0; i < 1000; i++) {

                        pop = GA.evolvePopulation(pop);

                    }

                    // Print final results
                    System.out.println("Finished");
                    double finalEnergy = pop.getFittest().getEnergy();
                    System.out.println("Final energy: " + finalEnergy);
                    System.out.println("Percentage reduction in energy : "+ ((initialEnergy - finalEnergy)/initialEnergy)*100 + "%" );
                    System.out.println("Solution:");
                    System.out.println(pop.getFittest());

                }
                else{

                }
            } else if (result == JFileChooser.CANCEL_OPTION) {

            }

        }
        catch(Exception e ){
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSelectFileActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Read.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Read.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Read.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Read.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Read().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelectFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}