import javax.script.ScriptException;

public class Driver {
	
	/*
	 * TODO:
	 * -Add elif
	 * -Add for/while
	 * -Arrays
	 * -Comments
	 */
	
	public static void main (String[] args) throws ScriptException {
		Parser p = new Parser(new Lexer().lex(new Input("test.ink").readFile()));
	}
}
