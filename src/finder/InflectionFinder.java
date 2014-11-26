package finder;

import finder.IFinder;
import search.ResultsCollector;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by M on 2014-10-20.
 */
public class InflectionFinder implements IFinder
{
    protected static String word;
    protected List<String> results;
    protected ResultsCollector resultsCollector;

    public InflectionFinder(String word, Set<String> results, ResultsCollector resultsCollector)
    {
        this.word = word;
        this.results = new ArrayList<String>();
        this.resultsCollector = resultsCollector;

    }

    @Override
    public Set<String> find(String searched) {
        return null;
    }

    public void addToResults(String word)
    {
        results.add(word);
    }

    public void run()
    {

    }

}
