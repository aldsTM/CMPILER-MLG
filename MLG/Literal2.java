public class Literal2 extends NonTerminal {

	public Literal2(String pattern) {
		super("literal2",pattern);
	}

	public void interpret() throws Exception {
		String str;
		Token t;
		printBranch();
		switch(getProdString()) {			
			case "float":
				t = (Token) getComponent("float");
				printIndent(t.token());
				float f = Float.parseFloat(t.token().trim());
				put("type","float");
				put("val",f);
				break;
			case "char":
				t = (Token)getComponent("char");
				printIndent(t.token());
				str = t.token();
				str = str.substring(1,str.length() - 1)
						.replaceAll("\\\\n","\n")
						.replaceAll("\\\\t","\t")
						.replaceAll("\\\\b","\b")
						.replaceAll("\\\\r","\r")
						.replaceAll("\\\\f","\f")
						.replaceAll("\\\\\'","'")
						.replaceAll("\\\\\"","\"")
						.replaceAll("\\\\\\\\","\\");
				put("type","char");
				put("val",str);
				break;
			case "string":
				t = (Token) getComponent("string");
				printIndent(t.token());
				str = t.token();
				str = str.substring(1,str.length() - 1)
						.replaceAll("\\\\n","\n")
						.replaceAll("\\\\t","\t")
						.replaceAll("\\\\b","\b")
						.replaceAll("\\\\r","\r")
						.replaceAll("\\\\f","\f")
						.replaceAll("\\\\\'","'")
						.replaceAll("\\\\\"","\"")
						.replaceAll("\\\\\\\\","\\");
				put("type","string");
				put("val",str);
				break;
			default:
		}
	}

	public void execute() {

	}
}