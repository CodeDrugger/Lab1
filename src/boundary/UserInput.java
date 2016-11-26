package boundary;

import java.util.Scanner;

public class UserInput {
	private String input;

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
	public void doUserInput() {
		System.out.println(">");
		Scanner inPut = new Scanner(System.in);
		setInput(inPut.nextLine());
		// inPut.close();
	}
}
