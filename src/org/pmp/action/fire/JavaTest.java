package org.pmp.action.fire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.pmp.util.JsonConvert;
import org.pmp.vo.FireInfo;

public class JavaTest {
    
	public static void main(String[] args) throws JDOMException, IOException {
/*		List<FireInfo> fireInfos=new ArrayList<FireInfo>();
		
		FireInfo f1=new FireInfo();
		f1.setFireId(1);
		f1.setDeviceNumber("A22343");
		f1.setReceiveInfo("data");
		f1.setReceiveTime(new Date());
		f1.setState(1);
		
		FireInfo f2=new FireInfo();
		f2.setFireId(2);
		f2.setDeviceNumber("A22343");
		f2.setReceiveInfo("data");
		f2.setReceiveTime(new Date());
		f2.setState(2);
		
		fireInfos.add(f1);
		fireInfos.add(f2);
		
		String json=JsonConvert.toJson(fireInfos);
		
		System.out.println(json);*/
		
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build("src\\org\\pmp\\action\\fire\\zoneConfig.xml");
		Element root = doc.getRootElement();
		
		Element zone=root.getChild("zone");
		List<Element> list = zone.getChildren();
        for (Element element : list) {
			String name=element.getAttributeValue("devicenumber");
			String devicetypename=element.getAttributeValue("devicetypename");
			System.out.println(name+":"+devicetypename);
		}
		
		//Element device1=	device.getValue();
		
		//XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
		//String outStr = xmlOut.outputString(device1);
		
		//System.out.println(outStr);
	}
	
}
