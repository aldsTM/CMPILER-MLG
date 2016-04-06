public class ArrSize extends NonTerminal {
	public ArrSize(String pattern) {
		super("arrSize",pattern);
	}

	public void interpret() throws Exception {
		Token t;
		printBranch();
		switch(getProdString()) {
			case "[ int ]":
				printIndent("[");
				t = (Token) getComponent("int");
				printIndent(t.token());
				int i = Integer.parseInt(t.token());
				put("type","int");
				put("val",i);
				printIndent("]");
				break;
			default:
		}
	}

	public void execute() {

	}
}