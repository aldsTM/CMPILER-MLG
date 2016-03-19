public class CodeSegment extends NonTerminal {

	private String type;
	private int lineNo;

	public CodeSegment(String pattern) {
		super("code_segment",pattern);
	}

	public void interpret() throws Exception {
		printBranch();
		switch(getProdString()) {
			case "conditional":
				Conditional cond;
				type = "conditional";
				cond = (Conditional) getComponent("conditional");
				propagate(cond);
				cond.interpret();
				put("line",getComponent("conditional"));
				break;
			case "non_cond_code_segment":
				NonCondCodeSegment code;
				code = (NonCondCodeSegment) getComponent("non_cond_code_segment");
				propagate(code);
				code.interpret();
				put("line",getComponent("non_cond_code_segment"));
				type = code.getType();
				lineNo = code.getLineNo();
				put("lineNo",lineNo);
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
		nt = (NonTerminal) getAsObject("line");
		nt.execute();
		//code.execute();
	}
}