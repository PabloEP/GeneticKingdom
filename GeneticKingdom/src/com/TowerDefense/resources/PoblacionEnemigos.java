package com.TowerDefense.resources;

import java.util.Arrays;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.*;

public class PoblacionEnemigos {
	
	
	int FILAS = 2000, COLUMNAS = 5, cantHIJOS = 20;
	int[][] POBLACION = new int[FILAS][COLUMNAS];
	int GENERACIONES = 50;
	int genActual = 0;
	int lastPost = 0;
	Random rnd = new Random();
	String tipo;
	//Nodos del XML
	Node nodoPob, nodoIni, nodoPos, nodoGen;
	/*
	 * j0 = Vida                            
	 * j1 = Resistencia a Flechas            
	 * j2 = Resistencia a Magia             
	 * j3 = Resistencia a Artilleria        
	 * 
	 * Orcos         +flecs      -magia-arti 
	 * ElfosOscuros  +magia      -flec-arti
	 * Mercenarios   +flec+arti  -magia
	 * Harpias       ~flec~magia 
	 * 
	 * Porcentajes de aumento de las estadisticas en cada gen
	 * pV = Porcentaje de la Vida
	 * pF = porcentaje de la resistencia a las flechas
	 * pM = porcentaje de la resistencia a la magia
	 * pA = porcentaje de la resistencia a la artilleria
	 */	
	double pV;
	double pF;
	double pM;
	double pA;
	/* Porcentaje para que se de la mutacion # = % */
	private static double MUTACION = 5;

