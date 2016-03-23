import java.util.ArrayList;

public class Conditional extends NonTerminal {
	private ArrayList<CodeSegment> codes;
	private ArrayList<CodeSegment> codes2;
	private NonTerminal nonTerminal;

	public Conditional(String pattern) {
		super("conditional",pattern);
		codes = new ArrayList<CodeSegment>();
		codes2 = new ArrayList<CodeSegment>();
		nonTerminal = null;
	}

	public void interpret() throws Exception {
		NonTerminal nt, nt3;
		CodeBlock nt2, nt4;
		NonTerminal nt5;
		CodeSegment[] cs, cs2;
		printBranch();
		switch(getProdString()) {
			case "ILLUMINATI CONFIRMED ( comparative ) code_block":
				printIndent("ILLUMINATI");
				printIndent("CONFIRMED");
				printIndent("(");
				
				nt = (NonTerminal) getComponent("comparative");
				propagate(nt);
				nt.interpret();

				printIndent(")");

				nt2 = (CodeBlock) getComponent("code_block");
				propagate(nt2);
				nt2.interpret();

				cs = (CodeSegment[]) nt2.getCodes();
				for(CodeSegment line : cs) {
					codes.add(line);
				}
				break;
			case "ILLUMINATI CONFIRMED ( comparative ) code_block m WHATCHASAY conditional":
				printIndent("ILLUMINATI");
				printIndent("CONFIRMED");
				printIndent("(");
				
				nt = (NonTerminal) getComponent("comparative");
				propagate(nt);
				nt.interpret();

				printIndent(")");

				nt2 = (CodeBlock) getComponent("code_block");
				propagate(nt2);
				nt2.interpret();

				cs = (CodeSegment[]) nt2.getCodes();
				for(CodeSegment line : cs) {
					codes.add(line);
				}

				nt3 = (NonTerminal) getComponent("m");
				propagate(nt3);
				nt3.interpret();

				printIndent("WHATCHASAY");

				nt5 = (NonTerminal) getComponent("conditional");
				propagate(nt5);
				nt5.interpret();
				nonTerminal = nt5;

				break;
			case "ILLUMINATI CONFIRMED ( comparative ) code_block m WHATCHASAY code_block":
				printIndent("ILLUMINATI");
				printIndent("CONFIRMED");
				printIndent("(");
				
				nt = (NonTerminal) getComponent("comparative");
				propagate(nt);
				nt.interpret();

				printIndent(")");

				nt2 = (CodeBlock) getComponent("code_block");
				propagate(nt2);
				nt2.interpret();

				cs = (CodeSegment[]) nt2.getCodes();
				for(CodeSegment line : cs) {
					codes.add(line);
				}

				nt3 = (NonTerminal) getComponent("m");
				propagate(nt3);
				nt3.interpret();

				printIndent("WHATCHASAY");

				nt4 = (CodeBlock) getComponent("code_block",1);
				propagate(nt4);
				nt4.interpret();

				cs2 = (CodeSegment[]) nt4.getCodes();
				for(CodeSegment line : cs2) {
					codes2.add(line);
				}
				break;
			default:
		}
	}

	public void execute() {
		SymbolTable st = SymbolTable.instance();
		NonTerminal comparative = (NonTerminal)getComponent("comparative");
		comparative.execute();
		if( comparative.getAsString("type").equals("boolean") ) {
			st.pushContext();
			if( comparative.getAsBoolean("val") ) {
				run(codes);
			} else if (nonTerminal != null) {
				nonTerminal.execute();
			} else {
				run(codes2);
			}
			st.popContext();
		} else {
			System.out.println("Illegal " + comparative.getAsString("type") 
								+ " as condition on line " 
								+ comparative.getAsInt("lineNo"));
		}

		// if (nonTerminal != null){
		// 	nonTerminal.execute();
		// }

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