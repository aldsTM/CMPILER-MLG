public class M extends NonTerminal {
	public M(String pattern) {
		super("m",pattern);
	}

	public void interpret() throws Exception {
		M nt;
		printBranch();
		switch(getProdString()) {
			case "M m":
				printIndent("M");
				nt = (M) getComponent("m");
				propagate(nt);
				nt.interpret();
				put("line",nt);
				break;
			default:
		}
	}

	public void execute() {

	}
}