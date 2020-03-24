package quake.comparator;

import quake.QuakeEntry;

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String title1 = qe1.getInfo();
        String title2 = qe2.getInfo();

        int compare = title1.substring(title1.lastIndexOf(" ")+1).compareTo(title2.substring(title2.lastIndexOf(" ")+1));
        if(compare == 0)
            return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        return compare;
    }
    
}
