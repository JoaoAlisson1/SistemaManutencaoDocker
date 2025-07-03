package br.ufsm.csi.controller;

import br.ufsm.csi.model.Usuario;
import br.ufsm.csi.service.LoginService;
import br.ufsm.csi.service.UsuarioService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        System.out.println("Email: "+email+" - senha: "+senha);

        RequestDispatcher dispatcher;

        if(new LoginService().autenticar(email, senha)) {
            dispatcher = req.getRequestDispatcher("WEB-INF/pages/dashbord.jsp");
            dispatcher.forward(req, resp);
        }else {
            dispatcher = req.getRequestDispatcher("index.jsp");
            req.setAttribute("msg","Login ou senha incorreto!");
            dispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Execuntado o servelt login...");

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");

        out.println("<h1>LISTA DE USUARIOS</h1>");

        for(Usuario u : new UsuarioService().listar()){
            out.println("<h3>");
            out.println("Email: "+u.getEmail());
            out.println("</h3>");
        }
        out.println("</body>");
        out.println("</html>");

    }

}
