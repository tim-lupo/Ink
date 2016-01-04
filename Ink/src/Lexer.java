import java.util.ArrayList;

public class Lexer {
	
	public static void main (String[] args) {
		System.out.println(lex(new Input("test.ink").readFile()));
	}
	
	public static ArrayList<String> lex (String input) {

		char[] toklist = input.toCharArray();
		ArrayList<String> tokens = new ArrayList<String>();
		String toks = "";
		boolean isPara = false;
		boolean isQuot = false;
		boolean isMath = false;
		
		for (char tok : toklist) {
			if (tok == '(') {
				isPara = true;
			} else if (tok == ')') {
				isPara = false;
			} else if (tok == '"') {
				if (isQuot == false) { isQuot = true; }
				else { isQuot = false; }
			}
			
			if ((tok == '=')) { 
				if (toks == "") {
					tokens.add(String.valueOf(tok));
					toks = "";
				} else{
					tokens.add(toks);
					tokens.add(String.valueOf(tok));
					toks = "";
				}
				isMath = true;
			} else if (tok == '~') {
				isMath = false;
				if (toks != "") { tokens.add(toks); toks = ""; }
				tokens.add("<EOL>");
			} else if ((tok == '(') || (tok == '{')) {
				if (toks != "") {
					tokens.add(toks);
					toks = "";
					toks += tok;
				} else {
					toks += tok;
				}
			} else if ((tok != ' ') || (isPara == true) || (isQuot == true) || (isMath == true)) {
				toks += tok;
			}  else {	
				if (toks != "") { tokens.add(toks); }
				toks = "";
			}
		}
		return tokens;
	}
}
