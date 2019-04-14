import java.util.ArrayList;
import java.util.Comparator;

public class EDFSSTF extends AlgorithmBase implements DiscScheduling {
    public EDFSSTF(Disc disc, ArrayList<Process> processes){
        super(disc,processes);
    }
    private ArrayList<Process> priorityQueue=new ArrayList<>();
    private ArrayList<Process> nonPriorityQueue=new ArrayList<>();
    @SuppressWarnings("Duplicates")
    private void splitByPriorityAndInputTime(ArrayList<Process> list){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getRealTimePriority()==0 && clock==list.get(i).getInputTime()){
                nonPriorityQueue.add(list.get(i));
            }
            if(list.get(i).getRealTimePriority()!=0 && clock==list.get(i).getInputTime()){
                priorityQueue.add(list.get(i));
            }
        }
    }
    @Override
    public int calculateTotalDistance() {
        ArrayList<Process> executionList=new ArrayList<>(processes);
        executionList.sort(Comparator.comparing(Process::getInputTime));
        int totalMoves=0;
        int finished=0;
        while(true){
            splitByPriorityAndInputTime(executionList);
            priorityQueue.sort(Comparator.comparing(Process::getRealTimePriority).reversed());
            finished+=priorityQueue.size()+nonPriorityQueue.size();
            FCFS fcfs=new FCFS(disc,priorityQueue);
            SSTF sstf=new SSTF(disc,nonPriorityQueue);
            totalMoves+=(fcfs.calculateTotalDistance()+sstf.calculateTotalDistance());
            priorityQueue=new ArrayList<>();
            nonPriorityQueue=new ArrayList<>();
            clock++;
            if(finished==executionList.size()){
                break;
            }
        }
        return totalMoves;
    }
}
