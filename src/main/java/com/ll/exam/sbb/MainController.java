package com.ll.exam.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {
    @RequestMapping("/sbb") // sbb가 들어오면 아래의 함수를 실행해라.
    @ResponseBody // 아래 함수의 리턴값을 그대로 브라우저에 보내는 것.
    public String index(){
        System.out.println("index");
        return "sbb 세계에 오신 여러분 시발 것을 환영합니다.";

    }

    @GetMapping("/page1")
    @ResponseBody
    public String showPage1() {
        return """
                <form method="POST" action="/page2">
                    <input type="number" name="age" placeholder="나이" />
                    <input type="submit" value="page2로 POST 방식으로 이동" />
                </form>
                """;
    }

    @PostMapping("/page2")
    @ResponseBody
    public String showPage2Post(@RequestParam(defaultValue = "0") int age) {
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>안녕하세요, POST 방식으로 오셨군요.</h1>
                """.formatted(age);
    }

    @GetMapping("/page2")
    @ResponseBody
    public String showPage2Get(@RequestParam(defaultValue = "0") int age) {
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>안녕하세요, GET 방식으로 오셨군요.</h1>
                """.formatted(age);
    }

    @GetMapping("/plus")
    @ResponseBody
    public String plus(@RequestParam(defaultValue = "0") int a, int b) {
        return """
                <h1>결과값 : %d</h1>
                """.formatted(a + b);
    }

    @GetMapping("/minus")
    @ResponseBody
    public String minus(@RequestParam(defaultValue = "0") int a, int b) {
        return """
                <h1>결과값 : %d</h1>
                """.formatted(a - b);
    }

    int i = 0;
    @GetMapping("/increase")
    @ResponseBody
    public String increase() {
        return """
                <h1>결과값 : %d</h1>
                """.formatted(i++);
    }


    @GetMapping("/gugudan")
    @ResponseBody
    // Integer를 쓰는 이유 : 입력 값이 없을 경우를 대비. null을 사용하기 위함.
    public String gugudan(Integer dan, Integer limit) {
        if(dan == null){
            return "단 또는 리미트를 입력하세요";
        }
        if(limit == null){
            return "단 또는 리미트를 입력하세요";
        }

        Integer finalDan = dan; // final로 사용해야 가능하다.
        return IntStream.rangeClosed(1, limit)
                .mapToObj(i -> "%d * %d = %d".formatted(finalDan, i, finalDan * i))
                .collect(Collectors.joining("<br>"));
    }

    @GetMapping("/mbti")
    @ResponseBody
    public String mbti(String name) {
        switch (name) {
            case "홍길동":
                return "INFP";
            case "홍길순":
                return "ENFP";
            case "임꺽정":
                return "INFJ";
            case "본인":
                return "????";
        }
        return null;
    }
}
