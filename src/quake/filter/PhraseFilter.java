package quake.filter;

import quake.QuakeEntry;
import quake.filter.Filter;

/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter
{
    private String location;
    private String syntax;

    public PhraseFilter(String loc, String str) {
        location = loc;
        syntax = str;
    } 

    public boolean satisfies(QuakeEntry qe) {
        if(location == "start")
            return qe.getInfo().startsWith(syntax);
        else if(location == "end")
            return qe.getInfo().endsWith(syntax);
        else if(location == "any")
            return qe.getInfo().contains(syntax);
        else return false;
    }

    public String getName(){
        return "PhraseFilter";
    }

}
