public class Comparative3 extends NonTerminal {
	public Comparative3(String pattern) {
		super("comparative3",pattern);
	}

	public void interpret() throws Exception {
		NonTerminal nt;
		printBranch();
		switch(getProdString()) {
			case "NOT ( comparative3 )":
				printIndent("NOT");
				printIndent("(");

				nt = (NonTerminal) getComponent("comparative3");
				propagate(nt);
				nt.interpret();

				printIndent(")");

				put("line",nt);
				break;
			// case "literal3":
			// case "NOT literal3":
			// 	if (getProdString().contains("NOT")) {
			// 		printIndent("NOT");
			// 	}

			// 	nt = (NonTerminal) getComponent("literal3");
			// 	propagate(nt);
			// 	nt.interpret();
			// 	put("line",nt);
			// 	break;
			case "comparative4":
				nt = (NonTerminal) getComponent("comparative4");
				propagate(nt);
				nt.interpret();
				put("line",nt);
				break;
			default:
		}
	}

	public void execute() {
		NonTerminal nt;
		boolean error;
		switch(getProdString()) {
			case "NOT ( comparative3 )":
				error = false;
				nt = (NonTerminal) getAsObject("line");
				if (nt.getAsString("type").equals("boolean")) {
					put("type","boolean");
					put("val",!(nt.getAsBoolean("type")));
				} else {
					error = true;
					put("type","error");
				}
				break;
			// case "literal3":
			// 	error = false;
			// 	nt = (NonTerminal) getAsObject("line");
			// 	if (nt.getAsString("type").equals("boolean")) {
			// 		put("type","boolean");
			// 		put("val",nt.getAsBoolean("type"));
			// 	} else {
			// 		error = true;
			// 		put("type","error");
			// 	}
			// 	break;
			// case "NOT literal3":
			// 	error = false;
			// 	nt = (NonTerminal) getAsObject("line");
			// 	if (nt.getAsString("type").equals("boolean")) {
			// 		put("type","boolean");
			// 		put("val",!(nt.getAsBoolean("type")));
			// 	} else {
			// 		error = true;
			// 		put("type","error");
			// 	}
			// 	break;
			case "comparative4":
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
			default:
				error = true;
				break;
		}
	}
}