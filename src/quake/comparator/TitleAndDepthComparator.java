package quake.comparator;

import quake.QuakeEntry;
import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        int compare = qe1.getInfo().compareTo(qe2.getInfo());
        if(compare == 0)
            return Double.compare(qe1.getDepth(), qe2.getDepth());
        return compare;
    }
    
}
