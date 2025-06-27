package br.ufsm.csi.service;

import br.ufsm.csi.dao.MecanicoDAO;
import br.ufsm.csi.dao.OrdemDeServicoDAO;
import br.ufsm.csi.model.Mecanico;
import br.ufsm.csi.model.OrdemDeServico;

import java.util.ArrayList;

public class OrdemDeServicoService {

    private OrdemDeServicoDAO dao = new OrdemDeServicoDAO();
    private MecanicoDAO mecanicoDAO = new MecanicoDAO();

    public ArrayList<OrdemDeServico> listar() {
        return dao.listar();
    }

    public OrdemDeServico buscar(int id) {
        return dao.buscar(id);
    }

    public String inserir(OrdemDeServico ordem) {

        Mecanico mecanico = mecanicoDAO.buscar(ordem.getMecanico().getId());

        if (mecanico == null) {
            return "Erro: Mecânico não encontrado.";
        }
        if (!mecanico.isAtivo()) {
            return "Erro: Não é possível atribuir ordem a um mecânico inativo.";
        }
        return dao.inserir(ordem);
    }

    public String alterar(OrdemDeServico ordem) {

        Mecanico mecanico = mecanicoDAO.buscar(ordem.getMecanico().getId());

        if (mecanico == null) {
            return "Erro: Mecânico não encontrado.";
        }
        if (!mecanico.isAtivo()) {
            return "Erro: Não é possível atribuir ordem a um mecânico inativo.";
        }
        return dao.alterar(ordem);
    }

    public String excluir(int id) {
        return dao.excluir(id);
    }
}
