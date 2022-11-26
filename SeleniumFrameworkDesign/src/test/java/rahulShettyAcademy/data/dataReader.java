package rahulShettyAcademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class dataReader {
	
	public List<HashMap<String,String>> getJsonDataToMap() throws IOException {
		
		// Converting Json Data to String in a variable 
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\rahulShettyAcademy\\data\\PurchaseOrder.json"),StandardCharsets.UTF_8);
	
		// For Converting string data to HashMap we need --> Jackson Api (Special case) from Maven Repository  
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
			
		});
		return data;
	}
	
}
