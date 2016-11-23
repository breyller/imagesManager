import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
* @author Bruno Lopes
* @author Bruno Reyller
* @author Henrique
* Classe que realiza a conexao com o banco de dados utilizado pelo programa.
*/
public class DbConnector {

    private static final String DRIVER = "com.mysql.jdbc.Driver"; /* Trava o driver para conexao com banco de dados (Usando para testes) */
    private static final String URL = "jdbc:mysql://db4free.net:3306/imagesmanager"; /* Trava o endereco do banco de dados (Usando para testes) */
    private static final String USER = "im_user"; /* Trava o usuario do baco de dados (Usando para testes) */
    private static final String PASS = "12345!@#$%"; /* Trava a senha do usuario do banco de dados (Usando para testes) */
    
    /*
    * Classe que envia os dados de conexao
    * @return - Retorna uma conexao usando o controlador do driver utilizado
    */
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASS);
    }
    /*
    * Funcao para encerrar a conexao com o banco de dados, evitando erros de stream. Importante: Sempre feche TODAS as conexoes usadas.
    * @param conn - Recebe um objeto de tipo Connection que já possui conexao com o Banco de dados
    */
    public static void closeConnection(Connection conn) throws SQLException{
        if(conn != null){
            conn.close();
        }
    }
    /*
    * Funcao sobrecarregada para encerrar a conexao com o banco de dados, evitando erros de stream. Importante: Sempre feche TODAS as conexoes usadas.
    * @param conn - Objeto de tipo Connection que já possui conexao com o Banco de dados
    * @param stmt - Objeto do tipo PreparedStatement, que é uma consulta fixada que e enviada para o Banco de Dados
    */
    public static void closeConnection(Connection conn, PreparedStatement stmt) throws SQLException{
        closeConnection(conn);
        
        if(stmt != null){
            stmt.close();
        }
    }
    /*
    * Funcao sobrecarregada para encerrar a conexao com o banco de dados, evitando erros de stream. Importante: Sempre feche TODAS as conexoes usadas.
    * @param conn - Objeto de tipo Connection que já possui conexao com o Banco de dados
    * @param stmt - Objeto do tipo PreparedStatement, que é uma operação fixada que e enviada para o Banco de Dados
    * @param rs - Objeto do tipo ResultSet, que é o resultado da operação com o banco de dados
    */
    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs) throws SQLException{
        closeConnection(conn, stmt);
        
        if(rs != null){
            rs.close();
        }
    }
}