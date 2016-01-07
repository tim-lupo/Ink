import java.util.ArrayList;
import javax.script.ScriptException;

public class Variable {
	
	private String name;
	private Object value;
	
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
	
	public String evalValue() throws ScriptException {
		System.out.println("NAME: " + name + " | VALUE: " + value);
		Value val = new Value();
		Object obj = val.evaluate(value.toString());
		String str = obj.toString();
		setValue(str);
		return str;
	}
}
