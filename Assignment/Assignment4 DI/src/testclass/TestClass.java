package testclass;

import dependency_injection.Value;

import java.util.List;
import java.util.Map;

public class TestClass {
    @Value(value = "{}")
    public Map<String, String> test1;

    @Value(value = "[]")
    public String[] test2;

    @Value(value = "")
    public String test3;

    @Value(value = "[]")
    public List<String> test4;

    @Value(value = "[]")
    public String test5;
    @Value(value = "{}")
    public String test6;
    @Value(value = "")
    public int test7;
    @Value(value = "")
    public boolean test8;
    @Value(value = "{key:value0,key:value1}")
    public Map<String, String> test9;
    @Value(value = "asd,f,gasdfasf")
    public int test10;
    @Value(value = "asd,f,gasdfasf")
    public boolean test11;
}
