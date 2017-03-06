import java.util.ArrayList;

public class AnticipatedUppdate extends Thread {

    private ArrayList<Ant> antList = new ArrayList<>();
    private GameState state;

    public AnticipatedUppdate(GameState state){
        this.state = state;
        this.start();
    }
    public void run(){
       while (true) {
            try {
                antList = state.getAnts();
                for (Ant a : antList) {
                    int m = a.getCurrentMapObject();
                    if (m == 3) {
                        a.setPos(a.getPosX(), (a.getPosY() + 4));
                    } else if (m == 4) {
                        a.setPos((a.getPosX() + 4), a.getPosY());
                    } else if (m == 8) {
                        a.setPos(a.getPosX(), (a.getPosY() - 4));
                    } else if (m == 9) {
                        a.setPos((a.getPosX() - 4), a.getPosY());
                    }
                }
                //System.out.print("hej \n");
                state.updateAllAnts(antList);
                sleep(33);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
