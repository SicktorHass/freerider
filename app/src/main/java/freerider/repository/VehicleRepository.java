package freerider.repository;

import org.springframework.stereotype.Repository;

import freerider.datamodel.Vehicle;


@Repository
public interface VehicleRepository extends
    org.springframework.data.repository.CrudRepository<Vehicle, Long>
    {

    }
