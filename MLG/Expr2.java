public class Expr2 extends NonTerminal {
	public Expr2(String pattern) {
		super("expr2",pattern);
	}

	public void interpret() throws Exception {
		NonTerminal nt, nt2;
		printBranch();
		switch(getProdString()) {
			case "expr2 * expr3":
			case "expr2 / expr3":
			case "expr2 % expr3":
				nt = (NonTerminal) getComponent("expr2");
				propagate(nt);
				nt.interpret();
				put("line",nt);

				if (getProdString().contains("*")){
					printIndent("*");
				} else if (getProdString().contains("/")){
					printIndent("/");
				} else if (getProdString().contains("%")){
					printIndent("%");
				}

				nt2 = (NonTerminal) getComponent("expr3");
				propagate(nt2);
				nt2.interpret();
				put("line2",nt2);
				break;
			case "expr3":
				nt = (NonTerminal) getComponent("expr3");
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
			case "expr2 * expr3":
				error = false;
				nt = (NonTerminal) getAsObject("line");
				nt.execute();
				nt2 = (NonTerminal) getAsObject("line2");
				nt2.execute();
				switch(nt.getAsString("type")){
					case "int":
						switch(nt2.getAsString("type")){
							case "int":
								put("type","int");
								put("val",nt.getAsInt("val") * nt2.getAsInt("val"));
								break;
							case "float":
								put("type","float");
								put("val",nt.getAsInt("val") * nt2.getAsFloat("val"));
								break;
							case "char":
								put("type","int");
								put("val",nt.getAsInt("val") * nt2.getAsString("val").charAt(0));
								break;
							default:
								error = true;
						}
						break;
					case "float":
						switch(nt2.getAsString("type")){
							case "int":
								put("type","float");
								put("val",nt.getAsFloat("val") * nt2.getAsInt("val"));
								break;
							case "float":
								put("type","float");
								put("val",nt.getAsFloat("val") * nt2.getAsFloat("val"));
								break;
							case "char":
								put("type","float");
								put("val",nt.getAsFloat("val") * nt2.getAsString("val").charAt(0));
								break;
							default:
								error = true;
						}
						break;
					case "char":
						switch(nt2.getAsString("type")){
							case "int":
								put("type","int");
								put("val",nt.getAsString("val").charAt(0) * nt2.getAsInt("val"));
								break;
							case "float":
								put("type","float");
								put("val",nt.getAsString("val").charAt(0) * nt2.getAsFloat("val"));
								break;
							default:
								error = true;
						}
						break;
					case "string":
						switch(nt2.getAsString("type")){
							case "int":
								put("type","string");
								String str = "";
								for(int i = 0; i < nt2.getAsInt("val"); i++ ) {
									str += nt.getAsString("val");
								}
								put("val",str);
								break;
							case "char":
								put("type","string");
								str = "";
								for(int i = 0; i < nt2.getAsInt("val"); i++ ) {
									str += nt.getAsString("val");
								}
								put("val",str);
								break;
							default:
								error = true;
						}
						break;
					default:
						error = true;
				}
				break;
			case "expr2 / expr3":
				error = false;
				nt = (NonTerminal) getAsObject("line");
				nt.execute();
				nt2 = (NonTerminal) getAsObject("line2");
				nt2.execute();
				switch(nt.getAsString("type")){
					case "int":
						switch(nt2.getAsString("type")){
							case "int":
								put("type","int");
								put("val",nt.getAsInt("val") / nt2.getAsInt("val"));
								break;
							case "float":
								put("type","float");
								put("val",nt.getAsInt("val") / nt2.getAsFloat("val"));
								break;
							case "char":
								put("type","float");
								put("val",nt.getAsInt("val") / nt2.getAsString("val").charAt(0));
								break;
							default:
								error = true;
						}
						break;
					case "float":
						switch(nt2.getAsString("type")){
							case "int":
								put("type","int");
								put("val",nt.getAsFloat("val") / nt2.getAsInt("val"));
								break;
							case "float":
								put("type","float");
								put("val",nt.getAsFloat("val") / nt2.getAsFloat("val"));
								break;
							case "char":
								put("type","float");
								put("val",nt.getAsFloat("val") / nt2.getAsString("val").charAt(0));
								break;
							default:
								error = true;
						}
						break;
					case "char":
						switch(nt2.getAsString("type")){
							case "int":
								put("type","int");
								put("val",nt.getAsString("val").charAt(0) / nt2.getAsInt("val"));
								break;
							case "float":
								put("type","float");
								put("val",nt.getAsString("val").charAt(0) / nt2.getAsFloat("val"));
								break;
							default:
								error = true;
						}
						break;
					default:
						error = true;
				}
				break;
			case "expr2 % expr3":
				error = false;
				nt = (NonTerminal) getAsObject("line");
				nt.execute();
				nt2 = (NonTerminal) getAsObject("line2");
				nt2.execute();
				switch(nt.getAsString("type")){
					case "int":
						switch(nt2.getAsString("type")){
							case "int":
								put("type","int");
								put("val",nt.getAsInt("val") % nt2.getAsInt("val"));
								break;
							default:
								error = true;
						}
						break;
					default:
						error = true;
				}
				break;
			case "expr3":
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
		}
	}

	public String toString(){
		return getAsObject("val").toString();
	}

}