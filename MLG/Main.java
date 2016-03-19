public class Main extends NonTerminal {
	private MainFunc main;
	private FuncList funcList;

	public Main(String pattern) {
		super("main",pattern);
	}

	public void interpret() throws Exception {
		// ParseObject funcs = getComponent("func_decs");
		// funcs.interpret();
		put("indent",0);
		printBranch();
		main = (MainFunc)getComponent("mainFunc");
		propagate(main);
		main.interpret();
		funcList = (FuncList)getComponent("funcList");
		propagate(funcList);
		funcList.interpret();
	}

	public void execute() {
		main.execute();
		funcList.execute();
	}
}