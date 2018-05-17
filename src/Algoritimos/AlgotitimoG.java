package Algoritimos;

import java.util.ArrayList;
import java.util.Random;

import javax.annotation.processing.Processor;

public class AlgotitimoG {
	
	double mediaBestFitness;
	double fitnessMedio;
	double mediaConvergencia;
	double finalbestIndivido;
	
	//ArrayList<Double> mediaFitness;
	
	double probMutation;
	double probCrosover;
	
	AlgotitimoG(double proMutation, double proCrosover) {
		

		this.probCrosover = proCrosover;
		this.probMutation = proMutation;
	
	}


	public int[][] createPopulation (int size, int xBits, int yBits) {
		
		int[][] population = new int[size][xBits + yBits];
		
		Random random = new Random();
		
		for (int i = 0; i < population.length; i++) {
			for (int j = 0; j < population[0].length; j++) {
				 population[i][j] = random.nextInt(2);
			}
		  
		}
		
		return population;
	}
	
	public int[][] convertPopulationInterger(int[][] value, int numberVar){
		
		int[][] newValue = new int[value.length][2];
		
		String number="";
		
		for (int i = 0; i < value.length; i++) {
			for (int j = 0; j < value[0].length; j++) {
				number += value[i][j];
				
				if(j == (numberVar - 1)) {
//					System.out.println("X Binario " +number);
					newValue[i][0] =  Integer.parseInt(number, 2);
//					System.out.println("X Inteiro " +newValue[i][0]);
//					System.out.println();
					number="";
				
				}
				
				if(j == (value[0].length - 1)){
//					System.out.println("Y Binario " +number);
					newValue[i][1] =  Integer.parseInt(number, 2);
//					System.out.println("Y Inteiro " +newValue[i][1]);
//					System.out.println();
					number="";
				}
				
			}
		}
		
		
		return newValue;
	}
	
	public double[][] convertPopulationReal(int[][] value, int xmax, int xmin, int ymax,int ymin, int xnumberBits, int ynumberBits){
		
		double[][] newValue = new double[value.length][2];
		//System.out.println("-----------------------------------------------------------------------");
		for (int i = 0; i < value.length; i++) {
			for (int j = 0; j < value[0].length; j++) {
				
				if(j == 0) {
//					System.out.println("X Inteiro " + value[i][j]);
					newValue[i][j] = xmin + (xmax - xmin) / (Math.pow(2, xnumberBits)-1) * value[i][j];
//					System.out.println("X Real " +newValue[i][j]);
//					System.out.println();
					
				
				}
				
				if(j == 1){
//					System.out.println("Y Inteiro " + value[i][j]);
					newValue[i][j] = ymin+(ymax - ymin) / (Math.pow(2, ynumberBits)-1) * value[i][j];
//					System.out.println("Y Real " +newValue[i][j]);
//					System.out.println();
//					
				}
				
			}
		}
		
		return newValue;
	}
	
	public double[] getFitness(double[][] value){
		
		double[] newValue = new double[value.length];
		//System.out.println("----------------------------FITNESS----------------------------");
		for (int i = 0; i < value.length; i++) {
				newValue[i] =  100/(1 +  Math.pow(value[i][0], 2) +  Math.pow(value[i][1],2));
//				System.out.println("fit "+i+" => "+ newValue[i]);
		}
		
		return newValue;
	}
	
	public int[] select (double[] fitness){
		
		int[] index = new int[fitness.length];
		
		double[] roleta = new double[fitness.length];
		double fitnessTotal = 0;
		for (int i = 0; i < fitness.length; i++) {
			fitnessTotal += fitness[i];
		}
		
		for (int i = 0; i < roleta.length; i++) {
			roleta[i] = fitness[i]/fitnessTotal;
		}
		
		double run = 0;
		double aux = 0;
		int in = 0;
		
		for (int i = 0; i < roleta.length; i++) {
			aux = 0;
			run = Math.random();
			for (int j = 0; j < roleta.length; j++) {
				
				aux += roleta[j];
				
				if(run < aux){
					in = j;
					break;
				}
			}
			
			index[i] = in;
			
			
//			System.out.println();
//			System.out.println("----------------------------Sele��o---------------------------------");
//			System.out.println("Index => "+ index);
//			System.out.println("fitness Total: "+fitnessTotal);
//			System.out.println("Sortiado: "+run);
//			System.out.println("Roleta: "+roleta[in]);
//			System.out.println("fitness selecionado: "+fitness[in]);
//			System.out.println();
			
		}
		
		
		
		return index;
	}
	
