package net.yosifov.accounting.accj.accj2login.auth;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class SomeGuard {

    public SomeGuard() {
        System.out.println("SomeGuard()");
    }

    public boolean check(Authentication authentication, long idToCheck) {
        // TODO to refine
//        long id = (long) ((Map<String,Object>) authentication.getPrincipal()).get("id");
        System.out.println("********* SomeGuard checked **********");
        return true; //id == idToCheck;
    }
}
