package com.buzurud.springmvc.controllers;


import com.buzurud.springmvc.model.Visit;
import com.buzurud.springmvc.repository.VisitsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class IndexController {
    public static final Logger LOG = Logger.getLogger("IndexController");
    final VisitsRepository visitsRepository;

    public IndexController(VisitsRepository visitsRepository) {
        this.visitsRepository = visitsRepository;
    }

    @GetMapping("/")
    public ModelAndView index() {
        LOG.info("Request: index");
        Map<String, String> model = new HashMap<>();
        model.put("name", "Alexey");

        Visit visit = new Visit();
        visit.setDescription(String.format("Visited at %s", LocalDateTime.now()));
        visitsRepository.save(visit);

        return new ModelAndView("index", model);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        LOG.info("Request: hello");

        Visit visit = new Visit();
        visit.setDescription(String.format("Visited at %s", LocalDateTime.now()));
        visitsRepository.save(visit);

        return String.format("Hello %s!", name);
    }
}