	public PoblacionEnemigos(String type) {
		try {
			String filepath = "c:\\Users\\Pablo\\git\\GeneticKingdom\\GeneticKingdom\\src\\com\\TowerDefense\\resources\\Poblaciones.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);			
			this.tipo = type;			
			nodoPob = doc.getElementsByTagName(tipo).item(0);
			switch (tipo) {
			case "orcos":
				pV = 0.15;
				pF = 0.9;
				pM = 0.18;
				pA = 0.18;
				nodoIni = doc.getElementsByTagName("inicialo").item(0);
				nodoPos = doc.getElementsByTagName("lastPosto").item(0);
				nodoGen = doc.getElementsByTagName("generacioneso").item(0);
				break;
				
			case "elfososcuros":
				pV = 0.1;
				pF = 0.18;
				pM = 0.9;
				pA = 0.18;
				nodoIni = doc.getElementsByTagName("iniciale").item(0);
				nodoPos = doc.getElementsByTagName("lastPoste").item(0);
				nodoGen = doc.getElementsByTagName("generacionese").item(0);
				break;
				
			case "mercenarios":
				pV = 0.15;
				pF = 0.9;
				pM = 0.18;
				pA = 0.9;
				nodoIni = doc.getElementsByTagName("inicialm").item(0);
				nodoPos = doc.getElementsByTagName("lastPostm").item(0);
				nodoGen = doc.getElementsByTagName("generacionesm").item(0);
				break;
				
			case "harpias":				
				pV = 0.1;
				pF = 0.18;
				pM = 0.18;
				pA = 0.18;
				nodoIni = doc.getElementsByTagName("inicialh").item(0);
				nodoPos = doc.getElementsByTagName("lastPosth").item(0);
				nodoGen = doc.getElementsByTagName("generacionesh").item(0);
				break;
			}
			int numIni = Integer.parseInt(nodoIni.getTextContent());
			if(numIni == 0){
				generarPoblacion();				
				String poblacion = "";
				for(int i = 0; i < lastPost; i++){
					if(i == (lastPost - 1)){
						poblacion += Arrays.toString(POBLACION[i]);
					}else{
						poblacion += Arrays.toString(POBLACION[i]) + ",";
					}
				}				
				String lastPo = String.valueOf(lastPost);
				lastPo = Integer.toString(lastPost);
				nodoPos.setTextContent(lastPo);
				nodoPob.setTextContent(poblacion);
				nodoIni.setTextContent("1");
				
			}else{
				String temp = nodoPob.getTextContent();
				String[] items = temp.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
				llenarPobla(items);
				lastPost = Integer.parseInt(nodoPos.getTextContent());
				genActual = Integer.parseInt(nodoGen.getTextContent());				
				
			}
			

			// Todo lo q se tenga q hacer va arriba de esto para actualizar el
			// XML
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
	
	public void llenarPobla(String[] items){
		int num = 0;
		for (int i = 0; i < items.length/5; i++) {
			for (int j = 0; j < 5; j++) {
				POBLACION[i][j] = Integer.parseInt(items[num]);
				num += 1;
			}
		}
	}
	
	//lista
	public void generarPoblacion() {
		for (int i = 0; i < 100; i++) {
			lastPost = i + 1;
			for (int j = 0; j < 5; j++) {
				switch (j) {
				case 0:
					switch (tipo) {
					case "orcos":
						POBLACION[i][j] = (int) (rnd.nextInt((60 - 20) + 1) + 20);
						break;
					case "elfososcuros":
						POBLACION[i][j] = (int) (rnd.nextInt((40 - 20) + 1) + 20);
						break;
					case "mercenarios":
						POBLACION[i][j] = (int) (rnd.nextInt((60 - 20) + 1) + 20);
						break;
					case "harpias":
						POBLACION[i][j] = (int) (rnd.nextInt((40 - 20) + 1) + 20);
					}
					break;
				case 1:
					switch (tipo) {
					case "orcos":
						POBLACION[i][j] = (int) (rnd.nextInt((8 - 3) + 1) + 3);
						break;
					case "elfososcuros":
						POBLACION[i][j] = (int) (rnd.nextInt((5 - 3) + 1) + 3);
						break;
					case "mercenarios":
						POBLACION[i][j] = (int) (rnd.nextInt((8 - 3) + 1) + 3);
						break;
					case "harpias":
						POBLACION[i][j] = (int) (rnd.nextInt((5 - 3) + 1) + 3);
					}
					break;
				case 2:
					switch (tipo) {
					case "orcos":
						POBLACION[i][j] = (int) (rnd.nextInt((5 - 3) + 1) + 3);
						break;
					case "elfososcuros":
						POBLACION[i][j] = (int) (rnd.nextInt((8 - 3) + 1) + 3);
						break;
					case "mercenarios":
						POBLACION[i][j] = (int) (rnd.nextInt((5 - 3) + 1) + 3);
						break;
					case "harpias":
						POBLACION[i][j] = (int) (rnd.nextInt((5 - 3) + 1) + 3);
					}
					break;
				case 3:
					switch (tipo) {
					case "orcos":
						POBLACION[i][j] = (int) (rnd.nextInt((5 - 3) + 1) + 3);
						break;
					case "elfososcuros":
						POBLACION[i][j] = (int) (rnd.nextInt((5 - 3) + 1) + 3);
						break;
					case "mercenarios":
						POBLACION[i][j] = (int) (rnd.nextInt((8 - 3) + 1) + 3);
						break;
					case "harpias":
						POBLACION[i][j] = (int) (rnd.nextInt((5 - 3) + 1) + 3);
					}
					break;
				case 4:
					POBLACION[i][j] = (POBLACION[i][j - 1] + POBLACION[i][j - 2] + POBLACION[i][j - 3]
							+ ((int) (POBLACION[i][j - 4] / 5))) / 4;
				}
			}
		}
		Ordenar(POBLACION);		
	}		
   //lista
	public void Ordenar(int[][] list) {
		for (int i = 0; i < lastPost - 1; i++) {
			for (int j = 0; j < lastPost - 1; j++) {
				if (list[j][4] < list[j + 1][4]) {
					int[] temp = list[j + 1];
					list[j + 1] = list[j];
					list[j] = temp;

				}
			}
		}
	}

	public void nuevaGeneracion(int cant) {
		int[][] par = new int[cant][5], impar = new int[cant][5];
		int np = 0, ni = 0, mV, mF, mM, mA;
		for (int i = 0; i < cant * 2; i++) {
			switch (i % 2) {
			case 0:
				par[np] = POBLACION[i];
				np++;
				break;
			case 1:
				impar[ni] = POBLACION[i];
				ni++;
				break;
			}

		}
		/*
		 * for(int i = 0; i < cant; i++){ System.out.println("Padre: " +
		 * Arrays.toString(par[i]) + "  Madre: " + Arrays.toString(impar[i])); }
		 */
		int k[][] = new int[cant][5];
		for (int i = 0; i < cant; i++) {
			//
			mV = (int) (rnd.nextInt((100 - 0) + 1) + 0);
			mF = (int) (rnd.nextInt((100 - 0) + 1) + 0);
			mM = (int) (rnd.nextInt((100 - 0) + 1) + 0);
			mA = (int) (rnd.nextInt((100 - 0) + 1) + 0);
			
			for (int j = 0; j < 5; j++) {

				switch (j) {
				case 0:
					if (mV <= MUTACION) {
						k[i][j] = (int) (par[i][j] + ((par[i][j] * pV) * 2));
					} else {
						k[i][j] = (int) (par[i][j] + (par[i][j] * pV));
					}

					break;
				case 1:
					if (mF <= MUTACION) {
						k[i][j] = (int) (par[i][j] + ((par[i][j] * pF) * 2));
					} else {
						k[i][j] = (int) (par[i][j] + (par[i][j] * pF));
					}
					break;
				case 2:
					if (mM <= MUTACION) {
						k[i][j] = (int) (par[i][j] + ((par[i][j] * pM) * 2));
					} else {
						k[i][j] = (int) (par[i][j] + (par[i][j] * pM));
					}
					break;
				case 3:
					if (mA <= MUTACION) {
						k[i][j] = (int) (par[i][j] + ((par[i][j] * pA) * 2));
					} else {
						k[i][j] = (int) (par[i][j] + (par[i][j] * pA));
					}
					break;
				case 4:
					k[i][j] = (k[i][j - 1] + k[i][j - 2] + k[i][j - 3] + ((int) (k[i][j - 4] / 5))) / 4;
					break;

				}
			}

		}
		//Llena la poblacion q hay con los nuevos creados
		for (int i = 0; i < cant; i++) {
			POBLACION[lastPost + i] = k[i];
		}
		lastPost += cant;
		genActual += 1;
		Ordenar(POBLACION);
		try {
			String filepath = "c:\\Users\\Pablo\\git\\GeneticKingdom\\GeneticKingdom\\src\\com\\TowerDefense\\resources\\Poblaciones.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			nodoPob = doc.getElementsByTagName(tipo).item(0);
			switch (tipo) {
			case "orcos":
				nodoIni = doc.getElementsByTagName("inicialo").item(0);
				nodoPos = doc.getElementsByTagName("lastPosto").item(0);
				nodoGen = doc.getElementsByTagName("generacioneso").item(0);
				break;

			case "elfososcuros":
				nodoIni = doc.getElementsByTagName("iniciale").item(0);
				nodoPos = doc.getElementsByTagName("lastPoste").item(0);
				nodoGen = doc.getElementsByTagName("generacionese").item(0);
				break;

			case "mercenarios":
				nodoIni = doc.getElementsByTagName("inicialm").item(0);
				nodoPos = doc.getElementsByTagName("lastPostm").item(0);
				nodoGen = doc.getElementsByTagName("generacionesm").item(0);
				break;

			case "harpias":
				nodoIni = doc.getElementsByTagName("inicialh").item(0);
				nodoPos = doc.getElementsByTagName("lastPosth").item(0);
				nodoGen = doc.getElementsByTagName("generacionesh").item(0);
				break;
			}
			String poblacion = "";
			for (int i = 0; i < lastPost; i++) {
				if (i == (lastPost - 1)) {
					poblacion += Arrays.toString(POBLACION[i]);
				} else {
					poblacion += Arrays.toString(POBLACION[i]) + ",";
				}
			}
			genActual = Integer.parseInt(nodoGen.getTextContent());
			genActual += 1;
			
			String genAc = String.valueOf(genActual);
			String lastPo = String.valueOf(lastPost);
			lastPo = Integer.toString(lastPost);
			genAc = Integer.toString(genActual);
					
			
			//Se actualizan Datos del XML
			nodoGen.setTextContent(genAc);
			nodoPos.setTextContent(lastPo);
			nodoPob.setTextContent(poblacion);

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
	
	public int[][] Obtener(int cant) {
		int[][] k = new int[cant][5];		
		if ((GENERACIONES - genActual) != 0) {
			nuevaGeneracion(cantHIJOS);
			for (int i = 0; i < cant; i++) {
				k[i] = POBLACION[i];
			}
			return k;
		} else {
			for (int i = 0; i < cant; i++) {
				k[i] = POBLACION[i];
			}
			return k;
		}
	}

	public void printPoblacion(int num) {
		for (int i = 0; i < num; i++) {
			System.out.println(Arrays.toString(POBLACION[i]) + "   " + i);
		}
	}
}
