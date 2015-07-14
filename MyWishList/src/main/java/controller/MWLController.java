package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AccountDto;
import model.MyWishDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import service.AccountService;
import service.MyWishService;
import service.Paging;

@Controller
public class MWLController {
	@Autowired
	MyWishService ms;
	@Autowired
	AccountService as;
	
	HttpSession session = null;
	
	@RequestMapping(value = "myList")
	public String list(HttpServletRequest req, String currentPage, Model model) {
		session = req.getSession();
		session.setAttribute("email", "ch@gmail.com");
		String email = session.getAttribute("email").toString();
		
		int total = ms.total(email);
		Paging pg = new Paging(total, currentPage);
		MyWishDto myWishDto = new MyWishDto();
		
		myWishDto.setEmail(email);
		myWishDto.setStart(pg.getStart());
		myWishDto.setEnd(pg.getEnd());
		List<MyWishDto> myWishList = ms.wishList(myWishDto);
		
		
		
		model.addAttribute("myWishList", myWishList);
		model.addAttribute("pg",pg);
		return "mwl/myList";
	}
	
	@RequestMapping(value = "myListChange")
	public String myListChange(HttpServletRequest req, HttpServletResponse rep, Model model) throws IOException {
		
		String currentPage = req.getParameter("currentPage");
		
		session = req.getSession();
		session.setAttribute("email", "ch@gmail.com");
		String email = session.getAttribute("email").toString();
		
		int total = ms.total(email);
		Paging pg = new Paging(total, currentPage);
		MyWishDto myWishDto = new MyWishDto();
		
		myWishDto.setEmail(email);
		myWishDto.setStart(pg.getStart());
		myWishDto.setEnd(pg.getEnd());
		List<MyWishDto> myWishList = ms.wishList(myWishDto);
		
		model.addAttribute("myWishList", myWishList);
		
		return "mwl/myListChange";
	}
	
	@RequestMapping(value = "pageFormChange")
	public String pageFormChange(HttpServletRequest req, HttpServletResponse rep, Model model) throws IOException {
		
		String currentPage = req.getParameter("currentPage");
		
		session = req.getSession();
		session.setAttribute("email", "ch@gmail.com");
		String email = session.getAttribute("email").toString();
		
		int total = ms.total(email);
		Paging pg = new Paging(total, currentPage);
		
		model.addAttribute("pg",pg);
		
		return "mwl/pageFormChange";
	}
	
	@RequestMapping(value = "myContent")
	public String content() {
		return "mwl/myWishContent";
	}
	
	@RequestMapping(value = "bankCreateForm")
	public String bankCreateForm(HttpServletRequest request, Model model) {
		session = request.getSession();
		
		String email = session.getAttribute("email").toString();
		
		model.addAttribute("email", email);
		
		return "bank/bankCreateForm";
	}
	
	@RequestMapping(value = "bankCreate")
	public String bankCreate(AccountDto aDto, Model model) {		
		int result = as.createBank(aDto);
		
		model.addAttribute("bankCreateResult", result);
		
		return null;
	}
	
	@RequestMapping(value = "bankSearch")
	public String bankSearch(HttpServletRequest req, HttpServletResponse rep) throws IOException {
		rep.setContentType("text/html; charset=utf-8");
		PrintWriter out = rep.getWriter();
		
		String account = req.getParameter("account");
		
		int result = 0;
		
		result = as.searchAccount(account);
		
		out.print(result);
		
		return null;
	}
}

//rep.setContentType("text/html; charset=utf-8");
//PrintWriter out = rep.getWriter();
//out.print(result);
