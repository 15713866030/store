package cn.tedu.store.controller.ex;

import org.apache.tomcat.util.http.fileupload.FileUpload;
/**
 * 上传文件被删除或移动，不可访问到该文件异常
 * @author soft01
 *
 */

public class FileUploadStateException extends FileUploadException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4286991940161762027L;

	public FileUploadStateException() {
		super();
	}

	public FileUploadStateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileUploadStateException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileUploadStateException(String message) {
		super(message);
	}

	public FileUploadStateException(Throwable cause) {
		super(cause);
	}

}
