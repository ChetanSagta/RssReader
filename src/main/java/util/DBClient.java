package util;


import org.sqlite.SQLiteException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBClient {

    String driverClassName = "org.sqlite.JDBC";
    String url = "jdbc:sqlite:/home/chetan/Java/RssReader/src/main/resources/cache.db";
    Connection con;

    public DBClient() throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName);
        con = DriverManager.getConnection(url);
    }

    //Insert based on Object
    public void insertIntoDatabase(List<Map<String,String>> feedInformation,String parent) throws SQLException {

        try {
            String query = "insert into information('title','description','link','pubDate','parent') values(?,?,?,?,?);";

            for (int i = 0; i < feedInformation.size(); i++) {
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, feedInformation.get(i).get("title"));
                pstmt.setString(2, feedInformation.get(i).get("description"));
                pstmt.setString(3, feedInformation.get(i).get("link"));
                pstmt.setString(4, feedInformation.get(i).get("pubDate"));
                pstmt.setString(5, parent);
                pstmt.execute();
            }
        }
        catch(SQLiteException e){
            System.out.println(e.getMessage());
        }

    }

    //return based on Object
    public List<Map<String,String>> getResults(String parent) throws SQLException {
        List<Map<String, String>> results = new ArrayList<>();
        String query = "select * from information where parent = ? ";

        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1,parent);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()){

            Map<String,String> temp = new HashMap<>();
            temp.put("title",rs.getString("Title"));
            temp.put("description",rs.getString("description"));
            temp.put("link",rs.getString("link"));
            temp.put("pubDate",rs.getString("pubDate"));

            results.add(temp);

        }

        return results;
    }

}
