public class FuncList extends NonTerminal {
	public FuncList(String pattern) {
		super("funcList",pattern);
	}

	public void interpret() throws Exception {
		printBranch();
		switch(getProdString()) {
			default:
		}
	}

	public void execute() {

	}
}