package chap10;

import chap10.pojo.Hello;
import chap10.printer.StringPrinter;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author penpen787
 *
 */
public class ApplicationContextTest {
    @Test
    public void staticApplicationContextTest1() {
        StaticApplicationContext ac = new StaticApplicationContext();
        ac.registerSingleton("Hello", Hello.class);
        Hello hello1 = (Hello) ac.getBean("Hello");
        assertThat(hello1, is(notNullValue()));
    }

    @Test
    public void staticApplicationContextTest2() {
        StaticApplicationContext sc = new StaticApplicationContext();
        sc.registerBeanDefinition("printer", new RootBeanDefinition(StringPrinter.class));

        BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
        helloDef.getPropertyValues().addPropertyValue("name", "SpringModified");
        helloDef.getPropertyValues().addPropertyValue("printer", new RuntimeBeanReference("printer"));

        sc.registerBeanDefinition("hello", helloDef);

        Hello hello = sc.getBean("hello", Hello.class);
        hello.print();

        assertThat(sc.getBean("printer").toString(), is("Hello SpringModified"));

    }
    @Test
    public void genericApplicationContextTest() {
        GenericApplicationContext ac = new GenericApplicationContext();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(ac);
        reader.loadBeanDefinitions("chap10/GenericApplicationContextTest.xml");

        ac.refresh();
        Hello hello = ac.getBean("hello", Hello.class);
        hello.print();

        assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
    }
}
