package model;

/**
 *
 * @author Alves
 * @author Rivaldo
 */
public class Compromisso {
    
    private int id_comp;
    private int id_med;
    private String descricao_comp;
    private String data_comp;
    private String hora_i_comp;
    private String hora_t_comp;
    private String estado = "pendente"; 

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_comp() {
        return id_comp;
    }

    public void setId_comp(int id_comp) {
        this.id_comp = id_comp;
    }

    public String getDescricao_comp() {
        return descricao_comp;
    }

    public void setDescricao_comp(String descricao_comp) {
        this.descricao_comp = descricao_comp;
    }

    public int getId_med() {
        return id_med;
    }

    public void setId_med(int id_med) {
        this.id_med = id_med;
    }

    public String getData_comp() {
        return data_comp;
    }

    public void setData_comp(String data_comp) {
        this.data_comp = data_comp;
    }

    public String getHora_i_comp() {
        return hora_i_comp;
    }

    public void setHora_i_comp(String hora_i_comp) {
        this.hora_i_comp = hora_i_comp;
    }

    public String getHora_t_comp() {
        return hora_t_comp;
    }

    public void setHora_t_comp(String hora_t_comp) {
        this.hora_t_comp = hora_t_comp;
    }
}
