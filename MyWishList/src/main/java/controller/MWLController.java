package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.AccountDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import service.AccountService;
import service.MyWishService;

@Controller
public class MWLController {
	@Autowired
	MyWishService ms;
	@Autowired
	AccountService as;
	
	@RequestMapping(value = "myList")
	public String list(Model model,HttpSession session) {
		System.out.println("CTRL:myList");
		session.setAttribute("email", "ch@gmail.com");
		
		String email = session.getAttribute("email").toString();
		List<AccountDto> aList = as.getAccountList(email);
		model.addAttribute("aList", aList);
		model.addAttribute("email","");
		return "mwl/myList";
	}
	
	// 계좌 관리 창 호출
	@RequestMapping(value = "manageAccount")
	public String manageAccount(HttpSession session, Model model){
		System.out.println("CTRL:mwl/manageAccount");
		String email = session.getAttribute("email").toString();
		List<AccountDto> aList = as.getAccountList(email);
		model.addAttribute("aList", aList);
		return "mwl/manageAccount";
	}
	
	// 새 계좌 등록
	@RequestMapping(value= "addNewAccount")
	public String addNewAccount(@ModelAttribute AccountDto account, Model model){
		System.out.print("CTRL:mwl/addNewAccount: " + account);
		int result = as.addNewAccount(account);
		System.out.println("result:"+result);
		model.addAttribute("result", result);
		return "forward:manageAccount.html";
	}

	// 잔고 변경
	@RequestMapping(value="editBalance")
	public String editBalance(@ModelAttribute AccountDto account, Model model){
		System.out.println("CTRL:mwl/editBalance");
		int result = as.editBalance(account);
		return "forward:manageAccount.html";
	}
	
	// 계좌 삭제
	@RequestMapping(value="deleteAccount")
	public String deleteAccount(HttpSession session, Model model, int account){
		System.out.println("CTRL:mwl/deleteAccount: " + account);
		int result = as.deleteAccount(account);
		model.addAttribute("result", result);
		return "forward:manageAccount.html";
	}
	
}

//rep.setContentType("text/html; charset=utf-8");
//PrintWriter out = rep.getWriter();
//out.print(result);
