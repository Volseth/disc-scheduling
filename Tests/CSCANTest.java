import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CSCANTest {

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
        DiscScheduling discScheduling=new CSCAN(disc,processes);
        assertEquals(184,discScheduling.calculateTotalDistance());
    }
}