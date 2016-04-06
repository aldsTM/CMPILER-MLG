import java.util.ArrayList;

public class FuncList extends NonTerminal {
	ArrayList<FuncDec> funcDecList;

	public FuncList(String pattern) {
		super("funcList",pattern);
		funcDecList = new ArrayList<FuncDec>();
	}

	public void interpret() throws Exception {
		NonTerminal nt;
		FuncList func2;
		FuncDec[] funcDecArr;
		printBranch();
		switch(getProdString()) {
			case "funcDec funcList":
				funcDecList.add((FuncDec)getComponent("funcDec"));
				nt = (NonTerminal) getComponent("funcDec");
				propagate(nt);
				nt.interpret();
				put("line",nt);

				func2 = (FuncList) getComponent("funcList");
				propagate(func2);
				func2.interpret();
				put("line2",func2);

				funcDecArr = (FuncDec[]) func2.getList();
				for (FuncDec po : funcDecArr){
					funcDecList.add(po);
				}

				put("funcDecList",this.getList());
				break;
			default:
		}
	}

	public FuncDec[] getList(){
		return funcDecList.toArray(new FuncDec[1]);
	}

	public void execute() {

	}
}