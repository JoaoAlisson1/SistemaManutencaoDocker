package br.ufsm.csi.controller;

import br.ufsm.csi.model.Mecanico;
import br.ufsm.csi.service.MecanicoService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("mecanico")
public class MecanicoServlet extends HttpServlet {

    private static final MecanicoService service = new MecanicoService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");      // para interpretar corretamente os parâmetros com acento
        resp.setContentType("text/html;charset=UTF-8");  // garante resposta em UTF-8
        resp.setCharacterEncoding("UTF-8");

        String nome = req.getParameter("nome");
        String registroAnac = req.getParameter("registroAnac");
        String especialidade = req.getParameter("especialidade");
        boolean ativo = req.getParameter("ativo") != null;

        Mecanico mecanico = new Mecanico();
        mecanico.setNome(nome);
        mecanico.setRegistroAnac(registroAnac);
        mecanico.setEspecialidade(especialidade);
        mecanico.setAtivo(ativo);

        String idStr = req.getParameter("id");
        String retorno;

        if (idStr != null && !idStr.isEmpty()) {
            mecanico.setId(Integer.parseInt(idStr));
            retorno = service.alterar(mecanico);
        } else {
            retorno = service.inserir(mecanico);
        }

        req.setAttribute("retorno", retorno);
        req.setAttribute("mecanicos", service.listar());

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/mecanicos.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");      // para interpretar corretamente os parâmetros com acento
        resp.setContentType("text/html;charset=UTF-8");  // garante resposta em UTF-8
        resp.setCharacterEncoding("UTF-8");

        String opcao = req.getParameter("opcao");
        String info = req.getParameter("info");

        if (opcao != null) {
            if (opcao.equals("editar")) {
                Mecanico mecanico = service.buscar(Integer.parseInt(info));
                req.setAttribute("mecanicoEditar", mecanico);
            } else if (opcao.equals("excluir")) {
                String msg = service.excluir(Integer.parseInt(info));
                req.setAttribute("msg", msg);
            }
        }

        req.setAttribute("mecanicos", service.listar());

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/mecanicos.jsp");
        rd.forward(req, resp);
    }


}
