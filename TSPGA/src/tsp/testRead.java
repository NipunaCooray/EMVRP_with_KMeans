/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Slayer
 */
public class testRead extends javax.swing.JFrame {

    /**
     * Creates new form testRead
     */
    public testRead() {
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

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Read");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(333, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(266, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            String fileName = "";
            String line = null;

            //String path = "C:\\Users\\Slayer\\Desktop\\Data";
            String path = "D:\\Education\\Research project\\Clustering\\Clustered Data\\Cluster 2";
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
                        if (m.find()) {
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
                    System.out.println("Before while");

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
                        System.out.println(cities.get(cityID));
                        TourManager.addCity(cities.get(cityID));
                    }

                    for(int i=0;i<TourManager.numberOfCities();i++){
                            System.out.println("Tour manager - " + TourManager.getCity(i));
                    }

                    //Adding vehicle capacity
                    TourManager.setVehicleCapacity(data.getCapacity());

//                    Population pop = new Population(50, true);
//                    double initialEnergy = pop.getFittest().getEnergy();
//                    System.out.println("Initial energy: " + initialEnergy);
//                    System.out.println(pop.getFittest());
//
//                    // Evolve population for 100 generations
//                    pop = GA.evolvePopulation(pop);
//                    for (int i = 0; i < 100; i++) {
//
//                        pop = GA.evolvePopulation(pop);
//
//                    }
//
//                    // Print final results
//                    System.out.println("Finished");
//                    double finalEnergy = pop.getFittest().getEnergy();
//                    System.out.println("Final energy: " + finalEnergy);
//                    System.out.println("Percentage reduction in energy : "+ ((initialEnergy - finalEnergy)/initialEnergy)*100 + "%" );
//                    System.out.println("Solution:");
//                    System.out.println(pop.getFittest());

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
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(testRead.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(testRead.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(testRead.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(testRead.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new testRead().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
