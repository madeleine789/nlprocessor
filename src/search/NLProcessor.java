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
    protected DictionaryLoader dictionaryLoader;

    public NLProcessor() throws Exception
    {
        loadDictionaries();
    }

    public void loadDictionaries() throws IOException, InterruptedException
    {
        DictionaryLoader dictionaryLoader = new DictionaryLoader();
        Thread thread = new Thread(dictionaryLoader);
        thread.start();
    }

    public Set<String> findWords(String word, WordVariant wordVariant)
    {
        dictionaries = dictionaryLoader.getDictionaries();

        try
        {
            result.clear();
            LexemeFinder lexemeFinder = new LexemeFinder(word);
            boolean hasLexemes = lexemeFinder.startSearch();
            if (!hasLexemes)
            {
                result.add(word);
            /*
            Mogloby byc potrzebne jakby PatternMatcher przekazywał wyrażenia kilkuwyrazowe

            if (wordVariant.searchForInflections())
            {
                Collection<HashSet<String>>  check = dictionaries.get(Dict.INFLECTIONS).get(word);
                check.forEach(result::addAll);
            }
            if (wordVariant.searchForSynonyms())
            {
                Collection<HashSet<String>>  check = dictionaries.get(Dict.SYNONYMS).get(word);
                check.forEach(result::addAll);
            }
            if (wordVariant.searchForDiminutives())
            {
                Collection<HashSet<String>>  check = dictionaries.get(Dict.DIMINUTIVES).get(word);
                check.forEach(result::addAll);
            }
            */

            }

            else
            {
                Set<String> lexemes = lexemeFinder.getLexemes();
                result.addAll(lexemes);
                for(int i = 0; i < lexemes.size(); i++)
                {
                    String searched = (String)lexemes.toArray()[i];
                    SearchEngine searchEngine = new SearchEngine(searched, dictionaries, wordVariant);
                    searchEngine.search(searched);
                    result.addAll(searchEngine.getResults());

                }
            }
        } catch (NullPointerException e) {

        }
        finally {
            return result;
        }

    }

    public static void main(String[] args) throws Exception
    {
        NLProcessor nlp = new NLProcessor();
        nlp.loadDictionaries();

        System.out.println(nlp.findWords("czasem", new WordVariant(true, true, false)));
        System.out.println(nlp.findWords("lackadaisical", new WordVariant(true, true, false)));

        System.out.println(nlp.findWords("kotki", new WordVariant(true, false, false)));
        System.out.println(nlp.findWords("exhilarated", new WordVariant(true, false, false)));

        System.out.println(nlp.findWords("bonanzy", new WordVariant(false, true, false)));
        System.out.println(nlp.findWords("perpendicular", new WordVariant(false, true, false)));

        System.out.println(nlp.findWords( "", new WordVariant(true, false, false)));
        System.out.println(nlp.findWords( null, new WordVariant(true, false, false)));


        Random r = new Random();
        System.out.println(r.nextInt(151238));


    }


}
