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
		int[][] oleada = new int[10][5];		
		oleada = harpias.Obtener(10);
		String pob = "";
		for (int i = 0; i < 10; i++) {
			if(i == 9){
				pob += Arrays.toString(oleada[i]) ;
			}else{
				pob += Arrays.toString(oleada[i]) + ", ";
			}	
		}
		return "<?xml version\"1.0\"?>" + "<harpias>" + pob + "</harpias>";
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String enviarHTMLHarpias(){
		int[][] oleada = new int[10][5];		
		oleada = harpias.Obtener(10);
		return "<html>" + "<title>" + "Harpias" + "</title>"
		+ "<body>"
		+ "<h1><font color=#ffe900>" + "Oleada" + "</h1></font>"
		+ "<h1><font color=#ffe900>" + harpias.Oleada() + "</h1></font>"
		+ "<h1><font color=#ffe900>" + "Generacion" + "</h1></font>"
		+ "<h1><font color=#ffe900>" + harpias.generacion() + "</h1></font>"
		+ "</body></html";
	}
}
