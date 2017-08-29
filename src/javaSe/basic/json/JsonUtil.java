package javaSe.basic.json;


import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.NullNode;

import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;


public final class JsonUtil {

    // ============================json-javaBean==================================

    /**
     * �����������п���Ϊnull�� ע��null���
     */
    public static <E> E json2Bean(ObjectMapper m, String json, Class<E> type) {

        try {
            return m.readValue(json, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    /**
     * �����������п���Ϊnull�� ע��null���
     */
    public static <E> String bean2Json(ObjectMapper m, E e) {
        try {
            return m.writeValueAsString(e);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    // ============================json-field==================================

    private static final JsonNode NULL_NODE = NullNode.getInstance();
    private static JsonFactory factory = new JsonFactory();

    public static JsonNode jsonTree(ObjectMapper m, String json) {
        try {
            JsonParser jp = factory.createJsonParser(new StringReader(json));
            m.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            return m.readTree(jp);
        } catch (Exception e) {
            System.out.println(e);
        }
        return NULL_NODE;
    }

    /**
     * ��thisΪ{@link NullNode}ֵ���߼�ֵ�����ڣ��᷵��null
     */
    public static String getValue(JsonNode node, String name) {
        JsonNode nodeThis = getJsonNode(node, name);
        if (isNull(nodeThis)) {
            return null;
        }
        return nodeThis.toString();
    }

    public static JsonNode getJsonNode(JsonNode node, String name) {
        if (name == null || isNull(node)) return NULL_NODE;

        StringTokenizer st = new StringTokenizer(name, ".");
        while (st.hasMoreTokens()) {
            String key = st.nextToken().trim();
            if (key.isEmpty() || null == (node = node.findValue(key))) {
                return NULL_NODE;
            }
        }
        return node;
    }

    /**
     * null���ߵ�{@code JsonNode}��{@link NullNode}
     */
    public static boolean isNull(JsonNode node) {
        return null == node || NULL_NODE == node;
    }

}

