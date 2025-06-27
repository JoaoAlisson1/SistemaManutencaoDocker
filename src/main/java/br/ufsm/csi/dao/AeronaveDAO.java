package br.ufsm.csi.dao;

import br.ufsm.csi.model.Aeronave;
import br.ufsm.csi.dao.ConectaBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AeronaveDAO {

    public ArrayList<Aeronave> listar() { // Listagem do banco

        ArrayList<Aeronave> aeronaves = new ArrayList<>();

        String sql = "SELECT * FROM Aeronave";


        try( Connection conn = ConectaBD.conectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Aeronave aeronave = new Aeronave();

                aeronave.setId(rs.getInt("id"));
                aeronave.setModelo(rs.getString("modelo"));
                aeronave.setFabricante(rs.getString("fabricante"));
                aeronave.setAnoFabricacao(rs.getInt("anoFabricacao"));
                aeronave.setRegistroNacional(rs.getString("registroNacional"));
                aeronaves.add(aeronave);

            }

          } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();
        }

        return aeronaves;
    }

    public String inserir(Aeronave aeronave) {

        String sql = "INSERT INTO aeronave(modelo, fabricante, anoFabricacao, registroNacional) VALUES(?,?,?,?)";



        try(Connection conn = ConectaBD.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aeronave.getModelo());
            stmt.setString(2, aeronave.getFabricante());
            stmt.setInt(3, aeronave.getAnoFabricacao());
            stmt.setString(4, aeronave.getRegistroNacional());
            stmt.execute();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao inserir aeronave: " + e.getMessage());
            e.printStackTrace();
        }

            return "Aeronave inserida com sucesso!";
    }

    public String alterar(Aeronave aeronave) {

        String sql = "UPDATE Aeronave SET modelo = ?, fabricante = ?, anoFabricacao = ?, registroNacional = ? WHERE id = ?";

        // Usando try-with-resources
        try (
                Connection conn = ConectaBD.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, aeronave.getModelo());
            stmt.setString(2, aeronave.getFabricante());
            stmt.setInt(3, aeronave.getAnoFabricacao());
            stmt.setString(4, aeronave.getRegistroNacional());
            stmt.setInt(5, aeronave.getId());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao alterar usuario");
        }

        return "Usuario alterado com sucesso";
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM Aeronave WHERE id = ?";

        try (
                Connection conn = ConectaBD.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate(); // retorna quantas linhas foram afetadas

            return linhasAfetadas > 0; // retorna true se a exclusão foi bem-sucedida
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao excluir aeronave: " + e.getMessage());
            return false;
        }
    }


    public Aeronave buscar(int id) {
        Aeronave aeronave = null;
        String sql = "SELECT * FROM Aeronave WHERE id = ?";

        try (
                Connection conn = ConectaBD.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    aeronave = new Aeronave();
                    aeronave.setId(rs.getInt("id"));
                    aeronave.setModelo(rs.getString("modelo"));
                    aeronave.setFabricante(rs.getString("fabricante"));
                    aeronave.setAnoFabricacao(rs.getInt("anoFabricacao"));
                    aeronave.setRegistroNacional(rs.getString("registroNacional"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar aeronave: " + e.getMessage());
        }

        return aeronave;
    }

    public Aeronave buscar(String registroNacional) { // apagar talvez
        Aeronave aeronave = null;
        String sql = "SELECT * FROM Aeronave WHERE registroNacional = ?";

        try (
                Connection conn = ConectaBD.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, registroNacional);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    aeronave = new Aeronave();
                    aeronave.setId(rs.getInt("id"));
                    aeronave.setModelo(rs.getString("modelo"));
                    aeronave.setFabricante(rs.getString("fabricante"));
                    aeronave.setAnoFabricacao(rs.getInt("anoFabricacao"));
                    aeronave.setRegistroNacional(rs.getString("registroNacional"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar aeronave: " + e.getMessage());
        }

        return aeronave;
    }


}
