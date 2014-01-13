package com.mmsh.atg.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ATGColumn {
	public String headerCaption() default "";
	public boolean searchable() default true;
	public boolean visible() default true;
	boolean collapsed() default false;
}
