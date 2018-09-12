package clase;

public abstract class Vehicul {
private String id;
public Vehicul()
{
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public Vehicul(String id) {
	this.id = id;
}
public abstract double consum_mediu(double x);
@Override
public String toString() {
	return "Vehicul [id=" + id + "]";
}
}
