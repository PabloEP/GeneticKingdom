package com.TowerDefense.resources;

import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.TowerDefense.resources.PoblacionEnemigos;

@Path("/orcos")
public class Orcs {	
	PoblacionEnemigos Orcos = new PoblacionEnemigos("orcos");
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String enviarPlainOrcos(){
		return "Orcos plain";
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public String enviarXMLOrcos(){
		return "<?xml version\"1.0\"?>" + "<orcos> Orcos xml" + "</orcos>";
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String enviarHTMLOrcos(){
		int[][] oleada = new int[10][5];		
		oleada = Orcos.Obtener(10);
		String pob = "";
		for (int i = 0; i < 10; i++) {
			pob += Arrays.toString(oleada[i]);			
		}
		return "<html>" + "<title>" + "Orcos" + "</title>"
		+ "<body>"
		+ "<h1><font color=#008000>" + pob + "</h1></font>"
		+ "</body></html";
	} 
}