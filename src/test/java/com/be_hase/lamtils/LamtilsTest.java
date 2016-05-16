package com.be_hase.lamtils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LamtilsTest {
    @Test
    public void newMap() {
        Map<String, Object> result;
        Date now = new Date();

        result = Lamtils.newMap(
                HashMap.class,
                one -> "one",
                two -> 2,
                three -> now,
                four -> 4.0
        );
        assertThat(result, instanceOf(HashMap.class));
        assertThat(result, hasEntry("one", "one"));
        assertThat(result, hasEntry("two", 2));
        assertThat(result, hasEntry("three", now));
        assertThat(result, hasEntry("four", 4.0));
    }

    @Test
    public void test_newHashMap() {
        Map<String, Object> result;
        Date now = new Date();

        result = Lamtils.newHashMap(
                one -> "one",
                two -> 2,
                three -> now,
                four -> 4.0
        );
        assertThat(result, instanceOf(HashMap.class));
        assertThat(result, hasEntry("one", "one"));
        assertThat(result, hasEntry("two", 2));
        assertThat(result, hasEntry("three", now));
        assertThat(result, hasEntry("four", 4.0));
    }

    @Test
    public void test_newLinkedHashMap() {
        Map<String, Object> result;
        Date now = new Date();

        result = Lamtils.newLinkedHashMap(
                one -> "one",
                two -> 2,
                three -> now,
                four -> 4.0
        );
        assertThat(result, instanceOf(LinkedHashMap.class));
        assertThat(result, hasEntry("one", "one"));
        assertThat(result, hasEntry("two", 2));
        assertThat(result, hasEntry("three", now));
        assertThat(result, hasEntry("four", 4.0));
    }

    @Test
    public void test_newTreeMap() {
        Map<String, Object> result;
        Date now = new Date();

        result = Lamtils.newTreeMap(
                one -> "one",
                two -> 2,
                three -> now,
                four -> 4.0
        );
        assertThat(result, instanceOf(TreeMap.class));
        assertThat(result, hasEntry("one", "one"));
        assertThat(result, hasEntry("two", 2));
        assertThat(result, hasEntry("three", now));
        assertThat(result, hasEntry("four", 4.0));
    }

    @Test
    public void test_newConcurrentMap() {
        Map<String, Object> result;
        Date now = new Date();

        result = Lamtils.newConcurrentMap(
                one -> "one",
                two -> 2,
                three -> now,
                four -> 4.0
        );
        assertThat(result, instanceOf(ConcurrentHashMap.class));
        assertThat(result, hasEntry("one", "one"));
        assertThat(result, hasEntry("two", 2));
        assertThat(result, hasEntry("three", now));
        assertThat(result, hasEntry("four", 4.0));
    }

    @Test
    public void test_newClass() {
        TestPojo result;
        Date now = new Date();

        result = Lamtils.newClass(
                TestPojo.class,
                one -> "one",
                two -> 2,
                three -> now,
                four -> 4.0,
                five -> true
        );
        assertThat(result, instanceOf(TestPojo.class));
        assertThat(result.getOne(), is("one"));
        assertThat(result.getTwo(), is(2));
        assertThat(result.getThree(), is(now));
        assertThat(result.getFour(), is(4.0));
        assertThat(result.isFive(), is(true));

        result = Lamtils.newClass(
                TestPojo.class,
                one -> "one"
        );
        assertThat(result, instanceOf(TestPojo.class));
        assertThat(result.getOne(), is("one"));
        assertThat(result.getTwo(), is(0));
    }

    @Test(expected = RuntimeException.class)
    public void test_newClass_invalid_property() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TestPojo result = Lamtils.newClass(
                TestPojo.class,
                hoge -> "hoge"
        );
    }


public static class TestPojo {
    private String one;
    private int two;
    private Date three;
    private double four;
    private boolean five;

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public int getTwo() {
        return two;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    public Date getThree() {
        return three;
    }

    public void setThree(Date three) {
        this.three = three;
    }

    public double getFour() {
        return four;
    }

    public void setFour(double four) {
        this.four = four;
    }

    public boolean isFive() {
        return five;
    }

    public void setFive(boolean five) {
        this.five = five;
    }
}
}
