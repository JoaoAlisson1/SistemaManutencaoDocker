package br.ufsm.csi.controller;

import br.ufsm.csi.model.Aeronave;
import br.ufsm.csi.model.Mecanico;
import br.ufsm.csi.model.OrdemDeServico;
import br.ufsm.csi.service.AeronaveService;
import br.ufsm.csi.service.MecanicoService;
import br.ufsm.csi.service.OrdemDeServicoService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("servico")
public class OrdemDeServicoServlet extends HttpServlet {

    private static final OrdemDeServicoService service = new OrdemDeServicoService();
    private static final AeronaveService aeronaveService = new AeronaveService();
    private static final MecanicoService mecanicoService = new MecanicoService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");      // para interpretar corretamente os parâmetros com acento
        resp.setContentType("text/html;charset=UTF-8");  // garante resposta em UTF-8
        resp.setCharacterEncoding("UTF-8");

        OrdemDeServico ordem = new OrdemDeServico();

        ordem.setDescricaoServico(req.getParameter("descricaoServico"));
        ordem.setTipoManutencao(req.getParameter("tipoManutencao"));
        ordem.setStatus(req.getParameter("status"));

        ordem.setDataSolicitacao(LocalDate.parse(req.getParameter("dataSolicitacao")));

        String dataConclusaoStr = req.getParameter("dataConclusao");
        if (dataConclusaoStr != null && !dataConclusaoStr.isEmpty()) {
            ordem.setDataConclusao(LocalDate.parse(dataConclusaoStr));
        }

        Aeronave aeronave = new Aeronave();
        aeronave.setId(Integer.parseInt(req.getParameter("aeronave_id")));
        ordem.setAeronave(aeronave);

        Mecanico mecanico = new Mecanico();
        mecanico.setId(Integer.parseInt(req.getParameter("mecanico_id")));
        ordem.setMecanico(mecanico);

        String idStr = req.getParameter("id");
        String retorno;

        if (idStr != null && !idStr.isEmpty()) {
            ordem.setId(Integer.parseInt(idStr));
            retorno = service.alterar(ordem);
        } else {
            retorno = service.inserir(ordem);
        }

        req.setAttribute("msg", retorno);
        req.setAttribute("ordens", service.listar());
        req.setAttribute("aeronaves", aeronaveService.listar());
        req.setAttribute("mecanicos", mecanicoService.listar());

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/servicos.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String opcao = req.getParameter("opcao");
        String info = req.getParameter("info");

        req.setCharacterEncoding("UTF-8");      // para interpretar corretamente os parâmetros com acento
        resp.setContentType("text/html;charset=UTF-8");  // garante resposta em UTF-8
        resp.setCharacterEncoding("UTF-8");

        if (opcao != null) {
            if (opcao.equals("editar")) {
                OrdemDeServico ordem = service.buscar(Integer.parseInt(info));
                req.setAttribute("ordemEditar", ordem);
            } else if (opcao.equals("excluir")) {
                String msg = service.excluir(Integer.parseInt(info));
                req.setAttribute("msg", msg);
            }
        }

        req.setAttribute("ordens", service.listar());
        req.setAttribute("aeronaves", aeronaveService.listar());
        req.setAttribute("mecanicos", mecanicoService.listar());

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/servicos.jsp");
        rd.forward(req, resp);
    }

}
