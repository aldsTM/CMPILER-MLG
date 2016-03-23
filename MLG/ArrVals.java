public class ArrVals extends NonTerminal {
	public ArrVals(String pattern) {
		super("arrVals",pattern);
	}

	public void interpret() throws Exception {
		NonTerminal nt, nt2;
		printBranch();
		switch(getProdString()){
			case "expr , arrVals":
				nt = (NonTerminal) getComponent("expr");
				propagate(nt);
				nt.interpret();
				printIndent(",");

				nt2 = (NonTerminal) getComponent("arrVals");
				propagate(nt2);
				nt2.interpret();
				break;
			case "expr":
				nt = (NonTerminal) getComponent("expr");
				propagate(nt);
				nt.interpret();
				break;
			default:
		}
	}

	public void execute() {
		System.out.println("ArrVals yeay");
	}
}