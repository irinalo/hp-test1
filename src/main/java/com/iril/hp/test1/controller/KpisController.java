package com.iril.hp.test1.controller;

import com.iril.hp.test1.model.Kpis;
import com.iril.hp.test1.service.KpisService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kpis")
@Api(tags = "Kpis Endpoint")
public class KpisController {

    @Autowired
    private KpisService kpisService;

    @GetMapping
    public Kpis getKpis() {
        return kpisService.getKpis();
    }

}
