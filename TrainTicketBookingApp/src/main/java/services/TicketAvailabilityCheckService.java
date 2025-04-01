package services;

import dao.GetTrainsDAO;
import java.util.List;
import models.Train;

public class TicketAvailabilityCheckService {
    public String getTrainOptions(int sourceId, int destinationId) {
        List<Train> trains = GetTrainsDAO.getTrainsBetweenStations(sourceId, destinationId);
        
        StringBuilder options = new StringBuilder();
        for (Train train : trains) {
            options.append("<option value='")
                  .append(train.getNumber())
                  .append("'>")
                  .append(train.getName())
                  .append(" (")
                  .append(train.getNumber())
                  .append(")</option>");
        }
        
        return options.toString();
    }
}