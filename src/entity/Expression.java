package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Expression {
	private String exp;
	private String cmd;
	private int action;
	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public int JudgeAndOgnize() {
		setExp(getExp().replace(" ", ""));
		setExp(getExp().replace("	", ""));
		char c;
		for (int i = 0; i < getExp().length(); i++) {
			c = getExp().charAt(i);
			if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || c == '+'
					|| c == '*' || c == '-')) {
				setExp("");
				return -1;// invalid
			}
		}
		return 2;// valid
	}

	private String simplify()// 表达式是getExp(),命令是getCmd()
	{
		return "";
	}

	public String doit() {
		if (getAction() == 0)
			return simplify();
		else if (getAction() == 1)
			return derivative();
		return null;
	}

	private String unitsort(final String substr) {
		String sorted = "";
		List<String> list = new ArrayList<String>();
		String unit = "";
		int prepos = 0;
		for (int i = 0; i < substr.length(); i++) {
			if (substr.charAt(i) == '*' || i + 1 == substr.length()) {
				if (i + 1 == substr.length()) { // 寰楀埌鏈�鍚庝竴涓猽nit
					unit = substr.substring(prepos, i + 1);
					prepos = 0;
				} else {
					unit = substr.substring(prepos, i);
					prepos = i + 1;
				}
				list.add(unit);
			}
		}
		list.sort(null);
		for (int i = 0; i < list.size(); i++) {
			sorted += list.get(i);
			sorted += "*";
		}
		if (!"".equals(sorted)) {
			sorted = sorted.substring(0, sorted.length() - 1);
		}
		return sorted;
	}

	private String reorganize(final String result) {
		int prepos = 0;
		int j = 0;
		int num;
		boolean isnum = false;
		boolean negative = false;
		String neweq = "";
		String temp;
		String nstr = "";
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < result.length(); i++) {
			if ((result.charAt(i) == '+' || result.charAt(i) == '-'
					|| i + 1 == result.length())) {
				if (i + 1 == result.length()) {
					temp = result.substring(prepos, i + 1);
					prepos = 0;
				} else {
					temp = result.substring(prepos, i);
					prepos = i + 1;
				}
				for (j = 0; j <= temp.length() - 1
						&& temp.charAt(j) != '*'; j++) {
					if ((temp.charAt(j) >= '0' && temp.charAt(j) <= '9')) {
						isnum = true;
					}
				}
				if (isnum) {
					num = Integer.parseInt(temp.substring(0, j));
					if (j + 1 < temp.length()) {
						temp = temp.substring(j + 1);
					} else {
						temp = "";
					}
				} else {
					num = 1;
				}
				if (negative) {
					num *= -1;
					negative = false;
				}
				temp = unitsort(temp);
				if (map.containsKey(temp)) {
					num += map.get(temp);
					map.put(temp, num);
				} else {
					map.put(temp, num);
				}
				j = 0;
				isnum = false;
				if (result.charAt(i) == '-') {
					negative = true;
				}
			}
		}
		Iterator<Entry<String, Integer>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Integer> entry = iter
					.next();
			String str = entry.getKey().toString();
			Integer n = entry.getValue();
			if (n > 0 && n != 1) {
				nstr = "+" + n.toString();
				neweq += nstr;
				if (!"".equals(str)) {
					neweq += "*";
					neweq += str;
				}
			} else if (n < 0) {
				nstr = n.toString();
				neweq += nstr;
				if (!"".equals(str)) {
					neweq += "*";
					neweq += str;
				}
			} else if (n == 1) {
				if ("".equals(str)) {
					nstr = "+" + n.toString();
					neweq += nstr;
				} else {
					str = "+" + str;
					neweq += str;
				}
			}
		}
		if (neweq.length() == 0) {
			neweq = "0";
		}
		if (neweq.length() != 0 && neweq.charAt(0) == '+') {
			neweq = neweq.substring(1);
		}
		return neweq;
	}

	private String derivative() {
		int times = 0; // 次数
		String var = null; // 要求导的变量
		for (int i = 0; i < getCmd().length(); i++) {
			if (getCmd().charAt(i) == 'd') {
				times++;
			}
			if (times == 2) {
				times = 0;
				var = getCmd().substring(i + 1, getCmd().length());
				break;
			}
		}
		String substr = null, unit = null; // 将整个式子按+分割后的串；将substr按*分割后的串
		int preposMain = 0;
		int preposSub = 0; // 分割整个式子时前一分割位置的标记；分割substr时前一分割位置的标记
		int coe = 1, temp; // substr的系数；临时变量
		int positive = 1; // // ‘+’、‘-’号标记，1：+ 0：- 2：空
		String result = "", tem = ""; // 最终求导的结果；临时变量
		boolean eHasVar = false, uHasVar = false; // 整个式子中是否包含var；unit是否是var
		for (int i = 0; i < getExp().length(); i++) {
			// 按加减号分割字符串
			if ((getExp().charAt(i) == '+' || getExp().charAt(i) == '-'
					|| i + 1 == getExp().length()) && (i != 0)) {
				if (i + 1 == getExp().length()) { // 得到最后一个substr
					substr = getExp().substring(preposMain, i + 1);
					preposMain = 0;
				} else {
					substr = getExp().substring(preposMain, i);
					preposMain = i + 1;
				}
				if (getExp().charAt(i) == '+') {
					positive = 1;
				} else if (getExp().charAt(i) == '-') {
					positive = 0;
				} else {
					positive = 2;
				}
				for (int j = 0; j < substr.length(); j++) { // 按乘号分割子字符串
					if (substr.charAt(j) == '*' || j + 1 == substr.length()) {
						if (j + 1 == substr.length()) { // 得到最后一个unit
							unit = substr.substring(preposSub, j + 1);
							preposSub = 0;
						} else {
							unit = substr.substring(preposSub, j);
							preposSub = j + 1;
						}
						if (unit.charAt(0) >= '0' && unit.charAt(0) <= '9') { // 数字
							temp = Integer.parseInt(unit);
							coe *= temp;
						} else if (unit.equals(var)) { // var
							times++;
							eHasVar = true;
							uHasVar = true;
						} else { // 其他
							tem += unit;
							tem += "*";
						}
					}
				}
				if (times > 0) { // substr的次数大于0
					coe *= times;
					if (coe == 1) {
						if ("".equals(tem)) {
							result += "1";
						} else {

							tem = tem.substring(0, tem.length() - 1);
							result += tem;
						}
					} else {
						result += String.valueOf(coe);
						if (!"".equals(tem)) {
							result += "*";
							tem = tem.substring(0, tem.length() - 1);
							result += tem;
						}
					}
					if (times != 1) {
						for (int k = times; k > 1; k--) {
							result += "*";
							result += var;
						}
					}
				}
				times = 0;
				coe = 1;
				tem = "";
				if (positive == 1 && uHasVar) {
					result += "+";
				} else if (positive == 0 && !"".equals(result)
						&& result.charAt(result.length() - 1) != '-'
						&& result.charAt(result.length() - 1) != '+') {
					result += "-";
				}
				uHasVar = false;
			}
		}
		if (result.length() != 0 && (result.charAt(result.length() - 1) == '+'
				|| result.charAt(result.length() - 1) == '-')) {
			result = result.substring(0, result.length() - 1);
		}
		result = reorganize(result);
		if (eHasVar) {
			return result;
		} else {
			return "Error,no variable!";
		}
	}
}
