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

    protected NLProcessor nlp = new NLProcessor();

    @Test
    public void testLoadDictionaries() throws Exception {
        nlp.loadDictionaries();
    }
    /*
    @Test
    public void testFindWords()  throws Exception
    {
        NLProcessor nlp = new NLProcessor();
        nlp.loadDictionaries();

        Set<String > expected1 = new HashSet<>(Arrays.asList("czasu", "periodach", "periodom", "czasem", "periodowi",
                                                            "czas", "czasami", "periodzie", "czasie", "czasom", "periody",
                                                            "periodem", "periodów", "czasach", "czasów", "periodami",
                                                            "czasy", "czasowi", "periodu"));
        Set<String> result1 = nlp.findWords("czasem", new WordVariant(true, true, false));
        assertEquals(expected1,result1);

        Set<String> expected2 = new HashSet<>(Arrays.asList("lackadaisical"));
        Set<String> result2 = nlp.findWords("lackadaisical", new WordVariant(true, true, false));
        assertEquals(expected2,result2);

        Set<String> expected3 = new HashSet<>(Arrays.asList("kotku", "kotkę", "kotce", "kotko", "kotek", "kotkiem", "kotkom",
                                            "kotkach", "kotką", "kotki", "kotków", "kotkami", "kotka", "kotkowi"));
        Set<String> result3 = nlp.findWords("kotki", new WordVariant(true, false, false));
        assertEquals(expected3,result3);

        Set<String> expected4 = new HashSet<>(Arrays.asList("qwert"));
        System.out.println(nlp.findWords("qwert", new WordVariant(true, false, false)));
        Set<String> result4 = nlp.findWords("qwert", new WordVariant(true, false, false));
        assertEquals(expected4,result4);

        Set<String> expected5 = new HashSet<>(Arrays.asList("kura znosząca złote jajka", "kopalnia złota", "żyła złota", "złoty interes",
                                                "dojna krowa", "złote jabłko", "kura znosząca złote jaja", "kokosowy interes", "bonanza"));
        Set<String> result5 = nlp.findWords("bonanzy", new WordVariant(false, true, false));
        assertEquals(expected5,result5);

        Set<String> expected6 = new HashSet<>(Arrays.asList("asdfgh"));
        Set<String> result6 = nlp.findWords("asdfgh", new WordVariant(false, true, false));
        assertEquals(expected6,result6);

        boolean expected7 = true;
        boolean result7 = nlp.findWords("", new WordVariant(true, true, false)).isEmpty();
        assertEquals(expected7,result7);

        boolean expected8 = true;
        boolean result8 = nlp.findWords(null, new WordVariant(true, true, false)).isEmpty();
        assertEquals(expected8,result8);

    }
    */
    @Test
    public void testFindWords1() throws Exception
    {

        Set<String > expected1 = new HashSet<>(Arrays.asList("czasu", "periodach", "periodom", "czasem", "periodowi",
                "czas", "czasami", "periodzie", "czasie", "czasom", "periody",
                "periodem", "periodów", "czasach", "czasów", "periodami",
                "czasy", "czasowi", "periodu"));
        Set<String> result1 = nlp.findWords("czasem", new WordVariant(true, true, false));
        assertEquals(expected1,result1);

        Set<String> expected2 = new HashSet<>(Arrays.asList("lackadaisical"));
        Set<String> result2 = nlp.findWords("lackadaisical", new WordVariant(true, true, false));
        assertEquals(expected2,result2);

    }
    @Test
    public void testFindWords2() throws Exception
    {

        Set<String> expected3 = new HashSet<>(Arrays.asList("kotku", "kotkę", "kotce", "kotko", "kotek", "kotkiem", "kotkom",
                "kotkach", "kotką", "kotki", "kotków", "kotkami", "kotka", "kotkowi"));
        Set<String> result3 = nlp.findWords("kotki", new WordVariant(true, false, false));
        assertEquals(expected3,result3);

        Set<String> expected4 = new HashSet<>(Arrays.asList("qwert"));
        System.out.println(nlp.findWords("qwert", new WordVariant(true, false, false)));
        Set<String> result4 = nlp.findWords("qwert", new WordVariant(true, false, false));
        assertEquals(expected4,result4);

    }


    @Test
    public void testFindWords3() throws Exception
    {


        Set<String> expected5 = new HashSet<>(Arrays.asList("kura znosząca złote jajka", "kopalnia złota", "żyła złota", "złoty interes",
                "dojna krowa", "złote jabłko", "kura znosząca złote jaja", "kokosowy interes", "bonanza"));
        Set<String> result5 = nlp.findWords("bonanzy", new WordVariant(false, true, false));
        assertEquals(expected5,result5);

        Set<String> expected6 = new HashSet<>(Arrays.asList("asdfgh"));
        Set<String> result6 = nlp.findWords("asdfgh", new WordVariant(false, true, false));
        assertEquals(expected6,result6);

    }

    @Test
    public void testFindWords4() throws Exception
    {


        Set<String> expected7 = new HashSet<>(Arrays.asList(""));
        Set<String> result7 = nlp.findWords("", new WordVariant(true, true, false));
        assertEquals(expected7,result7);


    }

    @Test
    public void testFindWords5() throws Exception
    {

        boolean expected8 = true;
        boolean result8 = nlp.findWords(null, new WordVariant(true, true, false)).isEmpty();
        assertEquals(expected8,result8);

    }



}
