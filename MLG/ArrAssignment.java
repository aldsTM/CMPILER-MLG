public class ArrAssignment extends NonTerminal {
	public ArrAssignment(String pattern) {
		super("arrAssignment",pattern);
	}

	public void interpret() throws Exception {
		Token t;
		NonTerminal nt;
		printBranch();
		switch(getProdString()) {
			case "IDENTIFIER = NEW DICK SIZE arrSize INCHES":
				t = (Token) getComponent("IDENTIFIER");
				printIndent(t.token());
				put("IDENTIFIER",t.token());
				put("variable",t);

				printIndent("=");
				printIndent("NEW");
				printIndent("DICK");
				printIndent("SIZE");

				nt = (NonTerminal) getComponent("arrSize");
				propagate(nt);
				nt.interpret();
				put("arrSize",nt.getAsInt("val"));

				printIndent("INCHES");
				break;
			case "IDENTIFIER = { arrVals }":
				t = (Token) getComponent("IDENTIFIER");
				printIndent(t.token());
				put("IDENTIFIER",t.token());
				put("variable",t);

				printIndent("=");
				put("arrSize",1);

				printIndent("{");
				nt = (NonTerminal) getComponent("arrVals");
				propagate(nt);
				nt.interpret();
				printIndent("}");
				put("arrVals",nt);
				break;
			default:
		}
	}

	public void execute() {
		SymbolTable st = SymbolTable.instance();

		// test code; i forgot my java arrays so i practiced
		// System.out.println("playground activated");
		// int[] intArray = new int[0];
		// intArray = new int[]{1,2,3,4,5};
		// for (int i = 0; i<intArray.length; i++){
		// 	System.out.println("The " + (i+1) + "th element in the array is " + intArray[i]);
		// }
		// System.out.println("Array assignment yay");

		// check the type of variable
		String typeOfVariable = getAsString("type");
		if (typeOfVariable == null){
			Variable v = st.get(getAsString("IDENTIFIER"));
			if (v == null){
				System.out.println("Undeclared variable " 
									+ getAsString("IDENTIFIER"));
									//+ " at line " 
									//+ getAsInt("lineNo"));
			} else {
				typeOfVariable = v.type();
			}
		} else {
			if(!st.declare(getAsString("IDENTIFIER"),getAsString("type"))) {
				// System.out.println("Type checking error: " + getAsString("varname") 
				// 						+ " already declared");
			}
		}


		// declare array of correct type and size, and
		// if applicable, assign items into the array
		switch (getProdString()){
			case "IDENTIFIER = NEW DICK SIZE arrSize INCHES":
				switch (typeOfVariable){
					case "int":
						int[] arrayInt = new int[getAsInt("arrSize")];
						put("val",arrayInt);
						break;
					case "float":
						float[] arrayFloat = new float[getAsInt("arrSize")];
						put("val",arrayFloat);
						break;
					case "char":
						char[] arrayChar = new char[getAsInt("arrSize")];
						put("val",arrayChar);
						break;
					case "string":
						String[] arrayStr = new String[getAsInt("arrSize")];
						put("val",arrayStr);
						break;
					case "boolean":
						boolean[] arrayBool = new boolean[getAsInt("arrSize")];
						put("val",arrayBool);
						break;
				}
				st.assign(getAsString("IDENTIFIER"),getAsArray("val"));
				break;
			case "IDENTIFIER = { arrVals }":
				int i = 0;

				NonTerminal arrVals = (NonTerminal) getAsObject("arrVals");
				arrVals.execute();
 				Object[] tempArray = arrVals.getAsArray("exprList");

				switch (typeOfVariable){
					case "int":
						int[] arrayInt = new int[tempArray.length];
						for (i = 0; i < tempArray.length; i++){
							switch(((Expr)tempArray[i]).getAsString("type")){
								case "int":
									arrayInt[i] = Integer.parseInt(tempArray[i].toString());
									// System.out.println("array[" + i + "] = " + arrayInt[i]);
									break;
								case "float":
									break;
								case "char":
									break;
								case "string":
									break;
								case "boolean":
									break;
							}
						}
						put("val",arrayInt);
						break;
					case "float":
						float[] arrayFloat = new float[tempArray.length];
						for (i = 0; i < tempArray.length; i++){
							switch(((Expr)tempArray[i]).getAsString("type")){
								case "int":
									break;
								case "float":
									arrayFloat[i] = Float.parseFloat(tempArray[i].toString());
									// System.out.println("array[" + i + "] = " + arrayFloat[i]);
									break;
								case "char":
									break;
								case "string":
									break;
								case "boolean":
									break;
							}
						}
						put("val",arrayFloat);
						break;
					case "char":
						char[] arrayChar = new char[tempArray.length];
						for (i = 0; i < tempArray.length; i++){
							switch(((Expr)tempArray[i]).getAsString("type")){
								case "int":
									break;
								case "float":
									break;
								case "char":
									arrayChar[i] = tempArray[i].toString().charAt(0);
									// System.out.println("array[" + i + "] = " + arrayChar[i]);
									break;
								case "string":
									break;
								case "boolean":
									break;
							}
						}
						put("val",arrayChar);
						break;
					case "string":
						String[] arrayStr = new String[tempArray.length];
						for (i = 0; i < tempArray.length; i++){
							switch(((Expr)tempArray[i]).getAsString("type")){
								case "int":
									break;
								case "float":
									break;
								case "char":
									break;
								case "string":
									arrayStr[i] = tempArray[i].toString();
									// System.out.println("array[" + i + "] = " + arrayStr[i]);
									break;
								case "boolean":
									break;
							}
						}
						put("val",arrayStr);
						break;
					case "boolean":
						boolean[] arrayBool = new boolean[tempArray.length];
						for (i = 0; i < tempArray.length; i++){
							switch(((Expr)tempArray[i]).getAsString("type")){
								case "int":
									break;
								case "float":
									break;
								case "char":
									break;
								case "string":
									break;
								case "boolean":
									arrayBool[i] = tempArray[i].toString().equalsIgnoreCase("true");
									// System.out.println("array[" + i + "] = " + arrayBool[i]);
									break;
							}
						}
						put("val",arrayBool);
						break;
				}
				st.assign(getAsString("IDENTIFIER"),tempArray);
				break;
			default:
		}

	}
}