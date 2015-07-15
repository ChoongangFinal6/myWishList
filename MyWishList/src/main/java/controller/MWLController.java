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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import upload.FileUpload;
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
		
		List<AccountDto> bankList = as.bankList(email);
		
		model.addAttribute("myWishList", myWishList);
		model.addAttribute("bankList", bankList);
		model.addAttribute("pg",pg);
		
		return "mwl/myList";
	}
	
	/*
	@RequestMapping(value = "myListChange")
	public String myListChange(HttpServletRequest req, HttpServletResponse rep, Model model) {
		
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
	public String pageFormChange(HttpServletRequest req, HttpServletResponse rep, Model model) {
		
		String currentPage = req.getParameter("currentPage");
		
		session = req.getSession();
		session.setAttribute("email", "ch@gmail.com");
		String email = session.getAttribute("email").toString();
		
		int total = ms.total(email);
		Paging pg = new Paging(total, currentPage);
		
		model.addAttribute("pg",pg);
		
		return "mwl/pageFormChange";
	}
	*/
	@RequestMapping(value = "myContent")
	public String content() {
		return "mwl/myWishContent";
	}

	@RequestMapping(value = "myWishWrite", method = RequestMethod.POST)
	public String myWishWrite(@ModelAttribute("myWishDto") MyWishDto myWishDto, BindingResult bindingResult, Model model, HttpServletRequest req, MultipartHttpServletRequest multipartRequest) throws IOException {
		int result = 0;
		session = req.getSession();
		String email = session.getAttribute("email").toString();
		MultipartFile file = (MultipartFile) multipartRequest.getFile("img");
		System.out.println(file);
		if (file != null) {
			String path = multipartRequest.getServletContext().getRealPath("/image");   //제 바탕화면의 upload 폴더라는 경로입니다. 자신의 경로를 쓰세요.
			FileUpload.fileUpload(file, path);
			myWishDto.setImg(file.getOriginalFilename());
		}
		myWishDto.setEmail(email);
//		myWishDto.setRemainDate(remainDate);
		System.out.println("입력때 : "+myWishDto.toString());
		result = ms.write(myWishDto);
		if (result == 0) System.out.println("에러");
		return "redirect:myList.html";
	}
	
	@RequestMapping(value = "myWishUpdate")
	public String myWishUpdate(int wishNo, HttpSession session, HttpServletResponse rep) throws IOException {
		String email = session.getAttribute("email").toString();
		String result = "";
		
		MyWishDto myWish = ms.selectItem(email, wishNo);
		myWish.setRemainDate(myWish.getRemainDate().substring(0, 10));
		result = "{\"wishNo\":\""+myWish.getWishNo()+"\",\"product\":\""+myWish.getProduct()+"\",\"price\":\""+myWish.getPrice()+"\",\"remainDate\":\""+myWish.getRemainDate()+"\",\"success\":\""+myWish.getSuccess()+"\",\"img\":\""+myWish.getImg()+"\"}";
		System.out.println(result);
		rep.setContentType("text/html; charset=utf-8");
		PrintWriter out = rep.getWriter();
		out.print(result);
		return null;
	}
	
	@RequestMapping(value = "myWishDelete")
	public String myWishDelete(int wishNo, HttpSession session, HttpServletResponse rep) throws IOException {
		String email = session.getAttribute("email").toString();
		int result = ms.delete(email, wishNo);
		rep.setContentType("text/html; charset=utf-8");
		PrintWriter out = rep.getWriter();
		out.print(result);
		return null;
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
	/*
	@RequestMapping(value = "bankSearch")
	public String bankSearch(HttpServletRequest req, HttpServletResponse rep) throws IOException {
		rep.setContentType("text/html; charset=utf-8");
		PrintWriter out = rep.getWriter();
		
		String account = req.getParameter("account");
		
		int result = 0;
		
		result = as.searchAccount(account);
		
		out.print(result);
		
		return null;
	}*/
	
	@RequestMapping(value="myWishChart", method=RequestMethod.GET)
	public String myWishChart(HttpServletRequest req, HttpServletResponse rep, Model model) throws IOException {
		rep.setContentType("text/html; charset=utf-8");
		int wishNo = Integer.parseInt(req.getParameter("wishNo"));
		
		session = req.getSession();		
		String email = session.getAttribute("email").toString();
		
		MyWishDto wishInfo = ms.wishInfo(wishNo);
		
		List<AccountDto> bankList = as.bankList(email);
		
		String str = "[{\"name\":\"목표금액\", \"y\": " + wishInfo.getPrice() +"},";
		for (int i = 0; i < bankList.size(); i++) {
			str += "{\"name\" : \"" + bankList.get(i).getBank() + "\",";
			if(i == bankList.size()-1){
				str += "\"y\" : " + bankList.get(i).getMoney() + "}";
			}else{
				str += "\"y\" : " + bankList.get(i).getMoney() + "}, ";	
			}
		}
		str += "]";
		
		rep.setContentType("text/html; charset=utf-8");
		PrintWriter out = rep.getWriter();
		out.print(str);
				
		return null;
	}
	
	@RequestMapping(value="bankSelect", method=RequestMethod.GET)
	public String bankSelect(HttpServletRequest req, HttpServletResponse rep, Model model) throws IOException {
		rep.setContentType("text/html; charset=utf-8");
		PrintWriter out = rep.getWriter();
		
		int wishNo = Integer.parseInt(req.getParameter("wishNo"));

		String bank = req.getParameter("bank");
		
		MyWishDto wishInfo = ms.wishInfo(wishNo);
				
		String str = "";
		
		session = req.getSession();		
		String email = session.getAttribute("email").toString();
		if(bank.equals("All")){
			
			List<AccountDto> bankList = as.bankList(email);
			
			str = "[{\"name\":\"목표금액\", \"y\": " + wishInfo.getPrice() +"},";
			for (int i = 0; i < bankList.size(); i++) {
				str += "{\"name\" : \"" + bankList.get(i).getBank() + "\",";
				if(i == bankList.size()-1){
					str += "\"y\" : " + bankList.get(i).getMoney() + "}";
				}else{
					str += "\"y\" : " + bankList.get(i).getMoney() + "}, ";	
				}
			}
			str += "]";
			
		}else{
			AccountDto account = new AccountDto();
			
			account.setEmail(email);
			account.setBank(bank);
			
			AccountDto bankSearch = as.bankSearch(account);
			
			str = "[{\"name\":\"목표금액\", \"y\": " + wishInfo.getPrice() +"},";
				str += "{\"name\" : \"" + bankSearch.getBank() + "\",";
				str += "\"y\" : " + bankSearch.getMoney() + "}]";
		}
		out.print(str);
		
		
		return null;
	}
	
	@RequestMapping(value="buyCheck", method=RequestMethod.GET)
	public String buyCheck(HttpServletRequest req, HttpServletResponse rep, Model model) throws IOException {
		rep.setContentType("text/html; charset=utf-8");
		PrintWriter out = rep.getWriter();
		
		int wishNo = Integer.parseInt(req.getParameter("wishNo"));
		String bank = req.getParameter("bank");
		
		session = req.getSession();		
		String email = session.getAttribute("email").toString();
		
		AccountDto account = new AccountDto();
		
		account.setEmail(email);
		account.setBank(bank);	

		AccountDto bankSearch = as.bankSearch(account);
		
		int bankMoney = bankSearch.getMoney();
		
		MyWishDto wishInfo = ms.wishInfo(wishNo);
		
		int wishMoney = wishInfo.getPrice();
		
		int result = 0;
		
		if(bankMoney >= wishMoney){
			result = 1;
		}
		
		out.print(result);
				
		return null;
	}
	
	@RequestMapping(value="wishBuy", method=RequestMethod.GET)
	public String wishBuy(HttpServletRequest req, HttpServletResponse rep, Model model) throws IOException {
		rep.setContentType("text/html; charset=utf-8");
		PrintWriter out = rep.getWriter();
		
		int wishNo = Integer.parseInt(req.getParameter("wishNo"));
		String bank = req.getParameter("bank");
		
		session = req.getSession();		
		String email = session.getAttribute("email").toString();
		
		AccountDto account = new AccountDto();
		
		account.setEmail(email);
		account.setBank(bank);	

		AccountDto bankSearch = as.bankSearch(account);
		
		int bankMoney = bankSearch.getMoney();
		
		MyWishDto wishInfo = ms.wishInfo(wishNo);
		
		int wishMoney = wishInfo.getPrice();
		
		int buyResult = bankMoney - wishMoney; 
		
		account.setMoney(buyResult);
		
		as.bankBuyUpdate(account);
		
		MyWishDto mywish = new MyWishDto();
		
		mywish.setWishNo(wishNo);
		mywish.setSuccess(1);
		
		ms.myWishUpdate(mywish);
		
		return null;
	}
	
	@RequestMapping(value="passChk", method=RequestMethod.GET)
	public String passChk(HttpServletRequest req, HttpServletResponse rep, Model model) throws IOException {
		rep.setContentType("text/html; charset=utf-8");
		PrintWriter out = rep.getWriter();
		
		String bank = req.getParameter("bank");
		String password = req.getParameter("password");
		
		session = req.getSession();		
		String email = session.getAttribute("email").toString();
		
		AccountDto account = new AccountDto();
		
		account.setEmail(email);
		account.setBank(bank);	
		account.setPassword(password);	
		
		
		int passwordChk = as.passwordChk(account);
		
		out.print(passwordChk);
		
		return null;
	}
}

//rep.setContentType("text/html; charset=utf-8");
//PrintWriter out = rep.getWriter();
//out.print(result);
