package net.neferett.linaris.lobby.items.epicchest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.neferett.linaris.utils.json.JSONException;
import net.neferett.linaris.lobby.Lobby;

public class ReadFile {

	protected List<ReadJSON> js = new ArrayList<ReadJSON>();
	protected BufferedReader bf;
	
	public ReadFile(){
		init();
	}
	
	public List<ReadJSON> getjs(){
		return js;
	}
	
	public void init(){
		String line;
		
		try {
			bf = new BufferedReader(new FileReader(new File(Lobby.getInstance().getDataFolder().getAbsolutePath().replace(Lobby.getInstance().getDataFolder().getPath(), "head.txt"))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while ((line = bf.readLine()) != null){
				js.add(new ReadJSON(line));
			}
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
