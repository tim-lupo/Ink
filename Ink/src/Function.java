import java.util.ArrayList;

public class Function {
	
	private String name;
	private String initialParam;
	private ArrayList<String> initialContents;
	
	public Function(String name, String initialParam, ArrayList<String> contents) {
		super();
		this.name = name;
		this.initialParam = initialParam;
		this.initialContents = contents;
	}

	public String getName() {
		return name;
	}
}
