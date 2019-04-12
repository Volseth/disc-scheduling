import java.util.ArrayList;

public abstract class AlgorithmBase {
    Disc disc;
    ArrayList<Process> processes;
    int clock=0;
    AlgorithmBase(Disc disc, ArrayList<Process> processes){
        this.disc=disc;
        this.processes=new ArrayList<>(processes);
    }
    public Process findNearestProcess(ArrayList<Process> list){
        Process nearest=list.get(0);
        for(Process p:list){
            if(p.calculateDistance(disc.getActualPos())<nearest.calculateDistance(disc.getActualPos()) && p.getInputTime()<=clock){
                nearest=p;
            }
        }
        return nearest;
    }

}
