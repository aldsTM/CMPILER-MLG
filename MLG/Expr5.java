public class Expr5 extends NonTerminal {
	public Expr5(String pattern) {
		super("expr5",pattern);
	}

	public void interpret() throws Exception {
		NonTerminal nt;
		printBranch();
		switch(getProdString()) {
			case "++ IDENTIFIER":

				break;
			case "-- IDENTIFIER":

				break;
			case "IDENTIFIER ++":

				break;
			case "IDENTIFIER --":

				break;
			case "literal":
				nt = (NonTerminal) getComponent("literal");
				propagate(nt);
				nt.interpret();
				put("line",nt);
				break;
			case "literal2":
				nt = (NonTerminal) getComponent("literal2");
				propagate(nt);
				nt.interpret();
				put("line",nt);
				break;
			case "literal3":
				nt = (NonTerminal) getComponent("literal3");
				propagate(nt);
				nt.interpret();
				put("line",nt);
				break;
			case "NOT literal3":
				printIndent("NOT");
				nt = (NonTerminal) getComponent("literal3");
				propagate(nt);
				nt.interpret();
				put("line",nt);
				break;
			case "IDENTIFIER":
				put("IDENTIFIER",((Token)getComponent("IDENTIFIER")).token());
				printIndent(((Token)getComponent("IDENTIFIER")).token());
				put("lineNo",((Token)getComponent("IDENTIFIER")).lineNo());
				break;
			case "IDENTIFIER arrIndex":
				break;
			default:
		}
	}

	public void execute() {
		NonTerminal nt;
		switch(getProdString()) {
			case "++ IDENTIFIER":
				break;
			case "-- IDENTIFIER":
				break;
			case "IDENTIFIER ++":
				break;
			case "IDENTIFIER --":
				break;
			case "literal":
				nt = (NonTerminal) getAsObject("line");
				nt.execute();
				switch(nt.getAsString("type")){
					case "int":
						put("type","int");
						put("val",nt.getAsInt("val"));
						break;
					default:
				}
				break;
			case "literal2":
				nt = (NonTerminal) getAsObject("line");
				nt.execute();
				switch(nt.getAsString("type")){
					case "float":
						put("type","float");
						put("val",nt.getAsFloat("val"));
						break;
					case "char":
						put("type","char");
						put("val",nt.getAsString("val"));
						break;
					case "string":
						put("type","string");
						put("val",nt.getAsString("val"));
						break;
				}
				break;
			case "literal3":
				nt = (NonTerminal) getAsObject("line");
				nt.execute();
				switch(nt.getAsString("type")){
					case "boolean":
						put("type","boolean");
						put("val",nt.getAsBoolean("val"));
						break;
					default:
				}
				break;
			case "NOT literal3":
				nt = (NonTerminal) getAsObject("line");
				nt.execute();
				switch(nt.getAsString("type")){
					case "boolean":
						put("type","boolean");
						if (nt.getAsBoolean("val") == true){
							put("val",false);
						} else {
							put("val",true);
						}
						break;
					default:
				}
				break;
			case "IDENTIFIER":
				SymbolTable st = SymbolTable.instance();
				Variable v = st.get(getAsString("IDENTIFIER"));
				put("type",v.type());
				switch(getAsString("type")) {
					case "int":
						put("type",v.type());
						put("val",v.getAsInt());
						break;
					case "float":
						put("type",v.type());
						put("val",v.getAsFloat());
						break;
					case "char":
						put("type",v.type());
						put("val",v.getAsString());
						break;
					case "string":
						put("type",v.type());
						put("val",v.getAsString());
						break;
					case "boolean":
						put("type",v.type());
						put("val",v.getAsBoolean());
						break;
					case "array":
						put("type",v.type());
						put("val",v.getAsArray());
						break;
					default:
				}
				break;
			//case "IDENTIFIER arrIndex":
			//	break;
			default:
		}
	}
}