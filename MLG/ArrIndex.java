public class ArrIndex extends NonTerminal {
	public ArrIndex(String pattern) {
		super("arrIndex",pattern);
	}

	public void interpret() throws Exception {
		NonTerminal nt;
		Token t;
		int i;
		printBranch();
		switch(getProdString()) {
			case "int":
				t = (Token) getComponent("int");
				printIndent(t.token());
				i = Integer.parseInt(t.token());
				put("val",i);
				break;
			case "IDENTIFIER":
				printIndent(((Token)getComponent("IDENTIFIER")).token());
				put("IDENTIFIER",((Token)getComponent("IDENTIFIER")).token());
				put("varList",new ParseObject[]{getComponent("IDENTIFIER")});
				break;
			case "IDENTIFIER [ arrIndex ]":
				put("IDENTIFIER",((Token)getComponent("IDENTIFIER")).token());
				printIndent(((Token)getComponent("IDENTIFIER")).token());
				put("lineNo",((Token)getComponent("IDENTIFIER")).lineNo());

				printIndent("[");
				nt = (NonTerminal) getComponent("arrIndex");
				propagate(nt);
				nt.interpret();
				put("line",nt);
				printIndent("]");
				break;

			default:
		}
	}

	public void execute() {
		SymbolTable st = SymbolTable.instance();
		Variable v;
		String type;
		NonTerminal nt;
		switch(getProdString()){
			case "int":
				put("val",getAsInt("val"));
				break;
			case "IDENTIFIER":
				v = st.get(getAsString("IDENTIFIER"));
				type = v.type();
				if (type.equalsIgnoreCase("int")){
					put("val",v.getAsInt());
				} else {
					put("val",0);
				}
				break;
			case "IDENTIFIER [ arrIndex ]":
				v = st.get(getAsString("IDENTIFIER"));
				type = v.type();
				if (type.equalsIgnoreCase("int")){
					nt = (NonTerminal) getAsObject("line");
					nt.execute();
					int i = nt.getAsInt("val");
					put("val",i);
				} else {
					put ("val",0);
				}
				break;
			default:
		}
	}
}