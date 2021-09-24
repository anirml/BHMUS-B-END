
package dto;

import entities.GameUser;
import java.util.Date;

/**
 *
 * @author Jeppe
 */
public class GameUserDTO {
    private Long id;
    private Date last_played;
    private String email;
    private String passw;  
    
    public GameUserDTO(GameUser d) {
        this.id = d.getId();
        this.last_played = d.getLastPlayed();
        this.email = d.getUserEmail();
        this.passw = d.getUserPass();

    } 
    
    public GameUserDTO(){
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLastPlayed() {
        return last_played;
    }

    public void setShippingDate(Date last_played) {
        this.last_played = last_played;
    }

    public String getUserEmail() {
        return email;
    }

    public void setFromLocation(String email) {
        this.email = email;
    }

    public String getUserPass() {
        return passw;
    }

    public void setToLocation(String passw) {
        this.passw = passw;
    }
 
}