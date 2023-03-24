package sistema.modelAdmin;
public class Plantas {
    private int id_planta=-1,numerocamas;
    private String nombre;    

    public int getId_planta() {
        return id_planta;
    }
    public int getNumerocamas() {
        return numerocamas;
    }
    public String getNombre() {
        return nombre;
    }

    public void setId_planta(int id_planta) {
        this.id_planta = id_planta;
    }
    public void setNumerocamas(int numerocamas) {
        this.numerocamas = numerocamas;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
