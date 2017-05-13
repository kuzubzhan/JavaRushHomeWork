package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.*;
import org.junit.Assert;
import org.junit.Test;


public class FunctionalTest {
    public void testStorage(Shortener shortener) {
        String s1 = "aaa";
        String s2 = "bbb";
        String s3 = "aaa";

        Long ids1 = shortener.getId(s1);
        Long ids2 = shortener.getId(s2);
        Long ids3 = shortener.getId(s3);

        Assert.assertNotEquals(ids2, ids1);
        Assert.assertNotEquals(ids2, ids3);

        Assert.assertEquals(ids1, ids3);

        String sFromId_1 = shortener.getString(ids1);
        String sFromId_2 = shortener.getString(ids2);
        String sFromId_3 = shortener.getString(ids3);

        Assert.assertEquals(s1, sFromId_1);
        Assert.assertEquals(s2, sFromId_2);
        Assert.assertEquals(s3, sFromId_3);
    }

    @Test
    public void testHashMapStorageStrategy() {
        testStorage(new Shortener(new HashMapStorageStrategy()));
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        testStorage(new Shortener(new OurHashMapStorageStrategy()));
    }

    @Test
    public void testFileStorageStrategy() {
        testStorage(new Shortener(new FileStorageStrategy()));
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        testStorage(new Shortener(new HashBiMapStorageStrategy()));
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        testStorage(new Shortener(new DualHashBidiMapStorageStrategy()));
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        testStorage(new Shortener(new OurHashBiMapStorageStrategy()));
    }
}
