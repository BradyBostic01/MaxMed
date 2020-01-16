package com.aca.rest.controller;



import java.util.List;

import javax.mail.MessagingException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import com.aca.rest.model.DrugData;
import com.aca.rest.service.AmazonEmail;
import com.aca.rest.service.DrugService;



@Path("/v1")
public class DrugController {
	private DrugService service = new DrugService();
	private AmazonEmail mail = new AmazonEmail();
	// GET DRUGS
		
	@GET
	@Path("/generic")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DrugData> getDrugName(@QueryParam("name")String drugName){
		return service.getDrugName(drugName);
	}
	
	@GET
	@Path("/brand")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DrugData> getDrugBrand(@QueryParam("brandName")String brandName){
		return service.getDrugBrand(brandName);
	}
	
	@GET
	@Path("/drugbankId")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DrugData> getDrugPrimaryKey(@QueryParam("primary_key")String id){
		return service.getDrugPrimaryKey(id);
	}
	
	@GET
	@Path("/Unii")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DrugData> getDrugUnii(@QueryParam("id")String unii){
		return service.getDrugUnii(unii);
	}
	
	@GET
	@Path("/Interaction")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DrugData> getInteraction(@QueryParam("drug")String drug){
		return service.getDrugInteraction(drug);
	}
	
	@GET
	@Path("/Compare")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DrugData> compareInteraction(@QueryParam("drug1")String drug1,@QueryParam("drug2") String drug2){
		return service.compareInteraction(drug1,drug2);
	}
	
	//POST
	@POST
	@Path("/email")
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String sendEmail(@QueryParam("address")String emailAddress) throws MessagingException{
		return mail.sendEmail(emailAddress);
	}
}