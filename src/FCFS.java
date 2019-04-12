import java.util.ArrayList;
import java.util.Comparator;

public class FCFS extends AlgorithmBase implements DiscScheduling {

    public FCFS(Disc disc, ArrayList<Process> processes){
        super(disc, processes);
    }
    public int calculateTotalDistance(){
        ArrayList<Process> executionList=new ArrayList<>(processes);
        executionList.sort(Comparator.comparing(Process::getInputTime));
        int totalMoves=0;
        for (Process p:executionList){
            totalMoves+=p.calculateDistance(disc.getActualPos());
            disc.setActualPos(p.getRequestedPos());
        }
        return totalMoves;
    }
}
