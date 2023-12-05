//PrimaryController.java
package koester.lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import koester.lab5.Path.PathVertexInfo;

public class PrimaryController {
    
    @FXML
    private ComboBox<City> firstCombo = new ComboBox<City>();
    
    @FXML
    private ComboBox<City> secondCombo = new ComboBox<City>();
    
    @FXML
    private Label readLbl, cityLbl, pathLbl, consideredLbl, distLbl;
    
    @FXML 
    private TextField userInput;
    
    @FXML
    private TextArea pathList;
    
    private OrderedAddOnce<City> CityList = new OrderedAddOnce<>();
    
    public String getShortestPath(Vertex startVertex, Vertex endVertex, HashMap<Vertex, PathVertexInfo> infoMap) {
        // Start from endVertex and build the path in reverse.
        String path = "";
        Vertex currentVertex = endVertex;
        while (currentVertex != startVertex) {
           path = " -> " + currentVertex.label + path;
           currentVertex = infoMap.get(currentVertex).predecessor;
        }
        path = startVertex.label + path;
        return path;
    }

    @FXML
    public void setCombos() 
    {       
        firstCombo.getItems().clear();
        secondCombo.getItems().clear();
        Graph pathGroup = new Graph();
        Edge[] edgeList = new Edge[100];
        HashMap<Vertex, Path.PathVertexInfo> shortPath;
        City c;
        try 
        {
            String filename = userInput.getText();
            File file = new File(filename);
            Scanner inputFile = new Scanner(file);
            inputFile.useDelimiter(",");

            // Input data into array
            if ("CityData5.csv".equals(filename))
            {
                readLbl.setText("The cities have been read!");
                readLbl.setStyle("-fx-text-fill: green; -fx-font-size: 10px;");
            }
            
            String garbage = inputFile.nextLine();
            int counter = 0;
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

                c = new City(zipcode, cName, sName, latitude, longitude, timezone, yesDaylight); 
                CityList.AddOnce(c);
                counter++;
                pathGroup.addVertex(cName);
            }
            Iterator<City> startIterator = CityList.iterator();
            while (startIterator.hasNext())
            {
                firstCombo.getItems().add(startIterator.next());
            }
            Iterator<City> destinationIterator = CityList.iterator();
            while (destinationIterator.hasNext())
            {
                secondCombo.getItems().add(destinationIterator.next());
            }
            cityLbl.setText("There are " + counter + " cities");
            cityLbl.setStyle("-fx-text-fill: blue; -fx-font-size: 10px; -fx-font-weight: bold;");
            inputFile.close();
        }
        
        catch(FileNotFoundException e)
        {
            readLbl.setText("The file was not found");
            readLbl.setStyle("-fx-text-fill: red; -fx-font-size: 10px;");
        }
//        HashMap<Vertex, PathVertexInfo> infoMap = dijkstraShortestPath(pathGroup, firstCombo.getValue());
        
        
//        
        
//        City a = pathIterator.next();
//        City b = pathIterator.next();
//        pathGroup.addUndirectedEdge(pathIterator, pathIterator.next(), startIterator.getDistance(pathIterator.next()));
    }
    
    @FXML 
    public void setPath() throws FileNotFoundException
    {   
        HashMap<Vertex, PathVertexInfo> infoMap;
        distLbl.setText("Direct: " + firstCombo.getValue().getDistance(secondCombo.getValue()) + " Miles");
        distLbl.setStyle("-fx-text-fill: black; -fx-font-size: 10px; -fx-font-weight: bold;");
        pathLbl.setText("Path: " + "" + " Miles");
        pathLbl.setStyle("-fx-text-fill: black; -fx-font-size: 10px; -fx-font-weight: bold;");
        consideredLbl.setText( "Paths Considered: " + "");
        consideredLbl.setStyle("-fx-text-fill: black; -fx-font-size: 10px; -fx-font-weight: bold;");
//        pathList.appendText(getShortestPath(getVertex(firstCombo.getValue()), getVertex(secondCombo.getValue()), infoMap));
    }    
}
