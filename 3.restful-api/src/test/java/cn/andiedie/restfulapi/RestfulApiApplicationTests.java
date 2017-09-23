package cn.andiedie.restfulapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestfulApiApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testUserController() throws Exception {
		// 1. get查询user列表 应该为空
		mvc.perform(MockMvcRequestBuilders.get("/users/"))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));

		// 2. post创建一个用户
		mvc.perform(MockMvcRequestBuilders.post("/users/")
						.param("id", "1")
						.param("name", "用户1")
						.param("age", "20"))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));

		// 3. get获取用户列表，应该包含刚刚创建的用户
		mvc.perform(MockMvcRequestBuilders.get("/users/"))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"用户1\",\"age\":20}]")));

		// 4. put修改id为1的用户
		mvc.perform(MockMvcRequestBuilders.put("/users/1")
						.param("name", "用户10086")
						.param("age", "10010"))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));

		// 5. get获取id为1的用户
		mvc.perform(MockMvcRequestBuilders.get("/users/1"))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\"id\":1,\"name\":\"用户10086\",\"age\":10010}")));

		// 6.delete删除id为1的用户
		mvc.perform(MockMvcRequestBuilders.delete("/users/1"))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));

		// 7. get查询用户列表，应为空
		mvc.perform(MockMvcRequestBuilders.get("/users/"))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));
	}

}
