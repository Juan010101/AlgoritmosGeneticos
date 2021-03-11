package es.unileon.computacion.practica1;

import java.util.ArrayList;

public class Poblacion {

	private ArrayList<Cromosoma> poblacion;
	
	private ArrayList<Integer> aptitudes;
	
	private int nGenes;
	
	private int nCromosomas;
	
	public Poblacion(int numeroCromosomas,int nGenes) {
		this.nGenes = nGenes;
		this.nCromosomas = numeroCromosomas;
		this.poblacion = new ArrayList<Cromosoma>(this.nCromosomas);
		this.aptitudes = new ArrayList<Integer>(this.nCromosomas);
		this.creaPoblacionAleatoria();
	}
	
	public ArrayList<Cromosoma> creaPoblacionAleatoria(){
		Cromosoma crom = null;
		for(int i = 0;i < this.nCromosomas;i++)
		{
			crom = new Cromosoma(this.nGenes);
			poblacion.add(crom);
		}
		return this.poblacion;
	}
	
	public ArrayList<Integer> evaluaAptitudes(){
		for(int i = 0;i < this.poblacion.size();i++)
		{
			this.aptitudes.add(this.poblacion.get(i).evaluaMaximo());
		}
		return this.aptitudes;
	}
	
	public ArrayList<Cromosoma> seleccionar(){
		ArrayList<Cromosoma> pobl = new ArrayList<Cromosoma>(); 
		int sumatorio = 0, aux = 0, selecciona = 0, contador = 0;
		
		do {
		
		for(int i = 0;i < this.aptitudes.size();i++)
		{
			aux = this.aptitudes.get(i);
			sumatorio = sumatorio + aux;
		}
		selecciona = this.getRandomNumber(1, sumatorio);
		int a = 0, sum = 0;
		for(int i = 0;i < this.aptitudes.size();i++)
		{	
			if(i != 0)
			{	
				a = this.aptitudes.get(i) + this.aptitudes.get(i-1);
			}else
			{
				a = this.aptitudes.get(i);
			}
			sum = sum +a;
			if(i != 0 && i != this.aptitudes.size()-1)
			{
				if(selecciona >= sum && selecciona < sum+this.aptitudes.get(i+1))
				{
					pobl.add(this.poblacion.get(i));
					contador++;
				}
			}else if(i == 0)
			{
				if(selecciona >= 0 && selecciona < this.aptitudes.get(i)) 
				{
					pobl.add(this.poblacion.get(i));
					contador++;
				}
			}else if (i == this.aptitudes.size()-1)
			{
				if(selecciona >= sum && selecciona < sum+this.aptitudes.get(i))
				{
					pobl.add(this.poblacion.get(i));
					contador++;
				}
			}
		}
		
		}while(contador < this.nCromosomas);
		
		this.poblacion = pobl;
		
		return pobl;
	}
	
	public ArrayList<Cromosoma> crossover(){
		ArrayList<Cromosoma> pobl = new ArrayList<Cromosoma>();
		Cromosoma crom, cro;
		int pareja1 = 0, pareja2 = 1;
		int nRandom = this.getRandomNumber(1, this.nGenes);
		int probabilidad = this.getRandomNumber(1, 101);
		
		if(probabilidad <= 85)
		{
		System.out.println("\nCROSSOVER ACTIVO: "+ nRandom +"\n");
		for(int i = 0;i < this.poblacion.size()/2;i++)
		{
			crom = new Cromosoma(this.nGenes,0);
			cro = new Cromosoma(this.nGenes,0);
			
			for(int j = 0;j < nRandom;j++)
			{
				crom.getListGenes().add(this.poblacion.get(pareja2).getListGenes().get(j));
			}
			for(int aux = nRandom;aux < this.nGenes;aux++)
			{
				crom.getListGenes().add(this.poblacion.get(pareja1).getListGenes().get(aux));
			}
			pobl.add(crom);
			
			for(int j = 0;j < nRandom;j++)
			{
				cro.getListGenes().add(this.poblacion.get(pareja1).getListGenes().get(j));
			}
			for(int aux = nRandom;aux < this.nGenes;aux++)
			{
				cro.getListGenes().add(this.poblacion.get(pareja2).getListGenes().get(aux));
			}
			pobl.add(cro);
		
			pareja1 = pareja1 + 2;
			pareja2 = pareja2 +2;
			
		}
			this.poblacion = pobl;
		}
		
		return pobl;
	}
	
	public void mutacion(){
		ArrayList<Cromosoma> pobl = new ArrayList<Cromosoma>();
		int nRandom = 0, nGen = 0, nuevoGen = 0;
		boolean mutar = false;

		for(int i = 0;i < this.poblacion.size();i++)
		{
			nRandom = this.getRandomNumber(1, 101);
			nGen = this.getRandomNumber(1, this.nGenes);
			//Probabilidad de 0,01
			if(nRandom == 50)
			{
				System.out.println("\nMUTACION ACTIVA CROMOSOMA: "+ (i+1) +"\n");
				mutar = true;
			}
			if(mutar == true)
			{
				nuevoGen = this.getRandomNumber(1, 100);
				this.poblacion.get(i).getListGenes().remove(nGen); 
				this.poblacion.get(i).getListGenes().add(nGen, nuevoGen);
				mutar = false;
			}
		}
	}
	
	public String toString() {
		StringBuffer cadena = new StringBuffer();
		for(int i = 0;i < this.poblacion.size();i++)
		{
			cadena.append("Cromosoma "+(i+1)+":");
			cadena.append(this.poblacion.get(i).toString()+"\n");
		}
		
		return cadena.toString();
	}
	
	public ArrayList<Cromosoma> getListaCromosomas(){
		return this.poblacion;
	}
	
	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
}
