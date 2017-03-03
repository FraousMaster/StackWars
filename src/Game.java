import java.io.File;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

import Global.Resources;

public class Game {
    private GameView gameView = null;
    private GameController gameController = null;
    private GameState gameState = null;
    public Game() {
    	//Resources.setHeight(1000);
    	//Resources.setWidth(1000);
    	//readingSettings();
        gameState = new GameState();
        gameView = new GameView(gameState);
        gameController = new GameController(gameView, gameState);
        gameState.addObserver(gameView);
        gameState.addObserver(gameController);
        gameView.addObserver(gameController);     
    }
    
    public GameState getState(){
    	return gameState;
    	
    }
    
    private void readingSettings(){
        try{
        	File setFile = Resources.getXMLFile();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(setFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root Element:" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("Game");
                Node node = nodeList.item(0);
                Element eElement = (Element) node;
                String temp[];
                String name = eElement.getElementsByTagName("resolution").item(0).getTextContent();
                temp = name.split("x");
                System.out.println("pls: " + Integer.parseInt(temp[0]));
        }catch(Exception filee){
            filee.printStackTrace();
        }
    }
}