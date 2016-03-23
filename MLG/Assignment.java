public class Assignment extends NonTerminal {
	private boolean isArray;

	public Assignment(String pattern) {
		super("assignment",pattern);
	}

	public void interpret() throws Exception {
		NonTerminal nt;
		Token t;
		printBranch();
		switch(getProdString()) {
			case "IDENTIFIER = assignment":
				t = (Token) getComponent("IDENTIFIER");
				printIndent(t.token());
				put("IDENTIFIER",t.token());

				printIndent("=");

				isArray = false;
				nt = (NonTerminal) getComponent("assignment");
				propagate(nt);
				nt.interpret();
				put("line",nt);
				break;
			case "IDENTIFIER = expr":
				t = (Token) getComponent("IDENTIFIER");
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
				t = (Token) getComponent("IDENTIFIER");
				printIndent(t.token());
				put("IDENTIFIER",t.token());

				printIndent("=");

				isArray = true;
				nt = (NonTerminal) getComponent("funcCall");
				propagate(nt);
				nt.interpret();
				put("line",nt);
				break;
			default:
		}
	}

	public void execute() {
		NonTerminal nt;
		nt = (NonTerminal) getAsObject("line");
		nt.execute();

		SymbolTable st = SymbolTable.instance();
		String typeOfVariable = getAsString("type");
		String typeOfExpr = nt.getAsString("type");

		if (typeOfVariable == null){
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
			if(!st.declare(getAsString("IDENTIFIER"),getAsString("type"))) {
				System.out.println("Error: " + getAsString("varname") 
										+ " already declared");
			}
		}

		if (isArray){
			System.out.println("Did an assignment statement! This type of assignment is not supported yet, though");
			// handle this later
		} else {
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
							try {
								st.assign(getAsString("IDENTIFIER"),Integer.parseInt(nt.getAsString("val")));
								put("val",Integer.parseInt(nt.getAsString("val")));
							} catch(Exception e) {
								System.out.println("Type Checking Error: " 
													+ nt.getAsString("val") 
													+ " cannot be converted to int.");
							}
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
						case "array":
							System.out.println("Type Checking Error: " 
												+ nt.getAsArray("val") 
												+ " cannot be converted to int.");
							break;
						default:
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
							try {
								st.assign(getAsString("IDENTIFIER"),Float.parseFloat(nt.getAsString("val")));
								put("val",Float.parseFloat(nt.getAsString("val")));
							} catch(Exception e) {
								System.out.println("Type Checking Error: " 
													+ nt.getAsString("val") 
													+ " cannot be converted to float.");
							}
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
						case "array":
							System.out.println("Type Checking Error: " 
												+ nt.getAsArray("val") 
												+ " cannot be converted to float.");
							break;
						default:
							break;
					}
					break;
				case "char":
					switch (typeOfExpr){
						case "int":
							st.assign(getAsString("IDENTIFIER"),""+(char)nt.getAsInt("val"));
							put("val",""+(char)nt.getAsInt("val"));
							break;
						case "float":
							st.assign(getAsString("IDENTIFIER"),""+(char)nt.getAsFloat("val"));
							put("val",""+(char)nt.getAsFloat("val"));
							break;
						case "char":
							st.assign(getAsString("IDENTIFIER"),nt.getAsString("val"));
							put("val",nt.getAsString("val"));
							break;
						case "string":
							String temp = nt.getAsString("val");
							if( temp.length() == 1 ) {
								st.assign(getAsString("IDENTIFIER"),temp);
								put("val",temp);
							} else {
							System.out.println("Type Checking Error: " 
												+ nt.getAsString("val") 
												+ " cannot be converted to char.");
							}
							break;
						case "boolean":
							boolean value = nt.getAsBoolean("val");
							if (value){
								st.assign(getAsString("IDENTIFIER"),"T");
								put("val","T");
							} else {
								st.assign(getAsString("IDENTIFIER"),"F");
								put("val","F");
							}
							break;
						case "array":
							System.out.println("Type Checking Error: " 
												+ nt.getAsArray("val") 
												+ " cannot be converted to char.");
							break;
						default:
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
						case "array":
							Object[] obs = nt.getAsArray("val");
							String temp = "";
							for(Object o : obs) {
								temp += o.toString();
							}
							st.assign(getAsString("IDENTIFIER"),temp);
							put("val",temp);
							break;
						default:
							break;
					}
					break;
				case "boolean":
					switch (typeOfExpr){
						case "int":
							st.assign(getAsString("IDENTIFIER"),nt.getAsInt("val") != 0);
							put("val",nt.getAsInt("val") != 0);
							break;
						case "float":
							st.assign(getAsString("IDENTIFIER"),nt.getAsFloat("val") != 0.0);
							put("val",nt.getAsFloat("val") != 0.0);
							break;
						case "char":
							st.assign(getAsString("IDENTIFIER"),!nt.getAsString("val").equals("\0"));
							put("val",!nt.getAsString("val").equals("\0"));
							break;
						case "string":
							String value = nt.getAsString("val");
							if (value.equalsIgnoreCase("true")){
								st.assign(getAsString("IDENTIFIER"),true);
								put("val",true);
							} else {
								st.assign(getAsString("IDENTIFIER"),false);
								put("val",false);
							}
							break;
						case "boolean":
							st.assign(getAsString("IDENTIFIER"),nt.getAsBoolean("val"));
							put("val",nt.getAsBoolean("val"));
							break;
						case "array":
							System.out.println("Type Checking Error: " 
												+ nt.getAsArray("val") 
												+ " cannot be converted to boolean.");
							break;
						default:
							break;
					}
					break;
				case "array":
					switch (typeOfExpr){
						case "int":
							st.assign(getAsString("IDENTIFIER"),new Object[]{nt.getAsInt("val")});
							put("val",new Object[]{nt.getAsInt("val")});
							break;
						case "float":
							st.assign(getAsString("IDENTIFIER"),new Object[]{nt.getAsFloat("val")});
							put("val",new Object[]{nt.getAsFloat("val")});
							break;
						case "char":
							st.assign(getAsString("IDENTIFIER"),new Object[]{nt.getAsString("val")});
							put("val",new Object[]{nt.getAsString("val")});
							break;
						case "string":
							st.assign(getAsString("IDENTIFIER"),new Object[]{nt.getAsString("val")});
							put("val",new Object[]{nt.getAsString("val")});
							break;
						case "boolean":
							st.assign(getAsString("IDENTIFIER"),new Object[]{nt.getAsBoolean("val")});
							put("val",new Object[]{nt.getAsBoolean("val")});
							break;
						case "array":
							st.assign(getAsString("IDENTIFIER"),nt.getAsArray("val"));
							put("val",nt.getAsArray("val"));
							break;
						default:
							break;
					}
					break;
				default:
					break;
			}
		}

		put("type",typeOfVariable);

	}
}