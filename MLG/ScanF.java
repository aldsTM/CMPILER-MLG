public class ScanF extends NonTerminal {
	public ScanF(String pattern) {
		super("scanf",pattern);
	}

	public void interpret() throws Exception {
		NonTerminal nt;
		printBranch();
		switch(getProdString()) {
			case "SONG NAME ? ( dataType )":
				printIndent("SONG");
				printIndent("NAME");
				printIndent("?");
				printIndent("(");
				nt = (NonTerminal) getComponent("dataType");
				propagate(nt);
				nt.interpret();
				put("type",nt.getAsString("type"));
				printIndent(")");
				break;
			default:
		}
	}

	public void execute() {

	}
}