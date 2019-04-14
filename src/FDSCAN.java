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

    @Override
    public int calculateTotalDistance() {
        ArrayList<Process> executionList=new ArrayList<>(processes);
        splitList();
        executionList.sort(Comparator.comparing(Process::getInputTime));

        leftSide.sort(Comparator.comparing(Process::getRequestedPos).reversed());
        rightSide.sort(Comparator.comparing(Process::getRequestedPos));

        clock=executionList.get(0).getInputTime();
        Process temp=findNearestProcessWithPriority(executionList);
        int totalMoves=0;

        boolean direction=temp.getRequestedPos()>disc.getStartingPos(); //False - LEFT , TRUE-RIGHT
        boolean first=direction;
        return totalMoves;
    }
}
