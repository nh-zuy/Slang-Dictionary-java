package slangwords.data;

import java.time.LocalDateTime;

/*
 * Store Browsing history
 */
public class History {
	private LocalDateTime _time;
	private String _keyword;
	
	public History(String slang) {
		this._keyword = slang;
		this._time = LocalDateTime.now();
	}

	public LocalDateTime getTime() {
		return _time;
	}

	public void setTime(LocalDateTime _time) {
		this._time = _time;
	}

	public String getKeyword() {
		return _keyword;
	}

	public void setKeyword(String _keyword) {
		this._keyword = _keyword;
	}
}
