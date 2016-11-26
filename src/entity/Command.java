package entity;

public class Command {
	private String cmd;
	private int action;
	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public void JudgeAction() {
		if (getCmd().charAt(1) == 's')
			setAction(0);// simplify
		else if (getCmd().charAt(1) == 'd')
			setAction(1);// derivative
		else
			setAction(-1);// invalid
	}
}
