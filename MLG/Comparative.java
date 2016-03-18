public class Comparative extends NonTerminal {
	public Comparative(String pattern) {
		super("comparative",pattern);
	}

	public void interpret() throws Exception {
		NonTerminal nt, nt2;
		printBranch();
		switch(getProdString()) {
			case "comparative AND comparative2":
				nt = (NonTerminal) getComponent("comparative");
				propagate(nt);
				nt.interpret();
				put("line",nt);

				printIndent("AND");

				nt2 = (NonTerminal) getComponent("comparative2");
				propagate(nt2);
				nt2.interpret();
				put("line2",nt2);

				break;
			case "comparative2":
				nt = (NonTerminal) getComponent("comparative2");
				propagate(nt);
				nt.interpret();
				put("line",nt);
				break;
			default:
		}
	}

	public void execute() {
		NonTerminal nt, nt2;
		boolean error;
		switch(getProdString()) {
			case "comparative AND comparative2":
				error = false;
				nt = (NonTerminal) getAsObject("line");
				nt.execute();
				nt2 = (NonTerminal) getAsObject("line2");
				nt2.execute();

				switch (nt.getAsString("type")){
					case "boolean":
						switch (nt2.getAsString("type")){
							case "boolean":
								put("type","boolean");
								put("val",nt.getAsBoolean("val") && nt2.getAsBoolean("val"));
								break;
							default:
								put("type","error");
								error = true;
						}
						break;
					default:
						put("type","error");
						error = true;
				}

				break;
			case "comparative2":
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