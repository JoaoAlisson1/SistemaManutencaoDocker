package br.ufsm.csi.controller;

import br.ufsm.csi.model.Aeronave;
import br.ufsm.csi.service.AeronaveService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("aeronave")
public class AeronaveServlet extends HttpServlet {

    private static AeronaveService service = new AeronaveService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String modelo = req.getParameter("modelo");
        String fabricante = req.getParameter("fabricante");
        String anoFabricacaoStr = req.getParameter("anoFabricacao");
        String registroNacional = req.getParameter("registroNacional");

        String idStr = req.getParameter("id");
        Aeronave aeronave = new Aeronave();
        aeronave.setModelo(modelo);
        aeronave.setFabricante(fabricante);
        aeronave.setAnoFabricacao(Integer.parseInt(anoFabricacaoStr));
        aeronave.setRegistroNacional(registroNacional);

        String retorno;

        if (idStr != null && !idStr.isEmpty()) {
            aeronave.setId(Integer.parseInt(idStr));
            retorno = service.alterar(aeronave);
        } else {
            retorno = service.inserir(aeronave);
        }

        req.setAttribute("retorno", retorno);
        req.setAttribute("aeronaves", service.listar());

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/aeronaves.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String opcao = req.getParameter("opcao");
        String info = req.getParameter("info");

        if (opcao != null) {
            if (opcao.equals("editar")) {
                Aeronave aeronave = service.buscar(Integer.parseInt(info));
                req.setAttribute("aeronaveEditar", aeronave);

                ArrayList<Aeronave> aeronaves = service.listar();
                req.setAttribute("aeronaves", aeronaves);

                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/aeronaves.jsp");
                rd.forward(req, resp);

            } else if (opcao.equals("excluir")) {
                String valor = service.excluir(Integer.parseInt(info));
                req.setAttribute("msg", valor);

                ArrayList<Aeronave> aeronaves = service.listar();
                req.setAttribute("aeronaves", aeronaves);

                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/aeronaves.jsp");
                rd.forward(req, resp);
            }
        } else {
            ArrayList<Aeronave> aeronaves = service.listar();
            req.setAttribute("aeronaves", aeronaves);

            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/aeronaves.jsp");
            rd.forward(req, resp);
        }
    }

}
