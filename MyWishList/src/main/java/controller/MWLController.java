﻿package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import service.AccountService;
import service.MyWishService;
import service.Paging;
import upload.FileUpload;

@Controller
public class MWLController {
	@Autowired
	MyWishService ms;
	@Autowired
	AccountService as;
	
	HttpSession session = null;
	
	// 현재 진행중인 리스트 표시
	@RequestMapping(value = "myList")
	public String list(HttpServletRequest req, String currentPage, Model model) throws ParseException, InterruptedException {
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

		List<AccountDto> aList = as.getAccountList(email);
		
		
		model.addAttribute("myWishList", myWishList);
		model.addAttribute("bankList", bankList);
		model.addAttribute("pg",pg);
		model.addAttribute("path","/mwl/image/");
		
		
		return "mwl/myList";
	}
	
	
	// 실패한거나 성공한 리스트 표시
	@RequestMapping(value = "mySucFailList")
	public String mySucFailList(HttpServletRequest req, String currentPage, Model model) throws ParseException, InterruptedException {
		session = req.getSession();
		session.setAttribute("email", "ch@gmail.com");
		String email = session.getAttribute("email").toString();
		int total = 0;
		String view = req.getParameter("view");
		if(view.equals("success")){
			total = ms.sucTotal(email);
			
		}else if(view.equals("fail")){
			total = ms.failTotal(email);			
		}
		Paging pg = new Paging(total, currentPage);
		MyWishDto myWishDto = new MyWishDto();
		
		myWishDto.setEmail(email);
		myWishDto.setStart(pg.getStart());
		myWishDto.setEnd(pg.getEnd());
		
		List<MyWishDto> myWishList = null;
		
		if(view.equals("success")){
			myWishList = ms.sucWishList(myWishDto);
		}else if(view.equals("fail")){
			myWishList = ms.failWishList(myWishDto);
		}
		
		model.addAttribute("myWishList", myWishList);
		model.addAttribute("pg",pg);
		model.addAttribute("view",view);
		model.addAttribute("path","/mwl/image/");
		
		
		return "mwl/mySucFailList";
	}
	
