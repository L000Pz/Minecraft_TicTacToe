package me.l00pz.tictactoe.tictactoe.DB;

import me.l00pz.tictactoe.tictactoe.TheGame.GameValues;

import java.sql.*;

public class DataBase {
    private Connection connection;
    public Connection getConnection()throws SQLException {
        if (connection != null){
            return connection;
        }
        String url = "jdbc:mysql://localhost/saved_game";
        String user = "root";
        String password = "";

        Connection connection= DriverManager.getConnection(url,user,password);
        this.connection = connection;
        System.out.println("connected to DB");

        return connection;

    }
    public void initializeDatabase() throws SQLException{
        Statement statement = getConnection().createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS saved_game(gameNum int primary key, uuidP1 varchar(36), uuidP2 varchar(36), board varchar(18), turn varchar(2))";
        statement.execute(sql);
        statement.close();
    }
    public GameValues findGame(int num) throws SQLException{
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM saved_game WHERE gameNum = ?");
        statement.setInt(1,num);

        ResultSet resultSet = statement.executeQuery();

        GameValues gameValues;
        if (resultSet.next()){
            gameValues = new GameValues(resultSet.getInt("gameNum"),resultSet.getString("uuidP1"),
                    resultSet.getString("uuidP2"),resultSet.getString("board"),resultSet.getString("turn"));
            statement.close();
            return gameValues;
        }
        statement.close();
        return null;
    }
    public void SaveGameValues(GameValues gameValues) throws SQLException{
        PreparedStatement statement = getConnection()
                .prepareStatement("INSERT INTO saved_game(gameNum, uuidP1, uuidP2, board, turn) VALUES (?, ?, ?, ?, ?)");
        statement.setInt(1,gameValues.getGameNum());
        statement.setString(2,gameValues.getP1UUID());
        statement.setString(3, gameValues.getP2UUID());
        statement.setString(4,gameValues.getBoard());
        statement.setString(5,gameValues.getTurn());
        statement.executeUpdate();
        statement.close();
    }
}
