package com.MakuCase.Chmelik.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class EmptyPageController {
@GetMapping("")
   public String empty() {
   return "<h1>Hello world</h1>";
}
}
