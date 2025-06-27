package br.ufsm.csi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaBD {

    // Constantes de configuração do banco de dados
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/SistemaManutencao";
    private static final String USER = "postgres";
    private static final String SENHA = "1234";

    public static Connection conectarBancoPostgres()
            throws ClassNotFoundException, SQLException {

        // Carrega o driver (não necessário nas versões mais novas, mas pode ser útil por compatibilidade)
        Class.forName(DRIVER);
        System.out.println("Driver carregado com sucesso.");

        // Estabelece a conexão e retorna
        return DriverManager.getConnection(URL, USER, SENHA);
    }
}