	public int[][] crossover(int[][] value, int[] index){
		
		int pointCut = 0;
		double pc = 1;
		
		Random random = new Random();
		
		int[][] newValue = new int[index.length][value[0].length];
		
		int[][] selectPopulation = new int[index.length][value[0].length];
		
		for (int i = 0; i < selectPopulation.length; i++) {
			for (int j = 0; j < selectPopulation[0].length; j++) {
				selectPopulation[i][j] = value[index[i]][j]; 
				
			}
			
		}
		
		//int[][] aux = new int[selectPopulation.length][selectPopulation[0].length];
		
		for (int i = 0; i < index.length / 2; i++) {
			
			if(Math.random() > this.probCrosover){
				
				for (int j = 0; j < selectPopulation[0].length; j++) {
					
					newValue[2 * i][j] = selectPopulation[2 * i][j];
					newValue[2 * i + 1][j] = selectPopulation[2 * i + 1][j];
				}
				
			}else {
				
				pointCut = random.nextInt(value[0].length - 1);
				
				if(pointCut == 0){pointCut++;}
				
				for (int j = 0; j < selectPopulation[0].length; j++) {
						
					if(j < pointCut) {
						newValue[2 * i][j] = selectPopulation[2 * i][j];
						newValue[2 * i + 1][j] = selectPopulation[2 * i + 1 ][j];


					}else  {
						newValue[2 * i][j] = selectPopulation[2 * i + 1][j];
						newValue[2 * i + 1][j] = selectPopulation[2 * i][j]; 
					}

				}
				


				
			}
			
		}
		
		
		
		
		return newValue;
	}
	
	public  int [][]crossoverUniforme (int vetorIndice[], int populacaoBinaria[][]){
		
		//double proCruzamento=0.6;
		
		int populacaoCruzada[][] = new int[populacaoBinaria.length][populacaoBinaria[0].length];
		int[][] individuosSelecionados = new int[vetorIndice.length][populacaoBinaria[0].length];
		for (int i = 0; i < populacaoBinaria.length; i++) {
			individuosSelecionados[i] = populacaoBinaria[vetorIndice[i]];
		}
		
		int mascara []= new int [individuosSelecionados[0].length];
		
		
		for (int i = 0; i < vetorIndice.length / 2; i++) {
			for (int j = 0; j < individuosSelecionados[0].length; j++) {
				if (Math.random()< this.probCrosover) {
					mascara[j]=0;
					
				}
					else {
						mascara[j]=1;
						System.out.println("asdfasdf");
					}
			}
			
			for (int k = 0; k < populacaoBinaria[0].length; k++) {
				if (mascara[k]==0) {
					
				populacaoCruzada[2 * i][k] = individuosSelecionados[vetorIndice[2 * i]][k];
				populacaoCruzada[2 * i + 1][k] = individuosSelecionados[vetorIndice[2 * i + 1]][k];
				}else {
					populacaoCruzada[2 * i][k] = individuosSelecionados[vetorIndice[2 * i + 1]][k];
					populacaoCruzada[2 * i + 1][k] = individuosSelecionados[vetorIndice[2 * i]][k];
				}
			}
		}
		return populacaoCruzada;
	}
	
	public int[] TournamentSelection (double[] fitness, int n) {
		int[] index = new int[fitness.length];
//		double torneio[][] = new double[fitness.length][n];
		
		Random random = new Random();
		
		int indAux = 0;
		int ind = 0;
		double fit = 0;
		double aux = 0;
		double p;
		for (int i = 0; i < fitness.length; i++) {
			for (int j = 0; j < n; j++) {
				indAux = random.nextInt(fitness.length);
				if (aux <= fitness[indAux]) {
					aux = fitness[indAux];
					ind = indAux;
				}
				index[i] = ind;
			}
		}
		return index;
	}
        
	public int[][] mutation (int[][] value){
		int[][] newValue = new int[value.length][value[0].length];
		//double mult = 0.05;

		// Multation
		for (int i = 0; i < newValue.length; i++) {
			for (int j = 0; j < newValue[0].length; j++) {	
				if(this.probMutation > Math.random()) {
					if(newValue[i][j] == 1) {
						newValue[i][j]	= 0;
					}else {
						newValue[i][j]	= 1;
					}	
				}

			}

		}

		return newValue;
	}
        
