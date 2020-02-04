package com.aca.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aca.rest.model.DrugData;

public class DrugDatabaseDao {

	private final static String selectDrugById = "SELECT d.primary_key, d.name, dm.name, d.description, d.indication, d.unii, d.pharmacodynamics, d.mechanism_of_action, d.fda_label, d.drug_interactions_count"
			+ " FROM drugs AS d, drug_mixtures AS dm" + " WHERE d.primary_key = ?";

	private final static String selectDrugByName = "SELECT d.primary_key, d.name, dm.name, d.description, d.indication, d.unii, d.pharmacodynamics, d.mechanism_of_action, d.fda_label, d.drug_interactions_count "
			+ " FROM drugs AS d, drug_mixtures AS dm" + " WHERE d.name = ? AND dm.parent_key = d.primary_key" + " ORDER BY dm.name ASC";

	private final static String selectDrugByBrandName = "SELECT d.primary_key, d.name, dm.name, d.description, d.indication, d.unii, d.pharmacodynamics, d.mechanism_of_action, d.fda_label, d.drug_interactions_count "
			+ " FROM  drugs AS d, drug_mixtures AS dm " + " WHERE dm.name like ? AND dm.parent_key = d.primary_key" + " ORDER BY dm.name ASC";

	private final static String selectDrugByUnii = "SELECT d.primary_key, d.name, dm.name, d.description, d.indication, d.unii, d.pharmacodynamics, d.mechanism_of_action, d.fda_label, d.drug_interactions_count "
			+ " FROM drugs AS d, drug_mixtures AS dm" + " WHERE d.unii = ?";
	
	private final static String getDrugInteraction = "SELECT ddi.drugbankId, ddi.name, ddi.description, ddi.parent_key, d.name" + " FROM drug_drug_interactions AS ddi " + " INNER JOIN drugs AS d ON ddi.parent_key = d.primary_key " +
			" WHERE ddi.name = ?" + "ORDER BY d.name ASC";

	private final static String CompareInteraction = "SELECT ddi.drugbankId, ddi.name, ddi.description, ddi.parent_key, d.name " +
			" FROM drug_drug_interactions AS ddi" +
			" INNER JOIN drugs AS d" +
			" ON ddi.drugbankId = d.primary_key" +
			" OR ddi.parent_key = d.primary_key" +
			" WHERE ddi.name = ?" +
			" AND d.name = ?"+
			" ORDER BY d.name ASC";
	
	private DrugData getDrugs(ResultSet result) throws SQLException {
		DrugData drug = new DrugData();
		drug.setPrimary_key(result.getString("d.primary_key"));
		drug.setUnii(result.getString("d.unii"));
		drug.setBrandName(result.getString("dm.name"));
		drug.setName(result.getString("d.name"));
		drug.setDescription(result.getString("d.description"));
		drug.setIndication(result.getString("d.indication"));
		drug.setMechanism_of_action(result.getString("d.mechanism_of_action"));
		drug.setFda_label(result.getString("d.fda_label"));
		drug.setDrug_interactions_count(result.getInt("d.drug_interactions_count"));
		return drug;
	}

	public List<DrugData> getDrugName(String drugdata) {
		List<DrugData> drugs = new ArrayList<DrugData>();
		Connection conn = MariaDbUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			preparedStatement = conn.prepareStatement(selectDrugByName);
			preparedStatement.setString(1, drugdata);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				DrugData drug = getDrugs(result);
				drugs.add(drug);
			}
			return drugs;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null) {
					result.close();
					preparedStatement.close();
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return drugs;
	}

	public List<DrugData> getDrugBrand(String brandName) {
		List<DrugData> drugs = new ArrayList<DrugData>();
		Connection conn = MariaDbUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			preparedStatement = conn.prepareStatement(selectDrugByBrandName);
			preparedStatement.setString(1, brandName + "%");
			result = preparedStatement.executeQuery();
			while (result.next()) {
				DrugData drug = getDrugs(result);
				drugs.add(drug);
			}
			return drugs;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null) {
					result.close();
					preparedStatement.close();
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return drugs;
	}

	public List<DrugData> getDrugPrimaryKey(String primaryKey) {
		List<DrugData> drugs = new ArrayList<DrugData>();
		Connection conn = MariaDbUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			preparedStatement = conn.prepareStatement(selectDrugById);
			preparedStatement.setString(1, primaryKey);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				DrugData drug = getDrugs(result);
				drugs.add(drug);
			}
			return drugs;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null) {
					result.close();
					preparedStatement.close();
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return drugs;
	}
	

	public List<DrugData> getDrugUnii(String unii) {
		List<DrugData> drugs = new ArrayList<DrugData>();
		Connection conn = MariaDbUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			preparedStatement = conn.prepareStatement(selectDrugByUnii);
			preparedStatement.setString(1, unii);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				DrugData drug = getDrugs(result);
				drugs.add(drug);
			}
			return drugs;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null) {
					result.close();
					preparedStatement.close();
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return drugs;
	}
	
	public List<DrugData> getDrugInteraction(String drugdata){
		List<DrugData> drugs = new ArrayList<>();
		Connection conn = MariaDbUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try{
			preparedStatement = conn.prepareStatement(getDrugInteraction);
			preparedStatement.setString(1, drugdata);
			result = preparedStatement.executeQuery();
			while(result.next()){
				DrugData drug = getInteraction(result);
				drugs.add(drug);
			}
			return drugs;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(conn != null){
					conn.close();
					preparedStatement.close();
					result.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		return drugs;
		
	}
	
	public List<DrugData> compareDrugInteraction(String drug1, String drug2){
		List<DrugData> drugs = new ArrayList<>();
		Connection conn = MariaDbUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try{
			preparedStatement = conn.prepareStatement(CompareInteraction);
			preparedStatement.setString(1, drug1);
			preparedStatement.setString(2, drug2);
			result = preparedStatement.executeQuery();
			while(result.next()){
				DrugData drug = getInteraction(result);
				drugs.add(drug);
			}
			return drugs;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(conn != null){
					conn.close();
					preparedStatement.close();
					result.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		return drugs;
		
	}
	
	
	private DrugData getInteraction(ResultSet result) throws SQLException {
		DrugData drug = new DrugData();
		drug.setDrugbankId(result.getString("ddi.drugbankId"));
		drug.setDdiName(result.getString("ddi.name"));
		drug.setDescription(result.getString("ddi.description"));
		drug.setParentKey(result.getString("ddi.parent_key"));
		drug.setName(result.getString("d.name"));
		return drug;
	}

}

	
