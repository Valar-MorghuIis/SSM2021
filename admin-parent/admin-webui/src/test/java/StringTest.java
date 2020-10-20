import com.jinhao.crowd.util.CrowdUtil;
import org.junit.Test;

/**
 * Created on 2020/10/14.
 *
 * @author Valar Morghulis
 */
public class StringTest {

    @Test
    public void md5Test(){
        String str = "xjh3210";
        String s = CrowdUtil.md5(str);
        System.out.println(s);
    }
}
