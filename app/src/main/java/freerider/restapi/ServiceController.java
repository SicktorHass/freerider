package freerider.restapi;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
class ServiceController implements ServiceAPI {
	//
	@Autowired
    private ApplicationContext context;

	private final HttpServletRequest request;


	/**
	 * Constructor.
	 * 
	 * @param request HTTP request object
	 */
	public ServiceController(HttpServletRequest request ) {
		this.request = request;
	}

	/**
	 * GET /server/stop
	 * 
	 * Stop sever and shut down application.
	 * @return
	 */
	@Override
	public ResponseEntity<Void> stop() {
		//
 		try {
			System.err.print( request.getRequestURI() + " " + request.getMethod() + "\nshutting down server..." );
			//
			ApplicationContext context = this.context;
			((ConfigurableApplicationContext) context).close();
			//
			System.err.println( "  done." );
			//
            return new ResponseEntity<Void>( HttpStatus.OK );

        } catch( Exception e ) {
            return new ResponseEntity<Void>( HttpStatus.INTERNAL_SERVER_ERROR );
        }
	}
}
