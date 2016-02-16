package chap10;

import chap10.pojo.Hello;
import chap10.printer.Printer;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author penpen787
 *
 */
public class HierarchyApplicationContextTest {
    @Test
    public void hierarchyApplicationContextTest() {
        ApplicationContext ac = new GenericXmlApplicationContext("chap10/ParentContext.xml");

        GenericApplicationContext child = new GenericApplicationContext(ac);

        XmlBeanDefinitionReader childBeanReader = new XmlBeanDefinitionReader(child);
        childBeanReader.loadBeanDefinitions("chap10/ChildContext.xml");

        child.refresh();

        Printer printer = child.getBean("printer", Printer.class);

        assertThat(printer, is(notNullValue()));

        Hello hello = child.getBean("hello", Hello.class);
        hello.print();

        System.out.println(printer.toString());

        assertThat(printer.toString(), is("Hello Child"));


    }
}
