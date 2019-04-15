import java.util.ArrayList;
import java.util.Comparator;

public class FDSCAN extends AlgorithmBase implements DiscScheduling  {

    public FDSCAN(Disc disc, ArrayList<Process> processes){
        super(disc, processes);
    }

    private ArrayList<Process> leftSide;
    private ArrayList<Process> rightSide;

    @SuppressWarnings("Duplicates")
    protected void splitList(){
        ArrayList<Process> executionList=new ArrayList<>(processes);
        leftSide=new ArrayList<>(processes);
        rightSide=new ArrayList<>(processes);
        for (int i=0;i<executionList.size();i++) {
            Process p=executionList.get(i);
            if (p.getRequestedPos() > disc.getStartingPos()) {
                //System.out.println(p.toString());
                leftSide.remove(p);
            }
        }
        for (int i=0;i<executionList.size();i++) {
            Process p=executionList.get(i);
            if (p.getRequestedPos() < disc.getStartingPos()) {
                //System.out.println(p.toString());
                rightSide.remove(p);
            }
        }
    }
    public Process findNearestProcessWithPriority(ArrayList<Process> list){
        Process nearest=list.get(0);
        int priority=0;
        for(Process p:list){
            if(p.calculateDistance(disc.getActualPos())<nearest.calculateDistance(disc.getActualPos()) && p.getInputTime()<=clock && p.getRealTimePriority()>priority){
                nearest=p;
                priority=p.getRealTimePriority();
            }
        }
        return nearest;
    }

    @Override@SuppressWarnings("Duplicates")
    public int calculateTotalDistance() {
        ArrayList<Process> executionList=new ArrayList<>(processes);
        splitList();
        executionList.sort(Comparator.comparing(Process::getInputTime));
        Comparator priorityComp=Comparator.comparing(Process::getRealTimePriority).reversed();

        leftSide.sort(Comparator.comparing(Process::getRequestedPos).thenComparing(Process::getRealTimePriority).reversed());
        rightSide.sort(Comparator.comparing(Process::getRequestedPos).thenComparing(priorityComp));

        clock=executionList.get(0).getInputTime();
        Process temp;
        Process priorityTemp=findNearestProcessWithPriority(executionList);
        int totalMoves=0;

        boolean direction=priorityTemp.getRequestedPos()>disc.getStartingPos(); //False - LEFT , TRUE-RIGHT

        while(!executionList.isEmpty()) {
            clock = executionList.get(0).getInputTime();
            temp = findNearestProcess(executionList);
            priorityTemp = findNearestProcessWithPriority(executionList);
            if (leftSide.contains(priorityTemp) && !direction) {
                while (!leftSide.isEmpty()) {
                    leftSide.sort(Comparator.comparing(Process::getInputTime).thenComparing(Process::getRequestedPos).reversed());
                    Process ongoing = leftSide.get(0);
                    if (ongoing.getInputTime() <= clock && priorityTemp.equals(temp)) {
                        totalMoves += ongoing.calculateDistance(disc.getActualPos());
                        disc.setActualPos(ongoing.getRequestedPos());
                        leftSide.remove(ongoing);
                        executionList.remove(ongoing);
                    } else {
                        leftSide.remove(ongoing);
                        rightSide.add(ongoing);
                    }
                }
                if (!rightSide.isEmpty()) {
                    totalMoves += disc.getActualPos() - 1;
                }
                disc.setActualPos(1);
                direction = true;
            } else {
                while (!rightSide.isEmpty() && direction) {
                    rightSide.sort(Comparator.comparing(Process::getInputTime).thenComparing(Process::getRequestedPos));
                    Process ongoing = rightSide.get(0);
                    if (ongoing.getInputTime() <= clock && priorityTemp.equals(temp)) {
                        totalMoves += ongoing.calculateDistance(disc.getActualPos());
                        disc.setActualPos(ongoing.getRequestedPos());
                        rightSide.remove(ongoing);
                        executionList.remove(ongoing);
                    } else {
                        rightSide.remove(ongoing);
                        leftSide.add(ongoing);
                    }
                }
                if (!leftSide.isEmpty()) {
                    totalMoves += (disc.getCylinders()) - (disc.getActualPos());
                }
                disc.setActualPos(disc.getCylinders());
                direction = false;
            }
        }
        return totalMoves;
    }
}
