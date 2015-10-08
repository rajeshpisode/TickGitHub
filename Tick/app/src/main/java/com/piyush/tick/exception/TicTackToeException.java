package com.piyush.tick.exception;

public class TicTackToeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mErrorCode;

	public TicTackToeException(String code, String msg) {
		super(msg);
		setErrorCode(code);
	}

	public String getErrorCode() {
		return mErrorCode;
	}

	public void setErrorCode(String mErrorCode) {
		this.mErrorCode = mErrorCode;
	}
}
