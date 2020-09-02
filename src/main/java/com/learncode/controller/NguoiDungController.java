package com.learncode.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.learncode.comon.Xuly;
import com.learncode.models.ChucNang1;
import com.learncode.models.Nguoidung;
import com.learncode.models.NhomNguoiDung;
import com.learncode.models.VaiTro;
import com.learncode.service.NguoiDungService;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

@Controller
@RequestMapping("/nguoidung")
public class NguoiDungController {
	
	@Autowired
	NguoiDungService nguoiDungService;
	
	
	@ModelAttribute("NHOMS")
	public List<NhomNguoiDung> getAllNhom() {
		return this.nguoiDungService.findAllNhom();
	}
	
	@ModelAttribute("CHUCNANGS")
	public List<ChucNang1> getAllChucNang(){
		return this.nguoiDungService.finAllChucNang();
	}
	
	@ModelAttribute("VAITROS")
	public List<VaiTro> getAllVaiTro(){
		return this.nguoiDungService.finAllVaiTro();
	}
	
	@RequestMapping("/")
	public String addOrEdit(ModelMap model) {
		Nguoidung nd = new Nguoidung();
		model.addAttribute("NGUOIDUNG", nd);
		return "Nguoidung-register";
	}
	
	@RequestMapping(value = "/saveNguoiDung", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String saveNguoiDung(Nguoidung nd, HttpSession session) {
		nd.setId(ThreadLocalRandom.current().nextLong(0, new Long("9000000000000000000")));
		nd.setCreateday(new Timestamp(new Date().getTime()));
		nd.setNguoitao((String) session.getAttribute("USERNAME"));
		nd.setUpdateday(new Timestamp(new Date().getTime()));
		nd.setNguoiupdate((String) session.getAttribute("USERNAME"));
		this.nguoiDungService.insertNguoidung(nd);
		return "redirect:/nguoidung/list";
	}
	
	@GetMapping(value = "/nguoidung-update", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, String> update(Long id, ModelMap model){
		Nguoidung nd = this.nguoiDungService.findById1(id);
		List<Long> lscn = this.nguoiDungService.findByIdchucnang(id);
		model.addAttribute("lscn", this.nguoiDungService.findByIdchucnang(id));
		List<Long> lsn = this.nguoiDungService.findByIdnhom(id);
		List<Long> lsvt = this.nguoiDungService.findByIdvaitro(id);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", String.valueOf(nd.getId()));
		map.put("manguoidung", nd.getManguoidung());
		map.put("tennguoidung", nd.getTennguoidung());
		map.put("password", nd.getPassword());
		map.put("email", nd.getEmail());
		map.put("gender", String.valueOf(nd.getGender()));
		map.put("phone", nd.getPhone());
		map.put("chucnangs", String.valueOf(lscn));
		map.put("nhoms", String.valueOf(lsn));
		map.put("vaitros", String.valueOf(lsvt));
		return map;
	}
	
	@GetMapping("/nguoidung-chitiet")
	public Optional<Nguoidung> ChiTiet(Long id){
		return this.nguoiDungService.findNguoidungById(id);
	}
	
	@RequestMapping(value = "/doUpdate", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String doUpdate(Nguoidung nd, HttpSession session) {
		nd.setIsdelete((Integer) 0);
		nd.setUpdateday(new Timestamp(new Date().getTime()));
		nd.setNguoiupdate((String) session.getAttribute("USERNAME"));
		this.nguoiDungService.updateNguoidung(nd);
		return "redirect:/nguoidung/list";
	}
	
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String list(ModelMap model, HttpSession session, HttpServletRequest request) {
		request.getSession().setAttribute("nguoidunglist", null);
		return "redirect:/nguoidung/list/page/1";
	}
	
	@RequestMapping(value="/list/page/{pageNumber}", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String showNguoidungPages(ModelMap model, HttpServletRequest request, @PathVariable int pageNumber) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("nguoidunglist");
		int pageSize = 5;
		List<Nguoidung> list = (List<Nguoidung>) this.nguoiDungService.getAllNguoiDung();
		int sum = list.size();
		if(pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pageSize);
		} else {
			final int gotoPage = pageNumber - 1;
			if (gotoPage <= pages.getPageCount() && gotoPage >= 0) {
				pages.setPage(gotoPage);
			}
		}
		
		request.getSession().setAttribute("nguoidunglist", pages);
		
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
		
		model.addAttribute("NGUOIDUNGS", pages);
		
		return "Nguoidung-view.html";
	}
	
	@RequestMapping("/list/search/{pageNumber}")
	public String search(ModelMap model, HttpServletRequest request, @RequestParam("keyword") String tennguoidung, @PathVariable int pageNumber) {
		if(tennguoidung.equals("")) {
			return "redirect:/nguoidung/list";
		}
		List<Nguoidung> list = this.nguoiDungService.findByTennguoidung(tennguoidung);
		if (list == null) {
			return "redirect:/nguoidung/list";
		}
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("nguoidunglist");
		int pagesize = 5;
		pages = new PagedListHolder<>(list);
		pages.setPageSize(pagesize);
		final int goToPage = pageNumber - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("nguoidunglist", pages);
		
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

		model.addAttribute("NGUOIDUNGS", pages);
		return "Nguoidung-view.html";
	}
	@RequestMapping("/delete")
	public String delete(ModelMap model, @RequestParam("id[]") List<Long> ids, HttpSession session) {
		for (Long long1 : ids) {
			Nguoidung nd = this.nguoiDungService.findNguoidungById(long1).get();
			nd.setUpdateday(new Timestamp(new Date().getTime()));
			nd.setNguoiupdate((String) session.getAttribute("USERNAME"));
			nd.setIsdelete((Integer) 1);
			this.nguoiDungService.deleteNguoidung(nd);
		}
		return "redirect:/nguoidung/list/";
	}
}
