package freerider.repository;

import org.springframework.stereotype.Repository;

import freerider.datamodel.Reservation;


@Repository
public interface ReservationRepository extends
    org.springframework.data.repository.CrudRepository<Reservation, Long>
{
    
}
