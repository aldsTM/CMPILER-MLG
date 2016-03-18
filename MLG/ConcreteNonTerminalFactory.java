public class ConcreteNonTerminalFactory implements NonTerminalFactory {
	private static NonTerminalFactory instance = null;

	private ConcreteNonTerminalFactory() {}

	public static NonTerminalFactory instance() {
		if(instance == null ) {
			instance = new ConcreteNonTerminalFactory();
		}
        return instance;
	}

	public NonTerminal getNonTerminal(String type, String pattern) {
		switch(type) {
			case "main":
				return new Main(pattern);
			case "mainFunc":
				return new MainFunc(pattern);
			case "funcList":
				return new FuncList(pattern);
			case "code":
				return new Code(pattern);
			case "non_cond_code_segment":
				return new NonCondCodeSegment(pattern);
			case "code_segment":
				return new CodeSegment(pattern);
			case "varDec":
				return new VarDec(pattern);
			case "dataType":
				return new DataType(pattern);
			case "identifiers":
				return new Identifiers(pattern);
			case "assignment":
				return new Assignment(pattern);
			case "expr":
				return new Expr(pattern);
			case "expr2":
				return new Expr2(pattern);
			case "expr3":
				return new Expr3(pattern);
			case "expr4":
				return new Expr4(pattern);
			case "expr5":
				return new Expr5(pattern);
			case "literal":
				return new Literal(pattern);
			case "literal2":
				return new Literal2(pattern);
			case "literal3":
				return new Literal3(pattern);
			case "funcDec":
				//return new funcDec(pattern);
				return null;
			case "funcParams":
				//return new funcParams(pattern);
				return null;
			case "funcCall":
				return new FuncCall(pattern);
			case "funcCallParams":
				//return new funcCallParams(pattern);
				return null;
			case "comparative":
				return new Comparative(pattern);
			case "comparative2":
				return new Comparative2(pattern);
			case "comparative3":
				return new Comparative3(pattern);
			case "comparative4":
				return new Comparative4(pattern);
			case "code_block":
				//return new code_block(pattern);
				return null;
			case "conditional":
				//return new conditional(pattern);
				return null;
			case "loop":
				//return new loop(pattern);
				return null;
			case "whileLoop":
				//return new whileLoop(pattern);
				return null;
			case "doWhileLoop":
				//return new doWhileLoop(pattern);
				return null;
			case "forLoop":
				//return new forLoop(pattern);
				return null;
			case "arrDec":
				//return new arrDec(pattern);
				return null;
			case "arrIndex":
				//return new arrIndex(pattern);
				return null;
			case "arrAssignment":
				//return new arrAssignment(pattern);
				return null;
			default:
				return null;
		}
	}
}