public class Disc {
    private int cylinders;
    private int startingPos;
    private int actualPos;
    private boolean isMoving=true;

    public Disc(int cylinders){
        this.cylinders=cylinders;
        this.startingPos=cylinders/2;
        this.actualPos=startingPos;
    }
    public Disc(int cylinders, int startingPos){
        this.cylinders=cylinders;
        this.startingPos=startingPos;
        actualPos=startingPos;
    }

    public int getStartingPos() {
        return startingPos;
    }

    public int getActualPos() {
        return actualPos;
    }

    public int getCylinders() {
        return cylinders;
    }

    public void setActualPos(int actualPos) {
        this.actualPos = actualPos;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }
}
