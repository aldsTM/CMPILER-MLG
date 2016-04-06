import java.util.ArrayList;

public class Loop extends NonTerminal {

	private String type;
	private ArrayList<CodeSegment> codes;

	public Loop(String pattern) {
		super("loop",pattern);
		type = "";
		codes = new ArrayList<>();
	}

	public void interpret() throws Exception {
		CodeBlock nt;
		NonTerminal nt2;
		NonTerminal varDec, comparative, assignment;
		CodeSegment[] cs;
		printBranch();
		switch(getProdString()) {
			case "SMOKE WEED code_block EVERYDAY ( comparative )":
				type = "doWhileLoop";
				printIndent("SMOKE");
				printIndent("WEED");

				nt = (CodeBlock) getComponent("code_block");
				propagate(nt);
				nt.interpret();
				cs = (CodeSegment[]) nt.getCodes();
				for(CodeSegment line : cs) {
					codes.add(line);
				}

				printIndent("EVERYDAY");
				printIndent("(");
				nt2 = (NonTerminal) getComponent("comparative");
				propagate(nt2);
				nt2.interpret();
				printIndent(")");
				break;
			case "EVERYDAY ( comparative ) code_block":
				type = "whileLoop";
				printIndent("EVERYDAY");

				printIndent("(");
				nt2 = (NonTerminal) getComponent("comparative");
				propagate(nt2);
				nt2.interpret();
				printIndent(")");

				nt = (CodeBlock) getComponent("code_block");
				propagate(nt);
				nt.interpret();
				cs = (CodeSegment[]) nt.getCodes();
				for(CodeSegment line : cs) {
					codes.add(line);
				}

				break;
			case "FOR TWENTY ( varDec ; comparative ; assignment ) code_block":
				type = "forLoop";
				printIndent("FOR");
				printIndent("TWENTY");
				
				printIndent("(");

				varDec = (NonTerminal) getComponent("varDec");
				propagate(varDec);
				varDec.interpret();
				printIndent(";");

				comparative = (NonTerminal) getComponent("comparative");
				propagate(comparative);
				comparative.interpret();
				printIndent(";");

				assignment = (NonTerminal) getComponent("assignment");
				propagate(assignment);
				assignment.interpret();
				printIndent(";");

				printIndent(")");

				nt = (CodeBlock) getComponent("code_block");
				propagate(nt);
				nt.interpret();
				cs = (CodeSegment[]) nt.getCodes();
				for(CodeSegment line : cs) {
					codes.add(line);
				}

				break;
			default:
		}
	}

	public String getType(){
		return this.type;
	}

	public void execute() {	
		SymbolTable st = SymbolTable.instance();
		st.pushContext();	
		NonTerminal comparative = (NonTerminal) getComponent("comparative");

		if (type.equalsIgnoreCase("forLoop")){
			NonTerminal assignment = (NonTerminal) getComponent("assignment");
			NonTerminal varDec = (NonTerminal) getComponent("varDec");
			varDec.execute();
			comparative.execute();

			if (comparative.getAsBoolean("val")){
				while (comparative.getAsBoolean("val")) {
					run(codes);
					assignment.execute();
					comparative.execute();
				}
			}
		} else {
			comparative.execute();
			if (comparative.getAsString("type").equals("boolean")){	
				// st.pushContext();	
				switch(type) {
					case "doWhileLoop":
						if (comparative.getAsBoolean("val")){
							do {
								run(codes);
								comparative.execute();
							} while (comparative.getAsBoolean("val"));
						}
						break;
					case "whileLoop":
						if (comparative.getAsBoolean("val")){
							while (comparative.getAsBoolean("val")) {
								run(codes);
								comparative.execute();
							}
						}
						break;
					default:
						System.out.println("That's not a valid loop, asshole.");
						break;
				}
			} else {
				System.out.println("Illegal " + comparative.getAsString("type") 
									+ " as condition on line " 
									+ comparative.getAsInt("lineNo"));
			}
		}

		st.popContext();
	}

	public void run(ArrayList<CodeSegment> codes) {
		boolean stop = false;
		for(CodeSegment cl: codes) {
			switch( cl.getType() ) {
				case "return":
					put("status","return");
					put("lineNo",cl.getAsInt("lineNo"));
					break;
				default:
					cl.execute();
					Object status;
					if( (status = cl.getAsObject("status")) != null ) {
						switch(status.toString()) {
						 	case "return":
						 		put("status","return");
						 		put("lineNo",cl.getAsInt("lineNo"));
								break;
						 }
					}
			}
			if( stop ) {
				break;
			}
		}
	}

}