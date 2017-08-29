package javaSe.basic.json;

import org.codehaus.jackson.JsonNode;

/**
 * {@link JsonNode} °ü×°
 * 
 * @author ppf@jiumao.org
 *
 */
public final class JsonWraper {
    private JsonNode nodeThis;
    
    private JsonWraper(){}
    
    public static JsonWraper toJsonWraper(JsonNode node) {
        JsonWraper wraper = new JsonWraper();
        wraper.nodeThis = node;
        return wraper;
    }
    
    
    public JsonWraper getJsonNode(String key) {
        nodeThis = JsonUtil.getJsonNode(nodeThis, key);
        return this;
    }
    
    
    public static JsonNode asJsonNode(JsonWraper wraper){
        return wraper.nodeThis;
    }
    
    @Override
    public String toString() {
        return nodeThis.toString();
    }
}
