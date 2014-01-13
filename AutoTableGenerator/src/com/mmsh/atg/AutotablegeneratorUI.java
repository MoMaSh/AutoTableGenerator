package com.mmsh.atg;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("atg")
public class AutotablegeneratorUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = AutotablegeneratorUI.class, widgetset = "com.mmsh.atg.widgetset.AutotablegeneratorWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

//		JPAContainer<Person> jpaPerson = JPAContainerFactory.make(Person.class, "AutoTableGeneratorPersistence");
//		JPAContainer<Address> jpaAddress = JPAContainerFactory.make(Address.class, "AutoTableGeneratorPersistence");
//		Person person = new Person();
//		person.setFirstName("Pantea");
//		person.setLastName("Magharei");
//		person.setAge(31);
//		person.setHasJob(true);
//		person.setMarried(true);
//		Address address = new Address();
//		address.setStreetName("Gottfried-Boehm-Ring");
//		address.setStreetName("27a");
//		jpaAddress.addEntity(address);
//		person.setAddress(address);
//		jpaPerson.addEntity(person);
		
//		JPAContainer<Person> jpaPerson = JPAContainerFactory.make(Person.class, "AutoTableGeneratorPersistence");
//		Table table = new Table("", jpaPerson);
//		
//		layout.addComponent(table);

	
//		layout.addComponent(new AutoTable(Person.class));
//		layout.addComponent(new AutoTable(Address.class));

	}
}