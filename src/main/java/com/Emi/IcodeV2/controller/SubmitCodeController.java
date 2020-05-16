package com.Emi.IcodeV2.controller;
import com.Emi.IcodeV2.service.CodeHandlerV1;
import com.Emi.IcodeV2.service.CodeHandlerV3;
import org.hibernate.validator.constraints.URL;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.nio.ch.Util;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class SubmitCodeController {


    @PostMapping("v4")
    public String RunitWithSsh(@RequestParam(name = "lang") String language,@RequestParam(name = "code") String ClientCode,@RequestParam(name = "in") String input,@RequestParam(name = "ref") String reference) throws IOException {
        return CodeHandlerV1.judge(language,ClientCode,input,reference);
    }



    @PostMapping("v6")
    public Map<String,String> RunitWithoutSsh(@RequestParam(name = "lang") String language, @RequestParam(name = "code") String ClientCode, @RequestParam(name = "in") String input, @RequestParam(name = "ref") String reference) throws IOException {

         List<String> WhatReturned=new ArrayList<>();
        WhatReturned=CodeHandlerV3.judgeWithoutSshV2(language,ClientCode,input,reference);

        HashMap<String,String> map=new HashMap<>();
        map.put("codeStatus", WhatReturned.get(0));
        map.put("errors",WhatReturned.get(1));
        

        return map;

    }



}
