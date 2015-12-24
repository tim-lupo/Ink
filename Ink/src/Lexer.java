import java.util.ArrayList;

public class Lexer {
	
	public static ArrayList<String> lex (String input) {

		char[] toklist = input.toCharArray();
		ArrayList<String> tokens = new ArrayList<String>();
		String toks = "";
		
		for (char tok : toklist) {
			if (tok != ' ') {
				toks += tok;
			} else {
				if (toks != "") { tokens.add(toks); }
				toks = "";
			}
		}
		return tokens;
	}
}