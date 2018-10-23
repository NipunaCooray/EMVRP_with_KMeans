/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clusteringData;

import java.util.ArrayList;


/**
 *
 * @author Slayer
 */
public class Cluster {
    public ArrayList<Point> points;
    public Point centroid;
    public int id;

    //Creates a new Cluster
    public Cluster(int id) {
            this.id = id;
            this.points = new ArrayList();
            this.centroid = null;
    }

    public ArrayList getPoints() {
            return points;
    }

    public void addPoint(Point point) {
            points.add(point);
    }

    public void setPoints(ArrayList points) {
            this.points = points;
    }

    public Point getCentroid() {
            return centroid;
    }

    public void setCentroid(Point centroid) {
            this.centroid = centroid;
    }

    public int getId() {
            return id;
    }

    public void clear() {
            points.clear();
    }

    public void plotCluster() {
            System.out.println("[Cluster: " + id+"]");
            System.out.println("[Centroid: " + centroid + "]");
            System.out.println("[Points: \n");
            for(Point p : points) {
                    System.out.println(p);
            }
            System.out.println("]");
    }
}
