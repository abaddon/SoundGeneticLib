package tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import soundGenetic.jgap.ConfigFitness;
import soundGenetic.jgap.ConfigJGap;

/**
 * @author   abaddon
 */
public class Configs {
	
	private ConfigFitness configFitness=null;
	private ConfigJGap configJgap=null;
	private DecimalFormat format=new DecimalFormat("####.####");
	
	public void ImportFromXml(ConfigFitness configFitness, ConfigJGap configJgap, File loadXml){
		ImportFromXml(configFitness, configJgap, loadXml.getAbsolutePath());
	}
	
	public void  ImportFromXml(ConfigFitness configFitness, ConfigJGap configJgap, String pathToLoad){
			this.configFitness=configFitness;
			this.configJgap=configJgap;
        try {
        	SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(pathToLoad);
            Element root = doc.getRootElement();
            listChildren(root, "root");
        } catch (IOException ex) {
            System.out.println("Err IO");
            System.exit(1);
        } catch (JDOMException ex) {
           System.out.println("Err DOM");
            System.exit(1);
        }
	}
	
	public void  ImportFromXml(ConfigFitness configFitness, ConfigJGap configJgap, URL pathToLoad){
		this.configFitness=configFitness;
		this.configJgap=configJgap;
    try {
    	SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(pathToLoad);
        Element root = doc.getRootElement();
        listChildren(root, "root");
    } catch (IOException ex) {
        System.out.println("Err IO");
        System.exit(1);
    } catch (JDOMException ex) {
       System.out.println("Err DOM");
        System.exit(1);
    }
}
	
	private void listChildren(Element current, String nodo) {
        if(nodo.equals("fitness")){
        	configFitness.put(current.getAttributeValue("name"), Double.valueOf(current.getValue()));
        }else if(nodo.equals("jgap")){
        	configJgap.put(current.getAttributeValue("name"), Double.valueOf(current.getValue()));
		}else if(nodo.equals("function")){
			configFitness.getConfigFunction().put(current.getAttributeValue("name"), Double.valueOf(current.getValue()));
		}
        
        List children = current.getChildren();
        Iterator iterator = children.iterator();
        while (iterator.hasNext()) {
            Element child = (Element) iterator.next();
            if(current.getAttributeValue("type")!=null)
                listChildren(child, current.getAttributeValue("type"));
            else
                listChildren(child, "root");
        }
    }

	public void  ExportToXml(ConfigFitness configFitness, ConfigJGap configJgap, File saveXml){
		ExportToXml(configFitness, configJgap, saveXml.getAbsolutePath());
	}
	
	public void ExportToXml(ConfigFitness configFitness, ConfigJGap configJgap, String pathToSave){
		Element root=new Element("layout");
        
        //JGAP
        Element categoryJgap=new Element("category");
        categoryJgap.setAttribute("type","jgap");
        Vector conf=new Vector();
        for(String key: configJgap.keySet()){
            Element value=new Element("object");
            value.setAttribute("name", key);
            value.setText(format.format(configJgap.get(key)).replace(",", "."));
            conf.add(value);
        }
        categoryJgap.addContent(conf);
        root.addContent(categoryJgap);
        
        //FITNESS
        Element categoryFitness=new Element("category");
        categoryFitness.setAttribute("type","fitness");
        conf=new Vector();
        for(String key: configFitness.keySet()){
            Element value=new Element("object");
            value.setAttribute("name", key);
            value.setText(format.format(configFitness.get(key)).replace(",", "."));
            conf.add(value);
        }
        categoryFitness.addContent(conf);
        root.addContent(categoryFitness);
        
        //FUNCTION
        Element categoryFunction=new Element("category");
        categoryFunction.setAttribute("type","function");
        conf=new Vector();
        for(String key: configFitness.getConfigFunction().keySet()){
            Element value=new Element("object");
            value.setAttribute("name", key);
            value.setText(format.format(configFitness.getConfigFunction().get(key)).replace(",", "."));
            conf.add(value);
        }
        categoryFunction.addContent(conf);
        root.addContent(categoryFunction);
        
        //ASSEMBLO
        Document newDoc=new Document(root);
        newDoc.setDocType(new DocType("GeneticSoundLayout.dtd"));
        XMLOutputter serializer = new XMLOutputter();
        serializer.setFormat(Format.getPrettyFormat());
        try {
            Writer wout=new FileWriter(new File(pathToSave));
            serializer.output(newDoc, wout);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	
}
