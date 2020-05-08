// TODO:  handler with ssh



package com.Emi.IcodeV2.service;

import com.jcabi.ssh.SSHByPassword;
import com.jcabi.ssh.Shell;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


@Service
public class CodeHandlerV1 {




    public static String  judge(String language, String clientCode,String input,String reference) throws IOException {


        //connection
        SSHByPassword shel = new SSHByPassword("yasmina.emi.ac.ma", 22, "melissati", "melissati ");
        Shell.Plain plain = new Shell.Plain(shel);

        // create files for input and output stream
        //paths to the 3 files : input file , user outputted file and compare file
        Path Path_to_input_file = Paths.get("src/main/resources/Problems/p1/in.txt");
        Path Path_to_ouput_file = Paths.get("src/main/resources/Problems/p1/out.txt");
        Path Path_to_compare_file = Paths.get("src/main/resources/Problems/p1/reference.txt");
        Path Path_to_error_file = Paths.get("src/main/resources/Problems/p1/error.txt");


        //create file to read input from it
        File In_file = new File(Path_to_input_file.toString()).getAbsoluteFile(); //add getAbsoluteFile because  it recognize files in same dir can't go up
        Scanner fileScanner = new Scanner(In_file);


        // create file to pass User output to it and also potentiel erros
        File Out_file = new File(Path_to_ouput_file.toString()).getAbsoluteFile(); // toString because the argument is string not a path object
        File compare_file = new File(Path_to_compare_file.toString()).getAbsoluteFile();
        File error_file = new File(Path_to_error_file.toString()).getAbsoluteFile();



        // write to input and reference files
        FileWriter writerIn=new FileWriter(In_file);
        FileWriter writerRef=new FileWriter(compare_file);

        writerIn.write(input);
        writerRef.write(reference);
        writerIn.close();
        writerRef.close();




//        String text = plain.exec(" cd forTestPurpose && gcc -o reda file1.c && ./reda");

        //create streams for the exec methods  in out error
        InputStream passeur = new FileInputStream(In_file);

        OutputStream ktebFih = new FileOutputStream(Out_file);

        OutputStream eroors = new FileOutputStream(error_file);


        // command to execute the thing on the ssh server

        String pyth=" echo '" + clientCode + "' > amir.p && python3.7 amir.p ";

        String c=" echo '" + clientCode + "' > file2.c && gcc -o reda2 file2.c && ./reda2";

         String jav=" echo '" + clientCode + "' > hamid.java && javac hamid.java  && java hamid"  ;

        int result;
        switch (language) {

            case "c":  result=shel.exec(c, passeur, ktebFih, eroors); break;

            case "java":result=shel.exec(jav, passeur, ktebFih, eroors); break;

            case  "python":result=shel.exec(pyth, passeur, ktebFih, eroors); break;

        }




//        String errorFound = new Scanner(error_file).useDelimiter("\\Z").next();
        String errorFound="wrong code";

        //  Reading files into string text + Replace all new line and whitespace at the end with ""
        String text1=FileUtils.readFileToString(Out_file);
        text1=text1.replaceAll("\\r|\\n|\\s+$", "");

        String text2=FileUtils.readFileToString(compare_file);
        text2=text2.replaceAll("\\r|\\n", "");

        //check if the files are equals
        boolean issequals=text1.equals(text2);

        if (issequals) return ("correct solution");
        else return errorFound;
    }



}
