/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clusteringData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tsp.City;

/**
 *
 * @author Slayer
 */
public class Point {
    private String pointName;
    private double x = 0;
    private double y = 0;
    private int cluster_number = 0;
    
    public Point(){};
    
    
    public Point(double x, double y)
    {
        this.setX(x);
        this.setY(y);
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }
    
    
    
    public void setX(double x) {
        this.x = x;
    }
    
    public double getX()  {
        return this.x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void setCluster(int n) {
        this.cluster_number = n;
    }
    
    public int getCluster() {
        return this.cluster_number;
    }
    
    //Calculates the distance between two points.
    protected static double distance(Point p, Point centroid) {
        return Math.sqrt(Math.pow((centroid.getY() - p.getY()), 2) + Math.pow((centroid.getX() - p.getX()), 2));
    }
    
    //Creates random point
    protected static Point createRandomPoint(int min, int max) {
    	Random r = new Random();
    	double x = min + (max - min) * r.nextDouble();
    	double y = min + (max - min) * r.nextDouble();
    	return new Point(x,y);
    }
    
    protected static ArrayList createRandomPoints(int min, int max, int number) {
    	ArrayList points = new ArrayList(number);
    	for(int i = 0; i < number; i++) {
    		points.add(createRandomPoint(min,max));
    	}
    	return points;
    }
    
    protected static ArrayList createPointsFromFile(File file){
        ArrayList points = new ArrayList();
        
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            Pattern p = Pattern.compile("(\\w+-\\w+-\\w+)\\s+(\\d+)\\s+(\\d+)");
            
            Point point = new Point();
            String line = null;
            
            while ((line = in.readLine()) != null) {
               
                        Matcher m = p.matcher(line);
                        
                        if (m.find()) {
                            point=new Point();
                            point.setPointName(m.group(1));
                            point.setX(Integer.parseInt(m.group(2)));
                            point.setY(Integer.parseInt(m.group(3)));
                            points.add(point);
                            
                        }
                        
                    }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Point.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Point.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return points;
    }
    
    public String toString() {
    	return "("+pointName+","+x+","+y+")";
    }
}
