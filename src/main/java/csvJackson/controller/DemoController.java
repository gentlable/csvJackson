package csvJackson.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import csvJackson.model.dto.DemoRecord;
import csvJackson.model.service.FileOutputService;

@Controller
public class DemoController {
	
	private static final String JSON_DATA_1 = "[{\"id\": 0, \"name\": \"test0\"},{\"id\": 1, \"name\": \"test1\"},{\"id\": 2, \"name\": \"test2\"}]";


	@Autowired
	FileOutputService fileOutputService;

	@GetMapping("/")
	public String getIndex(Model model) {

		model.addAttribute("json", JSON_DATA_1);

		return "index";
	}

	@GetMapping(value = "/csv")
	public Object getCsv(Model model) throws JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<DemoRecord> demoRecordList = objectMapper.readValue(JSON_DATA_1, new TypeReference<List<DemoRecord>>() {});

		
		CsvMapper csvMapper = new CsvMapper();
		CsvSchema schema = csvMapper.schemaFor(DemoRecord.class).withHeader();
		fileOutputService.output("demo", csvMapper.writer(schema).writeValueAsString(demoRecordList));

		return "index";
	}

}
