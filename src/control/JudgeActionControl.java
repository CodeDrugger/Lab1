package control;

import boundary.UserInput;
import entity.Command;
import entity.Expression;

public class JudgeActionControl {
	public int doJudgeAction(int seq, Command command, Expression expression,
			UserInput ui) {
		if (seq == 0) {
			command.setCmd(ui.getInput());
			command.JudgeAction();
			int action = command.getAction();
			expression.setAction(action);
			expression.setCmd(command.getCmd());
			return action;// 0:simplify 1:derivative -1:invalid
		} else {
			expression.setExp(ui.getInput());
			return expression.JudgeAndOgnize();// -1:invalid 2:valid
		}

	}
}
