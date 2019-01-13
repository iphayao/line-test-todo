package com.iphayao.linetest.secure;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SecureController {
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/auth")
    public void authenticationHandle(@RequestParam("code") String code,
                                     @RequestParam("state") String state,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws IOException {
        if(code != null) {
            String tokenVerifyUrl = "https://api.line.me/oauth2/v2.1/token";
            MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
            bodyMap.add("grant_type", "authorization_code");
            bodyMap.add("code", code);
            bodyMap.add("redirect_uri", "http://c25e9ee3.ngrok.io/auth");
            bodyMap.add("client_id", "");
            bodyMap.add("client_secret", "");

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<?> httpEntity = new HttpEntity<Object>(bodyMap, httpHeaders);
            ResponseEntity<AccessToken> token = restTemplate.postForEntity(tokenVerifyUrl, httpEntity, AccessToken.class);

            request.getSession().setAttribute("ACCESS_TOKEN", token.getBody());

            response.sendRedirect(request.getSession().getAttribute("REQUEST_URL").toString());
            return;
        }

        response.sendRedirect("/");
    }
}
