import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Test {
    private static void fillCollectionWithExampleData(HashSet<Process> processesQueue,int numberOfRequests, int maxRequestedPos, int maxInputTime, int maxPriority) {
        for (int i = 0; i < numberOfRequests; i++) {
            if(i%4==0) {
                Process p = new Process(maxRequestedPos, maxInputTime, maxPriority);
                processesQueue.add(p);
            }
            else {
                Process withoutPriority = new Process(maxRequestedPos, maxInputTime, 0);
                processesQueue.add(withoutPriority);
            }

        }
    }
    private static void saveCollection(ArrayList<Process> list){
        try(BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(new File("plik.csv")))){
            for(Process p:list){
                bufferedWriter.write(p.toString());
                bufferedWriter.newLine();
            }
        }
        catch(IOException e){
            e.printStackTrace();
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
        Disc disc=new Disc(cylinders);
        FCFS discScheduling=new FCFS(disc,discRequestsForSimulation);
        SSTF discScheduling2=new SSTF(disc,discRequestsForSimulation);
        SCAN discScheduling3=new SCAN(disc,discRequestsForSimulation);
        CSCAN discScheduling4=new CSCAN(disc,discRequestsForSimulation);
        EDFFCFS discScheduling5=new EDFFCFS(disc,discRequestsForSimulation);
        EDFSSTF discScheduling6=new EDFSSTF(disc,discRequestsForSimulation);
        FDSCAN discScheduling7=new FDSCAN(disc,discRequestsForSimulation);
        FDCSCAN discScheduling8=new FDCSCAN(disc,discRequestsForSimulation);
        saveCollection(discRequestsForSimulation);
        System.out.println("Ilość przemiesczeń FCFS:"+discScheduling.calculateTotalDistance());
        discScheduling.clean();
        System.out.println("Ilość przemiesczeń SSTF:"+discScheduling2.calculateTotalDistance());
        discScheduling2.clean();
        System.out.println("Ilość przemiesczeń SCAN:"+discScheduling3.calculateTotalDistance());
        discScheduling3.clean();
        System.out.println("Ilość przemiesczeń CSCAN:"+discScheduling4.calculateTotalDistance());
        discScheduling4.clean();
        System.out.println("Ilość przemiesczeń EDF-FCFS:"+discScheduling5.calculateTotalDistance());
        discScheduling5.clean();
        System.out.println("Ilość przemiesczeń EDF-SSTF:"+discScheduling6.calculateTotalDistance());
        discScheduling6.clean();
        //System.out.println("Ilość przemiesczeń FD-SCAN:"+discScheduling8.calculateTotalDistance());
        //discScheduling7.clean();
        System.out.println("Ilość przemiesczeń FD-CSCAN:"+discScheduling8.calculateTotalDistance());
        discScheduling8.clean();

    }
}
