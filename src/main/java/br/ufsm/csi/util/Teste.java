package br.ufsm.csi.util;

import br.ufsm.csi.dao.AeronaveDAO;
import br.ufsm.csi.dao.MecanicoDAO;
import br.ufsm.csi.dao.OrdemDeServicoDAO;
import br.ufsm.csi.dao.UsuarioDAO;
import br.ufsm.csi.model.Aeronave;
import br.ufsm.csi.model.Mecanico;
import br.ufsm.csi.model.OrdemDeServico;
import br.ufsm.csi.model.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;

public class Teste {

    public static void main(String[] args) {

        //TestaGetAeronaves();
        //testesMecanicoDAO();
        //TestaOrdemDeServico();
        testesUsuarioDAO();

    }

    public static void TestaGetAeronaves() {

        AeronaveDAO dao = new AeronaveDAO();

        //Criar aeronave
        Aeronave aeronave = new Aeronave();

        aeronave.setModelo("A320");
        aeronave.setFabricante("Airbus");
        aeronave.setAnoFabricacao(2010);
        aeronave.setRegistroNacional("PT-MZM");

        dao.inserir(aeronave);

        //Listar aeronave
        imprimirAeronaves(dao.listar());

        //Alterar usuario
        /*aeronave.setId(1);
        aeronave.setModelo("1000");
        aeronave.setFabricante("Boeing EDITADO");
        aeronave.setAnoFabricacao(2025);
        aeronave.setRegistroNacional("teste");


        dao.alterar(aeronave);

        //Listar aeronave
        imprimirAeronaves(dao.listar());*/

    }


    public static void testesMecanicoDAO() {

        MecanicoDAO mecanicoDAO = new MecanicoDAO();


        //Criar mecanico
        Mecanico mecanico = new Mecanico();


        mecanico.setNome("Matheus Cunha");
        mecanico.setRegistroAnac("ANAC321");
        mecanico.setEspecialidade("Fuselagem");
        mecanico.setAtivo(true);


        mecanicoDAO.inserir(mecanico);

        //Listar mecanicos
        imprimirMecanicos(mecanicoDAO.listar());

        //Alterar cliente
        mecanico.setId(1);
        mecanico.setNome("Matheus editado");

        mecanicoDAO.alterar(mecanico);

        //Listar cliente
        imprimirMecanicos(mecanicoDAO.listar());
    }

    public static void TestaOrdemDeServico() {
        AeronaveDAO aeronaveDAO = new AeronaveDAO();
        MecanicoDAO mecanicoDAO = new MecanicoDAO();
        OrdemDeServicoDAO osDAO = new OrdemDeServicoDAO();

        // Buscar aeronaves existentes
        ArrayList<Aeronave> aeronaves = aeronaveDAO.listar();
        if (aeronaves.isEmpty()) {
            System.out.println("Nenhuma aeronave cadastrada. Cadastre uma antes.");
            return;
        }
        Aeronave aeronaveSelecionada = aeronaves.get(0); // pegar a primeira, por exemplo

        // Buscar mecânicos existentes
        ArrayList<Mecanico> mecanicos = mecanicoDAO.listar();
        if (mecanicos.isEmpty()) {
            System.out.println("Nenhum mecânico cadastrado. Cadastre um antes.");
            return;
        }
        Mecanico mecanicoSelecionado = mecanicos.get(0); // pegar o primeiro, por exemplo

        // Criar a ordem de serviço usando os objetos buscados
        OrdemDeServico ordem = new OrdemDeServico();
        ordem.setDescricaoServico("Revisão geral");
        ordem.setTipoManutencao("PREVENTIVA");
        ordem.setDataSolicitacao(LocalDate.now());
        ordem.setDataConclusao(null);
        ordem.setStatus("PENDENTE");
        ordem.setAeronave(aeronaveSelecionada);
        ordem.setMecanico(mecanicoSelecionado);

        // Inserir no banco
        osDAO.inserir(ordem);

        // Listar e imprimir ordens para verificar
        imprimirOrdensDeServico(osDAO.listar());
    }

