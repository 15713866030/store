package cn.tedu.store.controller.ex;

import cn.tedu.store.service.ex.ServiceException;

public class DeleteException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8820343682972486279L;

	public DeleteException() {
		super();
	}

	public DeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DeleteException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeleteException(String message) {
		super(message);
	}

	public DeleteException(Throwable cause) {
		super(cause);
	}

}
