package quake;
import java.util.*;

public class QuakeSort {
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    /**
     * Write the method getLargestDepth that has two parameters,
     * an ArrayList of type QuakeEntry named quakeData and an int named from representing an index position in the ArrayList.
     * This method returns an integer representing the index position of the QuakeEntry
     * with the largest depth considering only those QuakeEntry’s from position from to the end of the ArrayList.
     */
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int index) {
        int indDeepest = index;
        for(int i = index; i < quakeData.size(); i++) {
            double depth = quakeData.get(i).getDepth();
            double largestDepth = quakeData.get(indDeepest).getDepth();
            if(depth > largestDepth) indDeepest = i;
        }
        return indDeepest;
    }

    /**
     * Write the void method sortByLargestDepth that has one parameter,
     * an ArrayList of type QuakeEntry named in.
     * This method sorts the QuakeEntry’s in the ArrayList by depth using the selection sort algorithm,
     * but in reverse order from largest depth to smallest depth
     * (the QuakeEntry with the largest depth should be in the 0th position in the ArrayList).
     * This method should call the method getLargestDepth repeatedly until the ArrayList is sorted.
     */
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        int limit = 70;
        for (int i=0; i<limit; i++) {
            int indDeepest = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qDeepest = in.get(indDeepest);
            in.set(i, qDeepest);
            in.set(indDeepest, qi);
        }
    }
    
    public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in) {
        //out starts as empty ArrayList
        ArrayList<QuakeEntry> out = new ArrayList<QuakeEntry>();
        //As long as in is not empty
        while(!in.isEmpty()) {
            //Find smallest element in in (minElement)
            int flag = getSmallestMagnitude(in, 0);
            QuakeEntry minElement = in.get(flag);
            //Remove minElement from in
            in.remove(minElement);                            
            //Add minElement to out
            out.add(minElement);
        }
        //out is the answer
        return out;
    }

    /* tester method to use in BlueJ */
    public void testSort(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "C:\\Users\\lingxia\\IdeaProjects\\EarthQuake\\src\\data\\earthQuakeDataDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //for(QuakeEntry qe: list) {
        //    System.out.println(qe);
        //}
    }


    /**
     * Sorting quakes by magnitude.
     * This method makes one pass of bubble sort on the ArrayList.
     * @param quakeData
     * @param numSorted	number of times this method has already been called on
     * this ArrayList
     */
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {

        for (int i = 0; i < quakeData.size()-1-numSorted; i++) {
            QuakeEntry q1 = quakeData.get(i);
            QuakeEntry q2 = quakeData.get(i+1);

            if (q1.getMagnitude() > q2.getMagnitude()) {
                quakeData.set(i, q2);
                quakeData.set(i+1,q1);
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for(int i = 0; i < in.size(); i++)
            onePassBubbleSort(in, i);
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for(int i = 0; i < quakes.size() - 1; i++) {
            if(quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude())
                return false;
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        for (int i=0; i<in.size();i++) {
            if (checkInSortedOrder(in)) {
                System.out.printf("Number of passes: %d\n", i);
                break;
            }
            onePassBubbleSort(in, i);
        }
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        for (int i=0; i< in.size(); i++) {
            if (checkInSortedOrder(in)) {
                System.out.printf("Number of passes: %d\n", i);
                break;
            }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
    }

}
