package br.ufsm.csi.service;

import br.ufsm.csi.dao.UsuarioDAO;
import br.ufsm.csi.model.Usuario;

public class LoginService {

    public boolean autenticar(String email, String senha) {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.buscar(email);

        // Verifica se o usuário foi encontrado e se a senha bate
        if (usuario != null && usuario.getEmail() != null) {
            if (usuario.isAtivo() && usuario.getSenha().equals(senha)) {
                return true;
            }
        }

        return false;
    }
}
