/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Jeppe
 */
@Entity
@Table(name = "deliveries")
public class Deliveries implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // private Cargo cargo;
    private List<Cargo> cargo = new ArrayList();

    private Trucks truck;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "shipping_date")
    private Date shippingDate;
    @Column(name = "from_location")
    private String fromLocation;
    @Column(name = "to_location")
    private String toLocation;   

    public Deliveries(Long id, Trucks truck, Date shippingDate, String fromLocation, String toLocation) {
        this.id = id;
        this.truck = truck;
        this.shippingDate = shippingDate;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    }    
    
        public Deliveries(Trucks truck, Date shippingDate, String fromLocation, String toLocation) {
        this.truck = truck;
        this.shippingDate = shippingDate;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    } 
        
        public Deliveries() {
    }  
        
    public List<Cargo> getCargo() {
        return cargo;
    }

    public void setCargo(List<Cargo> cargo) {
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
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    
}
