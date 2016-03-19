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

				nt2 = (NonTerminal) getComponent("expr",1);
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

				nt2 = (NonTerminal) getComponent("expr",1);
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

				nt2 = (NonTerminal) getComponent("expr",1);
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

				nt2 = (NonTerminal) getComponent("expr",1);
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

				nt2 = (NonTerminal) getComponent("expr",1);
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

				nt2 = (NonTerminal) getComponent("expr",1);
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
		put("type","boolean");
		switch(getProdString()) {
			case "expr <= expr":
				error = false;
				nt = (NonTerminal) getAsObject("line");
				nt2 = (NonTerminal) getAsObject("line2");
				nt.execute();
				nt2.execute();
				
				switch (nt.getAsString("type")){
					case "int":
						switch (nt2.getAsString("type")){
							case "int":
								put("val",nt.getAsInt("val") <= nt2.getAsInt("val"));
								break;
							case "float":
								put("val",nt.getAsInt("val") <= nt2.getAsFloat("val"));
								break;
							case "char":
								put("val",nt.getAsInt("val") <= nt2.getAsString("val").charAt(0));
								break;
							case "string":
								try {
									int number = Integer.parseInt(nt2.getAsString("val"));
									put("val",nt.getAsInt("val") <= number);
								} catch (Exception e){
									put("val",false);
								}
								break;
							case "boolean":
								if (nt2.getAsBoolean("val") == true){
									put("val",nt.getAsInt("val") <= 1);
								} else {
									put("val",nt.getAsInt("val") <= 0);
								}
								break;
							case "array":
								break;
						}
						break;
					case "float":
						switch (nt2.getAsString("type")){
							case "int":
								put("val",nt.getAsFloat("val") <= nt2.getAsInt("val"));
								break;
							case "float":
								put("val",nt.getAsFloat("val") <= nt2.getAsFloat("val"));
								break;
							case "char":
								put("val",nt.getAsFloat("val") <= nt2.getAsString("val").charAt(0));
								break;
							case "string":
								try {
									float number = Float.parseFloat(nt2.getAsString("val"));
									put("val",nt.getAsFloat("val") <= number);
								} catch (Exception e){
									put("val",false);
								}
								break;
							case "boolean":
								if (nt2.getAsBoolean("val") == true){
									put("val",nt.getAsFloat("val") <= 1.0);
								} else {
									put("val",nt.getAsFloat("val") <= 0.0);
								}
								break;
							case "array":
								break;
						}
						break;
					case "char":
						switch (nt2.getAsString("type")){
							case "int":
								put("val",nt.getAsString("val").charAt(0) <= nt2.getAsInt("val"));
								break;
							case "float":
								put("val",nt.getAsString("val").charAt(0) <= nt2.getAsFloat("val"));
								break;
							case "char":
								put("val",nt.getAsString("val").charAt(0) <= nt2.getAsString("val").charAt(0));
								break;
							case "string":
								try {
									int number = Integer.parseInt(nt2.getAsString("val"));
									put("val",nt.getAsString("val").charAt(0) <= number);
								} catch (Exception e){
									put("val",false);
								}
								break;
							case "boolean":
								if (nt2.getAsBoolean("val") == true){
									put("val",nt.getAsString("val").charAt(0) <= 1);
								} else {
									put("val",nt.getAsString("val").charAt(0) <= 0);
								}
								break;
							case "array":
								break;
						}
						break;
					case "string":
						switch (nt2.getAsString("type")){
							case "int":
								break;
							case "float":
								break;
							case "char":
								break;
							case "string":
								put("val",nt.getAsString("val").compareTo(nt2.getAsString("val")) <= 0);
								break;
							case "boolean":
								break;
							case "array":
								break;
						}
						break;
					case "array":
						switch (nt2.getAsString("type")){
							case "int":
								break;
							case "float":
								break;
							case "char":
								break;
							case "string":
								break;
							case "boolean":
								break;
							case "array":
								break;
						}
						break;
					default:
						error = true;
				}
				if( error ) {
					put("type","error");
					System.out.println("Unsupported operation " + nt + "<=" + nt2);
				}
				break;
			case "expr >= expr":
				error = false;
				nt = (NonTerminal) getAsObject("line");
				nt2 = (NonTerminal) getAsObject("line2");
				nt.execute();
				nt2.execute();
				switch (nt.getAsString("type")){
					case "int":
						switch (nt2.getAsString("type")){
							case "int":
								put("val",nt.getAsInt("val") >= nt2.getAsInt("val"));
								break;
							case "float":
								put("val",nt.getAsInt("val") >= nt2.getAsFloat("val"));
								break;
							case "char":
								put("val",nt.getAsInt("val") >= nt2.getAsString("val").charAt(0));
								break;
							case "string":
								try {
									int number = Integer.parseInt(nt2.getAsString("val"));
									put("val",nt.getAsInt("val") >= number);
								} catch (Exception e){
									put("val",false);
								}
								break;
							case "boolean":
								if (nt2.getAsBoolean("val") == true){
									put("val",nt.getAsInt("val") >= 1);
								} else {
									put("val",nt.getAsInt("val") >= 0);
								}
								break;
							case "array":
								break;
						}
						break;
					case "float":
						switch (nt2.getAsString("type")){
							case "int":
								put("val",nt.getAsFloat("val") >= nt2.getAsInt("val"));
								break;
							case "float":
								put("val",nt.getAsFloat("val") >= nt2.getAsFloat("val"));
								break;
							case "char":
								put("val",nt.getAsFloat("val") >= nt2.getAsString("val").charAt(0));
								break;
							case "string":
								try {
									float number = Float.parseFloat(nt2.getAsString("val"));
									put("val",nt.getAsFloat("val") >= number);
								} catch (Exception e){
									put("val",false);
								}
								break;
							case "boolean":
								if (nt2.getAsBoolean("val") == true){
									put("val",nt.getAsFloat("val") >= 1.0);
								} else {
									put("val",nt.getAsFloat("val") >= 0.0);
								}
								break;
							case "array":
								break;
						}
						break;
					case "char":
						switch (nt2.getAsString("type")){
							case "int":
								put("val",nt.getAsString("val").charAt(0) >= nt2.getAsInt("val"));
								break;
							case "float":
								put("val",nt.getAsString("val").charAt(0) >= nt2.getAsFloat("val"));
								break;
							case "char":
								put("val",nt.getAsString("val").charAt(0) >= nt2.getAsString("val").charAt(0));
								break;
							case "string":
								try {
									int number = Integer.parseInt(nt2.getAsString("val"));
									put("val",nt.getAsString("val").charAt(0) >= number);
								} catch (Exception e){
									put("val",false);
								}
								break;
							case "boolean":
								if (nt2.getAsBoolean("val") == true){
									put("val",nt.getAsString("val").charAt(0) >= 1);
								} else {
									put("val",nt.getAsString("val").charAt(0) >= 0);
								}
								break;
							case "array":
								break;
						}
						break;
					case "string":
						switch (nt2.getAsString("type")){
							case "int":
								break;
							case "float":
								break;
							case "char":
								break;
							case "string":
								put("val",nt.getAsString("val").compareTo(nt2.getAsString("val")) >= 0);
								break;
							case "boolean":
								break;
							case "array":
								break;
						}
						break;
					case "array":
						switch (nt2.getAsString("type")){
							case "int":
								break;
							case "float":
								break;
							case "char":
								break;
							case "string":
								break;
							case "boolean":
								break;
							case "array":
								break;
						}
						break;
					default:
						error = true;
				}
				if( error ) {
					put("type","error");
					System.out.println("Unsupported operation " + nt + ">=" + nt2);
				}
				break;
			case "expr < expr":
				error = false;
				nt = (NonTerminal) getAsObject("line");
				nt2 = (NonTerminal) getAsObject("line2");
				nt.execute();
				nt2.execute();
				switch (nt.getAsString("type")){
					case "int":
						switch (nt2.getAsString("type")){
							case "int":
								put("val",nt.getAsInt("val") < nt2.getAsInt("val"));
								break;
							case "float":
								put("val",nt.getAsInt("val") < nt2.getAsFloat("val"));
								break;
							case "char":
								put("val",nt.getAsInt("val") < nt2.getAsString("val").charAt(0));
								break;
							case "string":
								try {
									int number = Integer.parseInt(nt2.getAsString("val"));
									put("val",nt.getAsInt("val") < number);
								} catch (Exception e){
									put("val",false);
								}
								break;
							case "boolean":
								if (nt2.getAsBoolean("val") == true){
									put("val",nt.getAsInt("val") < 1);
								} else {
									put("val",nt.getAsInt("val") < 0);
								}
								break;
							case "array":
								break;
						}
						break;
					case "float":
						switch (nt2.getAsString("type")){
							case "int":
								put("val",nt.getAsFloat("val") < nt2.getAsInt("val"));
								break;
							case "float":
								put("val",nt.getAsFloat("val") < nt2.getAsFloat("val"));
								break;
							case "char":
								put("val",nt.getAsFloat("val") < nt2.getAsString("val").charAt(0));
								break;
							case "string":
								try {
									float number = Float.parseFloat(nt2.getAsString("val"));
									put("val",nt.getAsFloat("val") < number);
								} catch (Exception e){
									put("val",false);
								}
								break;
							case "boolean":
								if (nt2.getAsBoolean("val") == true){
									put("val",nt.getAsFloat("val") < 1.0);
								} else {
									put("val",nt.getAsFloat("val") < 0.0);
								}
								break;
							case "array":
								break;
						}
						break;
					case "char":
						switch (nt2.getAsString("type")){
							case "int":
								put("val",nt.getAsString("val").charAt(0) < nt2.getAsInt("val"));
								break;
							case "float":
								put("val",nt.getAsString("val").charAt(0) < nt2.getAsFloat("val"));
								break;
							case "char":
								put("val",nt.getAsString("val").charAt(0) < nt2.getAsString("val").charAt(0));
								break;
							case "string":
								try {
									int number = Integer.parseInt(nt2.getAsString("val"));
									put("val",nt.getAsString("val").charAt(0) < number);
								} catch (Exception e){
									put("val",false);
								}
								break;
							case "boolean":
								if (nt2.getAsBoolean("val") == true){
									put("val",nt.getAsString("val").charAt(0) < 1);
								} else {
									put("val",nt.getAsString("val").charAt(0) < 0);
								}
								break;
							case "array":
								break;
						}
						break;
					case "string":
						switch (nt2.getAsString("type")){
							case "int":
								break;
							case "float":
								break;
							case "char":
								break;
							case "string":
								put("val",nt.getAsString("val").compareTo(nt2.getAsString("val")) < 0);
								break;
							case "boolean":
								break;
							case "array":
								break;
						}
						break;
					case "array":
						switch (nt2.getAsString("type")){
							case "int":
								break;
							case "float":
								break;
							case "char":
								break;
							case "string":
								break;
							case "boolean":
								break;
							case "array":
								break;
						}
						break;
					default:
						error = true;
				}
				if( error ) {
					put("type","error");
					System.out.println("Unsupported operation " + nt + "<" + nt2);
				}
				break;
			case "expr > expr":
				System.out.println("I'M HERE FUCKER ");
				error = false;
				nt = (NonTerminal) getAsObject("line");
				nt2 = (NonTerminal) getAsObject("line2");
				nt.execute();
				nt2.execute();
				switch (nt.getAsString("type")){
					case "int":
						switch (nt2.getAsString("type")){
							case "int":
								put("val",nt.getAsInt("val") > nt2.getAsInt("val"));
								System.out.println("VAL IS " + nt.getAsInt("val"));
								System.out.println("VAL2 IS " + nt2.getAsInt("val"));
								System.out.println(nt.getAsInt("val") > nt2.getAsInt("val"));
								break;
							case "float":
								put("val",nt.getAsInt("val") > nt2.getAsFloat("val"));
								break;
							case "char":
								put("val",nt.getAsInt("val") > nt2.getAsString("val").charAt(0));
								break;
							case "string":
								try {
									int number = Integer.parseInt(nt2.getAsString("val"));
									put("val",nt.getAsInt("val") > number);
								} catch (Exception e){
									put("val",false);
								}
								break;
							case "boolean":
								if (nt2.getAsBoolean("val") == true){
									put("val",nt.getAsInt("val") > 1);
								} else {
									put("val",nt.getAsInt("val") > 0);
								}
								break;
							case "array":
								break;
						}
						break;
					case "float":
						switch (nt2.getAsString("type")){
							case "int":
								put("val",nt.getAsFloat("val") > nt2.getAsInt("val"));
								break;
							case "float":
								put("val",nt.getAsFloat("val") > nt2.getAsFloat("val"));
								break;
							case "char":
								put("val",nt.getAsFloat("val") > nt2.getAsString("val").charAt(0));
								break;
							case "string":
								try {
									float number = Float.parseFloat(nt2.getAsString("val"));
									put("val",nt.getAsFloat("val") > number);
								} catch (Exception e){
									put("val",false);
								}
								break;
							case "boolean":
								if (nt2.getAsBoolean("val") == true){
									put("val",nt.getAsFloat("val") > 1.0);
								} else {
									put("val",nt.getAsFloat("val") > 0.0);
								}
								break;
							case "array":
								break;
						}
						break;
					case "char":
						switch (nt2.getAsString("type")){
							case "int":
								put("val",nt.getAsString("val").charAt(0) > nt2.getAsInt("val"));
								break;
							case "float":
								put("val",nt.getAsString("val").charAt(0) > nt2.getAsFloat("val"));
								break;
							case "char":
								put("val",nt.getAsString("val").charAt(0) > nt2.getAsString("val").charAt(0));
								break;
							case "string":
								try {
									int number = Integer.parseInt(nt2.getAsString("val"));
									put("val",nt.getAsString("val").charAt(0) > number);
								} catch (Exception e){
									put("val",false);
								}
								break;
							case "boolean":
								if (nt2.getAsBoolean("val") == true){
									put("val",nt.getAsString("val").charAt(0) > 1);
								} else {
									put("val",nt.getAsString("val").charAt(0) > 0);
								}
								break;
							case "array":
								break;
						}
						break;
					case "string":
						switch (nt2.getAsString("type")){
							case "int":
								break;
							case "float":
								break;
							case "char":
								break;
							case "string":
								put("val",nt.getAsString("val").compareTo(nt2.getAsString("val")) > 0);
								break;
							case "boolean":
								break;
							case "array":
								break;
						}
						break;
					case "array":
						switch (nt2.getAsString("type")){
							case "int":
								break;
							case "float":
								break;
							case "char":
								break;
							case "string":
								break;
							case "boolean":
								break;
							case "array":
								break;
						}
						break;
					default:
						error = true;
				}
				if( error ) {
					put("type","error");
					System.out.println("Unsupported operation " + nt + ">" + nt2);
				}
				break;
			case "expr == expr":
				error = false;
				nt = (NonTerminal) getAsObject("line");
				nt2 = (NonTerminal) getAsObject("line2");
				nt.execute();
				nt2.execute();
				switch (nt.getAsString("type")){
					case "int":
						switch (nt2.getAsString("type")){
							case "int":
								put("val",nt.getAsInt("val") == nt2.getAsInt("val"));
								break;
							case "float":
								put("val",nt.getAsInt("val") == nt2.getAsFloat("val"));
								break;
							case "char":
								put("val",nt.getAsInt("val") == nt2.getAsString("val").charAt(0));
								break;
							case "string":
								try {
									int number = Integer.parseInt(nt2.getAsString("val"));
									put("val",nt.getAsInt("val") == number);
								} catch (Exception e){
									put("val",false);
								}
								break;
							case "boolean":
								if (nt2.getAsBoolean("val") == true){
									put("val",nt.getAsInt("val") == 1);
								} else {
									put("val",nt.getAsInt("val") == 0);
								}
								break;
							case "array":
								break;
						}
						break;
					case "float":
						switch (nt2.getAsString("type")){
							case "int":
								put("val",nt.getAsFloat("val") == nt2.getAsInt("val"));
								break;
							case "float":
								put("val",nt.getAsFloat("val") == nt2.getAsFloat("val"));
								break;
							case "char":
								put("val",nt.getAsFloat("val") == nt2.getAsString("val").charAt(0));
								break;
							case "string":
								try {
									float number = Float.parseFloat(nt2.getAsString("val"));
									put("val",nt.getAsFloat("val") == number);
								} catch (Exception e){
									put("val",false);
								}
								break;
							case "boolean":
								if (nt2.getAsBoolean("val") == true){
									put("val",nt.getAsFloat("val") == 1.0);
								} else {
									put("val",nt.getAsFloat("val") == 0.0);
								}
								break;
							case "array":
								break;
						}
						break;
					case "char":
						switch (nt2.getAsString("type")){
							case "int":
								put("val",nt.getAsString("val").charAt(0) == nt2.getAsInt("val"));
								break;
							case "float":
								put("val",nt.getAsString("val").charAt(0) == nt2.getAsFloat("val"));
								break;
							case "char":
								put("val",nt.getAsString("val").charAt(0) == nt2.getAsString("val").charAt(0));
								break;
							case "string":
								try {
									int number = Integer.parseInt(nt2.getAsString("val"));
									put("val",nt.getAsString("val").charAt(0) == number);
								} catch (Exception e){
									put("val",false);
								}
								break;
							case "boolean":
								if (nt2.getAsBoolean("val") == true){
									put("val",nt.getAsString("val").charAt(0) == 1);
								} else {
									put("val",nt.getAsString("val").charAt(0) == 0);
								}
								break;
							case "array":
								break;
						}
						break;
					case "string":
						switch (nt2.getAsString("type")){
							case "int":
								break;
							case "float":
								break;
							case "char":
								break;
							case "string":
								put("val",nt.getAsString("val").compareTo(nt2.getAsString("val")) == 0);
								break;
							case "boolean":
								break;
							case "array":
								break;
						}
						break;
					case "array":
						switch (nt2.getAsString("type")){
							case "int":
								break;
							case "float":
								break;
							case "char":
								break;
							case "string":
								break;
							case "boolean":
								break;
							case "array":
								break;
						}
						break;
					default:
						error = true;
				}
				if( error ) {
					put("type","error");
					System.out.println("Unsupported operation " + nt + "==" + nt2);
				}
				break;
			case "expr != expr":
				error = false;
				nt = (NonTerminal) getAsObject("line");
				nt2 = (NonTerminal) getAsObject("line2");
				nt.execute();
				nt2.execute();
				switch (nt.getAsString("type")){
					case "int":
						switch (nt2.getAsString("type")){
							case "int":
								put("val",nt.getAsInt("val") != nt2.getAsInt("val"));
								break;
							case "float":
								put("val",nt.getAsInt("val") != nt2.getAsFloat("val"));
								break;
							case "char":
								put("val",nt.getAsInt("val") != nt2.getAsString("val").charAt(0));
								break;
							case "string":
								try {
									int number = Integer.parseInt(nt2.getAsString("val"));
									put("val",nt.getAsInt("val") != number);
								} catch (Exception e){
									put("val",false);
								}
								break;
							case "boolean":
								if (nt2.getAsBoolean("val") != true){
									put("val",nt.getAsInt("val") != 1);
								} else {
									put("val",nt.getAsInt("val") != 0);
								}
								break;
							case "array":
								break;
						}
						break;
					case "float":
						switch (nt2.getAsString("type")){
							case "int":
								put("val",nt.getAsFloat("val") != nt2.getAsInt("val"));
								break;
							case "float":
								put("val",nt.getAsFloat("val") != nt2.getAsFloat("val"));
								break;
							case "char":
								put("val",nt.getAsFloat("val") != nt2.getAsString("val").charAt(0));
								break;
							case "string":
								try {
									float number = Float.parseFloat(nt2.getAsString("val"));
									put("val",nt.getAsFloat("val") != number);
								} catch (Exception e){
									put("val",false);
								}
								break;
							case "boolean":
								if (nt2.getAsBoolean("val") != true){
									put("val",nt.getAsFloat("val") != 1.0);
								} else {
									put("val",nt.getAsFloat("val") != 0.0);
								}
								break;
							case "array":
								break;
						}
						break;
					case "char":
						switch (nt2.getAsString("type")){
							case "int":
								put("val",nt.getAsString("val").charAt(0) != nt2.getAsInt("val"));
								break;
							case "float":
								put("val",nt.getAsString("val").charAt(0) != nt2.getAsFloat("val"));
								break;
							case "char":
								put("val",nt.getAsString("val").charAt(0) != nt2.getAsString("val").charAt(0));
								break;
							case "string":
								try {
									int number = Integer.parseInt(nt2.getAsString("val"));
									put("val",nt.getAsString("val").charAt(0) != number);
								} catch (Exception e){
									put("val",false);
								}
								break;
							case "boolean":
								if (nt2.getAsBoolean("val") != true){
									put("val",nt.getAsString("val").charAt(0) != 1);
								} else {
									put("val",nt.getAsString("val").charAt(0) != 0);
								}
								break;
							case "array":
								break;
						}
						break;
					case "string":
						switch (nt2.getAsString("type")){
							case "int":
								break;
							case "float":
								break;
							case "char":
								break;
							case "string":
								put("val",nt.getAsString("val").compareTo(nt2.getAsString("val")) != 0);
								break;
							case "boolean":
								break;
							case "array":
								break;
						}
						break;
					case "array":
						switch (nt2.getAsString("type")){
							case "int":
								break;
							case "float":
								break;
							case "char":
								break;
							case "string":
								break;
							case "boolean":
								break;
							case "array":
								break;
						}
						break;
					default:
						error = true;
				}
				if( error ) {
					put("type","error");
					System.out.println("Unsupported operation " + nt + "!=" + nt2);
				}
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
			default:
				error = true;
				break;
		}
	}
}