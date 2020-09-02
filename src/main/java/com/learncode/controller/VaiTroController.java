package com.learncode.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.learncode.comon.Xuly;
import com.learncode.models.VaiTro;
import com.learncode.service.VaiTroService;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

@Controller
@RequestMapping("/vaitro")
public class VaiTroController {

	@Autowired
	VaiTroService vaiTroService;
	
	@RequestMapping("/")
	public String addOrEdit(ModelMap model) {
		VaiTro vt = new VaiTro();
		model.addAttribute("VAITRO", vt);
		//model.addAttribute("ACTION", "saveVaitro");
		return "Vaitro-register";
	}
	
	@RequestMapping(value = "/saveVaitro", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String saveVaiTro(ModelMap model, @ModelAttribute("VAITRO") VaiTro vt, HttpSession session) {
		vt.setId(ThreadLocalRandom.current().nextLong(0, new Long("9000000000000000")));
		vt.setCreateday(new Timestamp(new Date().getTime()));
		vt.setUpdateday(new Timestamp(new Date().getTime()));
		vt.setNguoitao((String) session.getAttribute("USERNAME"));
		vt.setNguoiupdate((String) session.getAttribute("USERNAME"));
		this.vaiTroService.insertVaitro(vt);
		model.addAttribute("VAITROS", this.vaiTroService.listVaiTro());
		return "redirect:/vaitro/list";
	}
	
	@GetMapping("/update")
	public Optional<VaiTro> update(Long id) {
		return this.vaiTroService.findByVaitroId(id);
	}
	
	@GetMapping("/vaitro-chitiet")
	public Optional<VaiTro> chitiet(Long id) {
		return this.vaiTroService.findByVaitroId(id);
	}
	
	@RequestMapping(value = "/updateVaiTro",  method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String doUpdate(ModelMap model, @ModelAttribute("VAITRO") VaiTro vt, HttpSession session) {
		vt.setUpdateday(new Timestamp(new Date().getTime()));
		vt.setNguoiupdate((String) session.getAttribute("USERNAME"));
		this.vaiTroService.updateVaitro(vt);
		model.addAttribute("VAITROS", this.vaiTroService.listVaiTro());
		return "redirect:/vaitro/list";
	}
	
	@GetMapping("/list")
	public String list(ModelMap model, HttpServletRequest request) {
		request.getSession().setAttribute("vaitrolist", null);
		return "redirect:/vaitro/list/page/1";
	}
	
	@GetMapping("/list/page/{pageNumber}")
	public String showVaiTrosPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("vaitrolist");
		int pagesize = 5;
		List<VaiTro> list = (List<VaiTro>) this.vaiTroService.listVaiTro();
		int sum = list.size();
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1; 
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("vaitrolist", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/list/page/";
		model.addAttribute("sum", sum);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("VAITROS", pages);
		return "Vaitro-view";
	}
	
	@RequestMapping("/dataSearch")
	public String dataSearch(@RequestParam("namvt") String tenvaitro, @RequestParam("keyvt") String mavaitro, HttpSession session) {
		session.setAttribute("NAMEVT", tenvaitro);
		session.setAttribute("KEYVT", mavaitro);
		if (tenvaitro == null || tenvaitro.equals("")) {
			if (mavaitro == null || mavaitro.equals("")) {
				return "redirect:/vaitro/list";
			} else {
				mavaitro = Xuly.xuLySearch(mavaitro);
				session.setAttribute("KEYVT", mavaitro);
				session.setAttribute("SEARCH", 1);
				return "redirect:/vaitro/list/search/1";
			}
		} else {
			tenvaitro = Xuly.xuLySearch(tenvaitro);
			session.setAttribute("NAMEVT", tenvaitro);
			session.setAttribute("SEARCH", 2);
			return "redirect:/vaitro/list/search/1";
		}
	}
	@RequestMapping("/list/search/{numberPage}")
	public String search(ModelMap model, HttpServletRequest request, @PathVariable int numberPage, HttpSession session) {
		String tenvaitro = (String) session.getAttribute("NAMEVT");
		String mavaitro = (String) session.getAttribute("KEYVT");
		Integer temp = (Integer) session.getAttribute("SEARCH");
		List<VaiTro> list = null;
		switch (temp) {
		case 1:
			list = this.vaiTroService.findByMavaitro(mavaitro);
			break;
		case 2:
			list = this.vaiTroService.findByTenvaitro(tenvaitro);
			break;
		default:
			break;
		}
		if (list == null) {
			return "redirect:/vaitro/list";
		}
		
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("vaitrolist");
		int pagesize = 5;
		pages = new PagedListHolder<>(list);
		pages.setPage(pagesize);
		final int goToPage = numberPage - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("vaitrolist", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/list/page/";
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("VAITROS", pages);
		return "Vaitro-view";
	}
	
	@RequestMapping("/del")
	public String delete(ModelMap model, @RequestParam("lvt") List<Long> ids, HttpSession session) {
		for (Long long1 : ids) {
			VaiTro vaiTro = this.vaiTroService.findByVaitroId(long1).get();
			vaiTro.setUpdateday(new Timestamp(new Date().getTime()));
			vaiTro.setNguoiupdate((String) session.getAttribute("USERNAME"));
			vaiTro.setIsdelete((Integer) 1);
			this.vaiTroService.updateVaitro(vaiTro);
		}
		return "redirect:/vaitro/list";
	}
}
