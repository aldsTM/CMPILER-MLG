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
		if (getType().equalsIgnoreCase("return")){
			put("type",nt.getAsString("type"));
			switch(nt.getAsString("type")){
				case "int":
					put("val",nt.getAsInt("val"));
					break;
				case "float":
					put("val",nt.getAsFloat("val"));
					break;
				case "char":
					put("val",nt.getAsString("val").charAt(0));
					break;
				case "string":
					put("val",nt.getAsString("val"));
					break;
				case "boolean":
					put("val",nt.getAsBoolean("val"));
					break;
			}
		}
		//code.execute();
	}
}