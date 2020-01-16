package com.aca.rest.model;

public class DrugData {
	private String primary_key;
	private String drugbankId;
	private String parentKey;
	private String name;
	private String brandName;
	private String ddiName;
	private String description;
	private String unii;
	private String indication;
	private String pharmacodynamics;
	private String mechanism_of_action;
	private String fda_label;
	private int drug_interactions_count;
	
	
	
	public DrugData() {
	}

	@Override
	public String toString(){
		return "Drugbank key: " + primary_key + "\n" +
			   "UNII: " + unii + "\n"+
			   "Brand Name: " + brandName + "\n" +
			   "Description: " + description + "\n" +
			   "Indication: " + indication + "\n" +
			   "Pharmacodynamics: " + pharmacodynamics + "\n" +
			   "Mechanism of action: " + mechanism_of_action + "\n" +
			   "FDA Label: " + fda_label + "\n" +
			   "Interactions count: " + drug_interactions_count;
			   
				
	}
	
	public String getPrimary_key() {
		return primary_key;
	}
	public void setPrimary_key(String primary_key) {
		this.primary_key = primary_key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDdiName() {
		return ddiName;
	}

	public void setDdiName(String ddiName) {
		this.ddiName = ddiName;
	}

	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUnii() {
		return unii;
	}
	public void setUnii(String unii) {
		this.unii = unii;
	}
	public String getIndication() {
		return indication;
	}
	public void setIndication(String indication) {
		this.indication = indication;
	}
	public String getPharmacodynamics() {
		return pharmacodynamics;
	}
	public void setPharmacodynamics(String pharmacodynamics) {
		this.pharmacodynamics = pharmacodynamics;
	}
	public String getMechanism_of_action() {
		return mechanism_of_action;
	}
	public void setMechanism_of_action(String mechanism_of_action) {
		this.mechanism_of_action = mechanism_of_action;
	}
	public String getFda_label() {
		return fda_label;
	}
	public void setFda_label(String fda_label) {
		this.fda_label = fda_label;
	}

	public int getDrug_interactions_count() {
		return drug_interactions_count;
	}

	public void setDrug_interactions_count(int drug_interactions_count) {
		this.drug_interactions_count = drug_interactions_count;
	}

	public String getDrugbankId() {
		return drugbankId;
	}

	public void setDrugbankId(String drugbankId) {
		this.drugbankId = drugbankId;
	}

	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}
}
