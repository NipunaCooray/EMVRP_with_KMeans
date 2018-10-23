/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

/**
 *
 * @author Slayer
 */
public class Data {
    private String name;
    private String comment;
    private String type;
    private int dimension;
    private String edgeWaightType;
    private int capacity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = Integer.parseInt(dimension.trim());
    }

    public String getEdgeWaightType() {
        return edgeWaightType;
    }

    public void setEdgeWaightType(String edgeWaightType) {
        this.edgeWaightType = edgeWaightType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = Integer.parseInt(capacity.trim());
    }
    
    
}
