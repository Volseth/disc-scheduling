import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SCANTest {

    @Test
    void calculateTotalDistance() {
        ArrayList<Process> processes = new ArrayList<>();
        processes.add(new Process(98, 0));
        processes.add(new Process(183, 0));
        processes.add(new Process(37, 0));
        processes.add(new Process(122, 0));
        processes.add(new Process(14, 0));
        processes.add(new Process(124, 0));
        processes.add(new Process(65, 0));
        processes.add(new Process(67, 0));
        Disc disc = new Disc(200, 53);
        DiscScheduling discScheduling=new SCAN(disc,processes);

        ArrayList<Process> processes2=new ArrayList<>();
        processes2.add(new Process(128,0));
        processes2.add(new Process(36,0));
        processes2.add(new Process(138,0));
        processes2.add(new Process(21,2));
        processes2.add(new Process(50,2));
        processes2.add(new Process(200,2));
        Disc disc2=new Disc(200,40);
        DiscScheduling discScheduling2=new SCAN(disc2,processes2);
        assertEquals(417,discScheduling2.calculateTotalDistance());
        assertEquals(333,discScheduling.calculateTotalDistance());
    }
}