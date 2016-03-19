public class Expr3 extends NonTerminal {
	public Expr3(String pattern) {
		super("expr3",pattern);
	}

	public void interpret() throws Exception {
		NonTerminal nt;
		printBranch();
		switch(getProdString()) {
			case "( expr )":
				printIndent("(");

				nt = (NonTerminal) getComponent("expr");
				propagate(nt);
				nt.interpret();

				printIndent(")");
				put("line", nt);
				break;
			case "expr4":
				nt = (NonTerminal) getComponent("expr4");
				propagate(nt);
				nt.interpret();
				put("line", nt);
				break;
			default:
		}
	}

	public void execute() {
		NonTerminal nt;
		switch(getProdString()) {
			case "( expr )":
			case "expr4":
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

	public String toString(){
		return getAsObject("val").toString();
	}

}