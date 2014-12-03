package search;

import com.google.common.collect.Multimap;
import dictionary.Dict;
import finder.Finder;
import morfologik.stemming.Dictionary;
import morfologik.stemming.DictionaryLookup;
import morfologik.stemming.WordData;


import java.util.*;

/**
 * Created by M on 2014-10-20.
 */
public class SearchEngine
{
    protected static String word;
    protected String input;
    protected Set<String> results = new HashSet<>();
    protected Set<String> lexemes = new HashSet<>();
    protected static Map<Dict, Multimap<String,HashSet<String>>> dictionaries;
    protected WordVariant wordVariant;
    protected Map<String, Set<String>> result = new HashMap<>();


    public SearchEngine(String input,Map<Dict, Multimap<String,HashSet<String>>> dictionaries, WordVariant wordVariant)
    {
        this.input = input;
        this.dictionaries = dictionaries;
        this.wordVariant = wordVariant;
        this.results.clear();
    }


    public Set<String> getResults() {
        return results;
    }


    public Set<String> search(String word)
    {
        Set<String> synonymsSet = new HashSet<>(), inflectionSet = new HashSet<>(), diminutiveSet = new HashSet<>();
        Finder synonymFinder, inflectionFinder, diminutiveFinder;
        boolean inflections = this.wordVariant.searchForInflections();
        boolean synonyms = this.wordVariant.searchForSynonyms();
        boolean diminutives = this.wordVariant.searchForDiminutives();
        Set<String> searchResults = new HashSet<>();


        if (synonyms && inflections && diminutives)
        {

            synonymFinder = new Finder(word,dictionaries.get(Dict.SYNONYMS));
            synonymsSet = synonymFinder.getResults();
            HashSet<String> partialResults1 =  new HashSet<>();
            for(int i = 0; i < synonymsSet.size(); i++)
            {

                String synonym = (String) synonymsSet.toArray()[i];
                if(synonym.contains(" "))
                    partialResults1.add(synonym);
                else
                {
                    Set<String> found = new Finder(synonym, dictionaries.get(Dict.INFLECTIONS)).getResults();
                    partialResults1.addAll(found);
                }

            }
            inflectionSet.addAll(partialResults1);

            HashSet<String> partialResults2 =  new HashSet<>();
            for(int i = 0; i < diminutiveSet.size(); i++)
            {
                String diminutive = (String) diminutiveSet.toArray()[i];
                Set<String> found = new Finder(diminutive, dictionaries.get(Dict.SYNONYMS)).getResults();
                partialResults2.addAll(found);
            }
            synonymsSet.addAll(partialResults2);

            diminutiveFinder = new Finder(word,dictionaries.get(Dict.DIMINUTIVES));
            diminutiveSet = diminutiveFinder.getResults();
            HashSet<String> partialResults3 =  new HashSet<>();
            for(int i = 0; i < diminutiveSet.size(); i++)
            {
                String diminutive = (String) diminutiveSet.toArray()[i];
                Set<String> found = new Finder(diminutive, dictionaries.get(Dict.INFLECTIONS)).getResults();
                partialResults3.addAll(found);
            }
            inflectionSet.addAll(partialResults3);

            HashSet<String> partialResults4 =  new HashSet<>();
            for(int i = 0; i < synonymsSet.size(); i++)
            {
                String synonym = (String) synonymsSet.toArray()[i];
                Set<String> found = new Finder(synonym, dictionaries.get(Dict.DIMINUTIVES)).getResults();
                partialResults1.addAll(found);
            }
            diminutiveSet.addAll(partialResults4);

            inflectionFinder = new Finder(word,dictionaries.get(Dict.INFLECTIONS));
            inflectionSet = inflectionFinder.getResults();


            searchResults.addAll(inflectionSet);
            searchResults.addAll(synonymsSet);
            searchResults.addAll(diminutiveSet);

        }
        else if (synonyms && inflections)
        {
            synonymFinder = new Finder(word,dictionaries.get(Dict.SYNONYMS));
            synonymsSet = synonymFinder.getResults();
            HashSet<String> partialResults =  new HashSet<>();

            for(int i = 0; i < synonymsSet.size(); i++)
            {
                String synonym = (String) synonymsSet.toArray()[i];
                if(synonym.contains(" "))
                    partialResults.add(synonym);
                else
                {
                    Set<String> found = new Finder(synonym, dictionaries.get(Dict.INFLECTIONS)).getResults();
                    partialResults.addAll(found);
                }

            }

            inflectionFinder = new Finder(word,dictionaries.get(Dict.INFLECTIONS));
            inflectionSet = inflectionFinder.getResults();
            inflectionSet.addAll(partialResults);
            searchResults.addAll(inflectionSet);
            searchResults.addAll(synonymsSet);
            searchResults.add(word);
        }
        else if (diminutives && inflections)
        {

            diminutiveFinder = new Finder(word,dictionaries.get(Dict.DIMINUTIVES));
            diminutiveSet = diminutiveFinder.getResults();
            HashSet<String> partialResults =  new HashSet<>();

            for(int i = 0; i < diminutiveSet.size(); i++)
            {
                String diminutive = (String) diminutiveSet.toArray()[i];
                Set<String> found = new Finder(diminutive, dictionaries.get(Dict.INFLECTIONS)).getResults();
                partialResults.addAll(found);
            }

            inflectionFinder = new Finder(word,dictionaries.get(Dict.INFLECTIONS));
            inflectionSet = inflectionFinder.getResults();
            inflectionSet.addAll(partialResults);
            searchResults.addAll(diminutiveSet);
            searchResults.addAll(inflectionSet);

        }
        else if (diminutives && synonyms)
        {

            synonymFinder = new Finder(word,dictionaries.get(Dict.SYNONYMS));
            synonymsSet = synonymFinder.getResults();
            HashSet<String> partialResults1 =  new HashSet<>();
            for(int i = 0; i < synonymsSet.size(); i++)
            {

                String synonym = (String) synonymsSet.toArray()[i];
                if(synonym.contains(" "))
                    partialResults1.add(synonym);
                else
                {
                    Set<String> found = new Finder(synonym, dictionaries.get(Dict.DIMINUTIVES)).getResults();
                    partialResults1.addAll(found);
                }

            }

            diminutiveFinder = new Finder(word,dictionaries.get(Dict.DIMINUTIVES));
            diminutiveSet = diminutiveFinder.getResults();
            HashSet<String> partialResults2 =  new HashSet<>();
            for(int i = 0; i < diminutiveSet.size(); i++)
            {
                String diminutive = (String) diminutiveSet.toArray()[i];
                Set<String> found = new Finder(diminutive, dictionaries.get(Dict.SYNONYMS)).getResults();
                partialResults2.addAll(found);
            }

            synonymsSet.addAll(partialResults2);
            diminutiveSet.addAll(partialResults1);
            searchResults.addAll(diminutiveSet);
            searchResults.addAll(synonymsSet);
            searchResults.add(word);
        }
        else if (synonyms)
        {
            synonymFinder = new Finder(word,dictionaries.get(Dict.SYNONYMS));
            synonymsSet = synonymFinder.getResults();
            searchResults.addAll(synonymsSet);
        }
        else if (inflections)
        {
            inflectionFinder = new Finder(word,dictionaries.get(Dict.INFLECTIONS));
            inflectionSet = inflectionFinder.getResults();
            searchResults.addAll(inflectionSet);
        }
        else if (diminutives)
        {
            diminutiveFinder = new Finder(word,dictionaries.get(Dict.DIMINUTIVES));
            diminutiveSet = diminutiveFinder.getResults();
            searchResults.addAll(diminutiveSet);
        }

        results.addAll(searchResults);
        searchResults.clear();
        return results;


    }




}
