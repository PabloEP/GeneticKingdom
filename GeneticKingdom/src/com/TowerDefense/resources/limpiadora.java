package com.TowerDefense.resources;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class limpiadora {
	public limpiadora() {
		
	}
	
	public void limpiar(){
		try {
			String filepath = "c:\\Users\\Pablo\\git\\GeneticKingdom\\GeneticKingdom\\src\\com\\TowerDefense\\resources\\Poblaciones.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			Node inO, inE, inM, inH, geno, gene, genm, genh, lasto, laste, lastm, lasth, orcos, elfos, merce, harpi;

			inO = doc.getElementsByTagName("inicialo").item(0);
			inE = doc.getElementsByTagName("iniciale").item(0);
			inM = doc.getElementsByTagName("inicialh").item(0);
			inH = doc.getElementsByTagName("inicialm").item(0);

			geno = doc.getElementsByTagName("generacioneso").item(0);
			gene = doc.getElementsByTagName("generacionese").item(0);
			genm = doc.getElementsByTagName("generacionesh").item(0);
			genh = doc.getElementsByTagName("generacionesm").item(0);

			lasto = doc.getElementsByTagName("lastPosto").item(0);
			laste = doc.getElementsByTagName("lastPoste").item(0);
			lastm = doc.getElementsByTagName("lastPostm").item(0);
			lasth = doc.getElementsByTagName("lastPosth").item(0);

			orcos = doc.getElementsByTagName("orcos").item(0);
			elfos = doc.getElementsByTagName("elfososcuros").item(0);
			merce = doc.getElementsByTagName("mercenarios").item(0);
			harpi = doc.getElementsByTagName("harpias").item(0);

			inO.setTextContent("0");
			inE.setTextContent("0");
			inM.setTextContent("0");
			inH.setTextContent("0");
			geno.setTextContent("0");
			gene.setTextContent("0");
			genm.setTextContent("0");
			genh.setTextContent("0");
			lasto.setTextContent("0");
			laste.setTextContent("0");
			lastm.setTextContent("0");
			lasth.setTextContent("0");
			orcos.setTextContent("");
			elfos.setTextContent("");
			merce.setTextContent("");
			harpi.setTextContent("");

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

}
