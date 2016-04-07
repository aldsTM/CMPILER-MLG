import java.util.ArrayList;

public class FuncDec extends NonTerminal implements Functional {
	private ArrayList<CodeSegment> codeSegments;
	private String[] funcParamTypes;
	private String[] funcParams;
	private String returnType;
	private Object returnVal;

	public FuncDec(String pattern) {
		super("funcDec",pattern);
		codeSegments = new ArrayList<>();
		funcParamTypes = null;
		funcParams = null;
		returnType = null;
		returnVal = null;
	}

	public void interpret() throws Exception {
		FunctionTable ft = FunctionTable.instance();
		NonTerminal nt;
		FuncParams nt2;
		Code c;
		CodeSegment[] moreShit;
		printBranch();
		switch(getProdString()) {
			case "dataType IDENTIFIER ( funcParams ) { code }":
				nt = (NonTerminal) getComponent("dataType");
				propagate(nt);
				nt.interpret();
				put("type",nt.getAsString("type"));

				printIndent(((Token)getComponent("IDENTIFIER")).token());
				put("varList",new ParseObject[]{getComponent("IDENTIFIER")});

				printIndent("(");
				nt2 = (FuncParams) getComponent("funcParams");
				propagate(nt2);
				nt2.interpret();
				printIndent(")");

				funcParamTypes = (String[]) nt2.getDataTypes();
				funcParams = (String[]) nt2.getVarNames();

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
				put("lines",c);

				if (ft.register(((Token)getComponent("IDENTIFIER")).token(),(FuncDec)this)){
					// System.out.println("nice! registered " + ((Token)getComponent("IDENTIFIER")).token());
				}

				break;
			case "dataType IDENTIFIER ( ) { code }":
				nt = (NonTerminal) getComponent("dataType");
				propagate(nt);
				nt.interpret();
				put("type",nt.getAsString("type"));

				printIndent(((Token)getComponent("IDENTIFIER")).token());
				put("varList",new ParseObject[]{getComponent("IDENTIFIER")});

				printIndent("(");
				printIndent(")");

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
				put("lines",c);

				if (ft.register(((Token)getComponent("IDENTIFIER")).token(),(FuncDec)this)){
					// System.out.println("nice! registered " + ((Token)getComponent("IDENTIFIER")).token());
				}

				break;
			default:
		}
	}

	public void execute() {

	}

	public void run(){
		boolean stop = false;
		for(CodeSegment cl: codeSegments) {
			switch( cl.getType() ) {
				case "return":
					cl.execute();
					put("type",cl.getAsString("type"));
					switch(cl.getAsString("type")){
						case "int":
							put("val",cl.getAsInt("val"));
							break;
						case "float":
							put("val",cl.getAsFloat("val"));
							break;
						case "char":
							put("val",cl.getAsString("val").charAt(0));
							break;
						case "string":
							put("val",cl.getAsString("val"));
							break;
						case "boolean":
							put("val",cl.getAsBoolean("val"));
							break;
					}
					this.returnType = cl.getAsString("type");
					this.returnVal = cl.getAsObject("val");
					stop = true;
					break;
				default:
					cl.execute();
					// Object status;
					// if( (status = cl.getAsObject("status")) != null ) {
					// 	switch(status.toString()) {
					// 	 	case "return":
					// 	 		put("status","return");
					// 	 		put("lineNo",cl.getAsInt("lineNo"));
					// 			break;
					// 	 }
					// }
			}
			if( stop ) {
				break;
			}
		}
	}

	public String[] getFuncParamTypes(){
		return funcParamTypes;
	}

	public String[] getFuncParams(){
		return funcParams;
	}

	public String getReturnType(){
		return returnType;
	}

	public Object getReturnVal(){
		return returnVal;
	}

}