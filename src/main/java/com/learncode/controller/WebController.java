package com.learncode.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learncode.comon.Xuly;
import com.learncode.dto.CartDTO;
import com.learncode.models.BillDetail;
import com.learncode.models.Bills;
import com.learncode.models.ChucNang1;
import com.learncode.models.Kho;
import com.learncode.models.Sanpham;
import com.learncode.models.SanphamVaChitiet;

import com.learncode.repository.BillRepository;
import com.learncode.service.BillDetailService;
import com.learncode.service.BillService;
import com.learncode.service.KhoService;
import com.learncode.service.SanphamService;
import com.learncode.service.SanphamVaChitietService;


@Controller
@RequestMapping("/web")
public class WebController {

	@Autowired
	SanphamService sanphamService;

	@Autowired
	SanphamVaChitietService sanphamvachitietService;


	@Autowired
	BillService billService;

	@Autowired
	BillRepository billRepository;
	
	@Autowired
	KhoService khoService;
	
	@Autowired
	BillDetailService billDetailService;

	@RequestMapping("/")
	public String home() {
		return "/web/trangchuwebsite";
	}
	
//	@RequestMapping("/1")
//	public String home1() {
//		return "/web/trangchu";
//	}

	@RequestMapping("/tatcasanpham")
	public String tatcasanpham() {
		return "/web/tatcasanpham";
	}

	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("tatcasanphamlist", null);
		return "redirect:/web/list/page/1";
	}

	@RequestMapping(value = "/list/page/{pageNumber}", method = { RequestMethod.GET, RequestMethod.POST })
	public String showTatcasanphamsPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("tatcasanphamlist");
		int pagesize = 20;
		List<SanphamVaChitiet> list = (List<SanphamVaChitiet>) this.sanphamvachitietService.getTatcasanpham();
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
		request.getSession().setAttribute("tatcasanphamlist", pages);

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

		model.addAttribute("TATCASANPHAMS", pages);

		return "/web/tatcasanpham";
	}
	
	@RequestMapping(value = "/moilist", method = { RequestMethod.GET, RequestMethod.POST })
	public String SanphammoiList(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("sanphammoilist", null);
		return "redirect:/web/moilist/page/1";
	}

	@RequestMapping(value = "/moilist/page/{pageNumber}", method = { RequestMethod.GET, RequestMethod.POST })
	public String showSanphammoiPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("sanphammoilist");
		int pagesize = 20;
		List<SanphamVaChitiet> list = (List<SanphamVaChitiet>) this.sanphamvachitietService.getAllSanphammoi();

		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("sanphammoilist", pages);

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

		model.addAttribute("SANPHAMSALELIST", pages);

		return "/web/sanphamsale";
	}
	
	@RequestMapping(value = "/noibatlist", method = { RequestMethod.GET, RequestMethod.POST })
	public String SanphamnoibatList(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("sanphamnoibatlist", null);
		return "redirect:/web/noibatlist/page/1";
	}

	@RequestMapping(value = "/noibatlist/page/{pageNumber}", method = { RequestMethod.GET, RequestMethod.POST })
	public String showSanphamnoibatPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("sanphamnoibatlist");
		int pagesize = 20;
		List<SanphamVaChitiet> list = (List<SanphamVaChitiet>) this.sanphamvachitietService.getAllSanphamnoibat();
	
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("sanphamnoibatlist", pages);

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

		model.addAttribute("SANPHAMSALELIST", pages);

		return "/web/sanphamsale";
	}
	
	@RequestMapping(value = "/salelist", method = { RequestMethod.GET, RequestMethod.POST })
	public String SanphamsaleList(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("sanphamsalelist", null);
		return "redirect:/web/salelist/page/1";
	}

	@RequestMapping(value = "/salelist/page/{pageNumber}", method = { RequestMethod.GET, RequestMethod.POST })
	public String showSanphamsalePage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("sanphamsalelist");
		int pagesize = 20;
		List<SanphamVaChitiet> list = (List<SanphamVaChitiet>) this.sanphamvachitietService.getAllSanphamsale();
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("sanphamsalelist", pages);

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

		model.addAttribute("SANPHAMSALELIST", pages);

		return "/web/sanphamsale";
	}
	
	@RequestMapping(value = "/banchaylist", method = { RequestMethod.GET, RequestMethod.POST })
	public String SanphambanchayList(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("sanphambanchaylist", null);
		return "redirect:/web/banchaylist/page/1";
	}

	@RequestMapping(value = "/banchaylist/page/{pageNumber}", method = { RequestMethod.GET, RequestMethod.POST })
	public String showSanphambanchayPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("sanphambanchaylist");
		int pagesize = 20;
		List<SanphamVaChitiet> list = (List<SanphamVaChitiet>) this.sanphamvachitietService.getAllSanphambanchay();
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("sanphambanchaylist", pages);

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

		model.addAttribute("SANPHAMSALELIST", pages);

		return "/web/sanphamsale";
	}
	
	@RequestMapping(value = "/dataSearch", method = {RequestMethod.GET, RequestMethod.POST})
	public String datasearch(@RequestParam("min") String min, @RequestParam("max") String max, HttpSession session) {
		
		if (min == null && max == null || min.equals("") && max.equals("")) {
			return "redirect:/web/list";
		} else {
			session.setAttribute("MIN", min);
			session.setAttribute("MAX", max);
			return "redirect:/web/list/search/1";
		}
	}
	
	@RequestMapping(value = "/list/search/{pageNumber}", method = {RequestMethod.GET})
	public String search(HttpSession session, HttpServletRequest request, ModelMap model, @PathVariable int pageNumber) {
		String min =(String) session.getAttribute("MIN");
		String max =(String) session.getAttribute("MAX");
		List<SanphamVaChitiet> list = (List<SanphamVaChitiet>) this.sanphamvachitietService.searchGiatien(Float.parseFloat(min), Float.parseFloat(max));
		if (list == null) {
			return "redirect:/web/list";
		}
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("tatcasanphamlist");
		int pagesize = 20;
		pages = new PagedListHolder<>(list);
		pages.setPageSize(pagesize);
		final int goToPage = pageNumber - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("tatcasanphamlist", pages);

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

		model.addAttribute("TATCASANPHAMS", pages);

		return "/web/tatcasanpham";
	}
	
	@RequestMapping(value = "/dataSize", method = {RequestMethod.GET})
	public String datasearch(@PathParam("size") String size, HttpSession session) {
		
		if (size == null || size.equals("")) {
			return "redirect:/web/list";
		} else {
			session.setAttribute("SIZE", size);
			return "redirect:/web/list/size/1";
		}
	}
	
	@RequestMapping(value = "/list/size/{pageNumber}", method = {RequestMethod.GET})
	public String searchSize(HttpSession session, HttpServletRequest request, ModelMap model, @PathVariable int pageNumber) {
		String size =(String) session.getAttribute("SIZE");
		List<SanphamVaChitiet> list = (List<SanphamVaChitiet>) this.sanphamvachitietService.getSearchSize(size);
		if (list == null) {
			return "redirect:/web/list";
		}
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("tatcasanphamsizelist");
		int pagesize = 20;
		pages = new PagedListHolder<>(list);
		pages.setPageSize(pagesize);
		final int goToPage = pageNumber - 1;
		if (goToPage <= pages.getPageCount() && goToPage >= 0) {
			pages.setPage(goToPage);
		}
		request.getSession().setAttribute("tatcasanphamsizelist", pages);

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

		model.addAttribute("TATCASANPHAMS", pages);

		return "/web/tatcasanpham";
	}
	
	@GetMapping("/login1")
	public String login() {
		return "/web/loginweb";
	}
	@RequestMapping(value = "/checkLogin", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String checkLogin(HttpSession session,@RequestParam("email") String email, @RequestParam("password") String password) {
		if (this.billService.checkLogin(email, password)) {
			session.setAttribute("EMAIL_WEB", this.billRepository.findByEmail(email));
			
			return "redirect:/web/";
		} else {
			
			return "redirect:/web/";
		}
		
	}
	
	
	@GetMapping("/xoanguoimuahang")
	public String xoaNguoiMuaHang(HttpSession session, ModelMap model) {
		session.removeAttribute("EMAIL_WEB");
		session.removeAttribute("INFO");
		session.removeAttribute("quantity");
		session.removeAttribute("myCartNum");
		session.removeAttribute("myCartTotal");
		session.removeAttribute("myCartItems");
		return "redirect:/web/";
	}
	@ModelAttribute(name = "SANPHAMMOI")
	public List<SanphamVaChitiet> getSanphammoi() {
		return this.sanphamvachitietService.getSanphammoi();
	}

	@ModelAttribute(name = "SANPHAMNOIBAT")
	public List<SanphamVaChitiet> getSanphamnoibat() {
		return this.sanphamvachitietService.getSanphamnoibat();
	}
	


	@GetMapping("/sanpham-chitiet1/{id}")
	public String sanphamchitiet(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("spm", this.sanphamService.finBySanphamId(id).get());
		model.addAttribute("sizesp", this.sanphamvachitietService.findBySizeSanpham(id));
		return "/web/chitiet";
	}

	@GetMapping("/sanpham-size")
	public String sanphamsize(@RequestParam("id") Long id, ModelMap model) {
		SanphamVaChitiet sizeSanPham = this.sanphamvachitietService.findBySanphamId(id).get();
		model.addAttribute("sizes", sizeSanPham);
		return "/web/size";
	}

	@GetMapping(value = "/sp-chitiet", produces = "application/json")
	@ResponseBody
	public Optional<SanphamVaChitiet> getSizeSanphamVaChitiet(@RequestParam("id") Long id, ModelMap model) {
		return this.sanphamvachitietService.findBySanphamVaChitietId(id);
	}

	@RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
	public String viewAdd(ModelMap model, HttpSession session, @RequestParam("id") Long id,
			@RequestParam("quantity") Integer quantity) {
		HashMap<Long, CartDTO> cartItems = (HashMap<Long, CartDTO>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			cartItems = new HashMap<>();
			session.setAttribute("myCartItems", cartItems);
		}

		SanphamVaChitiet product = this.sanphamvachitietService.findBySanphamId(id).get();
		if (product != null) {
			CartDTO item = cartItems.get(id);
			if (null == item) {
				item = new CartDTO();
				item.setProduct(product);
				item.setQuantity(0);
				cartItems.put(id, item);
			}
			
			item.setQuantity(item.getQuantity() + quantity);
		}
		
		session.setAttribute("myCartTotal", totalPrice(cartItems));
		session.setAttribute("myCartNum", cartItems.size());
		session.setAttribute("quantity", totalQuantity(cartItems));
		return "/web/giohang";
	}
	
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String viewUpdate(ModelMap model, HttpSession session, @RequestParam("id1") long id,
			@RequestParam("quantity1") Integer quantity) {
		HashMap<Long, CartDTO> cartItems = (HashMap<Long, CartDTO>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			cartItems = new HashMap<>();
			session.setAttribute("myCartItems", cartItems);
		}

		SanphamVaChitiet product = this.sanphamvachitietService.findBySanphamId(id).get();
		
			CartDTO item = new CartDTO();
			if (cartItems.containsKey(id)) {
				item = cartItems.get(id);
				item.setQuantity(quantity);
				cartItems.put(id, item);
			}
			
		
		
		session.setAttribute("myCartTotal", totalPrice(cartItems));
		session.setAttribute("myCartNum", cartItems.size());
		session.setAttribute("quantity", totalQuantity(cartItems));
		return "redirect:/web/cart";
	}
	
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cart() {
		return "/web/giohang";
	}

	public double totalPrice(HashMap<Long, CartDTO> cartItems) {
		int count = 0;
		for (Map.Entry<Long, CartDTO> list : cartItems.entrySet()) {
			count += list.getValue().getQuantity()
					* (list.getValue().getProduct().getGiatien() - list.getValue().getProduct().getGiamgia());
		}
		return count;
	}

	public int totalQuantity(HashMap<Long, CartDTO> cartItems) {
		int count = 0;
		for (Map.Entry<Long, CartDTO> list : cartItems.entrySet()) {
			count += list.getValue().getQuantity();
		}
		return count;
	}
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String viewRemove(ModelMap model, HttpSession session, @PathVariable("id") Long id) {
		HashMap<Long, CartDTO> cartItems = (HashMap<Long, CartDTO>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}
		if (cartItems.containsKey(id)) {
			cartItems.remove(id);
		}
		session.setAttribute("myCartItems", cartItems);
		session.setAttribute("myCartTotal", totalPrice(cartItems));
		session.setAttribute("myCartNum", cartItems.size());
		session.setAttribute("quantity", totalQuantity(cartItems));
		return "/web/giohang";
	}

	@RequestMapping(value = "/checkout", method = { RequestMethod.GET })
	public String viewCheckout(ModelMap model, HttpSession session) {
		if (session.getAttribute("EMAIL_WEB") == null && session.getAttribute("INFO") == null) {
			Bills bills = new Bills();
			model.addAttribute("ACTION", "/web/saveCheckout");

			model.addAttribute("bills", bills);
			return "/web/checkout";
		}
		return "/web/thanhtoan";
	}

	@PostMapping("/saveCheckout")
	public String saveCheckout(HttpSession session, @ModelAttribute("bills") Bills bills) {
		session.setAttribute("INFO", bills);
		bills.setBill_password(Xuly.giaiMd5(bills.getBill_password()));
		bills.setBill_date(new Timestamp(new Date().getTime()));
		bills.setBill_status((Integer) 0);
		billService.insertBill(bills);
		Integer q = (Integer) session.getAttribute("quantity");
		if(q == null) {
			return "redirect:/web/";
		}
		return "/web/thanhtoan";
	}
	
	@RequestMapping(value="/updateCheckout1", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String doUpdate11(Bills b) {
		b.setBill_status((Integer) 0);
		this.billService.updateBill1(b);
		return "redirect:/web/";
	}
	
	@PostMapping("/savePay")
	public String chekoutPay(HttpSession session, @RequestParam("bill_id") Bills bill_id , @RequestParam("billdetail_pay") Integer billdetail_pay) {
		HashMap<Long, CartDTO> cartItems = (HashMap<Long, CartDTO>) session.getAttribute("myCartItems");
		session.getAttribute("INFO");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}
		
		for (Map.Entry<Long, CartDTO> entry : cartItems.entrySet()) {
			BillDetail billDetail = new BillDetail();
			Kho k = new Kho();
			
			billDetail.setBills(bill_id);
			billDetail.setSanphamvachitiet(entry.getValue().getProduct());
			billDetail.setBilldetail_quantity(entry.getValue().getQuantity());
			billDetail.setBilldetail_price(entry.getValue().getQuantity()
					* (entry.getValue().getProduct().getGiatien() - entry.getValue().getProduct().getGiamgia()));
			billDetail.setBilldetail_sale(entry.getValue().getProduct().getGiamgia());
			billDetail.setBilldetail_pay(billdetail_pay);
			billDetail.setBilldetail_status((Integer) 0);
			billDetail.setBilldetail_date(new Timestamp(new Date().getTime()));
			this.billDetailService.insertBillDetail(billDetail);
			
			k.setBilldetails_id(billDetail);
			k.setTrangthai((Integer) 0);
			k.setIsdelete((Integer) 0);
			this.khoService.save(k);
			
			SanphamVaChitiet spct = this.sanphamvachitietService.findBySanphamId(billDetail.getSanphamvachitiet().getId()).get();
			spct.setSoluong(spct.getSoluong() - entry.getValue().getQuantity());
			this.sanphamvachitietService.updateSoLuongDaMua(spct);
		}
		
		cartItems = new HashMap<>();
		session.setAttribute("myCartItems", cartItems);
		session.setAttribute("myCartTotal", 0);
		session.setAttribute("myCartNum", 0);
		session.setAttribute("quantity", 0);
		return "redirect:/web/damua";
	}
	
	@GetMapping("/thongtincoban")
	public String thongtincoban(ModelMap model, HttpSession session) {
		Bills b =(Bills) session.getAttribute("EMAIL_WEB");
		String email = b.getBill_email();
		model.addAttribute("thongtin", this.billRepository.findByEmail(email));
		return "/web/thongtincoban";
	}

	@GetMapping("/thongtincoban1")
	public String thongtincoban1(HttpSession session, ModelMap model) {
		Bills b =(Bills) session.getAttribute("INFO");
		String email = b.getBill_email();
		model.addAttribute("thongtin1", this.billRepository.findByEmail(email));
		return "/web/thongtincoban1";
	}
	
	@GetMapping("/lichsumuahang")
	public String lichSuMuaHang(HttpSession session, ModelMap model) {
		Bills email =(Bills) session.getAttribute("EMAIL_WEB");
		List<BillDetail> list = (List<BillDetail>) this.billDetailService.getLichSuMuaHang(email);
		if (list == null) {
			return "redirect:/web/";
		}
		
		model.addAttribute("LICHSUMUAHANG", list);
		return "/web/lichsumuahang";
	}
	
	@GetMapping("/damua")
	public String damua() {
		return "/web/damua";
	}
	
	@GetMapping("/lienhe") 
	public String lienhe() {
		return "/web/lienhe";
	}
	
	@GetMapping("/gioithieu") 
	public String gioithieu() {
		return "/web/gioithieu";
	}
}
