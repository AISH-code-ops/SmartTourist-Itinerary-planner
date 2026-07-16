public class TravelPlanner {

    // Method to calculate estimated cost based on days, travellers, and budget level
    public static double calculateCost(int days, int travellers, String budget) {
        double basePrice = 0;
        
        // Assign base price per day per person based on budget selection
        if (budget.equals("Low")) basePrice = 2000;
        else if (budget.equals("Medium")) basePrice = 3000;
        else if (budget.equals("High")) basePrice = 5000;
        
        return days * travellers * basePrice;
    }

    // Method to generate the travel plan based on the selected city
    public static String generatePlan(String city, int days) {
        StringBuilder plan = new StringBuilder();
        
        // 1. Recommended Stay
        plan.append("Recommended Stay\n");
        if (city.equals("Goa") || city.equals("Manali")) {
            plan.append("Resort or Homestay\n\n");
        } else {
            plan.append("Hotel or Guest House\n\n");
        }

        // 2. Day-wise Itinerary
        // 2. Day-Wise Itinerary
plan.append("Itinerary\n");

for (int i = 1; i <= days; i++) {

    plan.append("Day ").append(i).append(" : ");

    if(city.equals("Goa")){

        switch(i){
            case 1:
                plan.append("Visit Baga Beach");
                break;
            case 2:
                plan.append("Explore Fort Aguada");
                break;
            case 3:
                plan.append("Enjoy Water Sports");
                break;
            case 4:
                plan.append("Shop at Anjuna Market");
                break;
            default:
                plan.append("Relax at Candolim Beach");
        }

    }

    else if(city.equals("Mysore")){

        switch(i){
            case 1:
                plan.append("Visit Mysore Palace");
                break;
            case 2:
                plan.append("Explore Chamundi Hills");
                break;
            case 3:
                plan.append("Visit Mysore Zoo");
                break;
            case 4:
                plan.append("Enjoy Brindavan Gardens");
                break;
            default:
                plan.append("Shopping at Devaraja Market");
        }

    }

    else if(city.equals("Bangalore")){

        switch(i){
            case 1:
                plan.append("Visit Lalbagh");
                break;
            case 2:
                plan.append("Explore Cubbon Park");
                break;
            case 3:
                plan.append("Visit Bangalore Palace");
                break;
            case 4:
                plan.append("Shopping at Commercial Street");
                break;
            default:
                plan.append("Visit ISKCON Temple");
        }

    }

    else{

        plan.append("Explore the city and enjoy local food.");

    }

    plan.append("\n");

}

        // 3. Food Recommendation
        plan.append("\nFood Recommendation\n");
        if (city.equals("Goa")) plan.append("Try Seafood and Goan Fish Curry.\n");
        else if (city.equals("Mysore")) plan.append("Try Mysore Pak and Masala Dosa.\n");
        else plan.append("Try local multi-cuisine delicacies.\n");

        // 4. General Travel Tips
        plan.append("\nTravel Tips\n");
        plan.append("• Carry ID proof.\n");
        plan.append("• Keep emergency contacts.\n");
        plan.append("• Drink enough water.\n");
        plan.append("• Wear comfortable clothes.\n");
        plan.append("• Keep cash and digital payment options.\n");

        return plan.toString();
    }
}