    public static void testesUsuarioDAO() {

        UsuarioDAO dao = new UsuarioDAO();

        //Criar usuario
        Usuario usuario = new Usuario();

        usuario.setEmail("alencar@teste");
        usuario.setSenha("123");
        usuario.setAtivo(true);

        dao.inserir(usuario);

        //Listar usuario
        imprimirUsuarios(dao.listar());

        //Alterar usuario
        /*usuario.setId(1);
        usuario.setEmail("pedro@teste.editado");
        usuario.setSenha("senha editada");
        usuario.setAtivo(false);

        dao.alterar(usuario);

        //Listar usuario
        imprimirUsuarios(dao.listar());*/
    }


    public static void imprimirAeronave(Aeronave aeronave) {
        System.out.println(
                "Modelo: " + aeronave.getModelo()
                        + " Fabricante: " + aeronave.getFabricante()
                        + " Ano de Fabricação: " + aeronave.getAnoFabricacao()
                        + " Registro Nacional: " + aeronave.getRegistroNacional());
    }

    public static void imprimirAeronaves(ArrayList<Aeronave> aeronaves) {
        for ( Aeronave aeronave: aeronaves) {
            imprimirAeronave(aeronave);
        }
    }

    public static void imprimirMecanico(Mecanico mecanico) {
        System.out.println(
                "Id: " + mecanico.getId()
                        + " Nome: " + mecanico.getNome()
                        + " Registro Anac: " + mecanico.getRegistroAnac()
                        + " Especialidade: " + mecanico.getEspecialidade());

    }

    public static void imprimirMecanicos(ArrayList<Mecanico> mecanicos) {
        for (Mecanico mecanico : mecanicos) {
            imprimirMecanico(mecanico);
        }
    }

    public static void imprimirOrdensDeServico(ArrayList<OrdemDeServico> ordens) {
        for (OrdemDeServico os : ordens) {
            System.out.println("ID Ordem: " + os.getId());
            System.out.println("Descrição: " + os.getDescricaoServico());
            System.out.println("Tipo Manutenção: " + os.getTipoManutencao());
            System.out.println("Data Solicitação: " + os.getDataSolicitacao());
            System.out.println("Data Conclusão: " + (os.getDataConclusao() != null ? os.getDataConclusao() : "Ainda não concluída"));
            System.out.println("Status: " + os.getStatus());

            // Dados da Aeronave associada
            if (os.getAeronave() != null) {
                System.out.println("Aeronave:");
                System.out.println("  ID: " + os.getAeronave().getId());
                System.out.println("  Modelo: " + os.getAeronave().getModelo());
                System.out.println("  Fabricante: " + os.getAeronave().getFabricante());
                System.out.println("  Ano Fabricação: " + os.getAeronave().getAnoFabricacao());
                System.out.println("  Registro Nacional: " + os.getAeronave().getRegistroNacional());
            } else {
                System.out.println("Aeronave: Não associada");
            }

            // Dados do Mecânico associado
            if (os.getMecanico() != null) {
                System.out.println("Mecânico:");
                System.out.println("  ID: " + os.getMecanico().getId());
                System.out.println("  Nome: " + os.getMecanico().getNome());
                System.out.println("  Registro ANAC: " + os.getMecanico().getRegistroAnac());
                System.out.println("  Especialidade: " + os.getMecanico().getEspecialidade());
                System.out.println("  Ativo: " + (os.getMecanico().isAtivo() ? "Sim" : "Não"));
            } else {
                System.out.println("Mecânico: Não associado");
            }

            System.out.println("-------------------------------------------");
        }
    }

    public static void imprimirUsuario(Usuario usuario) {
        System.out.println(
                "Id: " + usuario.getId()
                        + " email: " + usuario.getEmail()
                        + " senha: " + usuario.getSenha());
    }

    public static void imprimirUsuarios(ArrayList<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            imprimirUsuario(usuario);
        }
    }
}
