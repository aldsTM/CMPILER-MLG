import java.util.ArrayList;

public class FuncCall extends NonTerminal {

	public FuncCall(String pattern) {
		super("funcCall",pattern);
	}

	public void interpret() throws Exception {
		NonTerminal nt;
		printBranch();
		switch(getProdString()) {
			case "IDENTIFIER ( funcCallParams )":

				put("IDENTIFIER",((Token)getComponent("IDENTIFIER")).token());
				printIndent(((Token)getComponent("IDENTIFIER")).token());
				put("lineNo",((Token)getComponent("IDENTIFIER")).lineNo());

				printIndent("(");

				FuncCallParams callParams;

				callParams = (FuncCallParams) getComponent("funcCallParams");
				propagate(callParams);
				callParams.interpret();

				printIndent(")");

				break;
			case "print ( expr )":
			case "println ( expr )":
				if (getProdString().contains("println")){
					printIndent("println");
				} else if (getProdString().contains("print")){
					printIndent("print");
				}
				printIndent("(");

				nt = (NonTerminal) getComponent("expr");
				propagate(nt);
				nt.interpret();
				put("line",nt);

				printIndent(")");
				break;
			case "println ( )":
				printIndent("println");
				printIndent("(");
				printIndent(")");
				break;
			case "scanf":
				nt = (NonTerminal) getComponent("scanf");
				propagate(nt);
				nt.interpret();
				put("line",nt);
			default:
		}
	}

	public void execute() {
		NonTerminal nt;
		switch(getProdString()) {
			case "IDENTIFIER ( funcCallParams )":
				SymbolTable st = SymbolTable.instance();
				FunctionTable ft = FunctionTable.instance();
				//get from singleton the function object
				Functional function = ft.get(getAsString("IDENTIFIER"));
				//getParams and getParamTypes
				String[] funcParamTypes = function.getFuncParamTypes();
				String[] funcParamNames = function.getFuncParams();

				FuncCallParams callParams = (FuncCallParams) getComponent("funcCallParams");

				callParams.execute();
				String [] funcCallParamTypes;
				Object[] funcCallParamVals;
				if (callParams.getProdString().length() > 0){
					funcCallParamTypes = callParams.getDataTypes();
					funcCallParamVals = callParams.getVals();
				} else {
					funcCallParamTypes = null;
					funcCallParamVals = null;
				}

				//declare and assign all passed parameters
				boolean isValidCall = true;
				
				// System.out.println("CALLING: " + getAsString("IDENTIFIER"));
				// System.out.println(funcParamTypes.length + " <= required, given => " + funcCallParamTypes.length);
				// for (int i = 0; i < funcParamTypes.length; i++){
				// 	System.out.println("required[" + i + "] = " + funcParamTypes[i] + " -> " + funcParamNames[i] );
				// }
				// for (int i = 0; i < funcCallParamTypes.length; i++){
				// 	System.out.println("given[" + i + "] = " + funcCallParamTypes[i] + " <- " + funcCallParamVals[i] );
				// } System.out.println();

				if (funcCallParamTypes != null && funcCallParamVals != null){
					if (funcParamTypes.length == funcCallParamTypes.length){
						for (int i = 0; i < funcParamTypes.length; i++){
							if (!funcParamTypes[i].equals(funcCallParamTypes[i])){
								isValidCall = false;
							}
						}
					} else {
						isValidCall = false;
					}
				}

				//pushFrame, pushContext
				st.pushFrame(getAsString("IDENTIFIER"));
				st.pushContext();

				if (isValidCall){
					if (funcParamTypes != null && funcParamNames != null){
						for (int i = 0; i < funcParamTypes.length; i++){
							if (st.declare(funcParamNames[i],funcCallParamTypes[i])){
								st.assign(funcParamNames[i],funcCallParamVals[i]);
							}
							
						}
					}
					//call run() on function
					function.run();
					// System.out.println("function return type = " + function.getReturnType());
					put("type",function.getReturnType());
					Object value = function.getReturnVal();
					put("val",value);
					// System.out.println("function returned " + value);
				} else {
					System.out.println("Type checking error: Invalid " +
										" parameters for function '" + 
										getAsString("IDENTIFIER") + "'!");
				}

				//popFrame
				st.popContext();
				st.popFrame();
				break;
			case "print ( expr )":
				nt = (NonTerminal) getAsObject("line");
				nt.execute();
				if (getAsObject("line") != null){
					System.out.print(getAsObject("line"));
				}
				break;
			case "println ( expr )":
				nt = (NonTerminal) getAsObject("line");
				nt.execute();
				if (getAsObject("line") != null){
					System.out.println(getAsObject("line"));	
				}
				break;
			case "println ( )":
				System.out.println();
				break;
			case "scanf":
				nt = (NonTerminal) getAsObject("line");
				nt.execute();
				break;
			default:
		}
	}
}