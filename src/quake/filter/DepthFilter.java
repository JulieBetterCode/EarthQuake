package quake.filter;

import quake.QuakeEntry;
import quake.filter.Filter;

/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter
{
    private double depMin;
    private double depMax;

    public DepthFilter(double min, double max) {
        depMin = min;
        depMax = max;
    } 

    public boolean satisfies(QuakeEntry qe) {

        return qe.getDepth() > depMin && qe.getDepth() < depMax;
    }

    public String getName(){
        return "DepthFilter";
    }

}
