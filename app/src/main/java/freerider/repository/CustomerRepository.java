package freerider.repository;

import java.util.HashMap;
import java.util.Optional;
import org.springframework.stereotype.Component;
import freerider.datamodel.Customer;


@Component
public class CustomerRepository implements CrudRepository<Customer, Long>{

    HashMap<Long, Customer> cmap;
    static long id = 1;

    public CustomerRepository(){
        this.cmap = new HashMap<Long, Customer>(); 
    }

    @Override
    public <S extends Customer> S save(S entity) {
        this.cmap.put(entity.getId(), entity);
        CustomerRepository.id++;
        return entity;
    }
    public long getLastID(){
        return CustomerRepository.id;
    }
    @Override
    public <S extends Customer> Iterable<S> saveAll(Iterable<S> entities) {
        for(Customer c : entities){
            this.save(c);
        }
        return entities;
    }

    @Override
    public boolean existsById(Long id) {
        return this.cmap.containsKey(id);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        if(this.cmap.get(id)== null) return Optional.empty();
        Optional<Customer> c = Optional.of(this.cmap.get(id));
        return c;
    }

    @Override
    public Iterable<Customer> findAll() {
        return cmap.values();
    }

    @Override
    public Iterable<Customer> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long count() {
        return this.cmap.size();
    }

    @Override
    public void deleteById(Long id) {
        this.cmap.remove(id);
        
    }

    @Override
    public void delete(Customer entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll(Iterable<? extends Customer> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }
    
}
