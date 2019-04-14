import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SSTFTest {

    @Test
    void calculateTotalDistance() {
       /* ArrayList<Process> processes = new ArrayList<>();
        processes.add(new Process(128,0));
        processes.add(new Process(36,0));
        processes.add(new Process(138,0));
        processes.add(new Process(21,2));
        processes.add(new Process(50,2));
        processes.add(new Process(200,2));
        */

        ArrayList<Process> processes1=new ArrayList<>();
        processes1.add(new Process(98, 0));
        processes1.add(new Process(183, 0));
        processes1.add(new Process(37, 0));
        processes1.add(new Process(122, 0));
        processes1.add(new Process(14, 0));
        processes1.add(new Process(124, 0));
        processes1.add(new Process(65, 0));
        processes1.add(new Process(67, 0));
        Disc disc = new Disc(200,0);
        Disc disc2 = new Disc(200,53);
        //DiscScheduling discScheduling=new SSTF(disc,processes);
        DiscScheduling discScheduling1=new SSTF(disc2,processes1);
        //assertEquals(379,discScheduling.calculateTotalDistance());
        assertEquals(236,discScheduling1.calculateTotalDistance());

    }
}