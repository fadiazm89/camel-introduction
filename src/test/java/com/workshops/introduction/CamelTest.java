package com.workshops.introduction;

import com.workshops.introduction.MySpringBootApplication;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = MySpringBootApplication.class)
public class CamelTest {


    @Autowired
    private CamelContext context;
    @Autowired
    private ProducerTemplate template;


    @Test
    public void testMoveFile() throws Exception {
        template.sendBodyAndHeader("file:data/inbox",
                "Hello World", Exchange.FILE_NAME, "hello.txt");
        Thread.sleep(2000);
        File target = new File("data/outbox/hello.txt");
        assertTrue("File not moved", target.exists());
        String content = context.getTypeConverter().convertTo(String.class, target);
        assertEquals("Hello World", content);

    }
}
