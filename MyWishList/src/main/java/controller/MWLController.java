package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import service.MyWishService;

@Controller
public class MWLController {
	@Autowired
	MyWishService ms;
	
	@RequestMapping(value = "myList")
	public String list(Model model) {
//		session.setAttribute("email", "ch@gmail.com");
//		String email = session.getAttribute("email").toString();
			
		model.addAttribute("email","");
		return "mwl/myList";
	}
}

//rep.setContentType("text/html; charset=utf-8");
//PrintWriter out = rep.getWriter();
//out.print(result);