	@RequestMapping(value = "myWishWrite", method = RequestMethod.POST)
	public String myWishWrite(@ModelAttribute("myWishDto") MyWishDto myWishDto, BindingResult bindingResult, Model model, HttpServletRequest req, MultipartHttpServletRequest multipartRequest) throws IOException {
		int result = 0;
		session = req.getSession();
		String email = session.getAttribute("email").toString();
		MultipartFile file = (MultipartFile) multipartRequest.getFile("image");
		try {
			if (file != null) {
				System.out.println(file.toString());
				String fileName = file.getOriginalFilename();
				String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
				Calendar cal = Calendar.getInstance();
				String replaceName = cal.getTimeInMillis() + fileType;
				String path = multipartRequest.getServletContext().getRealPath("/image");   //제 바탕화면의 upload 폴더라는 경로입니다. 자신의 경로를 쓰세요.
					//	C:\spring\SpringSrc\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MyWishList\image
				FileUpload.fileUpload(file, path, replaceName);
				myWishDto.setImg(replaceName);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		myWishDto.setEmail(email);
//		myWishDto.setRemainDate(remainDate);
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
	
	// 차트 부분
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
	
	// 선택한 은행 데이터를 JSON화 시키는 부분
	@RequestMapping(value="bankSelect", method=RequestMethod.GET)
	public String bankSelect(HttpServletRequest req, HttpServletResponse rep, Model model) throws IOException {
		rep.setContentType("text/html; charset=utf-8");
		PrintWriter out = rep.getWriter();
		
		int wishNo = Integer.parseInt(req.getParameter("wishNo"));

		String accountNo = req.getParameter("account");
		
		MyWishDto wishInfo = ms.wishInfo(wishNo);
				
		String str = "";
		
		session = req.getSession();		
		String email = session.getAttribute("email").toString();
		if(accountNo.equals("All")){
			
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
			account.setAccount(accountNo);
			
			AccountDto bankSearch = as.bankSearch(account);
			
			str = "[{\"name\":\"목표금액\", \"y\": " + wishInfo.getPrice() +"},";
				str += "{\"name\" : \"" + bankSearch.getBank() + "\",";
				str += "\"y\" : " + bankSearch.getMoney() + "}]";
		}
		out.print(str);
		
		
		return null;
	}
	
	// 살 수 있는지 없는지 여부를 확인하는 부분
	@RequestMapping(value="buyCheck", method=RequestMethod.GET)
	public String buyCheck(HttpServletRequest req, HttpServletResponse rep, Model model) throws IOException {
		rep.setContentType("text/html; charset=utf-8");
		PrintWriter out = rep.getWriter();
		
		int wishNo = Integer.parseInt(req.getParameter("wishNo"));
		String accountNo = req.getParameter("account");
	
		session = req.getSession();		
		String email = session.getAttribute("email").toString();
	
		AccountDto account = new AccountDto();
		
		account.setEmail(email);
		account.setAccount(accountNo);		
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
	
	// 구매시 위시리스트 구매 성공과 금액을 변동시키는 부분
	@RequestMapping(value="wishBuy", method=RequestMethod.GET)
	public String wishBuy(HttpServletRequest req, HttpServletResponse rep, Model model) throws IOException {
		rep.setContentType("text/html; charset=utf-8");
		PrintWriter out = rep.getWriter();
		
		int wishNo = Integer.parseInt(req.getParameter("wishNo"));
		String accountNo = req.getParameter("account");
		
		session = req.getSession();		
		String email = session.getAttribute("email").toString();
		
		AccountDto account = new AccountDto();

		account.setEmail(email);
		account.setAccount(accountNo);	

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
	
	// 결재시 패스워드 체크 부분
	@RequestMapping(value="passChk", method=RequestMethod.GET)
	public String passChk(HttpServletRequest req, HttpServletResponse rep, Model model) throws IOException {
		rep.setContentType("text/html; charset=utf-8");
		PrintWriter out = rep.getWriter();
		
		String accountNo = req.getParameter("account");
		String password = req.getParameter("password");
		
		session = req.getSession();		
		String email = session.getAttribute("email").toString();
		
		AccountDto account = new AccountDto();
		
		account.setEmail(email);
		account.setAccount(accountNo);	
		account.setPassword(password);	
		
		
		int passwordChk = as.passwordChk(account);
		
		out.print(passwordChk);
		
		return null;
	}

	// 계좌 관리 창 호출
	@RequestMapping(value = "manageAccount")
	public String manageAccount(HttpSession session, Model model){
		String email = session.getAttribute("email").toString();
		List<AccountDto> aList = as.getAccountList(email);
		model.addAttribute("aList", aList);
		return "bank/manageAccount";
	}
	
	// 계좌 목록 조회 (ajax)
	@RequestMapping(value="loadAccountList")
	public String accountList(HttpSession session, Model model){
		String email = session.getAttribute("email").toString();
		List<AccountDto> aList = as.getAccountList(email);
		model.addAttribute("aList", aList);
		return "bank/ajax_accountList";
	}
	
	// 새 계좌 등록
	@RequestMapping(value= "addNewAccount")
	public String addNewAccount(@ModelAttribute AccountDto account, Model model){
		int result = as.addNewAccount(account);
		model.addAttribute("result", result);
		return "forward:manageAccount.html";
	}

	// 잔고 변경
	@RequestMapping(value="editBalance")
	public String editBalance(@ModelAttribute AccountDto account, Model model){
		int result = as.editBalance(account);
		model.addAttribute("result", result);
		
		return "forward:manageAccount.html";
	}
	
	// 계좌 삭제
	@RequestMapping(value="deleteAccount")
	public String deleteAccount(Model model, String account){
		int result = as.deleteAccount(account);
		model.addAttribute("result", result);
		return "forward:manageAccount.html";
	}
	
}

//rep.setContentType("text/html; charset=utf-8");
//PrintWriter out = rep.getWriter();
//out.print(result);
