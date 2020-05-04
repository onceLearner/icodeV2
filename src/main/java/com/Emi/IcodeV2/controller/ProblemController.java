package com.Emi.IcodeV2.controller;

import com.Emi.IcodeV2.model.Problem;
import com.Emi.IcodeV2.model.UserProblem;
import com.Emi.IcodeV2.repository.ProblemRepo;
import com.Emi.IcodeV2.repository.UserProbRepo;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ProblemController {

    @Autowired
    ProblemRepo problemRepo;

    @Autowired
    UserProbRepo userProbRepo;

    @GetMapping("getp")
    public List<Problem> getProblem(){
        return problemRepo.findAll();
    }



    @PostMapping("/addp")
    public ResponseEntity<?>  addProblem(@RequestBody Problem problem){
        problemRepo.save(problem);
        return ResponseEntity.ok().body("added successfuly");
    }


    // maping to add problems a user solve

    @PostMapping("/addUP")
    public ResponseEntity<?>  addUserProb(@RequestBody UserProblem userProblem){
        userProbRepo.save(userProblem);
        return ResponseEntity.ok().body("addeed successfully");

    }

    @GetMapping("/getUP")
    public List<UserProblem> getuserProb(){
        return  userProbRepo.findAll();
    }

}
