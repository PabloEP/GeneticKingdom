package com.TowerDefense.resources;

import java.util.Arrays;
import java.util.Random;

public class PoblacionEnemigos {
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
	int FILAS = 600, COLUMNAS = 5, cantHIJOS = 20;
	int[][] POBLACION = new int[FILAS][COLUMNAS];
	int GENERACIONES = 50;
	int genActual = 0;
	int lastPost = 0;
	Random rnd = new Random();
	String tipo;
	double pV;
	double pF;
	double pM;
	double pA;
	/* Porcentaje para que se de la mutacion # = % */
	private static double MUTACION = 5;

	public PoblacionEnemigos(String type) {
		/*
		 * Orcos         +flecs      -magia-arti 
		 * ElfosOscuros  +magia      -flec-arti 
		 * Mercenarios   +flec+arti  -magia 
		 * Harpias       ~flec~magia
		 */
		this.tipo = type;
		switch(tipo){
		case "orcos":
			pV = 0.15;
			pF = 0.9;
			pM = 0.18;
			pA = 0.18;
			break;
		case "elfososcuros":
			pV = 0.1;
			pF = 0.18;
			pM = 0.9;
			pA = 0.18;
			break;
		case "mercenarios":
			pV = 0.15;
			pF = 0.9;
			pM = 0.18;
			pA = 0.9;
			break;
		case "harpias":
			pV = 0.1;
			pF = 0.18;
			pM = 0.18;
			pA = 0.18;
		}
		generarPoblacion();
	}
	
	public void generarPoblacion() {
		for (int i = 0; i < 100; i++) {
			lastPost = i + 1;
			for (int j = 0; j < 5; j++) {
				switch (j) {
				/*
				 * Orcos         +flecs      -magia-arti 
				 * ElfosOscuros  +magia      -flec-arti 
				 * Mercenarios   +flec+arti  -magia 
				 * Harpias       ~flec~magia
				 */
				case 0:
					switch(tipo){
					case "orcos":
						POBLACION[i][j] = (int) (rnd.nextInt((60 - 20) + 1) + 20);
						break;
					case "elfososcuros":
						POBLACION[i][j] = (int) (rnd.nextInt((50 - 20) + 1) + 20);
						break;
					case "mercenarios":
						POBLACION[i][j] = (int) (rnd.nextInt((70 - 20) + 1) + 20);
						break;
					case "harpias":
						POBLACION[i][j] = (int) (rnd.nextInt((40 - 20) + 1) + 20);
					}					
					break;
				case 1:					
					switch(tipo){
					/*
					 * Orcos         +flecs      -magia-arti 
					 * ElfosOscuros  +magia      -flec-arti 
					 * Mercenarios   +flec+arti  -magia 
					 * Harpias       ~flec~magia~arti
					 */
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
					/*
					 * Orcos         +flecs      -magia-arti 
					 * ElfosOscuros  +magia      -flec-arti 
					 * Mercenarios   +flec+arti  -magia 
					 * Harpias       ~flec~magia
					 */
					switch(tipo){
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
					/*
					 * Orcos         +flecs      -magia-arti 
					 * ElfosOscuros  +magia      -flec-arti 
					 * Mercenarios   +flec+arti  -magia 
					 * Harpias       ~flec~magia
					 */					
					switch(tipo){
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
		Ordenar();

	}

	public void Ordenar() {
		for (int i = 0; i < lastPost - 1; i++) {
			for (int j = 0; j < lastPost - 1; j++) {
				if (POBLACION[j][4] < POBLACION[j + 1][4]) {
					int[] temp = POBLACION[j + 1];
					POBLACION[j + 1] = POBLACION[j];
					POBLACION[j] = temp;

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
			mV = (int) (rnd.nextInt((100 - 0) + 1) + 0);
			mF = (int) (rnd.nextInt((100 - 0) + 1) + 0);
			mM = (int) (rnd.nextInt((100 - 0) + 1) + 0);
			mA = (int) (rnd.nextInt((100 - 0) + 1) + 0);
			// System.out.println(mV + " " + mF + " " + mM + " " + mA);
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
		/*
		 * System.out.println("--------------Hijos--------------");
		 * System.out.println("*V***F**M**A**FI*"); for(int i = 0; i < cant;
		 * i++){ System.out.println(Arrays.toString(k[i])); }
		 */
		for (int i = 0; i < cant; i++) {
			POBLACION[lastPost + i] = k[i];
		}
		lastPost += cant;
		genActual += 1;
		Ordenar();
	}
	
	public int[][] Obtener(int cant) {
		int[][] k = new int[cant][5];
		if ((GENERACIONES - genActual) != 0) {
			System.out.println("Actual: " + (GENERACIONES - genActual));
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

	public void printPoblacion() {
		for (int i = 0; i < lastPost; i++) {
			System.out.println(Arrays.toString(POBLACION[i]) + "   " + i);
		}
	}
}
