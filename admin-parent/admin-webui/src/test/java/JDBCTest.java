import com.jinhao.crowd.entity.Admin;
import com.jinhao.crowd.mapper.AdminMapper;
import com.jinhao.crowd.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created on 2020/10/8.
 *
 * @author Valar Morghulis
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class JDBCTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Test
    public void testService() {
        for (int i = 0; i < 17; i++) {
            Admin admin = new Admin(null, "wangjingyu" + i, "xjh3210", "王敬宇", "xiaojinhao@qq.com", null);
            adminService.saveAdmin(admin);
        }
    }

    @Test
    public void testMapper() {
        Admin admin = new Admin(null, "mengfanguo", "xjh3210", "孟繁国", "xiaojinhao@qq.com", null);
        int count = adminMapper.insert(admin);
        System.out.println("受影响的行数" + count);
    }

    @Test
    public void test() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }


}
