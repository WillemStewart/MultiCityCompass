//Main.java
package koester.lab1;
import java.io.*;    // For File class and FileNotFoundException
import java.util.*;  // For Scanner and InputMismatchException
import javax.swing.JOptionPane; // For the JOptionPane class

public class Main
{
    public static void main(String[] args)
    {
     String filename = "CityData1.csv"; 
     int zipcode;
     String cityName;
     String state;
     double latitude;
     double longitude;
     int timezone;
     boolean yesDaylight;
     String ynDaylight;
     City c;
     City mostDirection;
     CityGroup group = new CityGroup();
     String userInput;
     

     // Input the file
     try
     {
         File file = new File(filename);
         Scanner inputFile = new Scanner(file);
         inputFile.useDelimiter(",");
         
         String garbage = inputFile.nextLine();
         
         while (inputFile.hasNext())
         {
             zipcode = inputFile.nextInt();
             cityName = inputFile.next();
             state = inputFile.next();
             latitude = inputFile.nextDouble();
             longitude = inputFile.nextDouble();
             timezone = inputFile.nextInt();
             ynDaylight = inputFile.next();
             inputFile.nextLine();
             
             if (ynDaylight.charAt(1) == 1)
             {
                 yesDaylight = true;
             }
             else 
             {
                 yesDaylight = false;
             }

	     c = new City(zipcode, cityName, state, 
             latitude, longitude, timezone, yesDaylight);
             
             group.addCity(c);
         }
         // Close the file
         inputFile.close();
     }

     catch (FileNotFoundException e)
     {
         JOptionPane.showMessageDialog(null,
             "The file " + filename + " does not exist.");
     }
     
     //Enter a direction to find the city that has the most extreme direction
     userInput = JOptionPane.showInputDialog("Please Enter a Direction");
     
     userInput = userInput.toLowerCase();
     
        switch (userInput) {
            case "west" -> // Find the city with the lowest longitude
                mostDirection = group.findWestMost();
            case "east" -> // Find the city with the highest longitude
                mostDirection = group.findEastMost();
            case "north" -> // Find the city with the highest latitude
                mostDirection = group.findNorthMost();
            case "south" -> // Find the city with the lowest latitude
                mostDirection = group.findSouthMost();
            default -> {
                System.out.println(userInput + " is not a direction");
                return;
            }
        }
     
     // Output result
     JOptionPane.showMessageDialog(null, 
         "The " + userInput + "ern most city on the list is " + 
             mostDirection.toString() + ".\n");
     
     System.exit(0);
   }
}
