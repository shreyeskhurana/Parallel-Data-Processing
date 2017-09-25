import Tmax.Concurrent.ConcComp;
import Tmax.Sequential.SeqComp;
import Util.FileHandler;

import java.util.List;

public class PDPMain {
    public static void main(String[] args) {
        //Path of the file to be read.
        String path = "files/1912.csv";

        //List of parsed lines of the file
        List<String> rows = FileHandler.readFile(path);

        System.out.println(rows.size());
        //Objective: Calculate average 'TMAX' of each Station

        //Initialize: Sequential Computation Object
        SeqComp seqComp = new SeqComp();
        seqComp.countByStation(rows);


        //Initialize: Concurrent Computation Object
        ConcComp concComp = new ConcComp();

        //NO-LOCK version
        concComp.noLock(rows);

        //COARSE-LOCK version
        concComp.coarseLock(rows);

        //FINE-LOCK version
        concComp.fineLock(rows);

        //NO-SHARE version
        concComp.NoShare(rows);

    }
}
