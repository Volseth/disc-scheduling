import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EDFFCFSTest {

    @Test
    void calculateTotalDistance() {
        ArrayList<Process> processes = new ArrayList<>();
        Process p1=new Process(98,0);
        p1.setRealTimePriority(1);
        Process p2=new Process(183, 0);
        Process p3=new Process(37, 0);
        Process p4=new Process(122, 0);
        p4.setRealTimePriority(1);
        Process p5=new Process(14, 0);
        Process p6=new Process(124, 0);
        p6.setRealTimePriority(1);
        Process p7=new Process(65, 0);
        Process p8=new Process(67, 0);
        p8.setRealTimePriority(1);
        processes.add(p1);
        processes.add(p2);
        processes.add(p3);
        processes.add(p4);
        processes.add(p5);
        processes.add(p6);
        processes.add(p7);
        processes.add(p8);
        Disc disc = new Disc(200, 53);
        DiscScheduling discScheduling=new EDFFCFS(disc,processes);
        assertEquals(464,discScheduling.calculateTotalDistance());
    }
}