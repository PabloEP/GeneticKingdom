package com.TowerDefense.resources;

import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/harpias")
public class Harpias {
	PoblacionEnemigos harpias = new PoblacionEnemigos("harpias");
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String enviarPlainHarpias(){
		return "Harpias plain";
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public String enviarXMLHarpias(){
		return "<?xml version\"1.0\"?>" + "<orcos> Harpias xml" + "</orcos>";
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String enviarHTMLHarpias(){
		int[][] oleada = new int[10][5];		
		oleada = harpias.Obtener(10);
		String pob = "";
		for (int i = 0; i < 10; i++) {
			pob += Arrays.toString(oleada[i]);
		}
		return "<html>" + "<title>" + "Harpias" + "</title>"
		+ "<body>"
		+ "<h1><font color=#008000>" + pob + "</h1></font>"
		+ "</body></html";
	}
}
