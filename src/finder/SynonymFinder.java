package finder;//import output.search.ResultsCollector;

import finder.IFinder;
import search.ResultsCollector;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by M on 2014-10-20.
 */


public class SynonymFinder implements IFinder
{
    protected static String word;
    protected Set<String> results;
    protected ResultsCollector resultsCollector;

    public SynonymFinder(String word, Set<String> results, ResultsCollector resultsCollector)
    {
        this.word = word;
        this.results = new HashSet<>();
        this.resultsCollector = resultsCollector;

    }


    public Set<String> find(String searched)
    {

        return results;

    }

    public void addToResults(String word)
    {
        results.add(word);
    }


    public void run()
    {
        resultsCollector.processPartialResults(find(word));
    }


}
