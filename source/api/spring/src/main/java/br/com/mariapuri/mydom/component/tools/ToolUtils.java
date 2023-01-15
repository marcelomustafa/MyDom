package br.com.mariapuri.mydom.component.tools;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mariapuri.mydom.app.domain.dto.standard.JsonMapDTO;

@Component
public class ToolUtils {
	
	ObjectMapper mapper = new ObjectMapper();
	
	public String accentLass(String text) {
		String normalize = Normalizer.normalize(text,Normalizer.Form.NFD);
		return normalize.replaceAll("\\p{InCombiningDiacritiacalMarks}+", "");
	}

	public HashMap<String, Object> JsonToMap(String jsonInput) throws JsonMappingException, JsonProcessingException{
		TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};	
		return mapper.readValue(jsonInput, typeRef);
	}

	
	public String JsonValidBoolean(String jsonInput) throws JsonMappingException, JsonProcessingException {
		return JsonValidBoolean(jsonInput, null, "; ");
	}
	
	public String JsonValidBoolean(String jsonInput, Boolean value) throws JsonMappingException, JsonProcessingException {
		return JsonValidBoolean(jsonInput, value, "; ");
	}
	
	@SuppressWarnings("rawtypes")
	public String JsonValidBoolean(String jsonInput, Boolean value, String markSplit) throws JsonMappingException, JsonProcessingException {
		
		HashMap<String, Object> map = JsonToMap(jsonInput);
		
		String result = "";
		for(Map.Entry item : map.entrySet()){
			var key = item.getKey();
			var valuekey = item.getValue();
			result +=  value == null || Boolean.parseBoolean(valuekey.toString()) == value ?  key + markSplit : "";
		}  		
		
		return result;
		
	}
	
	
	public String JsonKeyValueString(String jsonInput) throws JsonMappingException, JsonProcessingException {
		return JsonKeyValueString(jsonInput, ": ", "; ");
	}
	
	public String JsonKeyValueString(String jsonInput, String markValue) throws JsonMappingException, JsonProcessingException {
		return JsonKeyValueString(jsonInput, markValue, "; ");
	}
	
	@SuppressWarnings("rawtypes")
	public String JsonKeyValueString(String jsonInput, String markValue, String markSplit) throws JsonMappingException, JsonProcessingException {
		
		HashMap<String, Object> map = JsonToMap(jsonInput);

		markValue = markValue == null ? ":" : markValue;
		
		String result = "";
		
		for( Map.Entry item : map.entrySet()){
			var key = item.getKey();
			var valuekey = item.getValue();
			result +=  key + markValue + valuekey + markSplit;
		}  		
		
		return result;
		
	}

	
	public String JsonKeyString(String jsonInput) throws JsonMappingException, JsonProcessingException {
		return JsonKeyString(jsonInput, "; ");
	}
	
	@SuppressWarnings({"rawtypes","unused"})
	public String JsonKeyString(String jsonInput, String markSplit) throws JsonMappingException, JsonProcessingException {
		
		HashMap<String, Object> map = JsonToMap(jsonInput);
		
		String result = "";
		for(Map.Entry item : map.entrySet()){
			var key = item.getKey();
			var valuekey = item.getValue();
			result +=  key  + markSplit;
		}  		
		
		return result;
		
	}	
	
	
	public String JsonValueString(String jsonInput) throws JsonMappingException, JsonProcessingException {
		return JsonValueString(jsonInput, "; ");
	}
	
	@SuppressWarnings({"rawtypes","unused"})
	public String JsonValueString(String jsonInput, String markSplit) throws JsonMappingException, JsonProcessingException {
		
		HashMap<String, Object> map = JsonToMap(jsonInput);
		
		String result = "";
		for(Map.Entry item : map.entrySet()){
			var key = item.getKey();
			var valuekey = item.getValue();
			result += valuekey + markSplit;
		}  		
		
		return result;
		
	}	
	
	
	@SuppressWarnings("rawtypes")
	public List<JsonMapDTO> JsonMapObejct(String jsonInput) throws JsonMappingException, JsonProcessingException {
		
		HashMap<String, Object> map = JsonToMap(jsonInput);
		
		List<JsonMapDTO> result = new ArrayList<>();
		for(Map.Entry item : map.entrySet()){
			var key = item.getKey();
			var valuekey = item.getValue();
			result.add(new JsonMapDTO((String) key, valuekey));
		}  		
		
		return result;
		
	}	
	
}
