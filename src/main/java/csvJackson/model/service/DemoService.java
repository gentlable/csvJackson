package csvJackson.model.service;

import org.springframework.stereotype.Service;

import csvJackson.model.dto.DemoRecord;

@Service
public class DemoService {
	
	
	private static final String JSON_DATA = "{\"id\": 0, \"name\": \"test\"}";
	
	public String get( ) {
		return JSON_DATA;
	}

}
