import java.util.ArrayList;
import java.util.Comparator;

public class FDCSCAN extends CSCAN implements DiscScheduling {
    public FDCSCAN(Disc disc, ArrayList<Process> processes){
        super(disc,processes);
    }

    private ArrayList<Process> priorityQueue=new ArrayList<>();
    private ArrayList<Process> nonPriorityQueue=new ArrayList<>();

    private Process findFirstWithPriority(ArrayList<Process> list){
        for (Process p:list) {
            if(p.getRequestedPos()>=disc.getActualPos() && p.getInputTime()<=clock &&p.getRealTimePriority()>0) return p;
        }
        return null;
    }
    private Process findFirst(ArrayList<Process> list) {
        for (Process p : list) {
            if (p.getRequestedPos() >= disc.getActualPos() && p.getInputTime() <= clock) return p;
        }
        return null;
    }
    @SuppressWarnings("Duplicates")
    private void splitByPriorityAndInputTime(ArrayList<Process> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRealTimePriority() == 0 && clock == list.get(i).getInputTime()) {
                nonPriorityQueue.add(list.get(i));
            }
            if (list.get(i).getRealTimePriority() != 0 && clock == list.get(i).getInputTime()) {
                priorityQueue.add(list.get(i));
            }
        }
    }
    public int calculateTotalDistance(){
        ArrayList<Process> executionList=new ArrayList<>(processes);
        Comparator reverserdPriority=Comparator.comparing(Process::getRealTimePriority).reversed();
        executionList.sort(Comparator.comparing(Process::getInputTime).thenComparing(Process::getRequestedPos).thenComparing(reverserdPriority));
        clock=executionList.get(0).getInputTime();
        int finishedPriorityProcess=0;
        int totalMoves=0;
        while(!executionList.isEmpty()) {
            clock=executionList.get(0).getInputTime();
            Process p = findFirstWithPriority(executionList);
            for(Process process:executionList){if(process.getRealTimePriority()>0 && process.getInputTime()==clock && !priorityQueue.contains(process)){priorityQueue.add(process);}}
            if (p != null) {
                totalMoves += p.calculateDistance(disc.getActualPos());
                disc.setActualPos(p.getRequestedPos());
                executionList.remove(p);
                finishedPriorityProcess++;
            } else {
                Process normalProcess=findFirst(executionList);
                if(normalProcess!=null && finishedPriorityProcess==priorityQueue.size()){
                    totalMoves += normalProcess.calculateDistance(disc.getActualPos());
                    disc.setActualPos(normalProcess.getRequestedPos());
                    executionList.remove(normalProcess);
                }
                else {
                    totalMoves += (disc.getCylinders()) - disc.getActualPos() + 1;
                    disc.setActualPos(1);
                }
            }
        }
        return totalMoves;
    }
}
