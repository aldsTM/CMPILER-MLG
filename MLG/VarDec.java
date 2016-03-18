public class VarDec extends NonTerminal {
	private ParseObject[] varListArr;

	public VarDec(String pattern) {
		super("varDec",pattern);
	}

	public void interpret() throws Exception {
		NonTerminal nt, nt2;
		printBranch();
		switch(getProdString()) {
			case "dataType identifiers":
				nt = (NonTerminal) getComponent("dataType");
				propagate(nt);
				nt.interpret();
				put("type",nt.getAsString("type"));

				nt2 = (NonTerminal) getComponent("identifiers");
				propagate(nt2);
				nt2.interpret();
				this.varListArr = (ParseObject []) nt2.getAsArray("varList");
				break;
			default:
		}
	}

	public void execute() {
		SymbolTable st = SymbolTable.instance();
		for(ParseObject var : varListArr) {
			if( var instanceof Token ) {
				Token t = (Token) var;
				boolean ok = st.declare(t.token(),getAsString("type"));
				if( !ok ) {
					System.out.println(t.token() + " already declared at line " 
										+ t.lineNo());
				}
			} else {
				NonTerminal nt = (NonTerminal) var;
				nt.put("type",getAsString("type"));
				nt.execute();
			}
		}
	}
}