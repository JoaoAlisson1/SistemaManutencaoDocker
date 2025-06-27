package br.ufsm.csi.dao;

import br.ufsm.csi.model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {

    public ArrayList<Usuario> listar() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM Usuario";

        // Usando try-with-resources para garantir o fechamento automático dos recursos
        try (
                Connection conn = ConectaBD.conectarBancoPostgres();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setAtivo(rs.getBoolean("ativo"));

                usuarios.add(u);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao conectar");
            e.printStackTrace();
        }

        return usuarios;
    }

    public String inserir(Usuario usuario) {

        String sql = "INSERT INTO Usuario (email, senha, ativo) VALUES (?, ?, ?)";
        // Usando try-with-resources para garantir o fechamento automático dos recursos
        try (
                Connection conn = ConectaBD.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            stmt.setBoolean(3, usuario.isAtivo());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao inserir usuario");
        }

        return "Inserido com sucesso";
    }

    public String alterar(Usuario usuario) {

        String sql = "UPDATE Usuario SET email = ?, senha = ?, ativo = ? WHERE id = ?";

        // Usando try-with-resources
        try (
                Connection conn = ConectaBD.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            stmt.setBoolean(3, usuario.isAtivo());
            stmt.setInt(4, usuario.getId());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao alterar usuario");
        }

        return "Usuario alterado com sucesso";
    }

    public boolean excluir(int id) {

        String sql = "DELETE FROM Usuario WHERE id = ?";
        try (
                Connection conn = ConectaBD.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            stmt.execute();
            return stmt.getUpdateCount() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao excluir usuario: " + e.getMessage());
            return false;
        }
    }

    public Usuario buscar(int id) {
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM Usuario WHERE id = ?";

        try (
                Connection conn = ConectaBD.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt("id"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setAtivo(rs.getBoolean("ativo"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao buscar usuario");
        }

        return usuario;
    }

    public Usuario buscar(String email) {
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM Usuario WHERE email = ?";

        try (
                Connection conn = ConectaBD.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt("id"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setAtivo(rs.getBoolean("ativo"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao buscar usuario");
        }

        return usuario;
    }


}
