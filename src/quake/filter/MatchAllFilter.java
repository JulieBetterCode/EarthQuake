package quake.filter;

import quake.QuakeEntry;
import quake.filter.Filter;

import java.util.ArrayList;

/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MatchAllFilter implements Filter
{
    private ArrayList<Filter> matchAll;

    public MatchAllFilter(){
        matchAll = new ArrayList<Filter>();
    }

    public void addFilter(Filter fe){
        matchAll.add(fe);
    }

    public boolean satisfies(QuakeEntry qe) {
        for(Filter f: matchAll){
            if(!f.satisfies(qe)) return false;
        }
        return true;
    }

    public String getName() {
        ArrayList<String> nameAll = new ArrayList<String>();
        for (Filter f : matchAll){
            nameAll.add(f.getName());
        }
        String New = "";
        for (int i=0; i< nameAll.size(); i++){
            New = New + " " + nameAll.get(i);
        }
        return New ;
    };

}
