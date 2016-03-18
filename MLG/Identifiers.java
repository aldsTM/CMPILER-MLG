import java.util.ArrayList;

public class Identifiers extends NonTerminal {
	ArrayList<ParseObject> varList;

	public Identifiers(String pattern) {
		super("identifiers",pattern);
		varList = new ArrayList<ParseObject>();
	}

	public void interpret() throws Exception {
		NonTerminal nt; 
		Token t;
		ParseObject[] varListArr;
		printBranch();
		switch(getProdString()) {
			case "IDENTIFIER , identifiers":
				varList.add(getComponent("IDENTIFIER"));
				printIndent(((Token)getComponent("IDENTIFIER")).token());

				printIndent(",");

				nt = (NonTerminal) getComponent("identifiers");
				propagate(nt);
				nt.interpret();

				varListArr = (ParseObject []) nt.getAsArray("varList");
				for (ParseObject po : varListArr){
					varList.add(po);
				}
				put("varList",varList.toArray(new ParseObject[1]));
				break;
			case "assignment , identifiers":
				varList.add(getComponent("assignment"));
				propagate((NonTerminal)getComponent("assignment"));
				varList.get(0).interpret();

				printIndent(",");

				nt = (NonTerminal) getComponent("identifiers");
				propagate(nt);
				nt.interpret();

				varListArr = (ParseObject []) nt.getAsArray("varList");
				for (ParseObject po : varListArr){
					varList.add(po);
				}
				put("varList",varList.toArray(new ParseObject[1]));
				break;
			case "IDENTIFIER":
				printIndent(((Token)getComponent("IDENTIFIER")).token());
				put("varList",new ParseObject[]{getComponent("IDENTIFIER")});
				//varList.add(getComponent("IDENTIFIER"));
				//put("varList",varList.toArray(new ParseObject[1]));

				//t = (Token) getComponent("IDENTIFIER");
				//String str = t.token();
				//put("varList",varList.toArray(new ParseObject[1]));
				//put("val",i);

				break;
			case "assignment":
				varList.add(getComponent("assignment"));
				propagate((NonTerminal)getComponent("assignment"));
				varList.get(0).interpret();
				put("varList",varList.toArray(new ParseObject[1]));
				break;
			default:
		}
	}

	public void execute() {

	}
}