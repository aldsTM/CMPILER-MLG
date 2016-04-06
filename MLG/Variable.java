public class Variable {
	private String type;
	private Object value;
	private boolean isConstant;
	private boolean isVirgin;

	public Variable(String type){
		this.type = type;
		this.isConstant = false;
		this.isVirgin = true;
	}

	public Variable(String type, boolean isConstant) {
		this.type = type;
		this.isConstant = isConstant;
		this.isVirgin = true;
	}

	public String type() {
		return type;
	}

	/**
	 * Sets this variable's value.
	 * @param key key of value
	 * @param value to store
	 */
	public void put(boolean value) {
		if (!isConstant || (isConstant && isVirgin == true)){
			this.value = value;
			isVirgin = false;
		} else {
			System.out.println("Type checking error: Can't assign value " + 
								value + " to a constant");
		}
	}

	/**
	 * Sets this variable's value.
	 * @param key key of value
	 * @param value to store
	 */
	public void put(int value) {
		if (!isConstant || (isConstant && isVirgin == true)){
			this.value = value;
			isVirgin = false;
		} else {
			System.out.println("Type checking error: Can't assign value " + 
								value + " to a constant");
		}
	}

	/**
	 * Sets this variable's value.
	 * @param key key of value
	 * @param value to store
	 */
	public void put(float value) {
		if (!isConstant || (isConstant && isVirgin == true)){
			this.value = value;
			isVirgin = false;
		} else {
			System.out.println("Type checking error: Can't assign value " + 
								value + " to a constant");
		}
	}

	/**
	 * Sets this variable's value.
	 * @param key key of value
	 * @param value to store
	 */
	public void put(double value) {
		if (!isConstant || (isConstant && isVirgin == true)){
			this.value = value;
			isVirgin = false;
		} else {
			System.out.println("Type checking error: Can't assign value " + 
								value + " to a constant");
		}
	}

	/**
	 * Puts an longinto this nonterminals value map.
	 * @param key key of value
	 * @param value to store
	 */
	public void put(long value) {
		if (!isConstant || (isConstant && isVirgin == true)){
			this.value = value;
			isVirgin = false;
		} else {
			System.out.println("Type checking error: Can't assign value " + 
								value + " to a constant");
		}
	}

	/**
	 * Sets this variable's value.
	 * @param key key of value
	 * @param value to store
	 */
	public void put(String value) {
		if (!isConstant || (isConstant && isVirgin == true)){
			this.value = value;
			isVirgin = false;
		} else {
			System.out.println("Type checking error: Can't assign value " + 
								value + " to a constant");
		}
	}

	/**
	 * Sets this variable's value.
	 * @param key key of value
	 * @param value to store
	 */
	public void put(Object value) {
		if (!isConstant || (isConstant && isVirgin == true)){
			this.value = value;
			isVirgin = false;
		} else {
			System.out.println("Type checking error: Can't assign value " + 
								value + " to a constant");
		}
	}

	/**
	 * Sets this variable's value.
	 * @param key key of value
	 * @param value to store
	 */
	public void put(Object[] value) {
		if (!isConstant || (isConstant && isVirgin == true)){
			this.value = value;
			isVirgin = false;
		} else {
			System.out.println("Type checking error: Can't assign value " + 
								value + " to a constant");
		}
	}

	/**
	 * Returns the value of this variable
	 * @param key key of value
	 * @return value stored
	 */
	public boolean getAsBoolean() {
		return (boolean)value;
	}

	/**
	 * Returns the value of this variable
	 * @param key key of value
	 * @return value stored
	 */
	public int getAsInt() {
		return (int)value;
	}

	/**
	 * Returns the value of this variable
	 * @param key key of value
	 * @return value stored
	 */
	public float getAsFloat() {
		return (float)value;
	}

	/**
	 * Returns the value of this variable
	 * @param key key of value
	 * @return value stored
	 */
	public double getAsDouble() {
		return (double)value;
	}

	/**
	 * Returns the value of this variable
	 * @param key key of value
	 * @return value stored
	 */
	public long getAsLong() {
		return (long)value;
	}

	/**
	 * Returns the value of this variable
	 * @param key key of value
	 * @return value stored
	 */
	public char getAsChar() {
		return (char)getAsInt();
	}

	/**
	 * Returns the value of this variable
	 * @param key key of value
	 * @return value stored
	 */
	public String getAsString() {
		return (String)value;
	}

	/**
	 * Returns the value of this variable
	 * @param key key of value
	 * @return value stored
	 */
	public Object getAsObject() {
		return value;
	}

	/**
	 * Returns the value of this variable
	 * @param key key of value
	 * @return value stored
	 */
	public Object[] getAsArray() {
		return (Object[])value;
	}
}