import java.util.ArrayList;

public class Code extends NonTerminal {
	private ArrayList<CodeSegment> codeSegments;

	public Code(String pattern) {
		super("code",pattern);
		codeSegments = new ArrayList<>();
	}

	public void interpret() throws Exception {
		CodeSegment nt;
		Code nt2;
		CodeSegment[] moreShit;
		printBranch();
		switch(getProdString()) {
			case "code_segment code":
				nt = (CodeSegment)getComponent("code_segment");
				propagate(nt);
				nt.interpret();
				if (nt != null){
					codeSegments.add(nt);
				}

				nt2 = (Code)getComponent("code");
				propagate(nt2);
				nt2.interpret();

				//moreShit = (CodeSegment[]) nt2.getAsArray("lines");
				moreShit = (CodeSegment[]) nt2.getCodes();
				for(CodeSegment temp: moreShit) {
					if (temp != null){
						codeSegments.add(temp);
					}
				}
				// CodeLine[] morecodes = (CodeLine[])code.getAsArray("lines");
				// for(CodeLine temp: morecodes) {
				// 	codes.add(temp);
				// }
				break;
				
				/* ArrayList<NonTerminal> subList 
					= (ArrayList<NonTerminal>)nt.getAsObject("list");
				for(NonTerminal x : subList) {
					cs.add(x);
				}
				put("list",cs); */
			default:

				break;
		}
		put("lines",this.getCodes());
	}


	public CodeSegment[] getCodes() {
		return codeSegments.toArray(new CodeSegment[1]);
	}


	public void execute() {
		/*ArrayList<NonTerminal> subList 
					= (ArrayList<NonTerminal>)getAsObject("list");
		for(NonTerminal x : subList) {
			x.execute();
		}*/
				
	}
}