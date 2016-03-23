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
		switch(type) {
			case "doWhileLoop":
				System.out.println("Nice. A Do-While Loop!");
				break;
			case "whileLoop":
				System.out.println("You've typed in a while loop.");
				break;
			case "forLoop":
				System.out.println("You did a for loop.");
				break;
			default:
				System.out.println("That's not a valid loop, asshole.");
				break;
		}

	}
}