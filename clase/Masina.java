package clase;

public class Masina extends Vehicul {

	private String model;
	private double consum;
	private float viteza_medie;

	public float getViteza_medie() {
		return viteza_medie;
	}

	public void setViteza_medie(float viteza_medie) {
		this.viteza_medie = viteza_medie;
	}

	public void setConsum(double consum) {
		this.consum = consum;
	}

	public Masina(String model,String id, double consum, float viteza_medie) {
		super(id);
		this.model = model;
		this.consum = consum;
		this.viteza_medie = viteza_medie;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	@Override
	public String toString() {
		return "Masina [model=" + model + ", consum_mediu=" + consum + ", viteza_medie=" + viteza_medie + ", "
				+ "Id="
				+ getId() + "]";
	}

	public double getConsum() {
		return consum;
	}
	public void setConsum(float consum) {
		this.consum = consum;
	}
	public Masina()
	{
	super();
	model="";
	consum=0;
	}
	@Override
	public double consum_mediu(double x) {
		
		return (this.consum*x)/100;
	}

}
