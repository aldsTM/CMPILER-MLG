public class Comparative4 extends NonTerminal {
	public Comparative4(String pattern) {
		super("comparative4",pattern);
	}

	public void interpret() throws Exception {
		NonTerminal nt, nt2;
		printBranch();
		switch(getProdString()) {
			case "expr <= expr":
				nt = (NonTerminal) getComponent("expr");
				propagate(nt);
				nt.interpret();
				put("line",nt);

				printIndent("<=");

				nt2 = (NonTerminal) getComponent("expr2");
				propagate(nt2);
				nt2.interpret();
				put("line2",nt2);
				break;
			case "expr >= expr":
				nt = (NonTerminal) getComponent("expr");
				propagate(nt);
				nt.interpret();
				put("line",nt);

				printIndent(">=");

				nt2 = (NonTerminal) getComponent("expr2");
				propagate(nt2);
				nt2.interpret();
				put("line2",nt2);
				break;
			case "expr < expr":
				nt = (NonTerminal) getComponent("expr");
				propagate(nt);
				nt.interpret();
				put("line",nt);

				printIndent("<");

				nt2 = (NonTerminal) getComponent("expr2");
				propagate(nt2);
				nt2.interpret();
				put("line2",nt2);
				break;
			case "expr > expr":
				nt = (NonTerminal) getComponent("expr");
				propagate(nt);
				nt.interpret();
				put("line",nt);

				printIndent(">");

				nt2 = (NonTerminal) getComponent("expr2");
				propagate(nt2);
				nt2.interpret();
				put("line2",nt2);
				break;
			case "expr == expr":
				nt = (NonTerminal) getComponent("expr");
				propagate(nt);
				nt.interpret();
				put("line",nt);

				printIndent("==");

				nt2 = (NonTerminal) getComponent("expr2");
				propagate(nt2);
				nt2.interpret();
				put("line2",nt2);
				break;
			case "expr != expr":
				nt = (NonTerminal) getComponent("expr");
				propagate(nt);
				nt.interpret();
				put("line",nt);

				printIndent("!=");

				nt2 = (NonTerminal) getComponent("expr2");
				propagate(nt2);
				nt2.interpret();
				put("line2",nt2);
				break;
			case "( comparative )":
				nt = (NonTerminal) getComponent("comparative");
				propagate(nt);
				nt.interpret();
				put("line",nt);
				break;
			// case "expr":	
			// 	nt = (NonTerminal) getComponent("expr");
			// 	propagate(nt);
			// 	nt.interpret();
			// 	put("line",nt);
			// 	break;
			default:
		}
	}

	public void execute() {
		NonTerminal nt, nt2;
		boolean error;
		switch(getProdString()) {
			case "expr COMPARISON_OPERATOR expr":
				error = false;
				break;
			case "( comparative )":
				error = false;
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
						put("val",nt.getAsString("val").charAt(0));
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
			/*case "expr":*/
			default:
				error = true;
				break;
		}
	}
}