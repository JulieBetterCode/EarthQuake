package quake.filter;

import quake.Location;
import quake.QuakeEntry;
import quake.filter.Filter;

/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter
{
    private double distMax;
    private Location location;

    public DistanceFilter(double dist, Location loc) {
        distMax = dist;
        location = loc;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) < distMax;
    }

    public String getName(){
        return "DistanceFilter";
    }

}
