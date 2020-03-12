package hr.ecc.corepoc.demo.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class HelloWorldResource {
    private final Logger log = LoggerFactory.getLogger(HelloWorldResource.class);

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(@RequestParam(name="name", required = false) String nameparam) {
        log.debug("REST request to sayHello to {}", nameparam);
        String name = "Stranger";
        if (StringUtils.hasText(nameparam)) {
            name = nameparam;
        }
        return ResponseEntity.ok().body(String.format("Hello %s!", name));
    }
}
