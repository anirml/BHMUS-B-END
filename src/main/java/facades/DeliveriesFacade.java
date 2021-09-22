package facades;

import dto.CargoDTO;
import dto.DeliveriesDTO;
import entities.Cargo;
import entities.Deliveries;
import entities.RenameMe;
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
public class DeliveriesFacade {

    private static DeliveriesFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private DeliveriesFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static DeliveriesFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DeliveriesFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public long getDeliveriesCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long DeliveriesCount = (long)em.createQuery("SELECT COUNT(r) FROM Deliveries r").getSingleResult();
            return DeliveriesCount;
        }finally{  
            em.close();
        }
        
    }
    
        public List<DeliveriesDTO> getAllDeliveries() {
        EntityManager em = getEntityManager();
        TypedQuery<Deliveries> tq = em.createQuery("SELECT d FROM Deliveries d", Deliveries.class);
        List<Deliveries> delivery = tq.getResultList();
        List<DeliveriesDTO> deliveriesDTO = new ArrayList<>();
        em.close();
        for (Deliveries deliveries : delivery) {
            deliveriesDTO.add(new DeliveriesDTO(deliveries));
        }
        return deliveriesDTO;
    }
        
        public DeliveriesDTO deleteDelivery(Long id){
        EntityManager em = getEntityManager();
        Deliveries d = em.find(Deliveries.class, id);
        Cargo c = (Cargo) d.getCargo();
        em.getTransaction().begin();
        em.remove(d);
        em.getTransaction().commit();
        em.close();
        return new DeliveriesDTO(d);
    }    
        
        public Deliveries addDelivery(DeliveriesDTO p) {
        EntityManager em = getEntityManager();
        Cargo a = new Cargo((CargoDTO) p.getCargo());
        
        List<Cargo> CargoList = new ArrayList();
        for (CargoDTO c : p.getCargo()) {
            CargoList.add(new Cargo(c));
        }
            
        Deliveries d = new Deliveries(p.getTruck(),p.getShippingDate(),p.getFromLocation(),p.getToLocation());
        d.setCargo((List<Cargo>) a);   

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
