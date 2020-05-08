// TODO: handler without ssh


package com.Emi.IcodeV2.service;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import sun.net.ResourceManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CodeHandlerV3 {

    public static List<String> judgeWithoutSshV2(String language, String clientCode, String input, String reference) throws IOException {



        //--------- the only file I will create is for the input stream;
        ClassPathResource res = new ClassPathResource("/Problems/in.txt");
        File fileInput = new File(res.getFilename());
//        File fileInput = new ClassPathResource("classpath:in.txt").getFile();
        File fileInput1=new File("in.txt");
        FileWriter fwrite=new FileWriter(fileInput);
        fwrite.write(input);
        fwrite.close();

        // ----------create a file with extension as lang and write code inside it ---------

        String filename="MyCode."+language;
//        ClassPathResource res2=new ClassPathResource("Problem/"+

        File fileCode=new File(System.getProperty("user.home") + "//" + filename);
        FileWriter fileWriter=new FileWriter(fileCode);
        fileWriter.write(clientCode);
        fileWriter.close();






        // -----------create command depend on languge
        String PythonCommand="python3.7 "+filename;
        String cCommand="gcc -o fileC "+filename +" && ./fileC";
        String javaCommand="javac " +filename +"&& java MyCode";





             ///////////////////


        ProcessBuilder processBuilder=new ProcessBuilder();

        processBuilder.directory(new File(System.getProperty("user.home")));

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


        List<String> toBeReturned=new ArrayList<>();

        Boolean flag=Outputcode.equals(refCode);
         String oldReturnedValue= "flag:"+flag +"\n"+
                 "outputed:"+Outputcode +"\n"+
                 "ref:"+reference+"\n"+
                 "Error :" +Errors;
         String flagString=flag? "correct": "not";

        toBeReturned.add(0,flagString);  //  returned : [correct,errors]
        toBeReturned.add(1,Errors);
        return  toBeReturned;







    }

    }
