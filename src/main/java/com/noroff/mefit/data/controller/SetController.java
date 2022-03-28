package com.noroff.mefit.data.controller;

import com.noroff.mefit.data.model.DefaultResponse;
import com.noroff.mefit.data.service.SetService;
import com.noroff.mefit.data.model.Set;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Set")
@RequestMapping("/api/set")
@CrossOrigin(
        originPatterns = { "http://*:[*]", "https://*.herokuapp.com/" },
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
                RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.HEAD,
                RequestMethod.OPTIONS },
        allowedHeaders = { "Origin", "Accept", "X-Requested-With", "Content-Type",
                "Access-Control-Request-Method", "Access-Control-Request-Headers" },
        exposedHeaders = { "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials", "Authorization" },
        allowCredentials = "true",
        maxAge = 10
)
public record SetController(SetService setService) {
    @GetMapping
    public ResponseEntity<DefaultResponse<List<Set>>> getAllSets() {
        return setService.getAll();
    }

    @PostMapping
    public ResponseEntity<DefaultResponse<Set>> createSet(@RequestBody Set set) {
        return setService.create(set);
    }

    @GetMapping("/{setId}")
    public ResponseEntity<DefaultResponse<Set>> getSetById(@PathVariable Long setId) {
        return setService.getById(setId);
    }

    @PatchMapping("/{setId}")
    public ResponseEntity<DefaultResponse<Set>> updateSet(@PathVariable Long setId, @RequestBody Set set) {
        return setService.update(setId, set);
    }

    @DeleteMapping("/{setId}")
    public ResponseEntity<DefaultResponse<Void>> deleteSet(@PathVariable Long setId) {
        return setService.delete(setId);
    }

}
