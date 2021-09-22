/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Cargo;

/**
 *
 * @author Jeppe
 */
public class CargoDTO {
    private Long id;
    private String name;
    private int weight;
    private int units;   

    public CargoDTO(Cargo cargo) {
        this.id = cargo.getId();
        this.name = cargo.getName();
        this.weight = cargo.getWeight();
        this.units = cargo.getUnits();
    }
    
    public CargoDTO(){
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
    
    
}