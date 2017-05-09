package es.uniovi.asw;

import alb.util.console.Console;
import es.uniovi.asw.parser.ReadList;
import es.uniovi.asw.parser.SingletonParser;

/**
 * Main application
 * 
 * @author UNIVERSO SIMPSON O BARBARIE ☭
 *
 */
public class LoadUsers {

	public static void main(String... ruta) {
		final LoadUsers runner = new LoadUsers();
		runner.run();
	}

	private void run() {
		String ruta = Console.readString("Input name of the file (xlsx or txt) "
				+ "and desired output extension (pdf, docx or cons)"
				+ "\nExample: example.xlsx docx\n>");
		while (ruta.length() < 1) {
			ruta = Console.readString("Input name of the file (xlsx or txt) "
					+ "and desired output extension (pdf, docx or cons)"
					+ "\nExample: example.xlsx docx\n>");
		}

		String[] text = ruta.split(" ");
		ReadList rl = null;

		if (text.length < 2) {
			System.err.println("Invalid input");
			return;
		}

		if (text[0].contains(".xlsx")) {
			if (text[1].equals("pdf")) {
				rl = SingletonParser.getInstance().getPDFLetterExcelReadList();
			} else if (text[1].equals("docx")) {
				rl = SingletonParser.getInstance().getWordLetterExcelReadList();
			} else if (text[1].equals("cons")) {
				rl = SingletonParser.getInstance().getDefaultExcelReadList();
			}
		} else if (text[0].contains(".txt")) {
			if (text[1].equals("pdf")) {
				rl = SingletonParser.getInstance().getPDFLetterTxtReadList();
			} else if (text[1].equals("docx")) {
				rl = SingletonParser.getInstance().getWordLetterTxtReadList();
			} else if (text[1].equals("cons")) {
				rl = SingletonParser.getInstance().getDefaultTxtReadList();
			}
		}

		if (rl != null) {
			rl.parse(text[0]);
		} else {
			System.err.println("Invalid input");
		}
	}

}
