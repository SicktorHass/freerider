package freerider.restapi;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * REST API of simple Service
 * 
 * @author sgra64
 *
 */

public interface ServiceAPI {
	/**
	 * GET /server/stop
	 * 
	 * Stop sever and shut down application.
	 * @return
	 */
	@RequestMapping( method = RequestMethod.GET, value = "/server/stop" )
	ResponseEntity<Void> stop();

}
