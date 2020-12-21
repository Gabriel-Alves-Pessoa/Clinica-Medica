package model;

/**
 *
 * @author Rivaldo
 */
public class Medico {
    
    private int id_med;
    private String cpf_med;
    private String rg_med;
    private String crm_med;
    private String nome_med;
    private String login_med;
    private String senha_med;

    public String getLogin_med() {
        return login_med;
    }

    public void setLogin_med(String login_med) {
        this.login_med = login_med;
    }

    public String getSenha_med() {
        return senha_med;
    }

    public void setSenha_med(String senha_med) {
        this.senha_med = senha_med;
    }

    public int getId_med() {
        return id_med;
    }

    public void setId_med(int id_med) {
        this.id_med = id_med;
    }

    public String getCpf_med() {
        return cpf_med;
    }

    public void setCpf_med(String cpf_med) {
        this.cpf_med = cpf_med;
    }

    public String getRg_med() {
        return rg_med;
    }

    public void setRg_med(String rg_med) {
        this.rg_med = rg_med;
    }

    public String getCrm_med() {
        return crm_med;
    }

    public void setCrm_med(String crm_med) {
        this.crm_med = crm_med;
    }

    public String getNome_med() {
        return nome_med;
    }

    public void setNome_med(String nome_med) {
        this.nome_med = nome_med;
    }
    
    
}
