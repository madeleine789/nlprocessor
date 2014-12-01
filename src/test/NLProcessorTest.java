package test;

import finder.LexemeFinder;
import org.junit.Test;
import search.NLProcessor;
import search.WordVariant;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by mms on 2014-12-01.
 */
public class NLProcessorTest
{
    @Test
    public void testLoadDictionaries()
    {

    }

    @Test
    public void testFindWords()  throws Exception
    {
        NLProcessor nlp = new NLProcessor();
        nlp.loadDictionaries();

        Set<String > expected = new HashSet<>(Arrays.asList("czasu", "periodach", "periodom", "czasem", "periodowi",
                                                            "czas", "czasami", "periodzie", "czasie", "czasom", "periody",
                                                            "periodem", "periodów", "czasach", "czasów", "periodami",
                                                            "czasy", "czasowi", "periodu"));
        Set<String> result = nlp.findWords("czasem", new WordVariant(true, true, false));
        assertEquals(expected,result);

        expected = new HashSet<>(Arrays.asList("lackadaisical"));
        result = nlp.findWords("lackadaisical", new WordVariant(true, true, false));
        assertEquals(expected,result);

        expected = new HashSet<>(Arrays.asList("kotku", "kotkę", "kotce", "kotko", "kotek", "kotkiem", "kotkom",
                                            "kotkach", "kotką", "kotki", "kotków", "kotkami", "kotka", "kotkowi"));
        result = nlp.findWords("kotki", new WordVariant(true, false, false));
        assertEquals(expected,result);

        expected = new HashSet<>(Arrays.asList("qwert"));
        result = nlp.findWords("qwert", new WordVariant(true, false, false));
        assertEquals(expected,result);

        expected = new HashSet<>(Arrays.asList("kura znosząca złote jajka", "kopalnia złota", "żyła złota", "złoty interes",
                                                "dojna krowa", "złote jabłko", "kura znosząca złote jaja", "kokosowy interes", "bonanza"));
        result = nlp.findWords("bonanzy", new WordVariant(false, true, false));
        assertEquals(expected,result);

        expected = new HashSet<>(Arrays.asList("asdfgh"));
        result = nlp.findWords("asdfgh", new WordVariant(false, true, false));
        assertEquals(expected,result);

        boolean expected2 = true;
        boolean result2 = nlp.findWords("", new WordVariant(true, true, false)).isEmpty();
        assertEquals(expected,result);

        expected2 = true;
        result2 = nlp.findWords(null, new WordVariant(true, true, false)).isEmpty();
        assertEquals(expected,result);

    }


}
