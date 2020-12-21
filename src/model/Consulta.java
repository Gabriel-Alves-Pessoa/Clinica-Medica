package model;

/**
 *
 * @author Bianca Lopes
 */
public class Consulta {
    private int consulta_id;
    private int id_medico;
    private int id_paciente;
    private String horario_inicio;
    private String horario_termino;
    private String data;
    private String descricao;
    private String diagnostico;
    private String estado;

    public String getDescricao() {
        return descricao;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getHorario_inicio() {
        return horario_inicio;
    }

    public int getConsulta_id() {
        return consulta_id;
    }

    public void setConsulta_id(int consulta_id) {
        this.consulta_id = consulta_id;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public void setHorario_inicio(String horario_inicio) {
        this.horario_inicio = horario_inicio;
    }
     public String getHorario_termino() {
        return horario_termino;
    }

    public void setHorario_termino(String horario_termino) {
        this.horario_termino = horario_termino;
    }
     public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
