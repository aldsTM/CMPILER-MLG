import javax.swing.JFrame;

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
			case "arrDec !":
				type = "arrDec";
				printIndent("!");
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
		NonTerminal nt;
		if (getProdString().equals("fuckTheUniverse !")){
			JFrame f = new JFrame("Fuck Da Universe");
			f.setSize(300,400);
			f.setVisible(true);
		} else if (getProdString().trim().equals("!")) {
			// do nothing; ignore!
		} else {
			nt = (NonTerminal) getAsObject("line");
			try {
				nt.execute();
			} catch (Exception e){
				System.out.println(e.getMessage());
			}
		}
	}
}