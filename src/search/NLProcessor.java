package search;

import com.google.common.collect.Multimap;
import dictionary.Dict;
import dictionary.DictionaryLoader;
import finder.LexemeFinder;

import java.io.IOException;
import java.util.*;

/**
 * Created by M on 2014-11-03.
 */
public class NLProcessor
{
    //private static search.NLProcessor NLProcessorInstance = null;

    protected static Map<Dict, Multimap<String,HashSet<String>>> dictionaries = new HashMap<>();
    protected static Set<String> result = new HashSet<>();

    protected NLProcessor() throws Exception
    {
        //loadDictionaries();
    }

    private void loadDictionaries() throws IOException, InterruptedException {
        DictionaryLoader dictionaryLoader = new DictionaryLoader();
        Thread thread = new Thread(dictionaryLoader);
        thread.start();
        thread.join();
        dictionaries = dictionaryLoader.getDictionaries();

    }

    public Set<String> findWords(String word, WordVariant wordVariant)
    {
        result.clear();
        LexemeFinder lexemeFinder = new LexemeFinder(word);

        if (!lexemeFinder.startSearch())
            result.add(word);
        else
        {
            Set<String> lexemes = lexemeFinder.getLexemes();
            result.addAll(lexemes);
            for(int i = 0; i < lexemes.size(); i++)
            {
                String searched = (String)lexemes.toArray()[i];
                SearchEngine searchEngine = new SearchEngine(searched, dictionaries, wordVariant);
                Set<String> set = searchEngine.search(searched);
                result.addAll(searchEngine.getResults());

            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception
    {
        NLProcessor nlp = new NLProcessor();
        nlp.loadDictionaries();
        System.out.println(nlp.findWords("mam", new WordVariant(true, true, false)));

        System.out.println(nlp.findWords("picie", new WordVariant(true, true, false)));
    }


}
