package com.hbr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
@GetMapping("center")
public String center() {
	return "center";
}
}
