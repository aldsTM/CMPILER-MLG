import java.util.ArrayList;

public class FuncCallParams extends NonTerminal {
	ArrayList<String> dataTypes;
	ArrayList<Object> vals;

	public FuncCallParams(String pattern) {
		super("funcCallParams",pattern);
		dataTypes = new ArrayList<String>();
		vals = new ArrayList<Object>();
	}

	public void interpret() throws Exception {
		NonTerminal nt;
		FuncCallParams nt2;
		printBranch();
		switch(getProdString()) {
			case "expr":
				nt = (NonTerminal) getComponent("expr");
				propagate(nt);
				nt.interpret();
				put("expr",nt);
				break;
			case "expr , funcCallParams":
				nt = (NonTerminal) getComponent("expr");
				propagate(nt);
				nt.interpret();
				put("expr",nt);
				printIndent(",");

				nt2 = (FuncCallParams) getComponent("funcCallParams");
				propagate(nt2);
				nt2.interpret();
				put("moreExprs",nt2);
				break;
			default:
		}
	}

	public String[] getDataTypes(){
		return dataTypes.toArray(new String[1]);
	}

	public Object[] getVals(){
		return vals.toArray(new Object[1]);
	}

	public void execute() {
		dataTypes.clear();
		vals.clear();
		NonTerminal nt;
		FuncCallParams nt2;
		String[] dataTypesArr;
		Object[] valsArr;
		switch(getProdString()){
			case "expr":
				nt = (NonTerminal) getAsObject("expr");
				nt.execute();
				dataTypes.add(nt.getAsString("type"));
				vals.add(nt.getAsObject("val"));
				break;
			case "expr , funcCallParams":
				nt = (NonTerminal) getAsObject("expr");
				nt.execute();
				dataTypes.add(nt.getAsString("type"));
				vals.add(nt.getAsObject("val"));

				nt2 = (FuncCallParams) getAsObject("moreExprs");
				nt2.execute();
				dataTypesArr = (String[]) nt2.getDataTypes();
				for (String temp : dataTypesArr){
					dataTypes.add(temp);
				}
				valsArr = (Object[]) nt2.getVals();
				for (Object tempVal : valsArr){
					vals.add(tempVal);
				}

				break;
		}
	}
}