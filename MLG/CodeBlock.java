import java.util.ArrayList;

public class CodeBlock extends NonTerminal {
	private ArrayList<CodeSegment> codeSegments;

	public CodeBlock(String pattern) {
		super("code_block",pattern);
		codeSegments = new ArrayList<>();
	}

	public void interpret() throws Exception {
		CodeSegment cs;
		Code c;
		CodeSegment[] moreShit;
		printBranch();
		switch(getProdString()) {
			case "non_cond_code_segment":

				cs = (CodeSegment) getComponent("non_cond_code_segment");
				propagate(cs);
				cs.interpret();
				put("line",cs);
				codeSegments.add(cs);
				break;
			case "{ code }":
				printIndent("{");
				c = (Code) getComponent("code");
				propagate(c);
				c.interpret();
				printIndent("}");
				//moreShit = (CodeSegment[]) c.getAsArray("lines");
				moreShit = (CodeSegment[]) c.getCodes();
				for(CodeSegment temp: moreShit) {
					if (temp != null){
						codeSegments.add(temp);
					}
				}

				// CodeLine[] codeList = (CodeLine[])c.getAsArray("lines");
				// for( CodeLine line: codeList ) {
				// 	codes.add(line);
				// }

				put("line",c);
				break;
			default:
		}
	}

	public CodeSegment[] getCodes() {
		return codeSegments.toArray(new CodeSegment[1]);
	}

	public void execute() {
	}
}