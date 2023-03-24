package sistema.modelAdmin;
public class Camas {
    private int numero_cama=-1,id_planta,oxigeno,termometro;
    private String caracteristicas;

    public void setNumero_cama(int numero_cama) {
        this.numero_cama = numero_cama;
    }
    public void setId_planta(int id_planta) {
        this.id_planta = id_planta;
    }
    public void setOxigeno(int oxigeno) {
        this.oxigeno = oxigeno;
    }
    public void setTermometro(int termometro) {
        this.termometro = termometro;
    }
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public int getNumero_cama() {
        return numero_cama;
    }
    public int getId_planta() {
        return id_planta;
    }
    public int getOxigeno() {
        return oxigeno;
    }
    public int getTermometro() {
        return termometro;
    }
    public String getCaracteristicas() {
        return caracteristicas;
    }
    
    
}
