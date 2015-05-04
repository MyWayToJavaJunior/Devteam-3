package resource;

import java.util.ResourceBundle; 

public class LocalizationManager { 
	private final static LocalizationManager instance = new LocalizationManager(); 
	private ResourceBundle bundle = ResourceBundle.getBundle("resource.locale");

	public static LocalizationManager getInstance() { 
		return instance; 
	} 

	public String getValue(String key){ 
		return bundle.getString(key); 
	} 
}