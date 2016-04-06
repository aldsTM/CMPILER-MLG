import javax.swing.JFrame;
import java.util.*;

public class NonCondCodeSegment extends NonTerminal {

	private String type;
	private int lineNo;

	public NonCondCodeSegment(String pattern) {
		super("non_cond_code_segment",pattern);
		lineNo = 0;
		type = "";
	}

	public void interpret() throws Exception {
		NonTerminal nt;
		printBranch();
		switch(getProdString()) {
			case "varDec !":
				type = "varDec";
				nt = (NonTerminal) getComponent("varDec");
				propagate(nt);
				nt.interpret();
				printIndent("!");
				put("line",nt);
				break;
			case "constDec !":
				type = "constDec";
				nt = (NonTerminal) getComponent("constDec");
				propagate(nt);
				nt.interpret();
				printIndent("!");
				put("line",nt);
				break;
			case "arrDec !":
				type = "arrDec";
				nt = (NonTerminal) getComponent("arrDec");
				propagate(nt);
				nt.interpret();
				printIndent("!");
				put("line",nt);
				break;
			case "assignment !":
				type = "assignment";
				nt = (NonTerminal) getComponent("assignment");
				propagate(nt);
				nt.interpret();
				printIndent("!");
				put("line",nt);
				break;
			case "funcCall !":
				type = "funcCall";
				nt = (NonTerminal) getComponent("funcCall");
				propagate(nt);
				nt.interpret();
				printIndent("!");
				put("line", nt);
				break;
			case "loop":
				type = "loop";
				nt = (NonTerminal) getComponent("loop");
				propagate(nt);
				nt.interpret();
				put("line",nt);
				break;
			case "fuckTheUniverse !":
				type = "fuckTheUniverse !";
				printIndent("fuckTheUniverse");
				printIndent("!");
				break;
			case "!":
				type = "!";
				printIndent("!");
				break;
			default:
		}
	}

	public String getType() {
		return type;
	}


	public int getLineNo() {
		return lineNo;
	}

	public void execute() {
		SymbolTable st = SymbolTable.instance();
		Scanner sc = new Scanner(System.in);
		NonTerminal nt;
		if (getProdString().equals("fuckTheUniverse !")){
			JFrame f = new JFrame("Fuck Da Universe");
			f.setSize(300,400);
			f.setVisible(true);
		} else if (getProdString().trim().equals("!")) {
			// do nothing; ignore!
		} else {
			nt = (NonTerminal) getAsObject("line");
			if (Driver.LINE_PER_LINE){
				Driver.cls();
				System.out.println("\nWarning: LINE_PER_LINE mode activated.");
				System.out.println("Some functionality, such as input statements, may not work.\n");
				System.out.println("EXECUTING PRODUCTION");
				System.out.println("\t" + nt.getClass().getName() + " -> " + nt.getProdString());
				try {
					System.out.print("\nCONSOLE OUTPUT FOR THIS LINE (if any)\n\t");
					nt.execute();
					System.out.println("\n\n" + st.callStack());					
					System.out.println("\n" + st.symbolTable());
					System.out.print("\nPress ENTER to continue...");
					String temp = (sc.nextLine().trim());
				} catch (Exception e) {
					System.out.println("Runtime error: " + e.getMessage());
				}
			} else {
				try {
					nt.execute();
				} catch (Exception e){
					System.out.println("Runtime error: " + e.getMessage());
				}
			}
		}
	}
}