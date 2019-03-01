package com.turing.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.turing.manage.service.ISubjectPaperTableService;

@Controller
@RequestMapping("paperTable")
public class SubjectPaperTableController {

	@Autowired
	private ISubjectPaperTableService service;
}
