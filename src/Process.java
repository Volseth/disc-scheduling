import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Process {
    private int inputTime;
    private int realTimePriority;
    private int traveledDistance;
    private int requestedPos;

    public Process(int requestedPos, int inputTime){
        this.inputTime=inputTime;
        this.realTimePriority=0;
        this.traveledDistance=0;
        this.requestedPos=requestedPos;
    }

    public int getInputTime() {
        return inputTime;
    }

    public int getRealTimePriority() {
        return realTimePriority;
    }

    public int getRequestedPos() {
        return requestedPos;
    }
    public int calculateDistance(int actualLocation) {
        traveledDistance = Math.abs(actualLocation - requestedPos);
        return traveledDistance;
    }

    public void setInputTime(int inputTime) {
        this.inputTime = inputTime;
    }

    public void setRealTimePriority(int realTimePriority) {
        this.realTimePriority = realTimePriority;
    }

    public void setTraveledDistance(int traveledDistance) {
        this.traveledDistance = traveledDistance;
    }

    public void setRequestedPos(int requestedPos) {
        this.requestedPos = requestedPos;
    }

    public Process(int maxRequestedPos, int maxInputTime, int realTimePriority){
        Random r= new Random();
        this.requestedPos=r.nextInt(maxRequestedPos)+1;
        this.inputTime=r.nextInt(maxInputTime);
        if(realTimePriority==0){this.realTimePriority=0;}
        else{ this.realTimePriority=r.nextInt(realTimePriority)+1;}

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Process process = (Process) o;
        return inputTime == process.inputTime &&
                requestedPos == process.requestedPos;
    }

    @Override
    public int hashCode() {

        return Objects.hash(inputTime,requestedPos);
    }

    @Override
    public String toString() {
        return inputTime+";"+requestedPos+";"+realTimePriority;
    }
}
