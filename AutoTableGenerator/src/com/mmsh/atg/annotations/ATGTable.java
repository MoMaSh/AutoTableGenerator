package com.mmsh.atg.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.vaadin.shared.ui.MultiSelectMode;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ATGTable {

	public String caption() default "";
	public String persistenceUnit() default "";
	public boolean searchable() default true;
	public boolean setFullSize() default false;
	public boolean showId() default false;
	public boolean columnCollapsing() default false;
	public boolean selectable() default true;
	public boolean multiSelect() default false;
	public MultiSelectMode selectingMode() default MultiSelectMode.DEFAULT;
	
}
