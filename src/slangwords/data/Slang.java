package slangwords.data;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

/**
 * Generic for Slang word
 * @author zuy
 *
 * @param <K>
 * @param <V>
 */
public class Slang<K, V> {
	private Hashtable<K, V> _dict;
	
	public Slang() {
		_dict = new Hashtable<K, V>();
	}
	
	/*
	 * Return set of all key
	 */
	public Set<K> slangs() {
		if (!this._dict.isEmpty()) {
			return this._dict.keySet();
		};
		
		return null;
	}
	
	/*
	 * Return all of value
	 */
	public Collection<V> definitions() {
		if (!this._dict.isEmpty()) {
			return this._dict.values();
		};
		
		return null;
	}

	/*
	 * 
	 */
	@SuppressWarnings("unchecked")
	public V getSlang(K slang) {
		V value = this._dict.get(slang);
		
		if (value == null) {
			return (V)("");
		};
		
		return value;
	}
	
	/*
	 * Add new slang
	 */
	public void addSlang(K key, V value) {
		this._dict.put(key, value);
	}
	
	/*
	 * Update a existed slang
	 */
	public void updateSlang(K key, V value) {
		this._dict.replace(key, value);
	}
	
	/*
	 * Delete slang
	 */
	public void deleteSlang(K key) {
		this._dict.remove(key);
	}
	
	/*
	 * Auto import from slang.txt
	 */
	@SuppressWarnings("unchecked")
	public void importData() throws IOException {
		try {
			String line;
			K slang;
			V def;
			String[] data;
			
			BufferedReader br = new BufferedReader(new FileReader("slangwords/data/slang.txt"));
			
			while ((line = br.readLine()) != null) {
				if (line.contains("`")) {
					data = line.split("`");
					slang = (K)data[0];
					def = (V)data[1];
					this._dict.put(slang, def);
				};
			};
			
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
