import java.util.ArrayList;

public class FuncParams extends NonTerminal {
	ArrayList<ParseObject> varList;

	public FuncParams(String pattern) {
		super("funcParams",pattern);
		varList = new ArrayList<ParseObject>();
	}

	public void interpret() throws Exception {
		NonTerminal nt, nt2;
		printBranch();
		switch(getProdString()) {
			case "dataType IDENTIFIER , funcParams":
				nt = (NonTerminal) getComponent("dataType");
				propagate(nt);
				nt.interpret();
				put("type",nt.getAsString("type"));

				printIndent(((Token)getComponent("IDENTIFIER")).token());
				put("varList",new ParseObject[]{getComponent("IDENTIFIER")});
				printIndent(",");

				nt2 = (NonTerminal) getComponent("funcParams");
				propagate(nt2);
				nt2.interpret();
				break;
			case "dataType IDENTIFIER":

				nt = (NonTerminal) getComponent("dataType");
				propagate(nt);
				nt.interpret();
				put("type",nt.getAsString("type"));

				printIndent(((Token)getComponent("IDENTIFIER")).token());
				put("varList",new ParseObject[]{getComponent("IDENTIFIER")});

				break;
			default:
		}
	}

	public void execute() {

	}
}