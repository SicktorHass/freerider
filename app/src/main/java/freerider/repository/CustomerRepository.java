package freerider.repository;

import org.springframework.stereotype.Repository;
import freerider.datamodel.Customer;


@Repository
public interface CustomerRepository extends
    org.springframework.data.repository.CrudRepository<Customer, Long>
{

}