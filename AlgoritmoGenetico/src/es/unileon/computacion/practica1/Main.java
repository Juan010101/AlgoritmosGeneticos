package es.unileon.computacion.practica1;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> aptitudes = new ArrayList<Integer>(); 
		
		List<Integer> avgAptitudes = new ArrayList<Integer>(); 
		
		List<Integer> mejorApt = new ArrayList<Integer>();
		
		ArrayList<Cromosoma> pobl = new ArrayList<Cromosoma>();
		
		int iteracion = 0, contadorGeneraciones = 1, aux = 0, max = 0, index = 0, auxiliar = 0, acaba = 0, a = 0, m = 0, media = 0, divide = 0, maxMed = 0, auxiliarMedia = 0, auxMed = 0;
		//Se crea la poblacion aleatoria
		Poblacion poblacion = new Poblacion(20,4);
		
		//Evaluar Poblacion
		aptitudes.removeAll(aptitudes);
		aptitudes = poblacion.evaluaAptitudes();
		//Procedemos a seleccionar
		
		System.out.println("\nPrimera Seleccion\n");
		
		System.out.println(poblacion.toString());
		
		System.out.println("\nPrimeras Aptitudes\n");
		
		for(int i = 0;i < aptitudes.size();i++)
		{
			System.out.println("Aptitud cromosoma "+ (i+1) +": "+aptitudes.get(i));
		}
		
		//Bucle intentos
		do {
		System.out.println("\nGeneracion "+ contadorGeneraciones +"\n");	
			
		pobl = poblacion.seleccionar();
		
		System.out.println("\nNueva Seleccion\n");
		
		for(int i = 0;i < pobl.size();i++)
		{
			System.out.println("Cromosoma " + (i+1)+ ":" + pobl.get(i));
		}
		
		//Procedemos con el crossover
		
		System.out.println("\nNueva Seleccion *WITH CROSSOVER\n");
		
		poblacion.crossover();
		
		System.out.println(poblacion.toString());
		
		//Procedemos con la mutacion
		
		System.out.println("\nNueva Seleccion *WITH MUTACION\n");
		
		poblacion.mutacion();
		
		System.out.println(poblacion.toString());
		
		//Evaluamos de nuevo
		aptitudes.removeAll(aptitudes);
		aptitudes = poblacion.evaluaAptitudes();
		
		System.out.println("\nNuevas Aptitudes\n");
		
		for(int i = 0;i < aptitudes.size();i++)
		{
			System.out.println("Aptitud cromosoma "+ (i+1) +": "+aptitudes.get(i));
		}
		
		iteracion++;
		
		for(int i = 0;i < aptitudes.size();i++)
		{
			aux = aptitudes.get(i);
			if(aux > max)
			{
				max = aux;
				auxiliar = contadorGeneraciones;
			}
			media = media + aptitudes.get(i);
		}
		divide = media / aptitudes.size();
		if(divide > maxMed)
		{
			maxMed = divide;
			auxiliarMedia = contadorGeneraciones;
		}
		avgAptitudes.add(divide);
		media = 0;
		divide = 0;
		
		for(int i = 0;i < aptitudes.size();i++) 
		{
			a = aptitudes.get(i);
			if(a > m)
			{
				m = a;
			}
		}
		mejorApt.add(m);
		m = 0;
		
		contadorGeneraciones++;
		
		//Condiciones de parada
		//Con auxliar media si la aptitud media de la poblacion no mejora en minimo 6 generaciones el algoritmo acaba
		//Con auxliar si la mejor aptitud de la poblacion no mejora en minimo 6 generaciones el algoritmo acaba
		if(iteracion - auxiliarMedia > 10)
		{
			acaba = 1;
			System.out.println("\nEl algoritmo acaba por que ha dejado de ser efectivo, ultima iteracion: " + iteracion);
		}
		
		}while(iteracion < 20 && acaba == 0);
		
		//Escribimos solucion
		aux = 0;
		max = 0;
		for(int i = 0;i < aptitudes.size();i++)
		{
			aux = aptitudes.get(i);
			if(aux > max)
			{
				max = aux;
				index = i;
			}
		}
		
		System.out.println("\nRESULTADO FINAL\n");
		
		System.out.println("Cromosoma final: " + "Cromosoma "+ (index+1) +poblacion.getListaCromosomas().get(index).toString());
		System.out.println("Aptitud final: " + max);
		System.out.println("Generacion del mejor cromosoma: "+ auxiliar);
		
		
		//GRAFICO
		//Linea red se refiera a la media de las aptitudes por generacion
		//LInea green se refiera a la mejor aptitud de cada generacion
		DrawGraph mainPanel1 = new DrawGraph(mejorApt,1,avgAptitudes);
		mainPanel1.main(args);
		
	}

}
