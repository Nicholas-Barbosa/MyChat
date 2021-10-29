package com.mychat.websocket.utils;

import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;

public class CloseReasonBuilder {

	private CloseCodes code;
	private String reason;
	
	public static CloseReasonBuilder code(CloseCodes code) {
		CloseReasonBuilder builder = new CloseReasonBuilder();
		return builder.withCode(code);
	}
	
	public CloseReasonBuilder withCode(CloseCodes code) {
		this.code = code;
		return this;
	}
	
	public CloseReasonBuilder withReason(String reason) {
		this.reason = reason;
		return this;
	}
	
	public CloseReason build() {
		return new CloseReason(code, reason);
	}
}
