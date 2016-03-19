import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConcreteTokenizer implements Tokenizer {
	private ArrayList<Token> tokens;
	private String code;
	private int state;
	private String currToken;
	private int lineNo;
	private static String[] reserved;
	private static String[] operators;

	public ConcreteTokenizer() {
		tokens = new ArrayList<Token>();
		state = 0;
		currToken = "";
		reserved = new String[] {
			"mmr","kdr","char","SAMPLETEXT","isMLG","WHILE","DO","FOR",
			"AND","OR","NOT","ELSE","print","println","fuckTheUniverse",
			"PRESS","START","ILLUMINATI","CONFIRMED","M","WHATCHASAY"
		};
		operators = new String[] {
			"(","+","-","[","=","*","/","{","<","<=",">",">=","==","!=","!",
			",",")","]","}",";","%"
		};
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void tokenize() {
		currToken = "";
		lineNo = 1;
		for(int i = 0; i < code.length(); i++) {
			char c = code.charAt(i);
			// System.out.println(state + " " + currToken + " " 
			// 					+ (c == '\n' ? "\\n" 
			// 						: (c == ' ' ? "space" : c)));
			switch(state) {
				//new token
				case 0:
					if(c == '/') {
						state = 1;
						currToken += c;
					} else if(c == '\t' || c == ' ') {
						continue;
					} else if(c >= 'A' && c <= 'Z'  || c >= 'a' && c <= 'z') {
						state = 4;
						currToken += c;
					} else if( c == '\n' ) {
						currToken = "";
						lineNo++;
					} else if (c >= '0' && c <= '9' ) {
						state = 5;
						currToken += c;
					} else if(c == '"' ) {
						state = 6;
						currToken += c;
					} else if(c == '\'') {
						state = 11;
						currToken += c;
					} else {
						//check if start of operator
						for(String s: operators) {
							if( c == s.charAt(0)) {
								state = 3;
								currToken += c;
								break;
							}
						}
					}
					break;
				//slash read
				case 1:
					if( c == '/') {
						state = 2;
						currToken = "";
					} else if(c == '*') {
						state = 8;
						currToken = "";
					} else {
						addToken();
						if( c == '\n') {
							lineNo++;
						}
						i--;
						//state = 7;
						state = 0;
					}
					break;
				//in multiline comment
				case 8:
					if( c == '*') {
						state = 9;
					} else if( c == '\n') {
						lineNo++;
					}
					break;
				case 9:
					if( c == '/') {
						state = 0;
					} else {
						if( c == '\n') {
							lineNo++;
						}
						state = 8;
					}
					break;
				//in single line comment
				case 2:
					if( c == '\n') {
						state = 0;
						currToken = "";
						lineNo++;
					}
					break;
				//operator check
				case 3:
					if(c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' ) {
						state = 4;
						addToken();
						currToken += c;
					} else if(c >= '0' && c <= '9' ) {						
						state = 5;
						addToken();
						currToken += c;
					} else if( c == ' ' || c == '\t' ) {
						state = 0;
						addToken();
					} else if( c == '\n') {
						state = 0;
						addToken();
						currToken = "";
						lineNo++;
					} else {
						boolean foundStart = false;
						//check if start of operator
						for(String s: operators) {
							if( s.startsWith(currToken + c)) {
								currToken += c;
								foundStart = true;
								break;
							}
						}

						if( !foundStart) {
							addToken();
							i--;
							state = 0;
						}
					}
					break;
				//word check
				case 4:
					if( c == ' ' || c == '\t' ) {
						state = 0;
						addToken();
					} else if( c == '\n') {
						state = 0;
						addToken();
						currToken = "";
						lineNo++;
					} else if( c == '/') {
						state = 1;
						addToken();
						currToken += c;
					} else if( c >= 'A' && c <= 'Z'  || c >= 'a' 
									&& c <= 'z' || c >= '0' && c <= '9' 
									|| c == '_') {
						currToken += c;
					} else {
						addToken();
						i--;
						state = 0;
					}
					break;
				//num check
				case 5: 
					if(c >= 'A' && c <= 'Z'  || c >= 'a' 
									&& c <= 'z') {
						state = 7;
						currToken += c;
					} else if( c == '.' ) {
						currToken += c;
						state = 14;
					} else if( c < '0' || c > '9' ) {
						addToken();
						i--;
						state = 0;
					} else {
						currToken += c;
					}
					break;
				//float check
				case 14:
					if(c >= 'A' && c <= 'Z'  || c >= 'a' 
									&& c <= 'z') {
						state = 7;
						currToken += c;
					} else if( c < '0' || c > '9' ) {
						addToken();
						i--;
						state = 0;
					} else {
						currToken += c;
					}
					break;
				//string check
				case 6:
					currToken += c;
					if( c == '\\' ) {
						state = 10;
					} else if( c == '"') {
						addToken();
						state = 0;
					}
					break;
				case 10:
					currToken += c;
					state = 6;
					break;
				//char check
				case 11:
					currToken += c;
					if( c == '\'') {
						addToken();
						state = 0;
					} else if( c == '\\') {
						state = 12;
					} else {
						state = 13;
					}
					break;
				case 12:
					currToken += c;
					state = 13;
					break;
				case 13:
					currToken += c;
					if( c != '\'') {
						state = 7;
					} else {
						addToken();
						state = 0;
					}
					break;
				//gibberish checker
				case 7:
					if( c == ' ' || c == '\t') {
						addToken();
						state = 0;
					} else if( c == '\n') {
						addToken();
						state = 0;
						lineNo++;
					} else if( c == '/') {
						addToken();
						state = 1;
						currToken += c;
					} else {
						currToken += c;
					}
					break;
				default:
			}
		}
		addToken();
	}

	public void addToken() {
		if( currToken.length() > 0 ) {
			Token t = new Token(currToken,tokenType(),lineNo);
			if( tokens.size() == 0 || !t.type().equals("newline")
					|| !tokens.get(tokens.size() - 1).type().equals("newline")) {
				tokens.add(t);
			}
			//System.out.println("Added " + t);
		}
		currToken = "";
	}

	public String tokenType() {
		if( currToken.equals("true") || currToken.equals("false") ) {
			return "boolean";
		}

		for(String s: reserved) {
			if( s.equals(currToken)) {
				return s;
			}
		}

		for(String s: operators) {
			if(s.equals(currToken)) {
				return s;
			}
		}

		try {
			int i = Integer.parseInt(currToken);
			return "int";
		} catch(NumberFormatException nfe) {
			try {
				double d = Double.parseDouble(currToken);
				return "float";
			} catch(NumberFormatException nfe2) {
				if( currToken.matches("^\"([^\"]|\\\")*\"$") ) {
					return "string";
				} else if( currToken.length() >= 2 && currToken.length() <= 4
							&& currToken.charAt(0) == '\'' 
							&& currToken.charAt(currToken.length() - 1) 
								== '\'') {
					return "char";
				} else if ( currToken.matches("^[a-zA-Z][A-Za-z0-9_]*$") ) {
					//else if( currToken.matches("^([a-zH-Z][A-Za-z0-9_]*|" + "[A-G][A-Za-z0-9_]+)$") ) {
					return "IDENTIFIER";
				} else {
					System.out.println("Lexical error: Unrecognized token " + currToken + " at line " + lineNo);
					return "other";
				}
			}
		}
	}

	public List<Token> getTokens() {
		return tokens;
	}
}