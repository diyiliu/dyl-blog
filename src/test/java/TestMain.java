import com.dyl.blog.support.util.DateUtil;
import org.junit.Test;

import java.util.Date;

/**
 * Description: TestMain
 * Author: DIYILIU
 * Update: 2018-11-30 17:04
 */
public class TestMain {

    @Test
    public void test(){

        String str = ".\\upload\\pic\\201811\\pic5204028673149667661.jpg";

        System.out.println(str.substring(2));
    }

    @Test
    public void test1(){
        String timeStr = DateUtil.dateToString(new Date(), "%1$tY%1$tm%1$td%1$tH%1$tM%1$tS");

        System.out.println(timeStr);
    }
}
