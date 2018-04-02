package net.neferett.linaris.lobby.handlers.items.magicbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import net.neferett.linaris.utils.json.JSONException;
import net.neferett.linaris.utils.json.JSONObject;

public class ReadJSON {

	protected JSONObject		js;
	protected String			name;
	protected BufferedReader 	bf;
	protected List<JSONObject>  objs;

	public ReadJSON(String a) throws IOException, JSONException{
		this.js = new JSONObject(a);
	}
	
	public String	getName() throws JSONException{
		return (js.getJSONObject("display").getString("Name"));
	}
	
	public String	getSkullOwner() throws JSONException{
		return (js.getJSONObject("SkullOwner").getString("Id"));
	}
	
	public String	getValue() throws JSONException{
		return (js.getJSONObject("SkullOwner").getJSONObject("Properties").getJSONArray("textures").getJSONObject(0).getString("Value"));
	}
	
}
