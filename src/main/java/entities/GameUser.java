
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
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Jeppe
 */
@Entity
@Table(name = "game_user")
public class GameUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "last_played")
    private Date last_played;
    @Column(name = "email")
    private String email;
    @Column(name = "passw")
    private String passw;   

    public GameUser(Long id, Date shippingDate, String fromLocation, String toLocation) {
        this.id = id;
        //this.truck = truck;
        this.last_played = shippingDate;
        this.email = fromLocation;
        this.passw = toLocation;
    }    
        
        public GameUser(Date shippingDate, String fromLocation, String toLocation) {
        this.last_played = shippingDate;
        this.email = fromLocation;
        this.passw = toLocation;
    } 
        
        public GameUser() {
    }  
        
    public Date getLastPlayed() {
        return last_played;
    }

    public void setShippingDate(Date shippingDate) {
        this.last_played = shippingDate;
    }

    public String getUserEmail() {
        return email;
    }

    public void setFromLocation(String fromLocation) {
        this.email = fromLocation;
    }

    public String getUserPass() {
        return passw;
    }

    public void setToLocation(String toLocation) {
        this.passw = toLocation;
    }  
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
  
}