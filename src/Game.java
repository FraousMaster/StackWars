import java.io.File;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

import Global.Resources;


/**
 * Game class, Where the game starts from, interacts with GameState, GameView, GameContrroller.
 * Simply startup class for the game.
 * @author Arvid Wiklund, Hugo Frost, Linus Nilsson, Johannes Edenholm
 */
public class Game {
    private GameView gameView = null;
    private GameController gameController = null;
    private GameState gameState = null;
    private AnticipatedUppdate antup;
    
    public Game() {
    	Resources.setResolution(1600,900);
    	readingSettings();
        gameState = new GameState();
        gameView = new GameView(gameState);
        gameController = new GameController(gameView, gameState);
        antup = new AnticipatedUppdate(gameState);
        gameState.addObserver(gameView);
        gameState.addObserver(gameController);
        gameView.addObserver(gameController);     
    }
    
    public GameState getState(){
    	return gameState;
    }
    
    /**
     * Reads the XML file and applies settings.
     */
    private void readingSettings(){
        try{
        	File setFile = Resources.getXMLFile();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(setFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("Game");
            for(int i = 0; i < nodeList.getLength();i++){
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) node;
                    String temp[];
                    String name = eElement.getElementsByTagName("Resolution").item(0).getTextContent();
                    temp = name.split("x");
                    Resources.setResolution(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
                }
            }
        }catch(Exception filee){
            filee.printStackTrace();
        }
    }
}