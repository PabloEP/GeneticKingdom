package com.TowerDefense.resources;

import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/mercenarios")
public class Mercenario {
	private PoblacionEnemigos mercenarios = new PoblacionEnemigos("mercenarios");
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String enviarPlainMercenarios(){
		return "Mercenario plain";
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public String enviarXMLMercenarios(){
		int[][] oleada = new int[10][5];
		oleada = mercenarios.Obtener(10);
		String pob = "";
		for (int i = 0; i < 10; i++) {
			if(i == 9){
				pob += Arrays.toString(oleada[i]) ;
			}else{
				pob += Arrays.toString(oleada[i]) + ", ";
			}	
		}
		return "<?xml version\"1.0\"?>" + "<mercenarios>" + pob + "</mercenarios>";
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String enviarHTMLMercenarios(){
		int[][] oleada = new int[10][5];
		oleada = mercenarios.Obtener(10);
		return "<html>" + "<title>" + "Mercenarios" + "</title>"
		+ "<body>"
		+ "<h1><font color=#ff0015>" + "Oleada" + "</h1></font>"
		+ "<h1><font color=#ff0015>" + mercenarios.Oleada() + "</h1></font>"
		+ "<h1><font color=#ff0015>" + "Generacion" + "</h1></font>"
		+ "<h1><font color=#ff0015>" + mercenarios.generacion() + "</h1></font>"
		+ "</body></html";
	}
}
