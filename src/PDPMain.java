import MultiThreading.Concurrent.ConcComp;
import MultiThreading.Sequential.SeqComp;
import Util.FileHandler;

import java.util.List;

public class PDPMain {
    public static void main(String[] args) {
        //Path of the file to be read.
        String path = "files/1912.csv";

        List<String> rows = FileHandler.readCsv(path);

        SeqComp seqComp = new SeqComp();
        seqComp.countByStation(rows);

        ConcComp concComp = new ConcComp();
        concComp.noLock(rows);
        concComp.coarseLock(rows);
        concComp.fineLock(rows);
        concComp.NoShare(rows);
    }
}
