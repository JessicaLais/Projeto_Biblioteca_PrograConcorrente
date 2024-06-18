package teste;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ConversorJson {
    
    private static ObjectMapper ObjMapper = new ObjectMapper();

    public static JsonNode objetoParaJsonNode(Object object) {
        return ObjMapper.valueToTree(object);
    }
    
    public static JsonNode stringParaJsonNode(String jsonString) throws JsonMappingException, JsonProcessingException {
        return ObjMapper.readTree(jsonString);
    }

    public static String jsonNodeParaString(JsonNode jsonNode) throws JsonProcessingException {
        return ObjMapper.writer().with(SerializationFeature.INDENT_OUTPUT).writeValueAsString(jsonNode);
    }

    public static <T> T jsonNodeParaObjeto(JsonNode jsonNode, Class<T> object) throws JsonProcessingException, IllegalArgumentException{
        return ObjMapper.treeToValue(jsonNode, object);
    }
    
}
