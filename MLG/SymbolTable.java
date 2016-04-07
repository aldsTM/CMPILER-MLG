import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class SymbolTable {
	private static SymbolTable instance = null;
	private Stack<ArrayList<HashMap<String,Variable>>> stack;
	private Stack<String> tags;

	private SymbolTable() {
		stack = new Stack<ArrayList<HashMap<String,Variable>>>();
		tags = new Stack<String>();
	}

	public static SymbolTable instance() {
		if( instance == null ) {
			instance = new SymbolTable();
		}
		return instance;
	}

	private ArrayList<HashMap<String,Variable>> getMap() {
		return stack.peek();
	}

	public void pushContext() {
		ArrayList<HashMap<String,Variable>> map = getMap();
		map.add(0,new HashMap<String,Variable>());
	}

	public void popContext() {
		ArrayList<HashMap<String,Variable>> map = getMap();
		map.remove(0);
	}

	public void pushFrame(String tag) {
		tags.push(tag);
		stack.push(new ArrayList<HashMap<String,Variable>>());
	}

	public void popFrame() {
		tags.pop();
		stack.pop();
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

	public boolean declare(String varname, String type, boolean isConstant) {
		ArrayList<HashMap<String,Variable>> map = getMap();
		Variable v = map.get(0).get(varname);
		if( v == null ) {
			map.get(0).put(varname,new Variable(type,isConstant));
			return true;
		} else {
			return false;
		}
	}

	public boolean isDeclared(String varname) {
		ArrayList<String> varnames = new ArrayList<String>();
		ArrayList<HashMap<String,Variable>> map = getMap();
		for(HashMap<String,Variable> context : map ) {
			String[] decs = context.keySet().toArray(new String[0]);
			for(String s : decs) {
				varnames.add(s);
			}
		}
		return varnames.indexOf(varname) != -1;
	}

	public String callStack() {
		String callStack = "CALL STACK\n";
		for(int i = 0; i < tags.size(); i++ ) {
			if( i > 0 ) {
				callStack += "\n";
			}
			callStack += "\t" + tags.get(i);
		}
		return callStack;
	}

	public String symbolTable() {
		ArrayList<HashMap<String,Variable>> map = getMap();
		String symbolTable = "SYMBOL TABLE\n";
		for(int i = 0; i < map.size(); i++) {
			if( i > 0 && map.get(i).keySet().size() > 0 ) {
				symbolTable += "\n";
			}
			String[] vars = map.get(i).keySet().toArray(new String[0]);
			for( int j = 0; j < vars.length; j++ ) {
				if( j > 0 ) {
					symbolTable += "\n";
				}
				symbolTable += "\t" + vars[j] + " - " 
								+ map.get(i).get(vars[j]).toString();
			}
		}
		return symbolTable;
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