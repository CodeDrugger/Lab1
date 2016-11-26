package control;

import entity.Expression;

public class OutputControl {
	public String doOutputControl(int state, Expression expression) {
		if (state >= 0 && state != 2) {
			return expression.doit();
		} else if (state == 2)
			return null;
		else
			return "Please check the input!";
	}
}
