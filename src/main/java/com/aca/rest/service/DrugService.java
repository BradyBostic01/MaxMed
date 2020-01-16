package com.aca.rest.service;


import java.time.LocalTime;
import java.util.List;

import com.aca.rest.dao.DrugDatabaseDao;
import com.aca.rest.model.DrugData;
import com.aca.rest.model.EmailMessage;


public class DrugService {
	private DrugDatabaseDao dao = new DrugDatabaseDao();
	

	public List<DrugData> getDrugName(String drugName) {
		
		return dao.getDrugName(drugName);
	}


	public List<DrugData> getDrugBrand(String brandName) {
		return dao.getDrugBrand(brandName);
	}


	public List<DrugData> getDrugPrimaryKey(String primaryKey) {
		return dao.getDrugPrimaryKey(primaryKey);
	}


	public List<DrugData> getDrugUnii(String unii) {
		return dao.getDrugUnii(unii);
	}


	public List<DrugData> getDrugInteraction(String drug) {
		return dao.getDrugInteraction(drug);
	}


	public List<DrugData> compareInteraction(String drug1, String drug2) {
		return dao.compareDrugInteraction(drug1, drug2);
	}


	
	

}
