package org.pmp.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.pmp.vo.FireDevice;

public class XmlReadUtil {

	public static List<FireDevice> readXml(String path) throws JDOMException, IOException {
		List<FireDevice> xmlList = new ArrayList<FireDevice>();
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(path);
		Element root = doc.getRootElement();
		Element zone = root.getChild("zone");
		List<Element> list = zone.getChildren();
		
		for (Element element : list) {
			FireDevice fireDevice=new FireDevice();
			String deviceNumber = element.getAttributeValue("devicenumber");
			String typeName = element.getAttributeValue("devicetypename");
			String mark=element.getAttributeValue("mark");
			//int len=deviceNumber.length();
			//Integer locationCode=Integer.valueOf(deviceNumber.substring(2, 3));
			//Integer 
			fireDevice.setDeviceNumber(deviceNumber.substring(2, deviceNumber.length()));
			fireDevice.setTypeName(typeName);
			fireDevice.setMark(mark);
			xmlList.add(fireDevice);
		}
		return xmlList;
	}
}
