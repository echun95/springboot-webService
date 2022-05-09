package book.springboot.web;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.core.Is.is;


import java.util.regex.Matcher;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello() throws Exception  {
        //given
        String hello = "hello";
        //when

        mvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(hello));
        //then
      }

      @Test
      public void helloDto가_리턴된다() throws Exception  {
          //given
          String name = "hello";
          int amount = 1000;

          //when
          mvc.perform(MockMvcRequestBuilders.get("/hello/dto")
                  .param("name", name)
                  .param("amount",String.valueOf(amount)))
                  .andExpect(MockMvcResultMatchers.status().isOk())
                  .andExpect(jsonPath("$.name",is(name)))
                  .andExpect(jsonPath("$.amount", is(amount)));

          //then
      }
}
