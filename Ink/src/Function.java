import java.util.ArrayList;

public class Function {
	
	private String name;
	public ArrayList<Object> parameters;
	public ArrayList<String> contents;
	
	public Function(String name, ArrayList<Object> parameters, ArrayList<String> contents) {
		super();
		this.name = name;
		this.parameters = parameters;
		this.contents = contents;
	}

	public String getName() {
		return name;
	}
}