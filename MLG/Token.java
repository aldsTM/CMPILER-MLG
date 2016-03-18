public class Token implements ParseObject {
	private String token;
	private String type;
	private int lineNo;
	private int value;
	private double time;

	public Token(String token, String type, int lineNo) {
		this.token = token;
		this.type = type;
		this.lineNo = lineNo;
	}

	public String token() {
		return token;
	}

	public String type() {
		return type;
	}

	public int lineNo() {
		return lineNo;
	}

	public int intValue() {
		return value;
	}

	public double time() {
		return time;
	}

	public void interpret() throws Exception {
		switch(type) {
			//feel free to write code here
			default:
		}
	}

	public String toString() {
		return (token.equals("\n") ? "\\n" : token) + " - " + type + " at line " 
				+ lineNo;
	}
}