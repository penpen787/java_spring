package etc;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by penpen787 on 2016-02-16.
 */
public class ClasspathExample {

    @Test
    public void testClassPath() {
        String path = this.getClass().getResource("text.txt").getPath();
        System.out.println(path);
        try {
            // Class 는 현재 자신의 Class 위치에서 찾음
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("text.txt");

            // ClassLoader 는 ClassPath  기준으로 찾음.
            String str =  this.getClass().getClassLoader().getResource("etc/text.txt").getPath();
            System.out.println(str);
            InputStream in2 =  this.getClass().getClassLoader().getResourceAsStream("etc/text.txt");
            int tmp = in2.read();
            System.out.println(tmp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testClassPath2() {
        try {
            String path1 = this.getClass().getClassLoader().getResource("BigGateTR.log").getPath();
            System.out.println(path1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
