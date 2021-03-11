package es.unileon.computacion.practica1;

import java.util.ArrayList;

public class Cromosoma {
	
	private ArrayList<Integer> genes;
	
	private int numeroGenes;
	 
	public Cromosoma(int numeroGenes,int aux){
		this.numeroGenes = numeroGenes;
		this.genes = new ArrayList<Integer>(this.numeroGenes);
	}

	public Cromosoma(int numeroGenes) {
		int aux = 0, minimoRango = 0, maximoRango = 0;
		this.numeroGenes = numeroGenes;
		this.genes = new ArrayList<Integer>(this.numeroGenes);
		for(int i = 0;i < this.numeroGenes;i++)
		{
			if(i == 0)
			{
				minimoRango = 1;
				maximoRango = 101;
			}else if(i == 1)
			{
				minimoRango = 1;
				maximoRango = 76;
			}else if(i == 2)
			{
				minimoRango = 1;
				maximoRango = 51;
			}else if(i == 3)
			{
				minimoRango = 1;
				maximoRango = 101;
			}
			aux = this.getRandomNumber(minimoRango, maximoRango);
			genes.add(aux);
		}
	}
	
	public ArrayList<Integer> getListGenes() {
		return this.genes;
	}
	
	public int evaluaMaximo() {
		int aux = 0, suma = 0;
		for(int i = 0;i < this.genes.size();i++)
		{
			aux = this.genes.get(i);
			suma = suma + aux;
		}
		return suma;
	}
	
	public String toString() {
		StringBuffer cadena = new StringBuffer();
		cadena.append("(");
		for(int i = 0;i < this.genes.size();i++)
		{
			cadena.append(this.genes.get(i));
			if(i != this.numeroGenes-1)
			{
				cadena.append(",");
			}
		}
		cadena.append(")");
		
		return cadena.toString();
	}
	
	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
}
