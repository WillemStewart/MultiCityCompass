// PrimaryController.java

package koester;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrimaryController {
    
    @FXML
    private CityGroup listOfCities = new CityGroup();
    
    @FXML
    private ComboBox<City> cityCombo = new ComboBox<City>();
    
    @FXML
    private Label readLbl, stateLbl, zipLbl, tzLbl;
    
    @FXML 
    private TextField userInput;

    @FXML
    public void readCities() 
    {
        try 
        {
            City c;
            String filename = userInput.getText();
            File file = new File(filename);
            Scanner inputFile = new Scanner(file);
            inputFile.useDelimiter(",");

            // Input data into array
            if ("CityData2.csv".equals(filename))
            {
                readLbl.setText("The cities have been read!");
                readLbl.setStyle("-fx-text-fill: green; -fx-font-size: 10px;");
            }
            
            String garbage = inputFile.nextLine();
            
            while (inputFile.hasNext())
           {
                boolean yesDaylight = false;
                int zipcode = inputFile.nextInt();
                String cityName = inputFile.next();
                String state = inputFile.next();
                double latitude = inputFile.nextDouble();
                double longitude = inputFile.nextDouble();
                int timezone = inputFile.nextInt();
                String ynDaylight = inputFile.nextLine();
                
                if (ynDaylight.charAt(1) == '1')
                {
                    yesDaylight = true;
                }
                
                c = new City(zipcode, cityName, state, 
                latitude, longitude, timezone, yesDaylight);
                listOfCities.addCity(c);
            }
            inputFile.close();
        }
        catch(FileNotFoundException e)
        {
            readLbl.setText("The file was not found");
            readLbl.setStyle("-fx-text-fill: red; -fx-font-size: 10px;");
        }
        
        listOfCities.sortCities(listOfCities.getGroup(), 0, (listOfCities.getCityCount() - 1));
        setCities();
    }
    
    @FXML
    public void setCities() 
    {
        for (int i = 0; i < listOfCities.getCityCount(); i++)
        {
            cityCombo.getItems().add(listOfCities.getCity(i));
        }
    }
    
    @FXML
    private void citySelected() 
    {
        stateLbl.setText(cityCombo.getValue().getState());
        zipLbl.setText(cityCombo.getValue().getZip());
        tzLbl.setText(cityCombo.getValue().getTz());
    }

}
