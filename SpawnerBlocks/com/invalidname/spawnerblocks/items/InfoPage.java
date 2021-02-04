package com.invalidname.spawnerblocks.items;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dangerzone.InventoryContainer;
import dangerzone.entities.Entity;
import dangerzone.items.Item;

public class InfoPage extends Item {
	
	NodeList contents;

	public InfoPage(String n, String txt, String content_path) {
		super(n, txt);
		
		File file = new File(content_path);
		
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
			contents = doc.getElementsByTagName("*");
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean onRightClick(Entity holder, Entity clickedon, InventoryContainer ic){
		
		
		return false; //return TRUE if your item did something and should be deleted/decremented
	}
}
