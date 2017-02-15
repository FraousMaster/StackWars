public class Ant {
    public enum player{
        playerOne,
        playerTwo,
        playerThree,
        PlayerFour
    }

    private int posX, posY;
    private player ownedBy;

    public Ant(int x, int y, player owns){
        posX = x;
        posY = y;
        ownedBy = owns;
    }

    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
    public player getOwnedBy(){
        return ownedBy;
    }
    public void setPos(int x, int y){
        posY = y;
        posX = x;
    }
}
