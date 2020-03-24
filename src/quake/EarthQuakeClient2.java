package quake;
import quake.filter.DepthFilter;
import quake.filter.Filter;
import quake.filter.MagnitudeFilter;
import quake.filter.MatchAllFilter;

import java.util.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "C:\\Users\\lingxia\\IdeaProjects\\EarthQuake\\src\\data\\nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        //Location city = new Location(36.1314, -95.9372);
        Filter f1 = new MagnitudeFilter(4.0, 5.0);
        //Filter f1 = new DistanceFilter(10000000, city);
        ArrayList<QuakeEntry> m  = filter(list, f1);

        Filter f2 = new DepthFilter(-35000.0, -12000.0 );
        //Filter f2 = new PhraseFilter("end", "Japan");
        ArrayList<QuakeEntry> m7  = filter(m, f2);
        for (QuakeEntry qe: m7) {
            System.out.println(qe);
        }
        System.out.println("get data for "+m7.size()+" quakes");
    }

    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "C:\\Users\\lingxia\\IdeaProjects\\EarthQuake\\src\\data\\nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        MatchAllFilter maf = new MatchAllFilter();
        //Filter f1 = new MagnitudeFilter(0.0, 5.0);
        //maf.addFilter(f1);
        Filter f2 = new DepthFilter(-4000.0,-2000.0);
        maf.addFilter(f2);
        //Filter f3 = new PhraseFilter("any", "e");
        //maf.addFilter(f3);
        //Location city = new Location(55.7308, 9.1153);
        //Filter f4 = new DistanceFilter(3000000, city);
        //maf.addFilter(f4);

        ArrayList<QuakeEntry> result  = filter(list, maf);
        for (QuakeEntry qe: result) {
            System.out.println(qe);
        }
        System.out.println("get data for "+result.size()+" quakes");
        System.out.println("Filters used are: " + maf.getName());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
