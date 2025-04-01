package dao;

import models.Station;
//import utils.DBConnection;
//import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetStationsDAO {
    public static List<Station> getAllStations() {
        List<Station> stations = new ArrayList<>();
        stations.add(new Station(1, "samalkot", "smkt"));
        stations.add(new Station(2, "rajahmundry", "rjy"));
        stations.add(new Station(3, "visakhapatnam", "vis"));
        stations.add(new Station(4, "vijayawada", "vza"));
        stations.add(new Station(5, "chennai", "chn"));
//        String sql = "SELECT * FROM stations";
//        
//        try (Connection conn = DBConnection.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//            
//            while (rs.next()) {
//                Station station = new Station();
//                station.setId(rs.getInt("id"));
//                station.setName(rs.getString("name"));
//                station.setCode(rs.getString("code"));
//                stations.add(station);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return stations;
    }
}
