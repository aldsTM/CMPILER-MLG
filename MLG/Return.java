public class Return extends NonTerminal {
	public Return(String pattern) {
		super("return",pattern);
	}

	public void interpret() throws Exception {
		printBranch();
		printIndent("GGWP");
		NonTerminal nt = (NonTerminal) getComponent("expr");
		propagate(nt);
		nt.interpret();
		put("line",nt);
		put("status","return");
	}

	public void execute() {
		NonTerminal nt = (NonTerminal) getAsObject("line");
		nt.execute();
		put("type",nt.getAsString("type"));
		switch(nt.getAsString("type")){
			case "int":
				put("val",nt.getAsInt("val"));
				break;
			case "float":
				put("val",nt.getAsFloat("val"));
				break;
			case "char":
				put("val",nt.getAsString("val").charAt(0));
				break;
			case "string":
				put("val",nt.getAsString("val"));
				break;
			case "boolean":
				put("val",nt.getAsBoolean("val"));
				break;
		}

	}
}