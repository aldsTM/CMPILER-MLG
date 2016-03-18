public class CodeSegment extends NonTerminal {

	private String type;
	private int lineNo;

	public CodeSegment(String pattern) {
		super("code_segment",pattern);
	}

	public void interpret() throws Exception {
		NonCondCodeSegment code;
		printBranch();
		switch(getProdString()) {
			case "conditional":
				code = (NonCondCodeSegment) getComponent("conditional");
				propagate(code);
				code.interpret();
				put("line",getComponent("conditional"));
				break;
			case "non_cond_code_segment":
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
		NonTerminal nt = (NonTerminal) getAsObject("line");
		nt.execute();
		//code.execute();
	}
}