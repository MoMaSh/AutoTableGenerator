package com.mmsh.atg.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ATGTable {

	public String caption() default "";
	public String persistenceUnit() default "";
	public boolean searchable() default true;
	public boolean setFullSize() default false;
	

	
}
