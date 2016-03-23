public class ArrDec extends NonTerminal {
	public ArrDec(String pattern) {
		super("arrDec",pattern);
	}

	public void interpret() throws Exception {
		NonTerminal nt;
		printBranch();
		nt = (NonTerminal) getComponent("arrDimens");
		propagate(nt);
		nt.interpret();
		put("val",nt);
	}

	public void execute() {
		System.out.println("You've declared an array, yay");
	}
}