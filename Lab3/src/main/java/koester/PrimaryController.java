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
    private Label readLbl, zipLbl, tzLbl;
    
    @FXML 
    private TextField userInput;

    @FXML
    public void setStates() 
    {       
        stateCombo.getItems().clear();
        State s;
        OrderedAddOnce<State> StateList = new OrderedAddOnce<>(); 
        try 
        {
            String filename = userInput.getText();
            File file = new File(filename);
            Scanner inputFile = new Scanner(file);
            inputFile.useDelimiter(",");

            // Input data into array
            if ("CityData3.csv".equals(filename))
            {
                readLbl.setText("The cities have been read!");
                readLbl.setStyle("-fx-text-fill: green; -fx-font-size: 10px;");
            }
            
            String garbage = inputFile.nextLine();
            
            while (inputFile.hasNext())
           {
                inputFile.nextInt();
                inputFile.next();
                String sName = inputFile.next();
                inputFile.nextDouble();
                inputFile.nextDouble();
                inputFile.nextInt();
                inputFile.nextLine();
                
                s = new State(sName);
                StateList.AddOnce(s);                
            }
            Iterator<State> newIterator = StateList.iterator();
            while (newIterator.hasNext())
            {
                stateCombo.getItems().add(newIterator.next());
            }
            inputFile.close();
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
        City c;
        String filename = userInput.getText();
        File file = new File(filename);
        Scanner inputFile;
        try
        {
            inputFile = new Scanner(file);
            inputFile.useDelimiter(",");
        }  
        catch (FileNotFoundException e)
        {
            System.out.println("SystemError");
            return;
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

            c = new City(zipcode, cName, latitude, 
                longitude, timezone, yesDaylight);  
            if(sName.equals(stateCombo.getValue().toString()))
            {
                stateCombo.getValue().getCityList().AddOnce(c);
            }
        }
        inputFile.close();
        Iterator<City> newIterator = stateCombo.getValue().getCityList().iterator();
        cityCombo.getItems().clear();
        while (newIterator.hasNext())
        {
            cityCombo.getItems().add(newIterator.next());
        }
    }    


    @FXML
    public void setData() 
    {
        zipLbl.setText(cityCombo.getValue().getZip());
        tzLbl.setText(cityCombo.getValue().getTimeZone());
    }
}
