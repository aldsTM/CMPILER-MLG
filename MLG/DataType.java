public class DataType extends NonTerminal {
	public DataType(String pattern) {
		super("dataType",pattern);
	}

	public void interpret() throws Exception {
		Token t;
		printBranch();
		printIndent(getProdString());
		switch(getProdString()) {
			case "mmr":
				t = (Token) getComponent("mmr");
				put("lineNo",t.lineNo());
				put("type","int");
				break;
			case "kdr":
				t = (Token) getComponent("kdr");
				put("lineNo",t.lineNo());
				put("type","float");
				break;
			case "char":
				t = (Token) getComponent("char");
				put("lineNo",t.lineNo());
				put("type","char");
				break;
			case "SAMPLETEXT":
				t = (Token) getComponent("SAMPLETEXT");
				put("lineNo",t.lineNo());
				put("type","string");
				break;
			case "isMLG":
				t = (Token) getComponent("isMLG");
				put("lineNo",t.lineNo());
				put("type","boolean");
				break;
			default:
		}
	}

	public void execute() {

	}
}