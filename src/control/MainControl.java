package control;

import boundary.SysOutput;
import boundary.UserInput;
import entity.Command;
import entity.Expression;

public class MainControl {
	private static SysOutput so;
	private static UserInput ui;
	private static InputRecoControl irc;
	private static JudgeActionControl jac;
	private static OutputControl oc;
	private static Command command;
	private static Expression expression;

	private static void init() {
		so = new SysOutput();
		ui = new UserInput();
		irc = new InputRecoControl();
		jac = new JudgeActionControl();
		oc = new OutputControl();
		command = new Command();
		expression = new Expression();
	}

	public static void main(final String[] args) {
		init();
		while (true) {
			ui.doUserInput();
			so.doSysOutput(oc.doOutputControl(jac.doJudgeAction(
					irc.doInputReco(ui), command, expression, ui), expression));
		}

	}
}
