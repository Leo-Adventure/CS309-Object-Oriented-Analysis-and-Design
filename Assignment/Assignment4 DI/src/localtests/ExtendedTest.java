package localtests;

import dependency_injection.BeanFactory;
import dependency_injection.BeanFactoryImpl;
import dependency_injection.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testclass.*;
import java.io.File;
import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.*;

public class ExtendedTest {

    private BeanFactory beanFactory;

    @BeforeEach
    public void setup() {
        this.beanFactory = new BeanFactoryImpl();
        beanFactory.loadInjectProperties(new File("local-inject.properties"));
        beanFactory.loadValueProperties(new File("local-value.properties"));
    }


    @Test
    public void test() {
        TestClass instance = beanFactory.createInstance(TestClass.class);
        assertNotNull(instance);
        assertNotNull(instance.test1);
        assertEquals(0, instance.test1.size());
        assertNotNull(instance.test2);
        assertEquals(0, instance.test2.length);
        assertNotNull(instance.test3);
        assertEquals("", instance.test3);
        assertNotNull(instance.test4);
        assertEquals(0, instance.test4.size());
        assertNotNull(instance.test5);
        assertEquals("[]", instance.test5);
        assertNotNull(instance.test6);
        assertEquals("{}", instance.test6);
        assertEquals(0, instance.test7);
        assertFalse(instance.test8);
        assertNotNull(instance.test9);
        assertEquals(1, instance.test9.size());
        assertEquals("value1", instance.test9.get("key"));
        assertEquals(0, instance.test10);
        assertFalse(instance.test11);
    }
}