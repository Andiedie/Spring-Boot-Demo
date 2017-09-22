package cn.andiedie.applicationproperties;

import cn.andiedie.applicationproperties.domain.Me;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationPropertiesApplicationTests {

	@Autowired
	private Me me;

	@Value("${cn.andiedie.string}")
	private String string;
	@Value("${cn.andiedie.integer}")
	private int i1;
	@Value("${cn.andiedie.long}")
	private long l;
	@Value("${cn.andiedie.int_less_than_3}")
	private int i2;
	@Value("${cn.andiedie.int_between_5_10}")
	private int i3;
	@Value("${cn.andiedie.uuid}")
	private String uuid;
	@Value("${cn.andiedie.sth}")
	private String sth;

	@Test
	public void contextLoads() {
		Assert.assertEquals("Andie", me.getName());
		Assert.assertEquals("male", me.getGender());
		Assert.assertEquals("Andie is learning Spring Boot", me.getDesc());
		Assert.assertEquals("dev", sth);
		System.out.println(string);
		System.out.println(i1);
		System.out.println(l);
		System.out.println(i2);
		System.out.println(i3);
		System.out.println(uuid);
	}

}
