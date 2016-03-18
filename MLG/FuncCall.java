public class FuncCall extends NonTerminal {
	public FuncCall(String pattern) {
		super("funcCall",pattern);
	}

	public void interpret() throws Exception {
		printBranch();
		switch(getProdString()) {
			case "IDENTIFIER ( funcCallParams )":
				break;
			case "print ( expr )":
			case "println ( expr )":
				if (getProdString().contains("println")){
					printIndent("println");
				} else if (getProdString().contains("print")){
					printIndent("print");
				}
				printIndent("(");

				NonTerminal nt = (NonTerminal) getComponent("expr");
				propagate(nt);
				nt.interpret();
				put("line",nt);

				printIndent(")");
				break;
			case "println ( )":
				printIndent("println");
				printIndent("(");
				printIndent(")");
				break;
			default:
		}
	}

	public void execute() {
		NonTerminal nt;
		switch(getProdString()) {
			case "IDENTIFIER ( funcCallParams )":
				break;
			case "print ( expr )":
				nt = (NonTerminal) getAsObject("line");
				nt.execute();
				System.out.print(getAsObject("line"));
				break;
			case "println ( expr )":
				nt = (NonTerminal) getAsObject("line");
				nt.execute();
				System.out.println(getAsObject("line"));
				break;
			case "println ( )":
				System.out.println();
			default:
		}
	}
}