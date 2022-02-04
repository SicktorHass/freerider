package freerider.restapi;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import freerider.repository.VehicleRepository;
import freerider.restapi.dto.VehicleDTO;

@RestController
public class VehiclesDTOController implements VehiclesDTOAPI{
    @Autowired
    VehicleRepository vehicleRepository;
    private final HttpServletRequest request;

    public VehiclesDTOController(HttpServletRequest request){
        this.request=request;
    }
    @Override
    public ResponseEntity<List<VehicleDTO>> getVehicles() {
        System.err.println( request.getMethod() + " " + request.getRequestURI() );
        ArrayList<VehicleDTO> allv= new ArrayList<>();
        vehicleRepository.findAll().forEach(c -> allv.add(new VehicleDTO(c)));;
        return new ResponseEntity<List<VehicleDTO>>(allv, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<VehicleDTO> getVehicle(long vid) {
        System.err.println( request.getMethod() + " " + request.getRequestURI() );
        if(vehicleRepository.existsById(vid)){
            VehicleDTO vdto = new VehicleDTO(vehicleRepository.findById(vid).get());
            return new ResponseEntity<VehicleDTO>(vdto, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}