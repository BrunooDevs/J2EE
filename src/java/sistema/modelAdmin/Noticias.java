package sistema.modelAdmin;

public class Noticias {
  private String titulo,descripcion;
  private int id_noticia=-1;

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getId_noticia() {
        return id_noticia;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String Descripcion) {
        this.descripcion = Descripcion;
    }

    public void setId_noticia(int id_noticia) {
        this.id_noticia = id_noticia;
    }

    
}
