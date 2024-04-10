package hello.itemservice.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
public class messageSourceTest {

    @Autowired
    MessageSource messageSource;

    @Test
    public void helloMessage_test() throws Exception{
        String result = messageSource.getMessage("hello",null,null);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    public void notFoundMessageCode_test() throws Exception{
        assertThatThrownBy(()->messageSource.getMessage("no_code",null,null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefaultMessage_test(){
        String result = messageSource.getMessage("no_code",null,"기본 메세지",null);
        assertThat(result).isEqualTo("기본 메세지");
    }

    @Test
    void argumentMessage(){
        String message = messageSource.getMessage("hello.name",new Object[]{"Spring!"},null);
        assertThat(message).isEqualTo("안녕 Spring!");
    }

    @Test
    void defaultLang(){
        assertThat(messageSource.getMessage("hello",null,null)).isEqualTo("안녕");
        assertThat(messageSource.getMessage("hello",null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
    void enLang(){
        assertThat(messageSource.getMessage("hello",null,Locale.ENGLISH)).isEqualTo("hello");
    }
}
