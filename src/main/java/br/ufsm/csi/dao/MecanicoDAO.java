package br.ufsm.csi.dao;



import br.ufsm.csi.model.Aeronave;
import br.ufsm.csi.model.Mecanico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MecanicoDAO {

    public ArrayList<Mecanico> listar() {
        ArrayList<Mecanico> mecanicos = new ArrayList<>();

        String sql = "SELECT * FROM Mecanico";

        try (
                Connection conn = ConectaBD.conectarBancoPostgres();
                PreparedStatement stmt = ((java.sql.Connection) conn).prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Mecanico mecanico = new Mecanico();
                mecanico.setId(rs.getInt("id"));
                mecanico.setNome(rs.getString("nome"));
                mecanico.setRegistroAnac(rs.getString("Registroanac"));
                mecanico.setEspecialidade(rs.getString("especialidade"));
                mecanico.setAtivo(rs.getBoolean("ativo"));

                mecanicos.add(mecanico);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao listar mecanicos");
        }

        return mecanicos;
    }

    public String inserir(Mecanico mecanico) {

        String sql = "INSERT INTO Mecanico(nome, registroAnac, especialidade, ativo) VALUES(?,?,?,?)";

        try (Connection conn = ConectaBD.conectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, mecanico.getNome());
            stmt.setString(2, mecanico.getRegistroAnac());
            stmt.setString(3, mecanico.getEspecialidade());
            stmt.setBoolean(4, mecanico.isAtivo());
            stmt.execute();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao inserir mecanico: " + e.getMessage());
            e.printStackTrace();
            return "Erro ao inserir mecânico.";
        }

        return "Mecânico inserido com sucesso!";
    }

    public String alterar(Mecanico mecanico) {

        String sql = "UPDATE Mecanico SET nome = ?, registroAnac = ?, especialidade = ?, ativo = ? WHERE id = ?";

        // Usando try-with-resources
        try (
                Connection conn = ConectaBD.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, mecanico.getNome());
            stmt.setString(2, mecanico.getRegistroAnac());
            stmt.setString(3, mecanico.getEspecialidade());
            stmt.setBoolean(4, mecanico.isAtivo());
            stmt.setInt(5, mecanico.getId());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao alterar mecanico");
        }

        return "Mecanico alterado com sucesso";
    }

    public boolean excluir(int id) {

        String sql = "DELETE FROM Mecanico WHERE id = ?";
        try (
                Connection conn = ConectaBD.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            stmt.execute();
            return stmt.getUpdateCount() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao excluir mecanico: " + e.getMessage());
            return false;
        }
    }

    public Mecanico buscar(int id) {
        Mecanico mecanico = null;
        String sql = "SELECT * FROM Mecanico WHERE id = ?";

        try (
                Connection conn = ConectaBD.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    mecanico = new Mecanico();
                    mecanico.setId(rs.getInt("id"));
                    mecanico.setNome(rs.getString("nome"));
                    mecanico.setRegistroAnac(rs.getString("registroAnac")); // Atenção: confere o nome no BD
                    mecanico.setEspecialidade(rs.getString("especialidade"));
                    mecanico.setAtivo(rs.getBoolean("ativo"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar mecânico: " + e.getMessage());
            e.printStackTrace();
        }

        return mecanico;
    }




}
