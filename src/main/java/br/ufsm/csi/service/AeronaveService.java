package br.ufsm.csi.service;

import br.ufsm.csi.dao.AeronaveDAO;
import br.ufsm.csi.model.Aeronave;

import java.util.ArrayList;

public class AeronaveService {

    private static AeronaveDAO dao = new AeronaveDAO();

    public String excluir(int id) {
        if (dao.excluir(id)) {
            return "Sucesso ao excluir aeronave";
        } else {
            return "Erro ao excluir aeronave";
        }
    }

    public ArrayList<Aeronave> listar() {
        return dao.listar();
    }

    public Aeronave buscar(int aeronaveId) {
        return dao.buscar(aeronaveId);
    }

    public String inserir(Aeronave aeronave) {
        return dao.inserir(aeronave);
    }

    public String alterar(Aeronave aeronave) {
        return dao.alterar(aeronave);
    }

}
