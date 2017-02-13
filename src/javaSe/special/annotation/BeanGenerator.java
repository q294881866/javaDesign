package javaSe.special.annotation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;

/**
 * һ�������ֽ����ļ���ע��
 * @author ppf@jiumao.org
 * @date 2017��1��25��
 */
public class BeanGenerator extends AbstractProcessor{


    // Ԫ�ز����ĸ�����  
    Elements elementUtils;  
  
    @Override  
    public synchronized void init(ProcessingEnvironment processingEnv) {  
        super.init(processingEnv);  
        elementUtils = processingEnv.getElementUtils();  
    }  
  
    @Override  
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {  
        // ��ñ���ע��������Ԫ��  
        Set<? extends Element> elememts = roundEnv.getElementsAnnotatedWith(ClassAnno.class);  
        TypeElement classElement = null;// ������Ԫ��  
        List<VariableElement> fields = null;// ����һ����ų�Ա�������б�  
        // ��Ŷ���  
        Map<String, List<VariableElement>> maps = new HashMap<String, List<VariableElement>>();  
        // ����  
        for (Element ele : elememts) {  
            // �жϸ�Ԫ���Ƿ�Ϊ��  
            if (ele.getKind() == ElementKind.CLASS) {  
                classElement = (TypeElement) ele;  
                maps.put(classElement.getQualifiedName().toString(), fields = new ArrayList<VariableElement>());  
  
            } else if (ele.getKind() == ElementKind.FIELD) // �жϸ�Ԫ���Ƿ�Ϊ��Ա����  
            {  
                VariableElement varELe = (VariableElement) ele;  
                // ��ȡ��Ԫ�ط�װ����  
                TypeElement enclosingElement = (TypeElement) varELe.getEnclosingElement();  
                // �õ�key  
                String key = enclosingElement.getQualifiedName().toString();  
                fields = maps.get(key);  
                if (fields == null) {  
                    maps.put(key, fields = new ArrayList<VariableElement>());  
                }  
                fields.add(varELe);  
            }  
        }  
  
        for (String key : maps.keySet()) {  
            if (maps.get(key).size() == 0) {  
                TypeElement typeElement = elementUtils.getTypeElement(key);  
                List<? extends Element> allMembers = elementUtils.getAllMembers(typeElement);  
                if (allMembers.size() > 0) {  
                    maps.get(key).addAll(ElementFilter.fieldsIn(allMembers));  
                }  
            }  
        }  
        generateFile(maps);  
        return true;  
    }  
  
    private void generateFile(Map<String, List<VariableElement>> maps) {  
        File dir = new File(BeanGenerator.class.getResource("/").getPath());  
        if (!dir.exists())  
            dir.mkdirs();  
        // ����map  
        for (String key : maps.keySet()) {  
  
            // �����ļ�  
            File file = new File(dir, key.replaceAll("\\.", "_") + ".txt");  
            try {  
                /** 
                 * ��д�ļ����� 
                 */  
                FileWriter fw = new FileWriter(file);  
                fw.append("{").append("class:").append("\"" + key + "\"").append(",\n ");  
                fw.append("fields:\n {\n");  
                List<VariableElement> fields = maps.get(key);  
  
                for (int i = 0; i < fields.size(); i++) {  
                    VariableElement field = fields.get(i);  
                    fw.append("  ").append(field.getSimpleName()).append(":")  
                            .append("\"" + field.asType().toString() + "\"");  
                    if (i < fields.size() - 1) {  
                        fw.append(",");  
                        fw.append("\n");  
                    }  
                }  
                fw.append("\n }\n");  
                fw.append("}");  
                fw.flush();  
                fw.close();  
  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    @Override  
    public Set<String> getSupportedAnnotationTypes() {  
        Set<String> set = super.getSupportedAnnotationTypes();  
        if (set == null) {  
            set = new HashSet<>();  
        }  
        set.add("com.robert.processor.Serialize");  
        return set;  
    }  
}
