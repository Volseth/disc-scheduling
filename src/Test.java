import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Test {
    private static void fillCollectionWithExampleData(HashSet<Process> processesQueue,int numberOfRequests, int maxRequestedPos, int maxInputTime, int maxPriority) {
        for (int i = 0; i < numberOfRequests; i++) {
            Process p= new Process(maxRequestedPos,maxInputTime,maxPriority);
            Process withoutPriority=new Process(maxRequestedPos,maxInputTime,0);
            processesQueue.add(p);
        }
    }
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Podaj ilość żądań do dysku:");
        int request= scanner.nextInt();
        System.out.println("Podaj maksymalny czas nadejścia procesu");
        int inputTime= scanner.nextInt();
        System.out.println("Podaj wielkość dysku");
        int cylinders= scanner.nextInt();
        System.out.println("Podaj maksymalną wartość prioryteru dla procesu RealTime");
        int priority= scanner.nextInt();
        HashSet<Process> discRequests=new HashSet<>();
        fillCollectionWithExampleData(discRequests,request,cylinders,inputTime,priority);
        ArrayList<Process> discRequestsForSimulation= new ArrayList<>(discRequests);
        Iterator iterator=discRequests.iterator();
        while(iterator.hasNext()){
            Process p= (Process) iterator.next();
            System.out.println(p.toString());
        }

    }
}
