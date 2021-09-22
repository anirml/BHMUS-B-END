/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Cargo;
import entities.Deliveries;
import entities.Trucks;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jeppe
 */
public class DeliveriesDTO {
    private Long id;
    private Trucks truck;
    private Date shippingDate;
    private String fromLocation;
    private String toLocation;  
    private List<CargoDTO> cargo = new ArrayList();
    
   // private List<HobbyDTO> hobbies = new ArrayList();
    
    public DeliveriesDTO(Deliveries d) {
        this.id = d.getId();
        this.truck = d.getTruck();
        this.shippingDate = d.getShippingDate();
        this.fromLocation = d.getFromLocation();
        this.toLocation = d.getToLocation();
        
        for (Cargo cargo : d.getCargo()){ 
            this.cargo.add(new CargoDTO(cargo));
        }

    } 
    
    public DeliveriesDTO(){
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CargoDTO> getCargo() {
        return cargo;
    }

    public void setCargo(List<CargoDTO> cargo) {
        this.cargo = cargo;
    }

    public Trucks getTruck() {
        return truck;
    }

    public void setTruck(Trucks truck) {
        this.truck = truck;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }
 
}
