import java.util.ArrayList;

public class FareCalculationService {

    public static double calculateFare(ArrayList<Passenger> passengers) {
        double baseFare = GetFareDAO.getFare();
        Train t = new Train();

        double surcharge = 100; // double surcharge = t.getSurcharge();
        double factor = 3.6; // GetFareFactor.getFactor(trainNo, trClass);
        double totalFare = 0;

        baseFare = (baseFare * factor) + surcharge;

        Passenger p1 = new Passenger("person1", 66, "Male", null);
        Passenger p2 = new Passenger("person2", 60, "Female", null);
        Passenger p3 = new Passenger("person3", 4, "Male", null);
        Passenger p4 = new Passenger("person4", 10, "Male", null);

        //ArrayList<Passenger> passengers = new ArrayList<>();
//        passengers.add(p1);
//        passengers.add(p2);
//        passengers.add(p3);
//        passengers.add(p4);

        for (Passenger pa : passengers) {
            double base = baseFare;
            if (pa.getAge() < 5) {
                base = 0;
            } else if (pa.getAge() >= 6 && pa.getAge() < 12) {
                base *= 0.5;
            } else if ((pa.getAge() > 65 && pa.getGender().equals("Male")) ||
                       (pa.getAge() >= 56 && pa.getGender().equals("Female"))) {
                base *= 0.75;
            }
            totalFare += base;
        }

        return totalFare;
    }
}