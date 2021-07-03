package slangwords.model;

import java.util.ArrayList;

import slangwords.data.History;

public class HistoryModel {
	private ArrayList<History> _data;
	
	public HistoryModel() {
		this._data = new ArrayList<History>();
	}

	public ArrayList<History> getHistory() {
		return _data;
	}

	public void setData(ArrayList<History> _data) {
		this._data = _data;
	}
	
	public void newBrowse(String keyword) {
		this._data.add(new History(keyword));
	}
	
	public void clearHistory() {
		this._data.clear();
	}
}
