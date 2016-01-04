import java.util.ArrayList;

public class Parser {
	
	private static char[] symbols = {'(', ')', '{', '}', '=', '+', '-', '*', '/', '"', '\''};
	private static ArrayList<String> tokens = new Lexer().lex(new Input("test.ink").readFile());
	private static ArrayList<Variable> vars = new ArrayList<Variable>();
	private static ArrayList<Function> funcs = new ArrayList<Function>();
	
	public static void main (String[] args) {
		//System.out.println(tokens);
		parse(tokens);
	}
	
	public static void parse (ArrayList<String> tokens) {
		int i = 0; //position of index
		for (String tok : tokens) { //loop through the tokens
			if (tok.compareTo("<EOL>") == 0) { //if its the end of the line
				//System.out.println("end of line");
			} else if (tok.compareTo("var") == 0) { //if it finds a var declaration
				if ((isName(tokens.get(i+1)) == true) && (tokens.get(i+2).compareTo("=") == 0)) {
					Variable var = new Variable(tokens.get(i+1), tokens.get(i+3));
					vars.add(var);
				} else {
					System.out.println("INK ERROR: invalid variable declaration");;
				}
			} else if (tok.compareTo("func") == 0) { //if it finds a func declaration
				if ((isName(tokens.get(i+1)) == true) && (isParameter(tokens.get(i+2)) == true) && (tokens.get(i+3).compareTo("{") == 0)) {
					//Collect the arguments inside of the function
					ArrayList<String> contents = new ArrayList<String>();
					boolean isArg = true;
					for (int n=i+4; isArg == true; n++) {
						if (tokens.get(n).compareTo("}") == 0) { isArg = false; break; }
					}
					Function f = new Function(tokens.get(i+1), tokens.get(i+2), contents);
					funcs.add(f);
				} else {
					System.out.println("INK ERROR: invalid function declaration");
				}
			} else if (tok.compareTo("print") == 0) {
				System.out.println("dicks");
			}
			i++;
		}
	}
	
	//Check if valid name (no invalid characters in name)
	private static boolean isName (String value) {
		char[] toks = value.toCharArray();
		boolean valid = true;
		for (char tok : toks) {
			for (char symbol : symbols) {
				if (tok == symbol) {
					valid = false;
				}
			}
		}
		return valid;
	}
	
	//Check if valid parameter list
	private static boolean isParameter(String value) {
		char[] toks = value.toCharArray();
		boolean valid = true;
		if (toks[0] != '(' || toks[toks.length-1] != ')') {
			valid = false;
		}
		return valid;
	}
	
	//Parse parameter list
	private static ArrayList<String> getParameterVars(String value) {
		ArrayList<String> vars = new ArrayList<String>();
		char[] toklist = value.toCharArray();
		String toks = "";
		for (char tok : toklist) {
			System.out.println(toks);
			if (tok != ' ' && tok != ',') {
				toks += tok;
			} else {
				if (toks != "") {
					if (isName(toks) == true) {
						vars.add(toks);
					}
				}
				toks = "";
			}
		}
		return vars;
	}
}