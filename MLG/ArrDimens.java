public class ArrDimens extends NonTerminal {
	public ArrDimens(String pattern) {
		super("arrDimens",pattern);
	}

	public void interpret() throws Exception {
		NonTerminal nt, nt2;
		printBranch();
		switch(getProdString()){
			case "[ arrVals ] arrDimens":
				printIndent("[");
				nt = (NonTerminal) getComponent("arrVals");
				propagate(nt);
				nt.interpret();
				printIndent("]");

				nt2 = (NonTerminal) getComponent("arrDimens");
				propagate(nt2);
				nt2.interpret();
				break;
			case "[ arrVals ]":
				printIndent("[");
				nt = (NonTerminal) getComponent("arrVals");
				propagate(nt);
				nt.interpret();
				printIndent("]");
			default:
		}
	}

	public void execute() {
	}
}