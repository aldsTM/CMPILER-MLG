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
			case "constDec":
				return new ConstDec(pattern);
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
			case "arrIndex":
				return new ArrIndex(pattern);
			case "literal":
				return new Literal(pattern);
			case "literal2":
				return new Literal2(pattern);
			case "literal3":
				return new Literal3(pattern);
			case "funcDec":
				return new FuncDec(pattern);
			case "funcParams":
				return new FuncParams(pattern);
			case "funcCall":
				return new FuncCall(pattern);
			case "scanf":
				return new ScanF(pattern);
			case "funcCallParams":
				return new FuncCallParams(pattern);
			case "comparative":
				return new Comparative(pattern);
			case "comparative2":
				return new Comparative2(pattern);
			case "comparative3":
				return new Comparative3(pattern);
			case "comparative4":
				return new Comparative4(pattern);
			case "code_block":
				return new CodeBlock(pattern);
			case "conditional":
				return new Conditional(pattern);
			case "m":
				return new M(pattern);
			case "loop":
				return new Loop(pattern);
			case "arrDec":
				return new ArrDec(pattern);
			case "arrAssignment":
				return new ArrAssignment(pattern);
			case "arrSize":
				return new ArrSize(pattern);
			// only activate if support for multi-dimensional arrays is intended
			// case "arrValues":
			// 	return new ArrValues(pattern);
			case "arrVals":
				return new ArrVals(pattern);
			default:
				return null;
		}
	}
}