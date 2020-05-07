package com.Emi.IcodeV2.service;

import org.apache.commons.io.IOUtils;
import org.springframework.util.ResourceUtils;
import sun.net.ResourceManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CodeHandlerV3 {

    public static String judgeWithoutSshV2(String language, String clientCode,String input,String reference) throws IOException {



        //--------- the only file I will create is for the input stream;
        File fileInput=new File("in.txt");
        FileWriter fwrite=new FileWriter(fileInput);
        fwrite.write(input);
        fwrite.close();

        // ----------create a file with extension as lang and write code inside it ---------
        String filename="MyCode."+language;
        File fileCode=new File(filename);
        FileWriter fileWriter=new FileWriter(fileCode);
        fileWriter.write(clientCode);
        fileWriter.close();




        // -----------create command depend on languge
        String PythonCommand="python3.7 "+filename;
        String cCommand="gcc -o fileC "+filename +" && ./fileC";
        String javaCommand="javac " +filename +"&& java MyCode.java";




             ///////////////////


        ProcessBuilder processBuilder=new ProcessBuilder();

//
        processBuilder.redirectInput(fileInput);



        switch (language) {

            case "c":  processBuilder.command("bash","-c",cCommand); break;

            case "java":processBuilder.command("bash","-c",javaCommand); break;

            case  "py":processBuilder.command("bash","-c",PythonCommand); break;

        }

        Process process=processBuilder.start();


        String Errors= IOUtils.toString(process.getErrorStream(), StandardCharsets.UTF_8);

        String Outputcode= IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8);

        // eliminate all spaces at the end for ref and outputed code
        Outputcode=Outputcode.replaceAll("\\r|\\n|\\s+$", "");

        String refCode=reference.replaceAll("\\r|\\n|\\s+$", "");


        Boolean flag=Outputcode.equals(refCode);
         return "flag:"+flag +"\n"+
                 "outputed:"+Outputcode +"\n"+
                 "ref:"+reference+"\n"+
                 "Error :" +Errors;






    }

    }
