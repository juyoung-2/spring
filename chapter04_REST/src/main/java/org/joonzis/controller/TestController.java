package org.joonzis.controller;

import org.joonzis.domain.TestVO;
import org.joonzis.domain.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//JSON 문자열을 수동으로 생성할 때 사용
import com.google.gson.Gson; 
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/test")
@RestController
public class TestController {
   
   @GetMapping(value="/getText", produces="text/plain; charset=utf-8")
   public String getText() {
      log.info("Mime Type : " + MediaType.TEXT_PLAIN_VALUE);
      // 기존 jsp의 파일 이름이 아닌 순수 데이터를 전달
      return "안녕하세요";
   }
   
   // Multipurpose Internet Mail Extensions: 인터넷에서 전송되는 파일이나 데이터의 종류와 형식을 알려주는 표준 식별자
   // MediaType: MIME 타입 상수 
   @GetMapping(value="/getObject", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE,
                                    MediaType.APPLICATION_XML_VALUE
                                   })
   public TestVO getObject() {
      return new TestVO(100, "kim");
   }
   
   /*
    * 메소드를 만들고 URL에 맞게 요청해서 결과를 확인하시오 (xml. json)
    * 1. 요청 URL : /test/check
    * 2. 파라미터 : 실수형 age
    * 3. 리턴타입 : TestVO
    *  - vo 객체 생성
    *  - no 필드는 0으로 고정
    *  - 전달 받은 age를 문자열로 name 필드에 저장
    */
   
   @GetMapping(value="/check", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE,
                                 MediaType.APPLICATION_XML_VALUE
                                })
   public ResponseEntity<TestVO> check(@RequestParam("age") Double age) {
      TestVO vo = new TestVO(0, String.valueOf(age));
      
      // HTTP 상태 코드, HTTP 헤더, HTTP 응답 본문(body)을 모두 제어할 수 있는 응답 객체 선언
      ResponseEntity<TestVO> result = null; 
      
      if(age > 150) {
         result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
      } else {
         result = ResponseEntity.status(HttpStatus.OK).body(vo);
      }
      
      return result;
   }
   
   @GetMapping("/product/{cat}/{pid}")
   public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") int pid) {
      return new String[] {
            "category : " + cat + ", " +
            "productId :" + pid
      };
   }
   
   @PostMapping("/ticket")
   public Ticket convert(@RequestBody Ticket t) {
      log.info("convert .. ticket : " + t); // System.out.println(t.toString()); 처럼 자동 
      String result = new Gson().toJson(t); // JSON 형식의 문자열로 직렬화
      log.info(result);
      return t;
   }
   
   /*
   {
     "name": "kim",
     "age": 10,
     "job": "student",
     "users": [
       { "user0": 0 },
       { "user1": 1 },
       { "user2": 2 },
       { "user3": 3 },
       { "user4": 4 }
     ]
   }
   */
   @GetMapping("/info")
   public String makeJson() {
      JsonObject json = new JsonObject();
      json.addProperty("name", "kim");
      json.addProperty("age", 10);
      json.addProperty("job", "student");
      
      JsonArray ja = new JsonArray();
      for(int i=0; i<5; i++) {
         JsonObject j = new JsonObject();
         j.addProperty("user"+i, i);
         ja.add(j);
      }
      json.add("users", ja);
      return json.toString();
   }
   
}
