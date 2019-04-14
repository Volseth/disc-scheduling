import java.util.ArrayList;
import java.util.Comparator;

public class EDFFCFS extends AlgorithmBase implements DiscScheduling{
    public EDFFCFS(Disc disc, ArrayList<Process> processes){
        super(disc,processes);
    }

    @Override
    public int calculateTotalDistance() {
        ArrayList<Process> executionList=new ArrayList<>(processes);
        executionList.sort(Comparator.comparing(Process::getInputTime).thenComparing(Process::getRealTimePriority).reversed());
        DiscScheduling discScheduling=new FCFS(disc,executionList);
        return discScheduling.calculateTotalDistance();
    }
}
