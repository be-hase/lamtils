[![CircleCI](https://circleci.com/gh/be-hase/lamtils.svg?style=svg)](https://circleci.com/gh/be-hase/lamtils)

# Lamtils

## Lamtils ? 

lamtils (LAMbda uTILS) is expression utility library for Creating map and POJO(Java bean).

Look this sample !

Creating HashMap : 

```java
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
```

Creating POJO(Java bean):
(Setter method is needed.)

```java
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
}

public static class TestPojo {
    private String one;
    private int two;
    private Date three;
    private double four;
    private boolean five;

    // And setter/getter methods
}
```

## Attention

This is not good for performance.  
I think that it is better for using only test code.

## Requirements

* JDK 8u60
* Need "-parameters" option on javac
