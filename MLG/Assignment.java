public class Assignment extends NonTerminal {
	private boolean isArray;

	public Assignment(String pattern) {
		super("assignment",pattern);
	}

	public void interpret() throws Exception {
		NonTerminal nt;
		printBranch();
		switch(getProdString()) {
			case "IDENTIFIER = expr":
				Token t = (Token) getComponent("IDENTIFIER");
				printIndent(t.token());
				put("IDENTIFIER",t.token());

				printIndent("=");

				isArray = false;
				nt = (NonTerminal) getComponent("expr");
				propagate(nt);
				nt.interpret();
				put("line",nt);
				break;
			case "IDENTIFIER = comparative":
				isArray = false;
				break;
			case "IDENTIFIER = funcCall":
				isArray = false;
				break;
			default:
		}
	}

	public void execute() {
		NonTerminal nt;
		nt = (NonTerminal) getAsObject("line");
		nt.execute();

		SymbolTable st = SymbolTable.instance();
		String typeOfExpr = nt.getAsString("type");
		String typeOfVariable = "";

		if (typeOfExpr == null){
			Variable v = st.get(getAsString("IDENTIFIER"));
			if( v == null ) {
				System.out.println("Undeclared variable " 
									+ getAsString("IDENTIFIER"));
									//+ " at line " 
									//+ getAsInt("lineNo"));
			} else {
				typeOfVariable = v.type();
			}
		} else {
			st.declare(getAsString("IDENTIFIER"),nt.getAsString("type"));
			typeOfVariable = st.get(getAsString("IDENTIFIER")).type();
		}

		if (isArray){
			// handle this later
		} else {
			System.out.print("\nFUCK " + typeOfExpr + " inassign mo");
			if (!typeOfExpr.equalsIgnoreCase(typeOfVariable)){
				System.out.println(" gago kelangan " + typeOfVariable);
			} else {
				System.out.println();
			}
			switch(typeOfVariable){
				case "int":
					switch (typeOfExpr){
						case "int":
							st.assign(getAsString("IDENTIFIER"),nt.getAsInt("val"));
							put("val",nt.getAsInt("val"));
							break;
						case "float":
							st.assign(getAsString("IDENTIFIER"),(int)nt.getAsFloat("val"));
							put("val",(int)nt.getAsFloat("val"));
							break;
						case "char":
							st.assign(getAsString("IDENTIFIER"),nt.getAsString("val").charAt(0));
							put("val",nt.getAsString("val").charAt(0));
							break;
						case "string":
							st.assign(getAsString("IDENTIFIER"),Integer.parseInt(nt.getAsString("val")));
							put("val",Integer.parseInt(nt.getAsString("val")));
							break;
						case "boolean":
							boolean value = nt.getAsBoolean("val");
							if (value){
								st.assign(getAsString("IDENTIFIER"),1);
								put("val",1);
							} else {
								st.assign(getAsString("IDENTIFIER"),0);
								put("val",0);
							}
							break;
					}
					break;
				case "float":
					switch (typeOfExpr){
						case "int":
							st.assign(getAsString("IDENTIFIER"),(float)nt.getAsInt("val"));
							put("val",(float)nt.getAsInt("val"));
							break;
						case "float":
							st.assign(getAsString("IDENTIFIER"),nt.getAsFloat("val"));
							put("val",nt.getAsFloat("val"));						
							break;
						case "char":
							st.assign(getAsString("IDENTIFIER"),nt.getAsString("val").charAt(0));
							put("val",nt.getAsString("val").charAt(0));
							break;
						case "string":
							st.assign(getAsString("IDENTIFIER"),Float.parseFloat(nt.getAsString("val")));
							put("val",Float.parseFloat(nt.getAsString("val")));
							break;
						case "boolean":
							boolean value = nt.getAsBoolean("val");
							if (value){
								st.assign(getAsString("IDENTIFIER"),1.0);
								put("val",1.0);
							} else {
								st.assign(getAsString("IDENTIFIER"),0.0);
								put("val",0.0);
							}
							break;
					}
					break;
				case "char":
					switch (typeOfExpr){
						case "int":
							st.assign(getAsString("IDENTIFIER"),nt.getAsInt("val"));
							put("val",nt.getAsInt("val"));
							break;
						case "float":
							st.assign(getAsString("IDENTIFIER"),nt.getAsFloat("val"));
							put("val",nt.getAsFloat("val"));
							break;
						case "char":
							st.assign(getAsString("IDENTIFIER"),nt.getAsString("val").charAt(0));
							put("val",nt.getAsString("val").charAt(0));
							break;
						case "string":
							break;
						case "boolean":
							boolean value = nt.getAsBoolean("val");
							if (value){
								st.assign(getAsString("IDENTIFIER"),'T');
								put("val",'T');
							} else {
								st.assign(getAsString("IDENTIFIER"),'F');
								put("val",'F');
							}
							break;
					}
					break;
				case "string":
					switch (typeOfExpr){
						case "int":
							st.assign(getAsString("IDENTIFIER"),nt.getAsInt("val") + "");
							put("val",nt.getAsInt("val") + "");
							break;
						case "float":
							st.assign(getAsString("IDENTIFIER"),nt.getAsFloat("val") + "");
							put("val",nt.getAsFloat("val") + "");
							break;
						case "char":
							st.assign(getAsString("IDENTIFIER"),nt.getAsString("val"));
							put("val",nt.getAsString("val"));
							break;
						case "string":
							st.assign(getAsString("IDENTIFIER"),nt.getAsString("val"));
							put("val",nt.getAsString("val"));
							break;
						case "boolean":
							boolean value = nt.getAsBoolean("val");
							if (value){
								st.assign(getAsString("IDENTIFIER"),"true");
								put("val","true");
							} else {
								st.assign(getAsString("IDENTIFIER"),"false");
								put("val","false");
							}
							break;
					}
					break;
				case "boolean":
					switch (typeOfExpr){
						case "int":
							break;
						case "float":
							break;
						case "char":
							break;
						case "string":
							String value = nt.getAsString("val");
							if (value.equalsIgnoreCase("true")){
								st.assign(getAsString("IDENTIFIER"),true);
								put("val",true);
							} else if (value.equalsIgnoreCase("false")){
								st.assign(getAsString("IDENTIFIER"),false);
								put("val",false);
							}
							break;
						case "boolean":
							st.assign(getAsString("IDENTIFIER"),nt.getAsBoolean("val"));
							put("val",nt.getAsBoolean("val"));
							break;
					}
					break;
				default:
					break;
			}
		}

	}
}