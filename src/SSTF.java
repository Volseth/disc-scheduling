import java.util.ArrayList;
import java.util.Comparator;

public class SSTF extends AlgorithmBase implements DiscScheduling {

    public SSTF(Disc disc, ArrayList<Process> processes){
        super(disc,processes);
    }
    public int calculateTotalDistance(){
        ArrayList<Process> executionList=new ArrayList<>(processes);
        executionList.sort(Comparator.comparing(Process::getInputTime));
        Process temp;
        int totalMoves=0;
        clock=executionList.get(0).getInputTime();
        while(!executionList.isEmpty()){
           temp=findNearestProcess(executionList);
           totalMoves+=temp.calculateDistance(disc.getActualPos());
           disc.setActualPos(temp.getRequestedPos());
           clock=temp.getInputTime();
           executionList.remove(temp);
        }
        clock=0;
        return totalMoves;
    }

}
