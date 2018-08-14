package fr.mel.integration;


//@XmlRootElement(name = "")
public class EntityClass {

	private String id;
	private String classifier;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassifier() {
		return classifier;
	}
	public void setClassifier(String classifier) {
		this.classifier = classifier;
	}
	
	public String toString() {
		return "{ "
				+ "ID : " + getId()
				+ "Classifier:" + getClassifier()
				+ "}";
	}
	
	

}
