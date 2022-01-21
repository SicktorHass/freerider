package freerider.restapi;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import freerider.datamodel.Customer;
import freerider.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

//@RestController
class CustomersController implements CustomersAPI{
    
	//@Autowired
	private CustomerRepository customerRepository;
	private final ObjectMapper objectMapper;
	//
	private final HttpServletRequest request;


	/**
	 * Constructor.
	 * 
	 * @param objectMapper entry point to JSON tree for the Jackson library
	 * @param request HTTP request object
	 */
	public CustomersController( ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

    

    @Override
    public ResponseEntity<List<?>> getCustomers() {
        ResponseEntity<List<?>> re = null;
		System.err.println( request.getMethod() + " " + request.getRequestURI() );   
		try {
			ArrayNode arrayNode = peopleAsJSON();
			ObjectReader reader = objectMapper.readerForListOf(ObjectNode.class);
			List<String> list = reader.readValue( arrayNode );
			//
			re = new ResponseEntity<List<?>>( list, HttpStatus.OK );

		} catch( IOException e ) {
			re = new ResponseEntity<List<?>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return re;
    }

    @Override
    public ResponseEntity<?> getCustomer(long id) {
        System.err.println(request.getMethod() + " " + request.getRequestURI() );
		if(customerRepository.existsById(id)){
			ObjectNode customerJSON = customerToJSON(customerRepository.findById(id).get());
			return new ResponseEntity<ObjectNode>(customerJSON, HttpStatus.OK);

		}else{
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
    }
 

	private ArrayNode peopleAsJSON() {
		//
		ArrayNode arrayNode = objectMapper.createArrayNode();
		//
		customerRepository.findAll().forEach( c -> {
			StringBuffer sb = new StringBuffer();
			c.getContacts().forEach( contact -> sb.append( sb.length()==0? "" : "; " ).append( contact ) );
			arrayNode.add(
				objectMapper.createObjectNode()
					.put("id", c.getId())
					.put( "name", c.getLastName() )
					.put( "first", c.getFirstName() )
					.put( "contacts", sb.toString() )
			);
		});
		return arrayNode;
	}
    private ObjectNode customerToJSON(Customer c){
        StringBuffer sb = new StringBuffer();
		c.getContacts().forEach( contact -> sb.append( sb.length()==0? "" : "; " ).append( contact ) );
        return objectMapper.createObjectNode()
			.put("id", c.getId())
			.put( "name", c.getLastName())
			.put( "first", c.getFirstName())
			.put( "contacts", sb.toString());
    }



	@Override
	public ResponseEntity<List<?>> postCustomers(Map<String, Object>[] jsonMap) {
		System.err.println( "POST /customers" );
		if( jsonMap == null ) return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );

		for( Map<String, Object> kvpairs : jsonMap ) {
			Optional<Customer> c = this.accept(kvpairs);
			if(c.isPresent()){
				customerRepository.save(c.get());
			}else{
				return new ResponseEntity(jsonMap, HttpStatus.CONFLICT );
			}
		}
		return new ResponseEntity<>( null, HttpStatus.CREATED );
	}



	@Override
	public ResponseEntity<List<?>> putCustomers(Map<String, Object>[] jsonMap) {
		
		return null;
	}



	@Override
	public ResponseEntity<?> deleteCustomer(long id) {
		System.err.println( "DELETE /customers/" + id );
		if (customerRepository.existsById(id)){
			customerRepository.deleteById(id);
			return new ResponseEntity<>( null, HttpStatus.ACCEPTED ); // status 202
		}else{
			return new ResponseEntity<>( null, HttpStatus.NOT_FOUND );
		}
		
	}
	private Optional<Customer> accept( Map<String, Object> kvpairs ) {
		Optional<Customer> oc = Optional.empty();
		Customer c = new Customer();
		long id = 0;
	
		if(kvpairs.containsKey("id")){
			if(Integer.parseInt(kvpairs.get("id").toString())<1 || customerRepository.findById((long)Integer.parseInt(kvpairs.get("id").toString())).isPresent()){
				return oc;
			}
		}else{
			kvpairs.put("id", customerRepository.getLastID());
		}
		if(kvpairs.containsKey("first")&&kvpairs.containsKey("name")){
			c.setName(kvpairs.get("first").toString(), kvpairs.get("name").toString());
			c.setId(Integer.parseInt(kvpairs.get("id").toString()));
		}else{
			return oc;
		}
		if(kvpairs.containsKey("contacts")){
			for(String contact : kvpairs.get("contacts").toString().split(";")){
				c.addContact(contact);
			}
		}
		oc = Optional.of(c);
		return oc;
	}
}
