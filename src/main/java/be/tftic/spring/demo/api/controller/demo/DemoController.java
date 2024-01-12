package be.tftic.spring.demo.api.controller.demo;

import be.tftic.spring.demo.api.model.CredentialsForm;
import be.tftic.spring.demo.api.model.form.ConstraintForm;
import be.tftic.spring.demo.api.model.form.CustomConstraintForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class DemoController {


    private final String applicationName;

    public DemoController(String appName) {
        this.applicationName = appName;
    }

    private final List<String> names = List.of(
            "luc",
            "pol",
            "marie"
    );

    @GetMapping( "/hello" )
    public String hello(){
        return "hello world!";
    }

    // GET - http://localhost:8080/addition?membreA=_&membreB=_
    @GetMapping("/addition")
    public int addition(@RequestParam("membreA") int a, @RequestParam int membreB){
        return a + membreB;
    }

    // POST - http://localhost:8080/login
//    @PostMapping("/login")
//    public boolean login(@RequestBody CredentialsForm credentials){
//        return Objects.equals( credentials.getUsername(), "user" ) &&
//                Objects.equals( credentials.getPassword(), "pass" );
//    }

    // GET - http://localhost:8080/name/1
    @GetMapping("/name/{index}")
    public String getName(@PathVariable("index") int i){
        return names.get(i);
    }

    @GetMapping("/header")
    public String getHeaderValue(@RequestHeader("monHeader") String myHeader){
        return myHeader;
    }

    @GetMapping("/param/all")
    public void seeAllParams(@RequestParam Map<String, Object> params){
        System.out.println(params);
    }

    @GetMapping("/header/all")
    public void seeAllHeaders(@RequestHeader HttpHeaders header){
        System.out.println(header);
    }

    @GetMapping("/request")
    public void seeFullRequest(HttpServletRequest request){
        System.out.println(request);
    }


    @GetMapping("/app")
    public String getAppName(){
        return applicationName;
    }

    @PostMapping("/constraint")
    public void testConstraints(@RequestBody @Valid ConstraintForm form){
        System.out.println(form);
    }

    @GetMapping("/date")
    public void testDate(
            @RequestParam /*@DateTimeFormat(pattern = "dd/MM/yyyy")*/ LocalDate date
    ){
        System.out.println(date);
    }

    @PostMapping("/constraint/custom")
    public void testCustomConstraint(@RequestBody @Valid CustomConstraintForm form){
        System.out.println(form);
    }

    @GetMapping("/throw")
    public void throwEx(){
        throw new RuntimeException();
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handle(RuntimeException ex){
        return ResponseEntity.status(419).body(ex.getMessage());
    }
}
