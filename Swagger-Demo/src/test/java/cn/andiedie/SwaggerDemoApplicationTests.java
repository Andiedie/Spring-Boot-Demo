package cn.andiedie;

import cn.andiedie.controllers.UserController;
import cn.andiedie.entities.User;
import cn.andiedie.entities.repositories.UserRepository;
import cn.andiedie.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SwaggerDemoApplicationTests {
	private MockMvc mvc;

	private static String createUserInJson(long id, String name, int age) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(new User(id, name, age));
	}

	@Before
	public void setUp() {
		UserController uc = new UserController(new UserService(new UserRepository()));
		mvc = MockMvcBuilders.standaloneSetup(uc).build();
	}

	@Test
	public void testUserController() throws Exception {
		// 测试UserController
		RequestBuilder request;

		// GET获取User列表, 应该为空
		request = get("/users");
		mvc.perform(request)
				.andExpect(content().string(equalTo("[]")));

		// POST提交一个用户
		request = post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(createUserInJson(1, "User1", 20));
		mvc.perform(request)
				.andExpect(status().isCreated());

		// GET获取用户列表, 应该包含刚刚提交的用户
		request = get("/users");
		mvc.perform(request)
				.andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"User1\",\"age\":20}]")));

		// 再次注册相同用户, 应该错误
		request = post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(createUserInJson(1, "User1", 20));
		mvc.perform(request)
				.andExpect(status().isConflict());

		// PUT修改ID为1的User
		request = put("/users/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(createUserInJson(1, "User0", 18));
		mvc.perform(request)
				.andExpect(status().isCreated());

		// GET获取ID为1的User
		request = get("/users/1");
		mvc.perform(request)
				.andExpect(content().string(equalTo("{\"id\":1,\"name\":\"User0\",\"age\":18}")));

		// GET获取不存在User, 应该错误
		request = get("/users/2");
		mvc.perform(request)
				.andExpect(status().isNotFound());

		// DELETE删除ID为1的User
		request = delete("/users/1");
		mvc.perform(request)
				.andExpect(status().isNoContent());

		// PUT修改不存在的User, 应该错误
		request = put("/users/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content(createUserInJson(1, "User0", 18));
		mvc.perform(request)
				.andExpect(status().isNotFound());

		// DELETE删除不存在的User, 应该错误
		request = delete("/users/2");
		mvc.perform(request)
				.andExpect(status().isNotFound());

		// GET获取User列表, 应该为空
		request = get("/users/");
		mvc.perform(request)
				.andExpect(content().string(equalTo("[]")));
	}
}
