package freerider.restapi;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import freerider.datamodel.Reservation;
import freerider.repository.ReservationRepository;
import freerider.restapi.dto.ReservationDTO;


@RestController
public class ReservationsDTOController implements ReservationsDTOAPI {

    @Autowired
    ReservationRepository reservationRepository;
    private final HttpServletRequest request;

    public ReservationsDTOController(HttpServletRequest request){
        this.request=request;
    }
    @Override
    public ResponseEntity<ReservationDTO> getReservation(long rid) {
        System.err.println( request.getMethod() + " " + request.getRequestURI() );
        if(reservationRepository.existsById(rid)){
            ReservationDTO rdto = new ReservationDTO(reservationRepository.findById(rid).get());
            return new ResponseEntity<ReservationDTO>(rdto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public ResponseEntity<ReservationDTO> postReservation(ReservationDTO rdto) {
        System.err.println( request.getMethod() + " " + request.getRequestURI() );
        if(rdto==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Optional<Reservation> reservOpt = rdto.create();
        if(reservOpt.isPresent()){
            reservationRepository.save(reservOpt.get());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @Override
    public ResponseEntity<ReservationDTO> putReservation(ReservationDTO rdto) {
        System.err.println( request.getMethod() + " " + request.getRequestURI() );
        if(rdto==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Optional<Reservation> or= rdto.create();
        if(reservationRepository.existsById(or.get().getID())){
            //Update
        }else{
            //not found
        }
        return null;
    }
    @Override
    public ResponseEntity<?> deleteReservation(long rid) {
        System.err.println( request.getMethod() + " " + request.getRequestURI() );
        if(reservationRepository.existsById(rid)){
            reservationRepository.deleteById(rid);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
