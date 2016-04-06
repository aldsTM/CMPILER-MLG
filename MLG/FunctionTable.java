import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class FunctionTable {
	private static FunctionTable instance = null;
	private ArrayList<HashMap<String,Functional>> maps;

	private FunctionTable (){
		maps = new ArrayList<HashMap<String,Functional>>();
		maps.add(new HashMap<String,Functional>());
	}

	public static FunctionTable instance(){
		if (instance == null){
			instance = new FunctionTable();
		}
		return instance;
	}

	public ArrayList<HashMap<String,Functional>> getMap(){
		return maps;
	}

	public Functional get(String funcName) {
		ArrayList<HashMap<String,Functional>> map = getMap();
		for(int i = 0; i < map.size(); i++) {
			Functional f = map.get(i).get(funcName);
			if( f != null ) {
				return f;
			}
		}
		return null;
	}

	public boolean register(String funcName, Functional func){
		ArrayList<HashMap<String,Functional>> map = getMap();
		Functional f = get(funcName);
		if ( f == null){
			map.get(0).put(funcName,func);
			return true;
		} else {
			return false;
		}
	}

}