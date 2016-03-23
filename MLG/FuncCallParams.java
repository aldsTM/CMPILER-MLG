import java.util.ArrayList;

public class FuncCallParams extends NonTerminal {

	public FuncCallParams(String pattern) {
		super("funcCallParams",pattern);
	}

	public void interpret() throws Exception {
		NonTerminal nt,nt2;
		printBranch();
		switch(getProdString()) {
			case "expr":
				nt = (NonTerminal) getComponent("expr");
				propagate(nt);
				nt.interpret();
				break;
			case "expr , funcCallParams":
				nt = (NonTerminal) getComponent("expr");
				propagate(nt);
				nt.interpret();
				printIndent(",");

				nt2 = (NonTerminal) getComponent("funcCallParams");
				propagate(nt2);
				nt2.interpret();
				break;
			default:
		}
	}

	public void execute() {
		System.out.println("FuncCallParams");
	}
}