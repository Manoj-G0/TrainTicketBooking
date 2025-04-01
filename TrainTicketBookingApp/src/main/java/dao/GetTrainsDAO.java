package dao;

import models.Station;
import models.Train;
//import utils.DBConnection;
//import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetTrainsDAO {
    public static List<Train> getTrainsBetweenStations(int sourceId, int destinationId) {
        List<Train> trains = new ArrayList<>();
        List<Station> st = GetStationsDAO.getAllStations();
        Train ratnachal = new Train(1, "12951", "ratnachal Express", 500);
		ratnachal.setStations(Arrays.asList(st.get(0), st.get(1)));
        
        Train kachiguda = new Train(2, "12009", "kachiguda Express", 400);
        kachiguda.setStations(Arrays.asList(st.get(2), st.get(3)));
        
        trains.add(ratnachal);
        trains.add(kachiguda);
//        String sql = "SELECT t.id, t.number, t.name, t.total_seats " +
//                     "FROM trains t " +
//                     "JOIN train_stations ts1 ON t.id = ts1.train_id AND ts1.station_id = ? " +
//                     "JOIN train_stations ts2 ON t.id = ts2.train_id AND ts2.station_id = ?";
//        
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            
//            pstmt.setInt(1, sourceId);
//            pstmt.setInt(2, destinationId);
//            
//            try (ResultSet rs = pstmt.executeQuery()) {
//                while (rs.next()) {
//                    Train train = new Train();
//                    train.setId(rs.getInt("id"));
//                    train.setNumber(rs.getString("number"));
//                    train.setName(rs.getString("name"));
//                    train.setTotalSeats(rs.getInt("total_seats"));
//                    trains.add(train);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return trains;
    }
}
