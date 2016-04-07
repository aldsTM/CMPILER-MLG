import java.io.*;
import java.util.Scanner;

public class Driver {

	public static final boolean SHOW_TREE = true;
	public static boolean CHECK_CFG = false;
	public static boolean LINE_PER_LINE = false;
	public static PrintWriter pw;

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(
									new FileReader(
										new File(args[args.length-1])));

			if (args.length > 1){
				for (int i = 0; i < args.length; i++){
					if (args[i].equalsIgnoreCase("-l")){
						LINE_PER_LINE = true;
					}
					if (args[i].equalsIgnoreCase("-cfg")){
						CHECK_CFG = true;
					}
				}
			}

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
							,"CFG.txt",CHECK_CFG);

			pw = new PrintWriter(new FileWriter(new File("ParseTree.txt")));
			interpreter.interpret();
			pw.close();
			if (LINE_PER_LINE){
				cls();
			}
			
		} catch(Exception e) {
			e.printStackTrace();	
		}
	}

	public static void cls(){
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("win") >= 0){
			try {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} catch (Exception e){
				System.out.println(e.getMessage());
			}
		}
	}
}