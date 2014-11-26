package search;

import dictionary.Dict;
import finder.Finder;
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
    protected Set<String> lexemes = new HashSet<>();
    protected static Map<Dict, dictionary.Dictionary> dictionaries;


    public SearchEngine(String input,Map<Dict, dictionary.Dictionary> dictionaries)
    {
        this.input = input;
        this.dictionaries = dictionaries;
        this.results = new HashSet<>();
        this.lexemes = searchForLexemes(input);
    }

    public boolean startSearch()
    {
        if (lexemes.isEmpty())
            return false;
        else
        {
            results.addAll(lexemes);
            //search for the rest of stuff
            return true;
        }
    }

    public Set<String> search(String word, boolean inflections, boolean synonyms, boolean diminutives)
    {
        Set<String> synonymsSet, inflectionSet, diminutiveSet;
        Finder synonymFinder, inflectionFinder, diminutiveFinder;


        if (synonyms && inflections && diminutives)
        {
            synonymFinder = new Finder(word,dictionaries.get(Dict.SYNONYMS));
            synonymsSet = synonymFinder.getResults();
            for(String synonym : synonymsSet)
                results.addAll(new Finder(synonym, dictionaries.get(Dict.INFLECTIONS)).getResults());
            results.addAll(synonymsSet);

            diminutiveFinder = new Finder(word,dictionaries.get(Dict.DIMINUTIVES));
            diminutiveSet = diminutiveFinder.getResults();
            for(String diminutive : diminutiveSet)
                results.addAll(new Finder(diminutive, dictionaries.get(Dict.INFLECTIONS)).getResults());
            results.addAll(diminutiveSet);

            inflectionFinder = new Finder(word,dictionaries.get(Dict.INFLECTIONS));
            inflectionSet = inflectionFinder.getResults();
            results.addAll(inflectionSet);
        }
        else if (synonyms && inflections)
        {
            synonymFinder = new Finder(word,dictionaries.get(Dict.SYNONYMS));
            synonymsSet = synonymFinder.getResults();
            for(String synonym : synonymsSet)
                results.addAll(new Finder(synonym, dictionaries.get(Dict.INFLECTIONS)).getResults());
            results.addAll(synonymsSet);

            inflectionFinder = new Finder(word,dictionaries.get(Dict.INFLECTIONS));
            inflectionSet = inflectionFinder.getResults();
            results.addAll(inflectionSet);
        }
        else if (diminutives && inflections)
        {
            diminutiveFinder = new Finder(word,dictionaries.get(Dict.DIMINUTIVES));
            diminutiveSet = diminutiveFinder.getResults();
            for(String diminutive : diminutiveSet)
                results.addAll(new Finder(diminutive, dictionaries.get(Dict.INFLECTIONS)).getResults());
            results.addAll(diminutiveSet);

            inflectionFinder = new Finder(word,dictionaries.get(Dict.INFLECTIONS));
            inflectionSet = inflectionFinder.getResults();
            results.addAll(inflectionSet);
        }
        else if (diminutives && synonyms)
        {
            synonymFinder = new Finder(word,dictionaries.get(Dict.SYNONYMS));
            synonymsSet = synonymFinder.getResults();
            for(String synonym : synonymsSet)
                results.addAll(new Finder(synonym, dictionaries.get(Dict.DIMINUTIVES)).getResults());
            results.addAll(synonymsSet);

            diminutiveFinder = new Finder(word,dictionaries.get(Dict.DIMINUTIVES));
            diminutiveSet = diminutiveFinder.getResults();
            for(String diminutive : diminutiveSet)
                results.addAll(new Finder(diminutive, dictionaries.get(Dict.SYNONYMS)).getResults());
            results.addAll(diminutiveSet);
        }
        else if (synonyms)
        {
            synonymFinder = new Finder(word,dictionaries.get(Dict.SYNONYMS));
            synonymsSet = synonymFinder.getResults();
            results.addAll(synonymsSet);
        }
        else if (inflections)
        {
            inflectionFinder = new Finder(word,dictionaries.get(Dict.INFLECTIONS));
            inflectionSet = inflectionFinder.getResults();
            results.addAll(inflectionSet);
        }
        else if (diminutives)
        {
            diminutiveFinder = new Finder(word,dictionaries.get(Dict.DIMINUTIVES));
            diminutiveSet = diminutiveFinder.getResults();
            results.addAll(diminutiveSet);
        }


        return results;


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
