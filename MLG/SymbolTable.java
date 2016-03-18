import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable {
	private static SymbolTable instance = null;
	private ArrayList<HashMap<String,Variable>> map;
	private ArrayList<HashMap<String,Variable>> map2;
	private boolean isSwitched;

	private SymbolTable() {
		map = new ArrayList<HashMap<String,Variable>>();
		map2 = new ArrayList<HashMap<String,Variable>>();
		isSwitched = false;
	}

	public static SymbolTable instance() {
		if( instance == null ) {
			instance = new SymbolTable();
		}
		return instance;
	}

	private ArrayList<HashMap<String,Variable>> getMap() {
		return isSwitched ? map2 : map;
	}

	public void pushContext() {
		ArrayList<HashMap<String,Variable>> map = getMap();
		map.add(0,new HashMap<String,Variable>());
	}

	public void popContext() {
		ArrayList<HashMap<String,Variable>> map = getMap();
		map.remove(0);
	}

	public void switchContext() {
		isSwitched = !isSwitched;
	}

	public void clear() {
		ArrayList<HashMap<String,Variable>> map = getMap();
		map.clear();	
	}

	public Variable get(String varname) {
		ArrayList<HashMap<String,Variable>> map = getMap();
		for(int i = 0; i < map.size(); i++) {
			Variable v = map.get(i).get(varname);
			if( v != null ) {
				return v;
			}
		}
		return null;
	}

	public boolean declare(String varname, String type) {
		ArrayList<HashMap<String,Variable>> map = getMap();
		Variable v = map.get(0).get(varname);
		if( v == null ) {
			map.get(0).put(varname,new Variable(type));
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Sets this variable's value.
	 * @param key key of value
	 * @param value to store
	 */
	public boolean assign(String varname,boolean value) {
		ArrayList<HashMap<String,Variable>> map = getMap();
		for(int i = 0; i < map.size(); i++) {
			Variable v = map.get(i).get(varname);
			if( v != null ) {
				v.put(value);
				return true;
			}
		}
		return true;
	}

	/**
	 * Sets this variable's value.
	 * @param key key of value
	 * @param value to store
	 */
	public boolean assign(String varname,int value) {
		ArrayList<HashMap<String,Variable>> map = getMap();
		for(int i = 0; i < map.size(); i++) {
			Variable v = map.get(i).get(varname);
			if( v != null ) {
				v.put(value);
				return true;
			}
		}
		return true;
	}

	/**
	 * Sets this variable's value.
	 * @param key key of value
	 * @param value to store
	 */
	public boolean assign(String varname,float value) {
		ArrayList<HashMap<String,Variable>> map = getMap();
		for(int i = 0; i < map.size(); i++) {
			Variable v = map.get(i).get(varname);
			if( v != null ) {
				v.put(value);
				return true;
			}
		}
		return true;
	}

	/**
	 * Sets this variable's value.
	 * @param key key of value
	 * @param value to store
	 */
	public boolean assign(String varname,double value) {
		ArrayList<HashMap<String,Variable>> map = getMap();
		for(int i = 0; i < map.size(); i++) {
			Variable v = map.get(i).get(varname);
			if( v != null ) {
				v.put(value);
				return true;
			}
		}
		return true;
	}

	/**
	 * Puts an longinto this nonterminals value map.
	 * @param key key of value
	 * @param value to store
	 */
	public boolean assign(String varname,long value) {
		ArrayList<HashMap<String,Variable>> map = getMap();
		for(int i = 0; i < map.size(); i++) {
			Variable v = map.get(i).get(varname);
			if( v != null ) {
				v.put(value);
				return true;
			}
		}
		return true;
	}

	/**
	 * Sets this variable's value.
	 * @param key key of value
	 * @param value to store
	 */
	public boolean assign(String varname,String value) {
		ArrayList<HashMap<String,Variable>> map = getMap();
		for(int i = 0; i < map.size(); i++) {
			Variable v = map.get(i).get(varname);
			if( v != null ) {
				v.put(value);
				return true;
			}
		}
		return true;
	}

	/**
	 * Sets this variable's value.
	 * @param key key of value
	 * @param value to store
	 */
	public boolean assign(String varname,Object value) {
		ArrayList<HashMap<String,Variable>> map = getMap();
		for(int i = 0; i < map.size(); i++) {
			Variable v = map.get(i).get(varname);
			if( v != null ) {
				v.put(value);
				return true;
			}
		}
		return true;
	}

	/**
	 * Sets this variable's value.
	 * @param key key of value
	 * @param value to store
	 */
	public boolean assign(String varname,Object[] value) {
		ArrayList<HashMap<String,Variable>> map = getMap();
		for(int i = 0; i < map.size(); i++) {
			Variable v = map.get(i).get(varname);
			if( v != null ) {
				v.put(value);
				return true;
			}
		}
		return true;
	}
}