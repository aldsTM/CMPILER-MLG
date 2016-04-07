import java.util.ArrayList;

public class FuncParams extends NonTerminal {
	ArrayList<ParseObject> varList;
	ArrayList<String> dataTypes;
	ArrayList<String> varNames;

	public FuncParams(String pattern) {
		super("funcParams",pattern);
		varList = new ArrayList<ParseObject>();
		dataTypes = new ArrayList<String>();
		varNames = new ArrayList<String>();
	}

	public void interpret() throws Exception {
		NonTerminal nt;
		FuncParams nt2;
		String[] dataTypeArr, varNameArr;
		printBranch();
		switch(getProdString()) {
			case "dataType IDENTIFIER , funcParams":
				nt = (NonTerminal) getComponent("dataType");
				propagate(nt);
				nt.interpret();
				put("type",nt.getAsString("type"));
				dataTypes.add(nt.getAsString("type"));

				printIndent(((Token)getComponent("IDENTIFIER")).token());
				put("varList",new ParseObject[]{getComponent("IDENTIFIER")});
				varNames.add(((Token)getComponent("IDENTIFIER")).token());

				printIndent(",");

				nt2 = (FuncParams) getComponent("funcParams");
				propagate(nt2);
				nt2.interpret();
				dataTypeArr = (String []) nt2.getDataTypes();
				for (String temp : dataTypeArr){
					dataTypes.add(temp);
				}
				varNameArr = (String []) nt2.getVarNames();
				for (String tempVar : varNameArr){
					varNames.add(tempVar);
				}

				break;
			case "dataType IDENTIFIER":

				nt = (NonTerminal) getComponent("dataType");
				propagate(nt);
				nt.interpret();
				put("type",nt.getAsString("type"));
				dataTypes.add(nt.getAsString("type"));

				printIndent(((Token)getComponent("IDENTIFIER")).token());
				put("varList",new ParseObject[]{getComponent("IDENTIFIER")});
				varNames.add(((Token)getComponent("IDENTIFIER")).token());

				break;
			default:
		}
	}

	public String[] getDataTypes(){
		return dataTypes.toArray(new String[1]);
	}

	public String[] getVarNames(){
		return varNames.toArray(new String[1]);
	}

	public void execute() {

	}
}