	public void view (int[][] value) {
		String s = "";
		
		for (int i = 0; i < value.length; i++) {
			for (int j = 0; j < value[0].length; j++) {	
			     s += value[i][j] + " ";
			}
			
			s += "\n";
			
		}
		
		System.out.println(s);
		System.out.println();
		
	}
	
	public void viewD (double[] fitness) {
		String s = "";
		
		for (int i = 0; i < fitness.length; i++) {
			     s += fitness[i] + " " + "\n";
		}

		System.out.println(s);
		System.out.println();
		
	}
	
	public double media (double[] value) {
		
		double media = 0;
		
		for (int i = 0; i < value.length; i++) {
			media += value[i];
		}
		
		return (media / value.length) ;
	}
	
	public int max (double[] value) {
		
		double max = 0;
		int index = 0;
		
		for (int i = 0; i < value.length; i++) {
			
			if(max < value[i] ) {
				max = value[i];
                index = i;
			}
		}
		
		return index;
	}
	
	public int min (double[] value) {
			
			double min = 999999;
			int index = 0;
			
			for (int i = 0; i < value.length; i++) {
				
				if(min > value[i] ) {
					min = value[i];
	                index = i;
				}
			}
			
			return index;
		}
	
	public void generateAG(int popSize, int popXbits, int popYbits, int epooc, int combSelect, int combCros) {
		
		int[][] startPopulation = this.createPopulation(popSize, popXbits, popYbits);
		int[][] populationInterger;
        int[][] newPopulation;
		double[][] populationReal;
		double[] fitness = null;
		int[] selects = null;
		int[][] cros = null;
		int epooca = 30;
		
		int[][] auxPopulation = new int[popSize][popXbits + popYbits];
		double[][] auxReal = new double[popSize][2];


        double[] finalbestIndivido = new double[epooc];
        double[] mediaConvergencia = new double[epooc];
        double[] mediaMedia = new double[epooc];
        double[] mediaBestFitness = new double[epooc];

        
                
        int indexM;
        int indexP;
        
        for (int n = 0; n < epooc; n++) {
        	
        	double[] fitnessMedio = new double[epooca];
            double[] bestFitness = new double[epooca];
            int[] bestFitnessIndex = new int[epooca];
            double[][] bestIndivido = new double[epooca][2];
            
        	for (int i = 0; i < epooca; i++) {
        		populationInterger = this.convertPopulationInterger(startPopulation,popXbits);
        		populationReal = this.convertPopulationReal(populationInterger, (popXbits + popYbits), 0, (popXbits + popYbits), 0, popXbits, popYbits);
        		fitness = this.getFitness(populationReal);

        		indexM = this.max(fitness);
        		//bestFitnessIndex[i] = indexM;

        		if(i > 0) {
        			indexP = this.min(fitness); //pior indece 

        			if(fitness[indexM] < bestFitness[i-1]) {
        				startPopulation[indexP] = auxPopulation[bestFitnessIndex[i-1]];
        				fitness[indexP] = bestFitness[i-1];
        				populationReal[indexP] = auxReal[bestFitnessIndex[i-1]];
        				
        				//System.out.println("Go!");

        			}
        		}

        		fitnessMedio[i] = this.media(fitness);

        		indexM = this.max(fitness);
        		bestFitnessIndex[i] = indexM;

        		bestFitness[i] = fitness[indexM];

        		bestIndivido[i][0] = populationReal[indexM][0];
        		bestIndivido[i][1] = populationReal[indexM][1];



        		if(combSelect == 1) {
        			selects = this.TournamentSelection(fitness, 3);
        		} else if(combSelect == 2) {
        			selects = this.select(fitness);
        		}


        		if(combCros == 1) {
        			cros = this.crossoverUniforme(selects, startPopulation);
        			
        		}else if (combCros == 2) {
        			cros = this.crossover(startPopulation, selects);
        		}


        		// selects = this.TournamentSelection(fitness, 3);

        		// cros = this.crossoverUniforme(selects, startPopulation);

        		auxPopulation = startPopulation;
        		auxReal = populationReal;

        		startPopulation = this.mutation(cros);
        	}
        	
//            for (int i = 0; i < epooca; i++) {
//                
//                System.out.println("_________________Saida___________________");
//                System.out.println("Epooca: "+i+" Media dos Fitness: "+fitnessMedio[i]);
//                System.out.println("______________________________________________________");
//                System.out.println("Epooca: "+i+" Melhor dos Fitness: "+bestFitness[i]);
//                System.out.println("______________________________________________________");
//                System.out.println("Epooca: "+i+" Melhor dos Individo: X = "+bestIndivido[i][0]+" | Y = "+bestIndivido[i][1]);
//            
//            }
        	
        	populationInterger = this.convertPopulationInterger(startPopulation,popXbits);
    		populationReal = this.convertPopulationReal(populationInterger, (popXbits + popYbits), 0, (popXbits + popYbits), 0, popXbits, popYbits);
    		fitness = this.getFitness(populationReal);

    		 indexM = this.max(fitness);
    		 indexP = this.min(fitness); //pior indece 

 			if(fitness[indexM] < bestFitness[bestFitness.length-1]) {
 				startPopulation[indexP] = auxPopulation[bestFitnessIndex[bestFitnessIndex.length-1]];
 				fitness[indexP] = bestFitness[bestFitness.length - 1];
 				populationReal[indexP] = auxReal[bestFitnessIndex[bestFitnessIndex.length - 1]];
 				
 				//System.out.println("Go!");
 			}
    		 
//    		 System.out.println(fitness[indexM]);
//    		 System.out.println(bestFitness[bestFitness.length - 1]);

 			 indexM = this.max(fitness);
 			 
    		 finalbestIndivido[n] = fitness[indexM];
    		 
    		    double media = 0;
    	        double aux = bestFitness[bestFitness.length - 1];

    	    	for (int i = 0; i < fitnessMedio.length; i++) {
    	    		media +=  fitnessMedio[i];
    	    	}
    	    	
    	    	mediaMedia[n] = media/fitnessMedio.length;
    	    	
    	    	double media2 = 0;
    	    	
    	    	for (int i = 0; i < bestFitness.length; i++) {
    	    		media2 += bestFitness[i];
				}
    	    	
    	    	mediaBestFitness[n] = media2/bestFitness.length;
    	    	
    	    	
    	    	for (int j = 0; j < fitness.length; j++) {
    	    		if(aux == fitness[j]) {
    	    			mediaConvergencia[n] = j;
    	    			System.out.println(" Epoca  => "+j);
    	    			break;

    	    		}
    	    	}	
      }
        
        System.out.println();

        for (int i = 0; i < finalbestIndivido.length; i++) {
			this.finalbestIndivido += finalbestIndivido[i];
		}
        
        this.finalbestIndivido = this.finalbestIndivido/finalbestIndivido.length;
        
        for (int i = 0; i < mediaBestFitness.length; i++) {
			this.mediaBestFitness += mediaBestFitness[i];
		}
        
        this.mediaBestFitness = this.mediaBestFitness/mediaBestFitness.length;
        
        for (int i = 0; i < mediaMedia.length; i++) {
			this.fitnessMedio += mediaMedia[i];
		}
        	this.fitnessMedio = this.fitnessMedio/mediaMedia.length;
        	
        for (int i = 0; i < mediaConvergencia.length; i++) {
			this.mediaConvergencia += mediaConvergencia[i];
		}
        
       // System.out.println(mediaConvergencia.length);
        
        this.mediaConvergencia = this.mediaConvergencia/mediaConvergencia.length;

       //this.mediaFitness.add(media/fitnessMedio.length);

	}


	public double getMediaBestFitness() {
		return mediaBestFitness;
	}


	public void setMediaBestFitness(double mediaBestFitness) {
		this.mediaBestFitness = mediaBestFitness;
	}


	public double getFitnessMedio() {
		return fitnessMedio;
	}


	public void setFitnessMedio(double fitnessMedio) {
		this.fitnessMedio = fitnessMedio;
	}


	public double getMediaConvergencia() {
		return mediaConvergencia;
	}


	public void setMediaConvergencia(double mediaConvergencia) {
		this.mediaConvergencia = mediaConvergencia;
	}


	public double getFinalbestIndivido() {
		return finalbestIndivido;
	}


	public void setFinalbestIndivido(double finalbestIndivido) {
		this.finalbestIndivido = finalbestIndivido;
	}
	
	


//	public static void main(String[] args) {
//		
//		Populacao population = new Populacao();
//		
//		int popSize = 10;
//		int popXbits = 5;
//		int popYbits = 5;
//		
//		
//	
//                
//
//		
//
//	}
	

}
