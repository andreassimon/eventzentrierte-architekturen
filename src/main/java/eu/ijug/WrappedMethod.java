package eu.ijug;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WrappedMethod {

	private Object that;
	private Method method;

	public WrappedMethod(Object that, Method method) {
		this.that = that;
		this.method = method;
	}

	public Object call(Command c) {
		try {
			return method.invoke(that, c);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

}