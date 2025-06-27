package br.ufsm.csi.service;


import br.ufsm.csi.dao.MecanicoDAO;
import br.ufsm.csi.model.Mecanico;

import java.util.ArrayList;

public class MecanicoService {

    private static final MecanicoDAO dao = new MecanicoDAO();

    public ArrayList<Mecanico> listar() {
        return dao.listar();
    }

    public Mecanico buscar(int id) {
        return dao.buscar(id);
    }

    public String inserir(Mecanico mecanico) {
        return dao.inserir(mecanico);
    }

    public String alterar(Mecanico mecanico) {
        return dao.alterar(mecanico);
    }

    public String excluir(int id) {
        if (dao.excluir(id)) {
            return "Mecânico excluído com sucesso!";
        } else {
            return "Erro ao excluir mecânico.";
        }
    }

}
