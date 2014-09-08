package eu.ijug.framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WrappedMethod {

	private Object that;
	private Method method;

	public WrappedMethod(Object that, Method method) {
		this.that = that;
		this.method = method;
	}

	public Object call(Object... args) {
		try {
			return method.invoke(that, args);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((that == null) ? 0 : that.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WrappedMethod other = (WrappedMethod) obj;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (that == null) {
			if (other.that != null)
				return false;
		} else if (!that.equals(other.that))
			return false;
		return true;
	}

}
