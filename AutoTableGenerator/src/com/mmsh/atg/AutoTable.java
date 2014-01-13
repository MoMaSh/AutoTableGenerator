package com.mmsh.atg;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.tepi.filtertable.FilterTable;
import org.w3c.dom.Document;

import com.mmsh.atg.annotations.ATGColumn;
import com.mmsh.atg.annotations.ATGTable;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Container;

@SuppressWarnings("serial")
public class AutoTable extends FilterTable {
	
	private ATGTable atgTable;
	private String tableCaption; 
	private Container containerDS;
	private boolean searchableTable = true;
	private boolean fullSizeTable = false;
	
	public AutoTable(Class<?> clazz) {
		this("MMSH_ATG_TABLE_CAPTION", clazz, null, null);
	}
	
	public AutoTable(String caption, Class<?> clazz) {
		this(caption, clazz, null, null);
	}

	public AutoTable(Container dataSource) {
		this("MMSH_ATG_TABLE_CAPTION", null, dataSource, null);
	}
	
	public AutoTable(String caption, Container dataSource) {
		this(caption, null, dataSource, null);
	}
	
	public AutoTable(Class<?> clazz, String persistenceUnit) {
		this(null, clazz, null, persistenceUnit);
	}
	
	@SuppressWarnings("rawtypes")
	private AutoTable(String caption, Class<?> clazz, Container dataSource, String persistenceUnit) {
		
		tableCaption = caption; 
		containerDS = dataSource;
		searchableTable = true;
		fullSizeTable = false;
		List<Object> visibleColumns = new ArrayList<Object>();
		
		if (clazz == null) {
			if (containerDS instanceof JPAContainer) {
				clazz = ((JPAContainer) containerDS).getEntityClass();
			}
		}
		
		if (clazz != null) {
			if (clazz.isAnnotationPresent(Entity.class)) {
				if (clazz.isAnnotationPresent(ATGTable.class)) {
					atgTable = clazz.getAnnotation(ATGTable.class);
					
					setSelectable(atgTable.selectable());
					setMultiSelect(atgTable.multiSelect());
					setMultiSelectMode(atgTable.selectingMode());
					
					searchableTable = atgTable.searchable();
					fullSizeTable = atgTable.setFullSize();
					
					setColumnCollapsingAllowed(atgTable.columnCollapsing());
					
					if ("MMSH_ATG_TABLE_CAPTION".equals(tableCaption)) {
						tableCaption = atgTable.caption();
					}
					
					if (persistenceUnit != null && !"".equals(persistenceUnit)) {
						containerDS = JPAContainerFactory.make(clazz, persistenceUnit);
					} else {
						generateFromPersistence(clazz);
					}
				} else {
					if ("MMSH_ATG_TABLE_CAPTION".equals(tableCaption)) {
						tableCaption = "";
					}
					generateFromPersistence(clazz);
				}
			} else {
				tableCaption = "This table you are trying to create does not have connection to any dataSource";
			}
			
			for (Field field : Arrays.asList(clazz.getDeclaredFields())) {
				if (field.isAnnotationPresent(ATGColumn.class)) {
					ATGColumn atgColumn = field.getAnnotation(ATGColumn.class);
					if (atgColumn.visible()) {
						visibleColumns.add(field.getName());							
						if (atgColumn.collapsed()) {
							if (!isColumnCollapsingAllowed() ) {
								setColumnCollapsingAllowed(true);
							}
							setColumnCollapsed(field.getName(), true);
						}
					}
				} else {
					
					if (atgTable == null || !field.isAnnotationPresent(Id.class) ||  atgTable.showId()) {
						visibleColumns.add(field.getName());							
					}
					
				}
			}
			setCaption(tableCaption);
			setContainerDataSource(containerDS);
			setFilterBarVisible(searchableTable);
			if (fullSizeTable) {
				setSizeFull();
			}
			
			setVisibleColumns(visibleColumns.toArray());

			for (Field field : Arrays.asList(clazz.getDeclaredFields())) {
				if (field.isAnnotationPresent(ATGColumn.class)) {
					ATGColumn atgColumn = field.getAnnotation(ATGColumn.class);
					if (atgColumn.visible()) {
						if (atgColumn.collapsed()) {
							if (!isColumnCollapsingAllowed() ) {
								setColumnCollapsingAllowed(true);
							}
							setColumnCollapsed(field.getName(), true);
						}
					}
					setFilterFieldVisible(field.getName(), field.getAnnotation(ATGColumn.class).searchable());
				} else {
					setFilterFieldVisible(field.getName(), true);
				}
			}
		}
	}

	private void generateFromPersistence(Class<?> clazz) {
		// Find persistence.xml file
		File f = new File(getClass().getClassLoader().getResource(".").getPath() + "/META-INF/persistence.xml");
		if (f.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(f);
				doc.getDocumentElement().normalize();
				containerDS = JPAContainerFactory.make(clazz, doc.getElementsByTagName("persistence-unit").item(0).getAttributes().getNamedItem("name").getNodeValue());
			} catch (Exception e) {
				tableCaption = "Error reading persistence.xml";
			}
		} else {
			tableCaption = "persistence.xml file not found";
		}
	}
	
}
