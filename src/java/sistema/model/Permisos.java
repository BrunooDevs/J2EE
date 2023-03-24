package sistema.model;

public class Permisos {

    public Permisos() {

    }

    private boolean select_info, update_info, insert_info, delete_info;

    private boolean isAdmin;
    

    public void setAdmin(boolean a) {
        this.isAdmin = a;
    }

    public boolean getAdmin() {
        return this.isAdmin;
    }
    
    public void setInfoInsert(boolean a)
    {
        this.select_info = a;
    }
    public boolean getInfoInsert()
    {
        return this.select_info;
    }  

    public void setInfoUpdate(boolean a)
    {
        this.update_info = a;
    }
    public boolean getinfoUpdate()
    {
        return this.update_info;
    }       

    public void setInfoSelect(boolean a)
    {
        this.insert_info = a;
    }
    public boolean getInfoSelect()
    {
        return this.insert_info;
    }       

    public void setInfoDelete(boolean a)
    {
        this.delete_info = a;
    }
    public boolean getInfoDelete()
    {
        return this.delete_info;
    }  
    

}
