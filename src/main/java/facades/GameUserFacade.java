package facades;

import dto.GameUserDTO;
import entities.GameUser;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class GameUserFacade {

    private static GameUserFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private GameUserFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static GameUserFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GameUserFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public long getUserCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long UserCount = (long)em.createQuery("SELECT COUNT(r) FROM GameUser r").getSingleResult();
            return UserCount;
        }finally{  
            em.close();
        }
        
    }
    
    
        public List<GameUserDTO> getAllUsers() {
        EntityManager em = getEntityManager();
        TypedQuery<GameUser> tq = em.createQuery("SELECT d FROM GameUser d", GameUser.class);
        List<GameUser> gameuser = tq.getResultList();
        List<GameUserDTO> gameuserDTO = new ArrayList<>();
        em.close();
        for (GameUser GameUser : gameuser) {
            gameuserDTO.add(new GameUserDTO(GameUser));
        }
        return gameuserDTO;
    }
        
        
        
        public GameUser loginCheckUser(String username, String password){
        EntityManager em = getEntityManager();
        GameUser d =  (GameUser) em.createQuery("SELECT f FROM GameUser f WHERE f.email LIKE :email").setParameter("email", username).getSingleResult();

        //TODO Bcrypt
        
        if(null != d.getUserEmail() || (null) != d.getUserPass()) {
            if (!d.getUserPass().equals(password)) {
                 return null;
            }
        }
        
        em.getTransaction().begin();
        em.contains(d);
        em.getTransaction().commit();
        
        em.close();
        return d;
    } 

        
        public GameUser addUser(GameUserDTO p) {
        EntityManager em = getEntityManager();
        
        GameUser d = new GameUser(p.getLastPlayed(),p.getUserEmail(),p.getUserPass());
        
        TypedQuery<GameUser> tq = em.createQuery("SELECT d FROM GameUser d", GameUser.class);
        List<GameUser> gameuser = tq.getResultList();
        
        for (GameUser GameUser : gameuser) {
            if(p.getUserEmail().equals(GameUser.getUserEmail())){System.out.println("user already exists!"); return null;}
        }
        
        try {
            em.getTransaction().begin();        
            em.persist(d);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return d;
    }    

}
