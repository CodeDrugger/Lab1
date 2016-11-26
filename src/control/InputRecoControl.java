package control;

import boundary.UserInput;

public class InputRecoControl {

	public int doInputReco(UserInput ui) {
		if (ui.getInput().charAt(0) == '!')
			return 0;// command
		else
			return 1;// expression
	}
}
