import java.util.ArrayList;

public class ConstDec extends NonTerminal {
	ArrayList<ParseObject> varList;

	public ConstDec(String pattern) {
		super("constDec",pattern);
		varList = new ArrayList<ParseObject>();
	}

	public void interpret() throws Exception {
		NonTerminal optional, nt, nt2;
		printBranch();
		switch(getProdString()) {
			case "CANTTOUCHTHIS dataType assignment":
				printIndent("CANTTOUCHTHIS");

				nt = (NonTerminal) getComponent("dataType");
				propagate(nt);
				nt.interpret();
				put("type",nt.getAsString("type"));

				varList.add(getComponent("assignment"));
				propagate((NonTerminal)getComponent("assignment"));
				varList.get(0).interpret();
				put("varList",varList.toArray(new ParseObject[1]));
				break;
			default:
		}
	}

	public void execute() {
		SymbolTable st = SymbolTable.instance();
		NonTerminal assignment = (NonTerminal) varList.get(0);
		Token t = (Token) assignment.getAsObject("variable");
		
		boolean ok = st.declare(t.token(),getAsString("type"),true);
		if( !ok ) {
			System.out.println(t.token() + " already declared at line " 
								+ t.lineNo());
		}

		assignment.execute();

		// for(ParseObject var : varListArr) {
		// 	if( var instanceof Token ) {
		// 		Token t = (Token) var;
		// 		boolean ok = st.declare(t.token(),getAsString("type"),true);
		// 		if( !ok ) {
		// 			System.out.println(t.token() + " already declared at line " 
		// 								+ t.lineNo());
		// 		}
		// 	} else {
		// 		NonTerminal nt = (NonTerminal) var;
		// 		nt.put("type",getAsString("type"));
		// 		nt.execute();
		// 	}
		// }
	}
}