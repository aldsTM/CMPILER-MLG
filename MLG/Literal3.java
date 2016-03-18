public class Literal3 extends NonTerminal {
	public Literal3(String pattern) {
		super("literal3",pattern);
	}

	public void interpret() throws Exception {
		Token t;
		printBranch();
		switch(getProdString()) {	
			case "boolean":
				t = (Token) getComponent("boolean");
				printIndent(t.token());
				boolean b;
				put("type","boolean");
				if (t.token().trim().equalsIgnoreCase("true")){
					b = true;
					put("val",b);
				} else if (t.token().trim().equalsIgnoreCase("false")){
					b = false;
					put("val",b);
				}
				break;
			default:
		}
	}

	public void execute() {

	}
}