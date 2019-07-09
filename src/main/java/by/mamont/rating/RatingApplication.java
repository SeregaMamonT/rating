package by.mamont.rating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RatingApplication
{

  public static void main(String[] args)
  {
    SpringApplication.run(RatingApplication.class, args);
  }

//  public WebMvcConfigurer corsConfigurer()
//  {
//    return new WebMvcConfigurer()
//    {
//      @Override
//      public void addCorsMappings(CorsRegistry registry)
//      {
//        registry.addMapping("api/player").allowedOrigins("http://localhost:1234");
//      }
//    };
//  }
}
