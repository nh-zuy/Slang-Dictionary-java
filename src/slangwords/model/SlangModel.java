package slangwords.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import slangwords.data.Slang;

/**
 * Model for control slang word
 * @author zuy
 *
 * @param <K>
 * @param <V>
 */
public class SlangModel<K, V> {
	Slang<K, V> _data;
	
	public SlangModel(Slang<K, V> value) {
		this._data = value;
	}
	
	public Set<K> slangs() {
		return this._data.slangs();
	}
	public Collection<V> definitions() {
		return this._data.definitions();
	}
	
	public V searchSlang(K key) {
		return this._data.getSlang(key);
	}
	
	public ArrayList<K> searchDef(V value) {
		Set<K> keys = this._data.slangs();
		ArrayList<K> result = new ArrayList<K>();
		String v;
		String query = value.toString();
		
		if (query.isBlank()) {
			return null;
		};
		
		for (K k: keys) {
			v = this._data.getSlang(k).toString();
			
			if (v.contains(query)) {
				result.add(k);
			};
		};
		
		return result;
	}
	
	public void addSlang(K key, V value) {
		this._data.addSlang(key, value);
	}
	
	public void updateSlang(K key, V value) {
		this._data.updateSlang(key, value);
	}
	
	public void deleteSlang(K key) {
		this._data.deleteSlang(key);
	}
	
	public void duplicateSlang(K key, V value) {
		V oldValue = this._data.getSlang(key);
		@SuppressWarnings("unchecked")
		V newValue = (V)(oldValue + "| " + value);
		this._data.updateSlang(key, newValue);
	}
	
	public void reset() {
		try {
			this._data.importData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
