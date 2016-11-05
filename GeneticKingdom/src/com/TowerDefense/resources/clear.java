package com.TowerDefense.resources;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

@Path("/clear")
public class clear {	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String enviarPlainLimpiar(){
		return "Elfos Oscuros plain";
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public String enviarXMLLimpiar(){
		return "<?xml version\"1.0\"?>" + "<orcos> Elfos Oscuros xml" + "</orcos>";
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String enviarHTMLLimpiar(){
		limpiadora limp = new limpiadora();
		limp.limpiar();
		return "<html>" + "<title>" + "Reiniciar Oleadas" + "</title>"
		+ "<body>"
		+ "<h1><font color=#008000>" + "OLEADAS REINICIADAS" + "</h1></font>"
		+ "</body></html";
	}	
	
}