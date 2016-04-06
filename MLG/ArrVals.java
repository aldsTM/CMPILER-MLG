import java.util.ArrayList;

public class ArrVals extends NonTerminal {
	ArrayList<ParseObject> exprList;

	public ArrVals(String pattern) {
		super("arrVals",pattern);
		exprList = new ArrayList<ParseObject>();
	}

	public void interpret() throws Exception {
		NonTerminal nt, nt2;
		printBranch();
		switch(getProdString()){
			case "expr , arrVals":
				nt = (NonTerminal) getComponent("expr");
				propagate(nt);
				nt.interpret();
				put("line",nt);

				printIndent(",");

				nt2 = (NonTerminal) getComponent("arrVals");
				propagate(nt2);
				nt2.interpret();
				put("line2",nt2);
				
				break;
			case "expr":
				nt = (NonTerminal) getComponent("expr");
				propagate(nt);
				nt.interpret();
				put("line",nt);
				break;				
			default:
		}
	}

	public void execute() {
		NonTerminal expr, arrVals;
		ParseObject[] exprListArr;
		switch(getProdString()){
			case "expr , arrVals":
				expr = (NonTerminal) getAsObject("line");
				expr.execute();
				put("type",expr.getAsString("type"));
				switch (expr.getAsString("type")){
					case "int":
						put("val",expr.getAsInt("val"));
						break;
					case "float":
						put("val",expr.getAsFloat("val"));
						break;
					case "char":
						put("val",expr.getAsString("val").charAt(0));
						break;
					case "string":
						put("val",expr.getAsString("val"));
						break;
					case "boolean":
						put("val",expr.getAsBoolean("val"));
						break;
				}
				exprList.add(expr);

				arrVals = (NonTerminal) getAsObject("line2");
				arrVals.execute();
				exprListArr = (ParseObject []) arrVals.getAsArray("exprList");
				for (ParseObject po : exprListArr){
					exprList.add(po);
				}

				put("exprList",exprList.toArray(new ParseObject[1]));
				break;
			case "expr":
				expr = (NonTerminal) getAsObject("line");
				expr.execute();
				put("type",expr.getAsString("type"));
				switch (expr.getAsString("type")){
					case "int":
						put("val",expr.getAsInt("val"));
						break;
					case "float":
						put("val",expr.getAsFloat("val"));
						break;
					case "char":
						put("val",expr.getAsString("val").charAt(0));
						break;
					case "string":
						put("val",expr.getAsString("val"));
						break;
					case "boolean":
						put("val",expr.getAsBoolean("val"));
						break;
				}
				put("exprList",new ParseObject[]{expr});
				break;				
			default:
		}
		
	}
}