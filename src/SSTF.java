import java.util.ArrayList;
import java.util.Comparator;

public class SSTF extends AlgorithmBase implements DiscScheduling {

    public SSTF(Disc disc, ArrayList<Process> processes){
        super(disc,processes);
    }
    public int calculateTotalDistance(){
        ArrayList<Process> executionList=new ArrayList<>(processes);
        ArrayList<Process> readyList=new ArrayList<>();
        executionList.sort(Comparator.comparing(Process::getInputTime));
        Process temp;
        int totalMoves=0;
        while(!executionList.isEmpty()){
            clock=executionList.get(0).getInputTime();
           temp=findNearestProcess(executionList);
           totalMoves+=temp.calculateDistance(disc.getActualPos());
           disc.setActualPos(temp.getRequestedPos());
           executionList.remove(temp);
        }
        return totalMoves;

        /*if(!executionList.isEmpty()) {
            int clock = executionList.get(0).getInputTime();
        }
        int totalMoves=0;
        Process temp;
        while(!executionList.isEmpty()|| !readyList.isEmpty()){
           if(readyList.isEmpty() && clock< executionList.get(0).getInputTime()){
               clock= executionList.get(0).getInputTime();
           }
           while(!executionList.isEmpty() && clock>=executionList.get(0).getInputTime()){
               readyList.add(executionList.get(0));
               executionList.remove(0);
           }
           if(!readyList.isEmpty()){
               temp=findNearestProcess(readyList);
               totalMoves+=temp.calculateDistance(disc.getActualPos());
               disc.setActualPos(temp.getRequestedPos());
               clock+=1;
               readyList.remove(temp);
           }
       }
       return totalMoves;
       */

    }

}
