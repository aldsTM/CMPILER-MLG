public class FuncCall extends NonTerminal {
	public FuncCall(String pattern) {
		super("funcCall",pattern);
	}

	public void interpret() throws Exception {
		NonTerminal nt;
		printBranch();
		switch(getProdString()) {
			case "IDENTIFIER ( funcCallParams )":

				put("IDENTIFIER",((Token)getComponent("IDENTIFIER")).token());
				printIndent(((Token)getComponent("IDENTIFIER")).token());
				put("lineNo",((Token)getComponent("IDENTIFIER")).lineNo());

				printIndent("(");

				nt = (NonTerminal) getComponent("funcCallParams");
				propagate(nt);
				nt.interpret();

				printIndent(")");

				break;
			case "print ( expr )":
			case "println ( expr )":
				if (getProdString().contains("println")){
					printIndent("println");
				} else if (getProdString().contains("print")){
					printIndent("print");
				}
				printIndent("(");

				nt = (NonTerminal) getComponent("expr");
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
			case "scanf":
				nt = (NonTerminal) getComponent("scanf");
				propagate(nt);
				nt.interpret();
				put("line",nt);
			default:
		}
	}

	public void execute() {
		NonTerminal nt;
		switch(getProdString()) {
			case "IDENTIFIER ( funcCallParams )":
				System.out.println("You called a user-defined function.");
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
				break;
			case "scanf":
				System.out.println("Scanf statement done");
				break;
			default:
		}
	}
}