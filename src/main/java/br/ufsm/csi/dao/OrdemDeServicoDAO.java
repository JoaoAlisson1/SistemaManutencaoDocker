package br.ufsm.csi.dao;

import br.ufsm.csi.model.Aeronave;
import br.ufsm.csi.model.Mecanico;
import br.ufsm.csi.model.OrdemDeServico;

import java.sql.*;
import java.util.ArrayList;

public class OrdemDeServicoDAO {

    public ArrayList<OrdemDeServico> listar() {
        ArrayList<OrdemDeServico> ordens = new ArrayList<>();
        String sql = "SELECT os.*, \n" +
                "               a.id AS aid, a.modelo, a.fabricante, a.anoFabricacao, a.registroNacional,\n" +
                "               m.id AS mid, m.nome, m.registroAnac, m.especialidade, m.ativo\n" +
                "        FROM OrdemDeServico os\n" +
                "        JOIN Aeronave a ON os.aeronave_id = a.id\n" +
                "        JOIN Mecanico m ON os.mecanico_id = m.id";


        try (
                Connection conn = ConectaBD.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                OrdemDeServico ordem = new OrdemDeServico();
                ordem.setId(rs.getInt("id"));
                ordem.setDescricaoServico(rs.getString("descricaoServico"));
                ordem.setTipoManutencao(rs.getString("tipoManutencao"));
                ordem.setDataSolicitacao(rs.getDate("dataSolicitacao").toLocalDate());

                Date dataConclusaoSql = rs.getDate("dataConclusao");
                if (dataConclusaoSql != null) {
                    ordem.setDataConclusao(dataConclusaoSql.toLocalDate());
                }

                ordem.setStatus(rs.getString("status"));

                // Aeronave completa
                Aeronave aeronave = new Aeronave();
                aeronave.setId(rs.getInt("aid"));
                aeronave.setModelo(rs.getString("modelo"));
                aeronave.setFabricante(rs.getString("fabricante"));
                aeronave.setAnoFabricacao(rs.getInt("anoFabricacao"));
                aeronave.setRegistroNacional(rs.getString("registroNacional"));
                ordem.setAeronave(aeronave);

                // Mecanico completo
                Mecanico mecanico = new Mecanico();
                mecanico.setId(rs.getInt("mid"));
                mecanico.setNome(rs.getString("nome"));
                mecanico.setRegistroAnac(rs.getString("registroAnac"));
                mecanico.setEspecialidade(rs.getString("especialidade"));
                mecanico.setAtivo(rs.getBoolean("ativo"));
                ordem.setMecanico(mecanico);

                ordens.add(ordem);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao listar ordens de serviço: " + e.getMessage());
            e.printStackTrace();
        }

        return ordens;
    }

    // Inserir nova ordem de serviço
    public String inserir(OrdemDeServico ordem) {
        String sql = "INSERT INTO OrdemDeServico (descricaoServico, tipoManutencao, dataSolicitacao, dataConclusao, status, aeronave_id, mecanico_id) VALUES (?, ?::tipomanutencao, ?, ?, ?::statusordem, ?, ?)";



        try (Connection conn = ConectaBD.conectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ordem.getDescricaoServico());
            stmt.setString(2, ordem.getTipoManutencao());
            stmt.setDate(3, java.sql.Date.valueOf(ordem.getDataSolicitacao()));

            if (ordem.getDataConclusao() != null) {
                stmt.setDate(4, java.sql.Date.valueOf(ordem.getDataConclusao()));
            } else {
                stmt.setNull(4, Types.DATE);
            }

            stmt.setString(5, ordem.getStatus());
            stmt.setInt(6, ordem.getAeronave().getId());
            stmt.setInt(7, ordem.getMecanico().getId());

            stmt.execute();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro ao inserir ordem de serviço: " + e.getMessage();
        }

        return "Ordem de serviço inserida com sucesso!";
    }


    public String alterar(OrdemDeServico ordem) {
        String sql = "UPDATE OrdemDeServico\n" +
                "SET descricaoServico = ?, tipoManutencao = ?::tipomanutencao, dataSolicitacao = ?, dataConclusao = ?, status = ?::statusordem, aeronave_id = ?, mecanico_id = ?\n" +
                "WHERE id = ?";



        try (Connection conn = ConectaBD.conectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ordem.getDescricaoServico());
            stmt.setString(2, ordem.getTipoManutencao());
            stmt.setDate(3, java.sql.Date.valueOf(ordem.getDataSolicitacao()));

            if (ordem.getDataConclusao() != null) {
                stmt.setDate(4, java.sql.Date.valueOf(ordem.getDataConclusao()));
            } else {
                stmt.setNull(4, Types.DATE);
            }

            stmt.setString(5, ordem.getStatus());
            stmt.setInt(6, ordem.getAeronave().getId());
            stmt.setInt(7, ordem.getMecanico().getId());
            stmt.setInt(8, ordem.getId());

            stmt.execute();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro ao alterar ordem de serviço: " + e.getMessage();
        }

        return "Ordem de serviço alterada com sucesso!";
    }

    public String excluir(int id) {
        String sql = "DELETE FROM OrdemDeServico WHERE id = ?";

        try (Connection conn = ConectaBD.conectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                return "Ordem de serviço excluída com sucesso!";
            } else {
                return "Ordem de serviço não encontrada para exclusão.";
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro ao excluir ordem de serviço: " + e.getMessage();
        }
    }

    public OrdemDeServico buscar(int id) {
        OrdemDeServico ordem = null;
        String sql = "SELECT os.*, " +
                "a.id AS aid, a.modelo, a.fabricante, a.anoFabricacao, a.registroNacional, " +
                "m.id AS mid, m.nome, m.registroAnac, m.especialidade, m.ativo " +
                "FROM OrdemDeServico os " +
                "JOIN Aeronave a ON os.aeronave_id = a.id " +
                "JOIN Mecanico m ON os.mecanico_id = m.id " +
                "WHERE os.id = ?";

        try (
                Connection conn = ConectaBD.conectarBancoPostgres();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ordem = new OrdemDeServico();
                    ordem.setId(rs.getInt("id"));
                    ordem.setDescricaoServico(rs.getString("descricaoServico"));
                    ordem.setTipoManutencao(rs.getString("tipoManutencao"));
                    ordem.setDataSolicitacao(rs.getDate("dataSolicitacao").toLocalDate());

                    Date dataConclusaoSql = rs.getDate("dataConclusao");
                    if (dataConclusaoSql != null) {
                        ordem.setDataConclusao(dataConclusaoSql.toLocalDate());
                    }

                    ordem.setStatus(rs.getString("status"));

                    Aeronave aeronave = new Aeronave();
                    aeronave.setId(rs.getInt("aid"));
                    aeronave.setModelo(rs.getString("modelo"));
                    aeronave.setFabricante(rs.getString("fabricante"));
                    aeronave.setAnoFabricacao(rs.getInt("anoFabricacao"));
                    aeronave.setRegistroNacional(rs.getString("registroNacional"));
                    ordem.setAeronave(aeronave);

                    Mecanico mecanico = new Mecanico();
                    mecanico.setId(rs.getInt("mid"));
                    mecanico.setNome(rs.getString("nome"));
                    mecanico.setRegistroAnac(rs.getString("registroAnac"));
                    mecanico.setEspecialidade(rs.getString("especialidade"));
                    mecanico.setAtivo(rs.getBoolean("ativo"));
                    ordem.setMecanico(mecanico);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar ordem de serviço: " + e.getMessage());
            e.printStackTrace();
        }

        return ordem;
    }


}
