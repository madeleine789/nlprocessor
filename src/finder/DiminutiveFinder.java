package finder;

import finder.IFinder;
import search.ResultsCollector;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by M on 2014-10-27.
 */
public class DiminutiveFinder implements IFinder {

    protected static String word;
    protected Set<String> results;
    protected ResultsCollector resultsCollector;

    public DiminutiveFinder(String word, Set<String> results, ResultsCollector resultsCollector)
    {
        this.word = word;
        this.results = new HashSet<>();
        this.resultsCollector = resultsCollector;

    }

    @Override
    public Set<String> find(String searched) {
        return null;
    }

    @Override
    public void addToResults(String word)
    {

    }

    @Override
    public void run()
    {

    }
}
