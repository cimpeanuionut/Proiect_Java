package clase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Inregistrare extends Thread implements Timp {

	private String id;
	private Timestamp t;
	private int viteza;
	public static Map<Masina,List<Inregistrare>> m=new Hashtable<Masina,List<Inregistrare>>() ;
	
	public String getId_masina() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Timestamp getT() {
		return t;
	}
	public void setT(Timestamp t) {
		this.t = t;
	}
	public int getViteza() {
		return viteza;
	}
	public void setViteza(int viteza) {
		this.viteza = viteza;
	}
	public Inregistrare(String id,Timestamp t, int viteza) {
		this.id = id;
		this.t = t;
		this.viteza = viteza;
	}
	public Inregistrare() {
		
	}
	@Override
	public String Afiseaza_data_curenta() {
		
		Date d=new Date(this.t.getTime());
		return (d.toGMTString());
	}
	public static boolean isParsable(String input){
	    boolean parsable = true;
	    try{
	        Integer.parseInt(input);
	    }catch(NumberFormatException e){
	        parsable = false;
	    }

	    return parsable;
	}
	public void run()
	{

		try {
			prelucrare();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static double calculeaza_distanta(List<Inregistrare> k,float x)
	{
		long diferenta=(k.get(k.size()-1).t.getTime()-k.get(0).t.getTime());
		return (double)x*diferenta/3600000;
	}
	public  void prelucrare() throws IOException
	{			
			int nr;
	    System.out.println("Cate autovehicule ati inregistrat?");
	    Scanner sc=new Scanner(System.in);
	    nr=Integer.parseInt(sc.nextLine());
	    for(int i=0;i<nr;i++)
	    {
	    	List<Inregistrare> l=new Vector<Inregistrare>();
	    	System.out.println("Nume fisier "+i+": ");
	    	FileReader fr=new FileReader(sc.nextLine());
		    BufferedReader br=new BufferedReader(fr);
		    String rand=br.readLine();
		    String[] s;
		    System.out.println("Model: ");
		    String model;
		    model=sc.nextLine();
		    System.out.println("Consum: ");
		    Double consum;
		    consum=Double.parseDouble(sc.nextLine());
		    while(rand!=null)
		    {
		    	s=rand.split(";");
		    	if(isParsable(s[2]))
		    	{
		    		if(Integer.parseInt(s[2])>=0)
		    		{
		    			l.add(new Inregistrare(s[0],new Timestamp(Long.parseLong(s[1])),Integer.parseInt(s[2])));
		    		}
		    	}
		 rand=br.readLine();
		    }
		    br.close();
		    fr.close();
		    float medie=0;
		    int max=0;
		    for(Inregistrare inr:l)
		    {
		    medie=medie+inr.viteza;
		    if(max<inr.viteza)
		    {
		    	max=inr.viteza;
		    }
		    }
		    medie=medie/l.size();
		    FileWriter fw=new FileWriter((i+1)+"result.txt");
		    BufferedWriter bw=new BufferedWriter(fw);
		    bw.write("Detalii automobil id:"+l.get(0).id);
		    bw.newLine();
		    bw.write("Viteza medie este:"+medie);
		    bw.newLine();
		    bw.write("Viteza maxima este:"+max);
		    bw.newLine();
		    bw.write("Inregistrarea s-a incheiat la data:"+l.get(l.size()-1).Afiseaza_data_curenta());
		    bw.close();
		    fw.close();
		    m.put(new Masina(model,l.get(0).getId_masina(),consum,medie),l);
	    }
	    sc.close();
	    raport_consum(); 
	    
	}
	public static void raport_consum() throws IOException
	{FileWriter fw=new FileWriter("raport_consum.txt");
	BufferedWriter bw=new BufferedWriter(fw);
		for(Map.Entry<Masina,List<Inregistrare>>e : m.entrySet())
		{
			bw.write(e.getKey().toString());
			System.out.println(e.getKey().toString());
			bw.newLine();
			bw.write("A consumat in medie"+(float)e.getKey().consum_mediu(calculeaza_distanta(e.getValue(), e.getKey().getViteza_medie()))+"L");
			System.out.println(("A consumat in medie"+(float)e.getKey().consum_mediu(calculeaza_distanta(e.getValue(), e.getKey().getViteza_medie()))+"L"));
			bw.newLine();
			bw.write(" Inregistrarea facandu-se de la data "+e.getValue().get(0).Afiseaza_data_curenta()+" pana la data "+e.getValue().get(e.getValue().size()-1).Afiseaza_data_curenta());
			bw.newLine();
		}
	bw.close();
	fw.close();
		}
	@Override
	public String toString() {
		return "Inregistrare [id=" + id + ", t=" + t + ", viteza=" + viteza + "]";
	}

}
