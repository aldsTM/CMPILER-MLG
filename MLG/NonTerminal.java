import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents a non-terminal in the grammar.
 * @author Austin Fernandez
 */
public abstract class NonTerminal implements ParseObject {
	private HashMap<String,ArrayList<ParseObject>> components;
	private HashMap<String,Object> values;
	protected String type;
	private String[] production;
	private int setCtr;

	/**
	 * Basic constructor
	 * @param type left hand side of the production represented by this object
	 * @param prod the right hand side of this object's production
	 */
	public NonTerminal(String type,String prod) {
		components = new HashMap<String,ArrayList<ParseObject>>();
		this.type = type;
		this.production = prod.equals("e") ? new String[0] : prod.split(" ");
		// System.out.println(type + " ->" + getProdString() + ": " 
		// 						+ this.production.length + " parts");
		setCtr = 0;
		values = new HashMap<String,Object>();
	}

	/**
	 * @return type of this non terminal
	 */
	public String type() {
		return type;
	}

	/**
	 * prints thie stored indent
	 */
	public void printIndent(String token) {
		if( Driver.SHOW_TREE ) {
			int indent = getIndent() + 1;
			for(int i = 0; i < indent - 1; i++) {
				// System.out.print("  ");
				Driver.pw.print("  ");
			}
			if( indent > 0 ) {
				// System.out.print("|-");
				Driver.pw.print("|- ");
			}
			// System.out.println(token);
			Driver.pw.println(token);
		}
	}

	/**
	 * prints the root of the parse tree's branch
	 */
	public void printBranch() {
		if( Driver.SHOW_TREE ) {
			int indent = getIndent();
			for(int i = 0; i < indent - 1; i++) {
				// System.out.print("  ");
				Driver.pw.print("  ");
			}
			if( indent > 0 ) {
				// System.out.print("|-");
				Driver.pw.print("|- ");
			}
			// System.out.println(token);
			Driver.pw.println(type());
		}
	}

	/**
	 * sets the indent of the passed parameter to this object's plus one
	 * @param nt nonterminal to set indent of
	 */
	public void propagate(NonTerminal nt) {
		nt.put("indent",getIndent() + 1);
	}

	/**
	 * returns this objects' indent
	 */
	public int getIndent() {
		return getAsInt("indent");
	}

	public abstract void execute();

	/** 
	 * returns the component associated with the given key
	 * @param key key of component
	 * @return value mapped to key
	 */
	protected ParseObject getComponent(String key) {
		ArrayList<ParseObject> al = components.get(key);
		return al == null ? null : al.get(0);
	}

	/** 
	 * returns the component associated with the given key and index
	 * @param key key of component
	 * @param index index of component
	 * @return value mapped to key
	 */
	protected ParseObject getComponent(String key,int index) {
		ArrayList<ParseObject> al = components.get(key);
		return al == null ? null : (index >= al.size() ? null : al.get(index));
	}

	/**
	 * returns the split up production of this object
	 * @return production of this object
	 */
	protected String[] getProduction() {
		return production;
	}

	/**
	 * returns production as one string
	 * @return production as one string
	 */
	public String getProdString() {
		String s = "";

		if( production.length == 0 ) {
			s = "e";
		} else {
			for(String s1 : production) {
				s += s1 + " ";
			}
		}
		return s.trim();
	}

	/**
	 * set the given parse object as the next expected component of this object
	 * @param po object to set
	 * @throws Exception if all the components of this object are already set
	 */
	protected void setNext(ParseObject po) throws Exception {
		if( !isSet() ) {
			ArrayList<ParseObject> al = components.get(production[setCtr]);
			if(al == null ) {
				al = new ArrayList<ParseObject>();
				components.put(production[setCtr],al);
			}
			al.add(po);
			setCtr++;
		} else {
			throw new Exception("This object is already set");
		}
	} 

	/**
	 * checks whether all the components of this object are set.
	 * @return whether this object is fully set or not
	 */
	public boolean isSet() {
		// System.out.println(setCtr + "==" + production.length);
		return setCtr == production.length;
	}

	/**
	 * Puts an boolean into this nonterminals value map.
	 * @param key key of value
	 * @param value to store
	 */
	public void put(String key,boolean value) {
		values.put(key,value);
	}

	/**
	 * Puts an integer into this nonterminals value map.
	 * @param key key of value
	 * @param value to store
	 */
	public void put(String key,int value) {
		values.put(key,value);
	}

	/**
	 * Puts an float into this nonterminals value map.
	 * @param key key of value
	 * @param value to store
	 */
	public void put(String key,float value) {
		values.put(key,value);
	}

	/**
	 * Puts an double into this nonterminals value map.
	 * @param key key of value
	 * @param value to store
	 */
	public void put(String key,double value) {
		values.put(key,value);
	}

	/**
	 * Puts an longinto this nonterminals value map.
	 * @param key key of value
	 * @param value to store
	 */
	public void put(String key,long value) {
		values.put(key,value);
	}

	/**
	 * Puts an string into this nonterminals value map.
	 * @param key key of value
	 * @param value to store
	 */
	public void put(String key,String value) {
		values.put(key,value);
	}

	/**
	 * Puts an object into this nonterminals value map.
	 * @param key key of value
	 * @param value to store
	 */
	public void put(String key,Object value) {
		values.put(key,value);
	}

	/**
	 * Puts an array into this nonterminals value map.
	 * @param key key of value
	 * @param value to store
	 */
	public void put(String key,Object[] value) {
		values.put(key,value);
	}

	/**
	 * Returns the value of the chosen key as a boolean
	 * @param key key of value
	 * @return value stored
	 */
	public boolean getAsBoolean(String key) {
		Object o = values.get(key);
		return (boolean)o;
	}

	/**
	 * Returns the value of the chosen key as an int
	 * @param key key of value
	 * @return value stored
	 */
	public int getAsInt(String key) {
		Object o = values.get(key);
		return (int)o;
	}

	/**
	 * Returns the value of the chosen key as a float
	 * @param key key of value
	 * @return value stored
	 */
	public float getAsFloat(String key) {
		Object o = values.get(key);
		return (float)o;
	}

	/**
	 * Returns the value of the chosen key as a double
	 * @param key key of value
	 * @return value stored
	 */
	public double getAsDouble(String key) {
		Object o = values.get(key);
		return (double)o;
	}

	/**
	 * Returns the value of the chosen key as a long
	 * @param key key of value
	 * @return value stored
	 */
	public long getAsLong(String key) {
		Object o = values.get(key);
		return (long)o;
	}

	/**
	 * Returns the value of the chosen key as a String
	 * @param key key of value
	 * @return value stored
	 */
	public String getAsString(String key) {
		Object o = values.get(key);
		return (String)o;
	}

	/**
	 * Returns the value of the chosen key as an Object
	 * @param key key of value
	 * @return value stored
	 */
	public Object getAsObject(String key) {
		return values.get(key);
	}

	/**
	 * Returns the value of the chosen key as an array
	 * @param key key of value
	 * @return value stored
	 */
	public Object[] getAsArray(String key) {
		Object o = values.get(key);
		return (Object[])o;
	}
}