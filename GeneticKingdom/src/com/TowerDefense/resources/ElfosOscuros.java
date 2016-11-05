package com.TowerDefense.resources;

import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/elfososcuros")
public class ElfosOscuros {
	PoblacionEnemigos elfos = new PoblacionEnemigos("elfososcuros");
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String enviarPlainElfosOscuros(){
		return "Elfos Oscuros plain";
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public String enviarXMLElfosOscuros(){
		return "<?xml version\"1.0\"?>" + "<orcos> Elfos Oscuros xml" + "</orcos>";
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String enviarHTMLElfosOscuros(){
		int[][] oleada = new int[10][5];
		oleada = elfos.Obtener(10);
		String pob = "";
		for (int i = 0; i < 10; i++) {
			if(i == 9){
				pob += Arrays.toString(oleada[i]) ;
			}else{
				pob += Arrays.toString(oleada[i]) + ", ";
			}						
		}
		return "<html>" + "<title>" + "Elfos Oscuros" + "</title>"
		+ "<body>"
		+ "<h1><font color=#008000>" + pob + "</h1></font>"
		+ "</body></html";
	}	
	
}
