package org.oxerr.ipyy.client.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "returnsms")
public class ReturnSMS implements Serializable {

	private static final long serialVersionUID = 2019071701L;

	private String returnStatus;

	private String message;

	private Long remainPoint;

	private String taskId;

	private Integer successCounts;

	@XmlElement(name = "returnstatus")
	public String getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@XmlElement(name = "remainpoint")
	public Long getRemainPoint() {
		return remainPoint;
	}

	public void setRemainPoint(Long remainPoint) {
		this.remainPoint = remainPoint;
	}

	public String getTaskId() {
		return taskId;
	}

	@XmlElement(name = "taskID")
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Integer getSuccessCounts() {
		return successCounts;
	}

	public void setSuccessCounts(Integer successCounts) {
		this.successCounts = successCounts;
	}

}
