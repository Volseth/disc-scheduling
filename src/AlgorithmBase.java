import java.util.ArrayList;

public class AlgorithmBase {
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
    public void clean(){
        clock=0;
        for (Process p:processes){
            p.setTraveledDistance(0);
        }
        disc.setActualPos(disc.getStartingPos());
    }

}
