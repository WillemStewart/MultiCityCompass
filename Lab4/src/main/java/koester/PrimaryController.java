// PrimaryController.java
package koester;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrimaryController {
    
    @FXML
    private ComboBox<State> stateCombo = new ComboBox<State>();
    
    @FXML
    private ComboBox<City> cityCombo = new ComboBox<City>();
    
    @FXML
    private Label readLbl, zipLbl, cityLbl, timeLbl, distanceLbl;
    
    @FXML 
    private TextField userInput;

    @FXML
    public void setStates() 
    {       
        stateCombo.getItems().clear();
        State s;
        City c;
        OrderedAddOnce<State> StateList = new OrderedAddOnce<>(); 
        City[] CityList = new City[10];
        int zipcodeCounter = 0;
        try 
        {
            String filename = userInput.getText();
            File file = new File(filename);
            Scanner inputFile = new Scanner(file);
            inputFile.useDelimiter(",");

            // Input data into array
            if ("CityData4.csv".equals(filename))
            {
                readLbl.setText("The cities have been read!");
                readLbl.setStyle("-fx-text-fill: green; -fx-font-size: 10px;");
            }
            
            String garbage = inputFile.nextLine();
            
            while (inputFile.hasNext())
            {
                boolean yesDaylight = false;
                int zipcode = inputFile.nextInt();
                String cName = inputFile.next();
                String sName = inputFile.next();
                double latitude = inputFile.nextDouble();
                double longitude = inputFile.nextDouble();
                int timezone = inputFile.nextInt();
                String ynDaylight = inputFile.nextLine();
                
                if (ynDaylight.charAt(1) == '1')
                {
                    yesDaylight = true;
                }

                s = new State(sName);
                s = StateList.AddOnce(s); 
                c = new City(zipcode, cName, latitude, longitude, timezone, yesDaylight);
                if (s.getCityTree().AddOnce(c) == c)
                {
                    s.cityCounter();
                } 
                s.arrayAdd(c);
                zipcodeCounter++;   
                
            }
            Iterator<State> newIterator = StateList.iterator();
            while (newIterator.hasNext())
            {
                stateCombo.getItems().add(newIterator.next());
            }
            inputFile.close();
            zipLbl.setText(zipcodeCounter + " Zip Codes");
            zipLbl.setStyle("-fx-text-fill: green; -fx-font-size: 10px;");
        }
        
        catch(FileNotFoundException e)
        {
            readLbl.setText("The file was not found");
            readLbl.setStyle("-fx-text-fill: red; -fx-font-size: 10px;");
        }
    }
    
    @FXML 
    public void setCities()
    {
        long Time_start = System.nanoTime();
        State s = stateCombo.getValue();
        s.sort(s.getCityList(), 0, (s.getCityTreeCount() - 1));
        cityCombo.getItems().clear();
        for(int i = 0; i < 10; i++)
        {
            cityCombo.getItems().add(stateCombo.getValue().getSpecCity(i));
//            System.out.println(stateCombo.getValue().getSpecCity(i).getCounter());
        }
        long Time_stop = System.nanoTime();
        cityLbl.setText(stateCombo.getValue().getCityTreeCount() + " Cities");
        cityLbl.setStyle("-fx-text-fill: blue; -fx-font-size: 10px; -fx-font-weight: bold;");
        timeLbl.setText(Time_stop - Time_start + " NanoSeconds");
        timeLbl.setStyle("-fx-text-fill: blue; -fx-font-size: 10px; -fx-font-weight: bold;");
    }    


    @FXML
    public void setData() 
    {           
        distanceLbl.setText(cityCombo.getValue().getDistance() + " Miles");
        distanceLbl.setStyle("-fx-text-fill: black; -fx-font-size: 10px;");
    }
}
