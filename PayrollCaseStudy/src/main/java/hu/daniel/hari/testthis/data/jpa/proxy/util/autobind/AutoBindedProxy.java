package hu.daniel.hari.testthis.data.jpa.proxy.util.autobind;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AutoBindedProxy {
	/** binded class */
	Class<?> value();
}
