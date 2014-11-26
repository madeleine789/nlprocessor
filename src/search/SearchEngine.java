package search;

import dictionary.Dict;
import finder.DiminutiveFinder;
import finder.InflectionFinder;
import finder.SynonymFinder;
import morfologik.stemming.Dictionary;
import morfologik.stemming.DictionaryLookup;
import morfologik.stemming.WordData;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by M on 2014-10-20.
 */
public class SearchEngine
{
    protected static String word;
    protected String input;
    protected Set<String> results;
    protected Set<String> lexemes;
    protected ResultsCollector resultsCollector;
    protected static Map<Dict, dictionary.Dictionary> dictionaries;


    public SearchEngine(String input,Map<Dict, dictionary.Dictionary> dictionaries)
    {
        this.input = input;
        this.dictionaries = dictionaries;
        this.results = new HashSet<>();
        this.lexemes = searchForLexemes(input);
        this.resultsCollector = new ResultsCollector(word);

    }

    public boolean startSearch()
    {
        if (lexemes.isEmpty())
            return false;
        else
        {
            resultsCollector.processPartialResults(lexemes);
            //search for the rest of stuff
            return true;
        }
    }

    public Set<String> search(String word, boolean inflections, boolean synonyms, boolean diminutives)
    {
        if (synonyms)
        {
            SynonymFinder synonymFinder = new SynonymFinder(word, results, resultsCollector);
        }
        if (inflections)
        {
            InflectionFinder synonymFinder = new InflectionFinder(word, results, resultsCollector);
        }
        if (diminutives)
        {
            DiminutiveFinder diminutiveFinder = new DiminutiveFinder(word, results, resultsCollector);
        }

        /*
        To be continued...
         */

        return resultsCollector.getFinalResults();
    }

    public Set<String> searchForLexemes(String word)
    {
        Dictionary polish = Dictionary.getForLanguage("pl");
        DictionaryLookup dl = new DictionaryLookup(polish);
        List<WordData> wordList = dl.lookup(word);
        if (wordList.isEmpty())
            return null;
        else
        {
            for(int i = 0; i < wordList.size(); i++){
                CharSequence stem = wordList.get(i).getStem();
                StringBuilder sb = new StringBuilder(stem.length());
                sb.append(stem);
                lexemes.add(sb.toString());
            }

        }
        return lexemes;
    }


}
