package sistema.modelAdmin;
public class Diagnosticos {
    private int folio=-1, numero_cama;
    private String medico,diagnostico,tratamiento;

    public int getFolio() {
        return folio;
    }

    public int getNumero_cama() {
        return numero_cama;
    }

    public String getMedico() {
        return medico;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public void setNumero_cama(int numero_cama) {
        this.numero_cama = numero_cama;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }
    
    
}
