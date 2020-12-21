package model;

/**
 *
 * @author Rivaldo
 */

public class Paciente {
    private int id_paciente;
    private String nome_paciente;
    private String cpf_paciente;
    private String rg_paciente;

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getNome_paciente() {
        return nome_paciente;
    }

    public void setNome_paciente(String nome_paciente) {
        this.nome_paciente = nome_paciente;
    }

    public String getCpf_paciente() {
        return cpf_paciente;
    }

    public void setCpf_paciente(String cpf_paciente) {
        this.cpf_paciente = cpf_paciente;
    }

    public String getRg_paciente() {
        return rg_paciente;
    }

    public void setRg_paciente(String rg_paciente) {
        this.rg_paciente = rg_paciente;
    }
    
    
}
