package com.Emi.IcodeV2.controller;
import com.Emi.IcodeV2.service.CodeHandlerV1;
import com.Emi.IcodeV2.service.CodeHandlerV2;
import com.Emi.IcodeV2.service.CodeHandlerV3;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin("*")
public class SubmitCodeController {


    @PostMapping("v4")
    public String Runit(@RequestParam(name = "lang") String language,@RequestParam(name = "code") String ClientCode,@RequestParam(name = "in") String input,@RequestParam(name = "ref") String reference) throws IOException {
        return CodeHandlerV1.judge(language,ClientCode,input,reference);
    }


    @PostMapping("v5")
    public String Runit2(@RequestParam(name = "lang") String language,@RequestParam(name = "code") String ClientCode,@RequestParam(name = "in") String input,@RequestParam(name = "ref") String reference) throws IOException {
        return CodeHandlerV2.judgeWithoutSsh(language,ClientCode,input,reference);

    }
    @PostMapping("v6")
    public String Runit3(@RequestParam(name = "lang") String language,@RequestParam(name = "code") String ClientCode,@RequestParam(name = "in") String input,@RequestParam(name = "ref") String reference) throws IOException {
        return CodeHandlerV3.judgeWithoutSshV2(language,ClientCode,input,reference);

    }



}
