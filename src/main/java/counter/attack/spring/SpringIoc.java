package counter.attack.spring;

import counter.attack.spring.service.MessgeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Create by zhang on 2017/12/15
 */
public class SpringIoc {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        System.out.println("context启动成功");
        // 从 context 中取出我们的 Bean，而不是用 new MessageServiceImpl() 这种方式
        MessgeService messgeService = context.getBean(MessgeService.class);
        // 这句将输出: hello world
        System.out.println(messgeService.getMessage());
    }

}
