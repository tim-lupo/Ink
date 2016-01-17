import java.util.ArrayList;
import javax.script.ScriptException;

public class Variable {
	
	private String name;
	private Object value;
	private Dictionary dic;
	
	public Variable(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
