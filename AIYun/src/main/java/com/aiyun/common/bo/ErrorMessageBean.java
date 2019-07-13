package com.aiyun.common.bo;

public class ErrorMessageBean implements java.io.Serializable {

	private String commonMessage = null;
	int iITeam = 1;

	private java.lang.String repeatError = null;
	public ErrorMessageBean() {
	}
	public void addCommonMessage(String newCommonMessage) {
		if (newCommonMessage == null) {
			newCommonMessage = "";
		}
		if (commonMessage == null) {
			commonMessage = "";
		}
		commonMessage += "(" + (iITeam++) + ")" + newCommonMessage + "\n";

		if (commonMessage.trim().equals("(1)\n"))
			commonMessage = "";
		for (int i = 1; i < 30; i++) {
			if (commonMessage.endsWith("(" + i + ")\n")) {
				commonMessage = commonMessage.substring(0, commonMessage.length() - 7);
				break;
			}
		}
	}

	public void setCommonMessage(String newCommonMessage) {
		iITeam = 1;
		commonMessage = null;
		addCommonMessage(newCommonMessage);
	}

	public String getCommonMessage() {

		return commonMessage;
	}

}
