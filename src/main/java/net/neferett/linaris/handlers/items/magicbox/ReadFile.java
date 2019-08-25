package net.neferett.linaris.handlers.items.magicbox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.neferett.linaris.Main;
import net.neferett.linaris.utils.json.JSONException;

public class ReadFile {

	protected BufferedReader	bf;
	protected List<ReadJSON>	js	= new ArrayList<>();

	public ReadFile() {
		this.init();
	}

	public List<ReadJSON> getjs() {
		return this.js;
	}

	public void init() {
		String line;

		try {
			this.bf = new BufferedReader(new FileReader(new File(Main.getMainInstance().getDataFolder()
					.getAbsolutePath().replace(Main.getMainInstance().getDataFolder().getPath(), "head.txt"))));
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while ((line = this.bf.readLine()) != null)
				this.js.add(new ReadJSON(line));
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
