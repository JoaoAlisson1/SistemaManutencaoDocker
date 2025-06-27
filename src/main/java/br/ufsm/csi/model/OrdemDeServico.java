package br.ufsm.csi.model;
import java.time.LocalDate;

public class OrdemDeServico {

    private int id;
    private String descricaoServico;
    private String tipoManutencao;
    private LocalDate dataSolicitacao;
    private LocalDate dataConclusao;
    private String statusOrdem; //  Enum se preferir
    private Aeronave aeronave;
    private Mecanico mecanico;

    public OrdemDeServico(int id, String descricaoServico, LocalDate dataSolicitacao,
                          LocalDate dataConclusao, String status,
                          Aeronave aeronave, Mecanico mecanico) {
        this.id = id;
        this.descricaoServico = descricaoServico;
        this.dataSolicitacao = dataSolicitacao;
        this.dataConclusao = dataConclusao;
        this.statusOrdem = status;
        this.aeronave = aeronave;
        this.mecanico = mecanico;
    }
    public OrdemDeServico() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public String getTipoManutencao() {
        return tipoManutencao;
    }

    public void setTipoManutencao(String tipoManutencao) {
        this.tipoManutencao = tipoManutencao;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public String getStatus() {
        return statusOrdem;
    }

    public void setStatus(String status) {
        this.statusOrdem = status;
    }

    public Aeronave getAeronave() {
        return aeronave;
    }

    public void setAeronave(Aeronave aeronave) {
        this.aeronave = aeronave;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }
}
