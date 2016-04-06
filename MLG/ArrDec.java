import java.util.ArrayList;

public class ArrDec extends NonTerminal {
	ArrayList<ParseObject> varList;

	public ArrDec(String pattern) {
		super("arrDec",pattern);
		varList = new ArrayList<ParseObject>();
	}

	public void interpret() throws Exception {
		NonTerminal nt, nt2;
		Token t;
		printBranch();
		switch(getProdString()){
			case "dataType [ ] IDENTIFIER":
				nt = (NonTerminal) getComponent("dataType");
				propagate(nt);
				nt.interpret();
				put("type",nt.getAsString("type"));

				printIndent("[");
				printIndent("]");

				printIndent(((Token)getComponent("IDENTIFIER")).token());
				put("varList",new ParseObject[]{getComponent("IDENTIFIER")});
				break;
			case "dataType [ ] arrAssignment":
				nt = (NonTerminal) getComponent("dataType");
				propagate(nt);
				nt.interpret();
				put("type",nt.getAsString("type"));

				printIndent("[");
				printIndent("]");

				varList.add(getComponent("arrAssignment"));
				nt2 = (NonTerminal) getComponent("arrAssignment");
				propagate(nt2);
				nt2.interpret();
				put("varList",new ParseObject[]{nt2});
				break;
			default:
		}
	}

	public void execute() {
		SymbolTable st = SymbolTable.instance();
		NonTerminal nt, nt2;
		Token t;
		boolean ok;
		switch (getProdString()){
			case "dataType [ ] IDENTIFIER":
				t = (Token) getComponent("IDENTIFIER");
				ok = st.declare(t.token(),getAsString("type") + "[]");
				if ( !ok ){
					System.out.println(t.token() + " already declared at line " 
										+ t.lineNo());
				}
				break;
			case "dataType [ ] arrAssignment":
				NonTerminal assignment = (NonTerminal) varList.get(0);
				t = (Token) assignment.getAsObject("variable");
				ok = st.declare(t.token(),getAsString("type") + "[]");
				if ( !ok ){
					System.out.println(t.token() + " already declared at line " 
										+ t.lineNo());

				}
				assignment.execute();
				break;
		}
	}
}