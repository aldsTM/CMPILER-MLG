public class Literal extends NonTerminal {
	public Literal(String pattern) {
		super("literal",pattern);
	}

	public void interpret() throws Exception {
		Token t;
		printBranch();
		switch(getProdString()) {	
			case "int":
				t = (Token) getComponent("int");
				printIndent(t.token());
				int i = Integer.parseInt(t.token());
				put("type","int");
				put("val",i);
				break;
			default:
		}
	}

	public void execute() {

	}
}