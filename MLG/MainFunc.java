public class MainFunc extends NonTerminal {
	private Code code;

	public MainFunc(String pattern) {
		super("mainFunc",pattern);
	}

	public void interpret() throws Exception {
		printBranch();
		printIndent("PRESS");
		printIndent("START");
		printIndent("{");
		code = (Code)getComponent("code");
		propagate(code);
		code.interpret();
		printIndent("}");
	}

	public void execute() {
		SymbolTable.instance().pushContext();
		CodeSegment[] codes = code.getCodes();
		boolean fail = false;
		for(CodeSegment cs: codes) {
			switch( cs.getType() ) {
				case "return":
					break;
				default:
					cs.execute();
			}
			if( fail ) {
				break;
			}
		}
	}
}