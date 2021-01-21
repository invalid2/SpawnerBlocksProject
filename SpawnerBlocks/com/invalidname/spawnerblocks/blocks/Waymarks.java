package com.invalidname.spawnerblocks.blocks;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import dangerzone.DangerZone;
import dangerzone.Utils;

public class Waymarks {
	
	public static List<WaymarkPos> waymarks = new ArrayList<WaymarkPos>();
	public static Properties prop = new Properties();
	
	public void init() {
		try {
			prop.load(new FileInputStream("./worlds/"+DangerZone.worldname+"/waymarks.properties"));
			int id = 0, posx, posy, posz, dim;
			while(id < 64) {
				posx = Utils.getPropertyInt(prop, String.format("waymark_%d_%s", id, "x"), 0, Integer.MAX_VALUE, 0);
				posy = Utils.getPropertyInt(prop, String.format("waymark_%d_%s", id, "y"), 0, Integer.MAX_VALUE, 0);
				posz = Utils.getPropertyInt(prop, String.format("waymark_%d_%s", id, "x"), 0, Integer.MAX_VALUE, 0);
				dim = Utils.getPropertyInt(prop, String.format("waymark_%d_%s", id, "d"), 0, Integer.MAX_VALUE, 0);
				
				if(posx == 0 && posy == 0 && posz == 0 && dim == 0)
					break;
				
				waymarks.add(id, new WaymarkPos( id, posx, posy, posz, dim));
				id++;
			}
			
		}  catch (IOException e) {
			System.out.println("No waymarks file here");
			//e.printStackTrace();
		}
	}
	
	public void insert(int posx, int posy, int posz, int dimension) {
		if(waymarks.isEmpty()) {
			
			prop.setProperty(String.format("waymark_%d_%s", 0, "x"), ""+posx);
			prop.setProperty(String.format("waymark_%d_%s", 0, "y"), ""+posy);
			prop.setProperty(String.format("waymark_%d_%s", 0, "z"), ""+posz);
			prop.setProperty(String.format("waymark_%d_%s", 0, "d"), ""+dimension);
			try {
				prop.store(new FileOutputStream("./worlds/"+DangerZone.worldname+"/waymarks.properties"), "");
				waymarks.add( new WaymarkPos( 0, posx, posy, posz, dimension));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			prop.setProperty(String.format("waymark_%d_%s", waymarks.size(), "x"), ""+posx);
			prop.setProperty(String.format("waymark_%d_%s", waymarks.size(), "y"), ""+posy);
			prop.setProperty(String.format("waymark_%d_%s", waymarks.size(), "z"), ""+posz);
			prop.setProperty(String.format("waymark_%d_%s", waymarks.size(), "d"), ""+dimension);
			
			try {
				prop.store(new FileOutputStream("./worlds/"+DangerZone.worldname+"/waymarks.properties"), "");
				waymarks.add( new WaymarkPos( waymarks.size(), posx, posy, posz, dimension));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void remove(int id) {
		
		if(waymarks.isEmpty()) {
			return;
		} else {
			waymarks.remove(id);
			try {
				prop.store(new FileOutputStream("./worlds/"+DangerZone.worldname+"/waymarks.properties"), "");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
