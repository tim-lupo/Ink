import javax.script.ScriptException;

public class Driver {
	
	public static void main (String[] args) throws ScriptException {
		Parser p = new Parser(new Lexer(new Input("test.ink").readFile()).lex());
	}
}
