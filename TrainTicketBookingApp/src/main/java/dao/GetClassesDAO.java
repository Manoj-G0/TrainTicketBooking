package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.TrainClass;
import utils.DBConnection;

public class GetClassesDAO {

    public static ArrayList<TrainClass> getClasses(int trainNo) {
        ArrayList<TrainClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM traintravelclasses WHERE trn_no = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, trainNo);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TrainClass cls = new TrainClass();
                    cls.setTrn_no(rs.getInt("trn_no"));
                    cls.setClassName(rs.getString("trn_tcl_id"));
                    cls.setFareFactor(Double.parseDouble(rs.getString("far_farefactor")));
                    
                    classes.add(cls);

                    // Debugging Log
                    System.out.println("Train No: " + cls.getTrn_no() + " - Class: " + cls.getClassName());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logger in production
        }
        return classes;
    }
}
