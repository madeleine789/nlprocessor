package search;

/**
 * Created by M on 2014-11-21.
 */
public class WordVariant
{
    private boolean synonyms;
    private boolean diminutives;
    private boolean inflections;

    public WordVariant(boolean synonyms, boolean diminutives, boolean inflections) {
        this.synonyms = synonyms;
        this.diminutives = diminutives;
        this.inflections = inflections;
    }

    public boolean searchForSynonyms() {
        return synonyms;
    }

    public void setSynonyms(boolean synonyms) {
        this.synonyms = synonyms;
    }

    public boolean searchForDiminutives() {
        return diminutives;
    }

    public void setDiminutives(boolean diminutives) {
        this.diminutives = diminutives;
    }

    public boolean searchForInflections() {
        return inflections;
    }

    public void setInflections(boolean inflections) {
        this.inflections = inflections;
    }
}
