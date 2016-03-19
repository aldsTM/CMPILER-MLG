public class Expr4 extends NonTerminal {
	public Expr4(String pattern) {
		super("expr4",pattern);
	}

	public void interpret() throws Exception {
		NonTerminal nt;
		printBranch();
		switch(getProdString()) {
			case "+ expr4":
			case "- expr4":
				if (getProdString().contains("+")){
					printIndent("+");
				} else if (getProdString().contains("-")){
					printIndent("-");
				}

				nt = (NonTerminal) getComponent("expr4");
				propagate(nt);
				nt.interpret();
				put("line",nt);
				break;
			case "expr5":
				nt = (NonTerminal) getComponent("expr5");
				propagate(nt);
				nt.interpret();
				put("line",nt);
				break;
			/*case "literal":
				nt = (NonTerminal) getComponent("literal");
				nt.interpret();
				put("line",nt);
				break;
			case "literal2":
				nt = (NonTerminal) getComponent("literal2");
				nt.interpret();
				put("line",nt);
				break;*/
			default:
		}
	}

	public void execute() {
		NonTerminal nt;
		switch(getProdString()) {
			case "+ expr4":
				nt = (NonTerminal) getAsObject("line");
				nt.execute();
				switch(nt.getAsString("type")){
					case "int":
						put("type","int");
						put("val",nt.getAsInt("val"));
						break;
					case "float":
						put("type","float");
						put("val",nt.getAsFloat("val"));
						break;
					case "string":
						put("type","string");
						put("val",nt.getAsString("val"));
						break;
					case "boolean":
						put("type","boolean");
						put("val",nt.getAsBoolean("val"));
						break;
				}
				break;
			case "- expr4":
				nt = (NonTerminal) getAsObject("line");
				nt.execute();
				switch(nt.getAsString("type")){
					case "int":
						put("type","int");
						put("val",-nt.getAsInt("val"));
						break;
					case "float":
						put("type","float");
						put("val",-nt.getAsFloat("val"));
						break;
					case "string":
						put("type","string");
						put("val",new StringBuilder(nt.getAsString("val")).toString());
						break;
					case "boolean":
						put("type","boolean");
						if (nt.getAsBoolean("val") == true){
							put("val",false);
						} else {
							put("val",true);
						}
						break;
				}
				break;
			/*case "literal":
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
						put("val",nt.getAsString("val").charAt(0));
						break;
					case "string":
						put("type","string");
						put("val",nt.getAsString("val"));
						break;
				}
				break;*/
			case "expr5":
				nt = (NonTerminal) getAsObject("line");
				nt.execute();
				switch(nt.getAsString("type")){
					case "int":
						put("type","int");
						put("val",nt.getAsInt("val"));
						break;
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
					case "boolean":
						put("type","boolean");
						put("val",nt.getAsBoolean("val"));
						break;
				}
				break;
			default:
		}

	}
}