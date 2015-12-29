import java.util.ArrayList;

public class Function {
	
	private String name;
	public ArrayList<String> parameterVar;
	public ArrayList<Object> parameterValue;
	public ArrayList<String> contents;
	
	public Function(String name, ArrayList<String> parameterVar, ArrayList<Object> parameterValue, ArrayList<String> contents) {
		super();
		this.name = name;
		this.parameterVar = parameterVar;
		this.parameterValue = parameterValue;
		this.contents = contents;
	}

	public String getName() {
		return name;
	}
}
