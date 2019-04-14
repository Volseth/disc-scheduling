import java.util.ArrayList;
import java.util.Comparator;

public class CSCAN extends AlgorithmBase implements DiscScheduling{
    public CSCAN(Disc disc, ArrayList<Process> processes){
        super(disc,processes);
    }
    private Process findFirst(ArrayList<Process> list){
        for (Process p:list) {
            if(p.getRequestedPos()>=disc.getActualPos() && p.getInputTime()<=clock) return p;
        }
        return null;
    }
    @Override @SuppressWarnings("Duplicates")
    public int calculateTotalDistance() {
        ArrayList<Process> executionList=new ArrayList<>(processes);
        executionList.sort(Comparator.comparing(Process::getInputTime).thenComparing(Process::getRequestedPos));

        clock=executionList.get(0).getInputTime();
        int totalMoves=0;
        while(!executionList.isEmpty()){
           clock=executionList.get(0).getInputTime();
           Process p=findFirst(executionList);
           if(p!=null) {
               totalMoves += p.calculateDistance(disc.getActualPos());
               disc.setActualPos(p.getRequestedPos());
               executionList.remove(p);
           }
           else{
               totalMoves+=(disc.getCylinders())-disc.getActualPos()+1;
               disc.setActualPos(1);
           }
        }
        return totalMoves;
    }
}
