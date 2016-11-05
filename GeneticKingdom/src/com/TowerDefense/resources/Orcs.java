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
		int[][] oleada = new int[10][5];		
		oleada = Orcos.Obtener(10);
		String pob = "";
		for (int i = 0; i < 10; i++) {
			if(i == 9){
				pob += Arrays.toString(oleada[i]) ;
			}else{
				pob += Arrays.toString(oleada[i]) + ", ";
			}				
		}
		return "<?xml version\"1.0\"?>" + "<orcos>" + pob + "</orcos>";
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String enviarHTMLOrcos(){
		int[][] oleada = new int[10][5];		
		//oleada = Orcos.Obtener(10);
		return "<html>" + "<title>" + "Orcos" + "</title>"
		+ "<body>"
		+ "<h2><font color=#018736>" + "Oleada Actual de Orcos: " + "</h2></font>"
		+ "<h3><font color=#018736>" + Orcos.Oleada() + "</h3></font>"
		+ "<h2><font color=#018736>" + "Generacion: " + Orcos.generacion() + "</h2></font>"
		+ "</body></html";
	} 
}
