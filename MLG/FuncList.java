public class FuncList extends NonTerminal {
	public FuncList(String pattern) {
		super("funcList",pattern);
	}

	public void interpret() throws Exception {
		NonTerminal nt, nt2;
		printBranch();
		switch(getProdString()) {
			case "funcDec funcList":
				nt = (NonTerminal) getComponent("funcDec");
				propagate(nt);
				nt.interpret();

				nt2 = (NonTerminal) getComponent("funcList");
				propagate(nt2);
				nt2.interpret();
			default:
		}
	}

	public void execute() {

	}
}