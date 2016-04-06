import java.util.Scanner;

public class ScanF extends NonTerminal {
	public ScanF(String pattern) {
		super("scanf",pattern);
	}

	public void interpret() throws Exception {
		Token t;
		NonTerminal nt;
		printBranch();
		switch(getProdString()) {
			case "SONG NAME ? ( dataType , IDENTIFIER )":
				printIndent("SONG");
				printIndent("NAME");
				printIndent("?");

				printIndent("(");
				nt = (NonTerminal) getComponent("dataType");
				propagate(nt);
				nt.interpret();
				put("typeOfInput",nt.getAsString("type"));

				printIndent(",");

				t = (Token) getComponent("IDENTIFIER");
				printIndent(t.token());
				put("IDENTIFIER",t.token());
				printIndent(")");
				break;
			default:
		}
	}

	public void execute() {
		SymbolTable st = SymbolTable.instance();
		String typeOfVariable = getAsString("typeOfVariable");
		String typeOfInput = getAsString("typeOfInput");
		
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
		}

		// System.out.println("Type of input should be: " + typeOfVariable);
		// System.out.println("Type of input is: " + typeOfInput);

		Scanner sc = new Scanner(System.in);
		String tempInput = "";
		switch (typeOfInput){
			case "int":

				// get user input
				int userInputInt = 0;
				while (true){
					try {
						tempInput = sc.nextLine().trim();
						userInputInt = Integer.parseInt(tempInput);
						break; // will only get here if userInput is of correct type
					} catch (NumberFormatException nfe){
						System.out.println("Type Checking Error: " 
											+ tempInput
											+ " is not a valid int.");
					}
				}

				// assign user input to variable
				switch(typeOfVariable){
					case "int":
						st.assign(getAsString("IDENTIFIER"),userInputInt);
						put("val",userInputInt);
						put("status",true);
						break;
					case "float":
						st.assign(getAsString("IDENTIFIER"),(float)userInputInt);
						put("val",(float)userInputInt);
						put("status",true);						
						break;
					case "char":
						st.assign(getAsString("IDENTIFIER"),("" + userInputInt).charAt(0));
						put("val",("" + userInputInt).charAt(0));
						put("status",true);
						break;
					case "string":
						st.assign(getAsString("IDENTIFIER"),"" + userInputInt);
						put("val","" + userInputInt);
						put("status",true);
						break;
					case "boolean":
						st.assign(getAsString("IDENTIFIER"),userInputInt != 0);
						put("val",userInputInt != 0);
						put("status",true);
						break;
				}

				break;
			case "float":

				// get user input
				float userInputFloat = 0;
				while (true){
					try {
						tempInput = sc.nextLine().trim();
						userInputFloat = Float.parseFloat(tempInput);
						break; // will only get here if userInput is of correct type
					} catch (NumberFormatException nfe){
						System.out.println("Type Checking Error: " 
											+ tempInput
											+ " is not a valid float.");
					}
				}

				// assign user input to variable
				switch(typeOfVariable){
					case "int":
						st.assign(getAsString("IDENTIFIER"),(int)userInputFloat);
						put("val",(int)userInputFloat);
						put("status",true);
						break;
					case "float":
						st.assign(getAsString("IDENTIFIER"),userInputFloat);
						put("val",userInputFloat);
						put("status",true);						
						break;
					case "char":
						st.assign(getAsString("IDENTIFIER"),("" + userInputFloat).charAt(0));
						put("val",("" + userInputFloat).charAt(0));
						put("status",true);
						break;
					case "string":
						st.assign(getAsString("IDENTIFIER"),"" + userInputFloat);
						put("val","" + userInputFloat);
						put("status",true);
						break;
					case "boolean":
						st.assign(getAsString("IDENTIFIER"),userInputFloat != 0.0);
						put("val",userInputFloat != 0.0);
						put("status",true);
						break;
				}

				break;
			case "char":

				// get user input
				char userInputChar = '\0';
				userInputChar = (sc.nextLine()).charAt(0);

				// assign user input to variable
				switch(typeOfVariable){
					case "int":
						st.assign(getAsString("IDENTIFIER"),(int)userInputChar);
						put("val",(int)userInputChar);
						put("status",true);
						break;
					case "float":
						st.assign(getAsString("IDENTIFIER"),(float)userInputChar);
						put("val",(float)userInputChar);
						put("status",true);						
						break;
					case "char":
						st.assign(getAsString("IDENTIFIER"),userInputChar);
						put("val",userInputChar);
						put("status",true);
						break;
					case "string":
						st.assign(getAsString("IDENTIFIER"),"" + userInputChar);
						put("val","" + userInputChar);
						put("status",true);
						break;
					case "boolean":
						if (userInputChar == 'T' || userInputChar == 't' || userInputChar == '1'){
							st.assign(getAsString("IDENTIFIER"),true);
							put("val",true);
						} else if (userInputChar == 'F' || userInputChar == 'f' || userInputChar == '0'){
							st.assign(getAsString("IDENTIFIER"),false);
							put("val",false);
						}
						put("status",true);
						break;
				}

				break;
			case "string":

				// get user input
				String userInputStr = "";
				userInputStr = (sc.nextLine()).trim();

				// assign user input to variable
				switch(typeOfVariable){
					case "int":
						try {
							st.assign(getAsString("IDENTIFIER"),Integer.parseInt(userInputStr));
							put("val",Integer.parseInt(userInputStr));
						} catch(Exception e) {
							System.out.println("Type Checking Error: " 
												+ userInputStr
												+ " cannot be converted to int.");
						}
						put("status",true);
						break;
					case "float":
						try {
							st.assign(getAsString("IDENTIFIER"),Float.parseFloat(userInputStr));
							put("val",Float.parseFloat(userInputStr));
						} catch(Exception e) {
							System.out.println("Type Checking Error: " 
												+ userInputStr
												+ " cannot be converted to float.");
						}
						put("status",true);						
						break;
					case "char":
						st.assign(getAsString("IDENTIFIER"),userInputStr.charAt(0));
						put("val",userInputStr.charAt(0));
						put("status",true);
						break;
					case "string":
						st.assign(getAsString("IDENTIFIER"),userInputStr);
						put("val",userInputStr);
						put("status",true);
						break;
					case "boolean":
						if (userInputStr.equalsIgnoreCase("true")){
							st.assign(getAsString("IDENTIFIER"),true);
							put("val",true);
						} else if (userInputStr.equalsIgnoreCase("false")){
							st.assign(getAsString("IDENTIFIER"),false);
							put("val",false);
						}
						put("status",true);
						break;
				}

				break;
			case "boolean":

				// get user input
				boolean userInputBool = false;
				String userInputTemp = (sc.nextLine().trim());
				if (userInputTemp.equalsIgnoreCase("true") ||
					userInputTemp.equalsIgnoreCase("T") ||
					userInputTemp.equalsIgnoreCase("1")){
					userInputBool = true;
				} else if (userInputTemp.equalsIgnoreCase("false") ||
					userInputTemp.equalsIgnoreCase("F") ||
					userInputTemp.equalsIgnoreCase("0")){
					userInputBool = false;
				}

				// assign user input to variable
				switch(typeOfVariable){
					case "int":
						if (userInputBool){
							st.assign(getAsString("IDENTIFIER"),1);
							put("val",1);
						} else {
							st.assign(getAsString("IDENTIFIER"),0);
							put("val",0);
						}
						put("status",true);
						break;
					case "float":
						if (userInputBool){
							st.assign(getAsString("IDENTIFIER"),1.0);
							put("val",1.0);
						} else {
							st.assign(getAsString("IDENTIFIER"),0.0);
							put("val",0.0);
						}
						put("status",true);						
						break;
					case "char":
						if (userInputBool){
							st.assign(getAsString("IDENTIFIER"),'T');
							put("val",'T');
						} else {
							st.assign(getAsString("IDENTIFIER"),'F');
							put("val",'F');
						}
						put("status",true);
						break;
					case "string":
						if (userInputBool){
							st.assign(getAsString("IDENTIFIER"),"true");
							put("val","true");
						} else {
							st.assign(getAsString("IDENTIFIER"),"false");
							put("val","false");
						}
						put("status",true);
						break;
					case "boolean":
						st.assign(getAsString("IDENTIFIER"),userInputBool);
						put("val",userInputBool);
						put("status",true);
						break;
				}
				break;
			default:
				break;

		}

		put("typeOfVariable",typeOfVariable);


	}
}