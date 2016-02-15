package chap10;

import chap10.pojo.Hello;
import org.junit.Test;
import org.springframework.context.support.StaticApplicationContext;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by 8002215 on 2016-02-15.
 */
public class StaticApplicationContextTest {
        @Test
        public void test() {
            StaticApplicationContext ac = new StaticApplicationContext();
            ac.registerSingleton("Hello", Hello.class);

            Hello hello1 = (Hello) ac.getBean("Hello");
            assertThat(hello1, is(notNullValue()));
        }
}
