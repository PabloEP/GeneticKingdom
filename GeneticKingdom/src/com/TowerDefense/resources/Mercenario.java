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
		return "<?xml version\"1.0\"?>" + "<orcos> Mercenarios xml" + "</orcos>";
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String enviarHTMLMercenarios(){
		int[][] oleada = new int[10][5];
		oleada = mercenarios.Obtener(10);
		String pob = "";
		for (int i = 0; i < 10; i++) {
			pob += Arrays.toString(oleada[i]);
		}
		return "<html>" + "<title>" + "Mercenarios" + "</title>"
		+ "<body>"
		+ "<h1><font color=#008000>" + pob + "</h1></font>"
		+ "</body></html";
	}
}
