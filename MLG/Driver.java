import java.io.*;

public class Driver {

	public static final boolean SHOW_TREE = true;
	public static PrintWriter pw;

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(
								new FileReader(
									new File(args[0])));
			String code = "";
			String s;
			do {
				s = br.readLine();
				if( s != null ) {
					code += s + "\n";
				}
			} while(s != null);

			br.close();

			Interpreter interpreter 
				= new Interpreter(new ConcreteTokenizer()
							,ConcreteNonTerminalFactory.instance(),code
							,"Alds.txt",true);

			pw = new PrintWriter(new FileWriter(new File("ParseTree.txt")));
			interpreter.interpret();
			pw.close();
			
		} catch(Exception e) {
			// if( e.getMessage() != null) {
			// 	System.out.println(e.getMessage());
			// } else {
				e.printStackTrace();
			// }
		}
	}

}