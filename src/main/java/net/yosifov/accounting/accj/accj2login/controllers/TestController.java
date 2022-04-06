package net.yosifov.accounting.accj.accj2login.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class TestController {

    @GetMapping("/testRequest")
    public TestResp resp() {
        return new TestResp();
    }
}

class TestResp {
    private String resp = "Test response";

    public TestResp() {}

    public TestResp(String resp) {
        this.resp = resp;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    @Override
    public String toString() {
        return "TestResp{" +
                "resp='" + resp + '\'' +
                '}';
    }
}