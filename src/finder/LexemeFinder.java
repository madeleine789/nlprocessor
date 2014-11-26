package finder;

import finder.IFinder;
import search.ResultsCollector;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by M on 2014-10-20.
 */
public class LexemeFinder implements IFinder
{
    protected static String word;
    protected List<String> results;
    protected List<String> dictionary;
    protected ResultsCollector resultsCollector;

    public LexemeFinder(String word, Set<String> results, List<String> dictionary, ResultsCollector resultsCollector)
    {
        this.word = word;
        this.results = new ArrayList<String>();
        this.dictionary = dictionary;
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
