package cn.tedu.store.unit;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
//如果通过json返回页面数据有null的数据，则不返回回去,
//@JsonInclude(JsonInclude.Include.ALWAYS)可以属性的前面使用最后的参数ALLAYS，就可以保持参数一直上传
public class JsonResult<T> implements Serializable {

	private static final long serialVersionUID = 7003527395863647855L;

	private Integer state;
	private String message;
	private T data;
	

	
	public JsonResult(Integer state, T data) {
		super();
		this.state = state;
		this.data = data;
	}

	public JsonResult(Throwable e) {
		this.message=e.getMessage();
	}

	public JsonResult() {
		super();
	}

	public JsonResult(Integer state) {
		super();
		this.state = state;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "JsonResult [state=" + state + ", message=" + message + ", data=" + data + "]";
	}

}
