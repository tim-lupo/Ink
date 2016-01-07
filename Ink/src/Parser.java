import java.util.ArrayList;

import javax.script.ScriptException;

public class Parser {
	
	private char[] symbols = {'(', ')', '{', '}', '=', '+', '-', '*', '/', '"', '\''};
	private ArrayList<String> tokens;
	private ArrayList<Variable> vars = new ArrayList<Variable>();
	private ArrayList<Function> funcs = new ArrayList<Function>();
	
	public Parser(ArrayList<String> tokens) throws ScriptException {
		super();
		this.tokens = tokens;
		parse(tokens);
	}

	public void parse (ArrayList<String> tokens) throws ScriptException {
		int i = 0; //position of index
		for (String tok : tokens) { //loop through the tokens
			if (tok.compareTo("<EOL>") == 0) { //if its the end of the line
				//System.out.println("end of line");
			} else if (tok.toLowerCase().compareTo("var") == 0) { //if it finds a var declaration
				if ((isName(tokens.get(i+1)) == true) && (tokens.get(i+2).compareTo("=") == 0)) {
					Variable var = new Variable(tokens.get(i+1), tokens.get(i+3));
					vars.add(var);
				} else {
					System.out.println("INK ERROR: invalid variable declaration");;
				}
			} else if (tok.toLowerCase().compareTo("func") == 0) { //if it finds a func declaration
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
				String value = tokens.get(i+1).substring(1, tokens.get(i+1).length()-1);
				for (final Variable var : vars) {
		            if (var.getName().equals(value)) {
		                var.setValue(reparseVar(var.getValue()+"~"));
		                System.out.println(var.evalValue());
		            }
				}
			}
			i++;
		}
	}
	
	//Check if valid name (no invalid characters in name)
	private boolean isName (String value) {
		char[] toks = value.toCharArray();
		for (char tok : toks) {
			for (char symbol : symbols) {
				if (tok == symbol || (Character.isDigit(toks[0])) == true) {
					return false;
				}
			}
		}
		return true;
	}
	
	//Check if variable
	private boolean isVariable (String name) {
		for (final Variable var : vars) {
            if (var.getName().equals(name)) {
                return true;
            }
		}
		return false;
	}
	
	//Check if valid parameter list
	private boolean isParameter(String value) {
		char[] toks = value.toCharArray();
		if (toks[0] != '(' || toks[toks.length-1] != ')') {
			return false;
		}
		return true;
	}
	
	//Parse parameter list
	private ArrayList<String> getParameterVars(String value) {
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
	
	public String reparseVar(String value) throws ScriptException {
		//loop through lexed answer, replace all variables with their value, reconcatinate and evaluate
		char[] toklist = value.toString().toCharArray();
		ArrayList<String> tokens = new ArrayList<String>();
		String toks = "";
		for (char tok : toklist) {
			if (tok == '+' || tok == '-' || tok == '/' || tok == '*') {
				tokens.add(toks.replace(" ", ""));
				toks = "";
				toks += tok;
				tokens.add(toks.replace(" ", ""));
				toks = "";
			} else if (tok == '~') {
				if (toks != "") { tokens.add(toks.replace(" ", "")); toks = ""; }
			} else {
				toks += tok;
			}
		}
		for (String tok : tokens) {
			for (Variable var : vars) {
				if (tokens.contains(var.getName())) {
					tokens.set(tokens.indexOf(var.getName()), (String) var.getValue());
				}
			}
		}
		String reparsed = "";
		for (String tok : tokens) {
			reparsed += tok;
		}
		return reparsed;
	}
}