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
		System.out.println("You've declared a constant. BUT it's not officially in memory yet (function not implemented)");
		// SymbolTable st = SymbolTable.instance();
		// for(ParseObject var : varListArr) {
		// 	if( var instanceof Token ) {
		// 		Token t = (Token) var;
		// 		boolean ok = st.declare(t.token(),getAsString("type"));
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