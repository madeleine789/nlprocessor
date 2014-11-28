package finder;

import morfologik.stemming.Dictionary;
import morfologik.stemming.DictionaryLookup;
import morfologik.stemming.WordData;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mms on 2014-11-28.
 */
public class LexemeFinder
{
    private  String word;
    private Set<String> lexemes;

    public LexemeFinder(String word) {
        this.setWord(word);
        this.lexemes = new HashSet<>();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Set<String> getLexemes() {
        return lexemes;
    }

    public Set<String> searchForLexemes(String word)
    {
        Dictionary polish = Dictionary.getForLanguage("pl");
        DictionaryLookup dl = new DictionaryLookup(polish);
        List<WordData> wordList = dl.lookup(word);
        if (wordList.isEmpty())
            return Collections.emptySet();
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
    public boolean startSearch()
    {
        lexemes = searchForLexemes(word);
        if (lexemes.isEmpty())
            return false;
        else
        {
            return true;
        }
    }
}
