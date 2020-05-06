package com.Emi.IcodeV2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
@RestController
public class localExecutionV1 {
    @RequestMapping("remote")
    public  String anythj(){
        return "it work";
    }

       @RequestMapping("lv1")
    public String handlerV1() throws IOException {

        File fileInput = new File("reda.txt");
        File fileOutput=new File ("out.txt");
        File fileError=new File ("error.txt");



        ProcessBuilder processBuilder= new ProcessBuilder();

        InputStream ktebfiel= new FileInputStream(fileInput);

        processBuilder.command("cmd.exe", "/c","python reda.py");
        processBuilder.redirectInput(fileInput);
        processBuilder.redirectOutput(fileOutput);
        processBuilder.redirectErrorStream(true);
        processBuilder.redirectError(fileError);
        Process proc =processBuilder.start();

        return  "wakila t";




    }

}
