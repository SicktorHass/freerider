package freerider.restapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import freerider.restapi.dto.CustomerDTO;
import freerider.datamodel.Customer;
import freerider.repository.CustomerRepository;

@RestController
class CustomersDTOController implements CustomersDTOAPI{
    @Autowired
	private CustomerRepository customerRepository;
    private final HttpServletRequest request;

    public CustomersDTOController(HttpServletRequest request){
        this.request=request;
    }
    
    @Override
    public ResponseEntity<List<CustomerDTO>> getCustomers() {
        System.err.println( request.getMethod() + " " + request.getRequestURI() );
        ArrayList<CustomerDTO> allC = new ArrayList<>();
        this.customerRepository.findAll().forEach(c -> allC.add(new CustomerDTO(c)));
        return new ResponseEntity<List<CustomerDTO>>(allC, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerDTO> getCustomer(long id) {
        System.err.println( request.getMethod() + " " + request.getRequestURI() );
        if(customerRepository.existsById(id)){
            CustomerDTO cd = new CustomerDTO(customerRepository.findById(id).get());
            return new ResponseEntity<CustomerDTO>(cd,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<CustomerDTO>> postCustomers(List<CustomerDTO> dtos) {
        System.err.println( request.getMethod() + " " + request.getRequestURI() );
        ResponseEntity re = null;
        if(dtos==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        for (CustomerDTO dto : dtos){
            dto.print();
            Optional<Customer> customerOpt = dto.create();
            if(customerOpt.isPresent()){
                if(customerRepository.existsById(customerOpt.get().getId())){
                    re = new ResponseEntity<>(HttpStatus.CONFLICT);
                }else{
                    this.customerRepository.save(customerOpt.get());
                    re = new ResponseEntity<>(HttpStatus.ACCEPTED);
                }
            }else{
                re = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    return re;
    }

    /**
    * PUT /customers
    */
    @Override
    public ResponseEntity<List<CustomerDTO>> putCustomers(@RequestBody List<CustomerDTO> dtos ) {
        // TODO: replace by logger
        System.err.println( request.getMethod() + " " + request.getRequestURI() );
        //
        dtos.stream().forEach( dto -> {
            dto.print();
            Optional<Customer> customerOpt = dto.create();
            CustomerDTO.print( customerOpt );
            if(customerOpt.isPresent()){
                customerRepository.save(customerOpt.get());
            }
            System.out.println();
        });
        return new ResponseEntity<>( null, HttpStatus.ACCEPTED );
    }

    @Override
    public ResponseEntity<?> deleteCustomer(long id) {
        System.err.println( request.getMethod() + " " + request.getRequestURI() );
        if (customerRepository.existsById(id)){
			customerRepository.deleteById(id);
			return new ResponseEntity<>( null, HttpStatus.ACCEPTED ); // status 202
		}else{
			return new ResponseEntity<>( null, HttpStatus.NOT_FOUND );
		}
    }

}