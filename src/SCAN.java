import java.util.ArrayList;
import java.util.Comparator;

public class SCAN extends  AlgorithmBase implements DiscScheduling {
    public SCAN(Disc disc, ArrayList<Process> processes){
        super(disc, processes);
    }
    protected ArrayList<Process> leftSide;
    protected ArrayList<Process> rightSide;
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
    @Override @SuppressWarnings("Duplicates")
    public int calculateTotalDistance() {
        ArrayList<Process> executionList=new ArrayList<>(processes);
        splitList();
        executionList.sort(Comparator.comparing(Process::getInputTime));

        leftSide.sort(Comparator.comparing(Process::getRequestedPos).reversed());
        rightSide.sort(Comparator.comparing(Process::getRequestedPos));

        clock=executionList.get(0).getInputTime();
        Process temp=findNearestProcess(executionList);
        int totalMoves=0;

        boolean direction=temp.getRequestedPos()>disc.getStartingPos(); //False - LEFT , TRUE-RIGHT
        boolean first=direction;
        while(!executionList.isEmpty()){
            clock=executionList.get(0).getInputTime();
            temp=findNearestProcess(executionList);
            if(leftSide.contains(temp) && !direction){
                while(!leftSide.isEmpty()){
                    leftSide.sort(Comparator.comparing(Process::getInputTime).thenComparing(Process::getRequestedPos).reversed());
                    Process ongoing=leftSide.get(0);
                    if(ongoing.getInputTime()<=clock){
                       totalMoves+=ongoing.calculateDistance(disc.getActualPos());
                       disc.setActualPos(ongoing.getRequestedPos());
                       leftSide.remove(ongoing);
                       executionList.remove(ongoing);
                    }
                    else{
                        leftSide.remove(ongoing);
                        rightSide.add(ongoing);
                    }
                }
                if(!rightSide.isEmpty()) {
                    totalMoves += disc.getActualPos() - 1;
                }
                disc.setActualPos(1);
                direction=true;
            }
            else {
                while(!rightSide.isEmpty() && direction){
                    rightSide.sort(Comparator.comparing(Process::getInputTime).thenComparing(Process::getRequestedPos));
                    Process ongoing=rightSide.get(0);
                    if(ongoing.getInputTime()<=clock){
                        totalMoves+=ongoing.calculateDistance(disc.getActualPos());
                        disc.setActualPos(ongoing.getRequestedPos());
                        rightSide.remove(ongoing);
                        executionList.remove(ongoing);
                    }
                    else{
                        rightSide.remove(ongoing);
                        leftSide.add(ongoing);
                    }
                }
                if(!leftSide.isEmpty()) {
                    totalMoves += (disc.getCylinders()) - (disc.getActualPos());
                }
                disc.setActualPos(disc.getCylinders());
                direction=false;
            }
        }
        return totalMoves;
    }
}
