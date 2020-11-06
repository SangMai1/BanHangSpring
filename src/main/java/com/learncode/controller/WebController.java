package com.learncode.controller;

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
import com.learncode.models.Sanpham;
import com.learncode.models.SanphamVaChitiet;
import com.learncode.models.Slides;
import com.learncode.repository.BillRepository;
import com.learncode.service.BillDetailService;
import com.learncode.service.BillService;
import com.learncode.service.SanphamService;
import com.learncode.service.SanphamVaChitietService;
import com.learncode.service.SlidesService;

@Controller
@RequestMapping("/web")
public class WebController {

	@Autowired
	SanphamService sanphamService;

	@Autowired
	SanphamVaChitietService sanphamvachitietService;

	@Autowired
	SlidesService slidesService;

	@Autowired
	BillService billService;

	@Autowired
	BillRepository billRepository;
	
	@Autowired
	BillDetailService billDetailService;

	@RequestMapping("/")
	public String home() {
		return "/web/trangchu";
	}

	@RequestMapping("/tatcasanpham")
	public String tatcasanpham() {
		return "/web/tatcasanpham";
	}

//	@RequestMapping(value="/seachGia/{min}/{max}", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE}, headers = {"Accept=application/json"})
//	public ResponseEntity<List<SanphamVaChitiet>> search(@RequestParam("min") String min, @RequestParam("max") String max){
//	
//		try {
//			List<SanphamVaChitiet> spct = this.sanphamvachitietService.searchGiatien(Float.parseFloat(min), Float.parseFloat(max));
//			System.out.println("aaaa"+spct);
//			return new ResponseEntity<List<SanphamVaChitiet>>(spct, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<List<SanphamVaChitiet>>(HttpStatus.BAD_REQUEST);
//		}
//	}
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(ModelMap model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("tatcasanphamlist", null);
		return "redirect:/web/list/page/1";
	}

	@RequestMapping(value = "/list/page/{pageNumber}", method = { RequestMethod.GET, RequestMethod.POST })
	public String showTatcasanphamsPage(HttpServletRequest request, @PathVariable int pageNumber, ModelMap model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("tatcasanphamlist");
		int pagesize = 20;
		List<Sanpham> list = (List<Sanpham>) this.sanphamService.getAllSanpham();
		System.out.println("list day"+list);
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
		System.out.println(current);
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
	
	@RequestMapping("/dataSearch")
	public String datasearch(@RequestParam("min") String min, @RequestParam("max") String max, HttpSession session) {
		
		if (min == null && max == null || min.equals("") && max.equals("")) {
			return "redirect:/web/list";
		} else {
			session.setAttribute("MIN", min);
			session.setAttribute("MAX", max);
			return "redirect:/web/list/search/1";
		}
	}
	
	@RequestMapping("/list/search/{pageNumber}")
	public String search(HttpSession session, HttpServletRequest request, ModelMap model, @PathVariable int pageNumber) {
		String min =(String) session.getAttribute("MIN");
		String max =(String) session.getAttribute("MAX");
		List<Sanpham> list = (List<Sanpham>) this.sanphamService.searchGiatien(Float.parseFloat(min), Float.parseFloat(max));
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
	
	@RequestMapping("/dataSize")
	public String datasearch(@PathParam("size") String size, HttpSession session) {
		
		if (size == null || size.equals("")) {
			return "redirect:/web/list";
		} else {
			session.setAttribute("SIZE", size);
			return "redirect:/web/list/size/1";
		}
	}
	
	@RequestMapping("/list/size/{pageNumber}")
	public String searchSize(HttpSession session, HttpServletRequest request, ModelMap model, @PathVariable int pageNumber) {
		String size =(String) session.getAttribute("SIZE");
		List<Sanpham> list = (List<Sanpham>) this.sanphamService.searchSize(size);
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
	@PostMapping(value = "/checkLogin")
	public String checkLogin(HttpSession session,@RequestParam("email") String email, @RequestParam("password") String password) {
		
		if (this.billService.checkLogin(email, password)) {
			session.setAttribute("EMAIL_WEB", this.billRepository.findByEmail(email));
			System.out.println("đăng nhập thành công");
			return "redirect:/web/";
		} else {
			System.out.println("đăng nhập thất bại");
			
		}
		return "redirect:/web/login1";
	}
	@ModelAttribute(name = "SANPHAMMOI")
	public List<Sanpham> getSanphammoi() {
		return sanphamService.getSanphammoi();
	}

	@ModelAttribute(name = "SLIDESSHOW")
	public List<Slides> getAllSlides() {
		return slidesService.getAll();
	}

	@GetMapping("/sanpham-chitiet1/{id}")
	public String sanphamchitiet(@PathVariable("id") Long id, ModelMap model) {
//		model.addAttribute("spm", this.sanphamService.findBySanphamAndSanphamchitiet(id).orElse(null));
		model.addAttribute("spm", this.sanphamService.finBySanphamId(id).get());
		model.addAttribute("sizesp", this.sanphamvachitietService.findBySizeSanpham(id));
//		System.out.println(this.sanphamService.findBySanphamAndSanphamchitiet(id).get());
		return "/web/chitiet";
	}

	@GetMapping("/sanpham-size")
	public String sanphamsize(@RequestParam("id") Long id, ModelMap model) {
		SanphamVaChitiet sizeSanPham = this.sanphamvachitietService.findBySanphamId(id).get();
		model.addAttribute("sizes", sizeSanPham);
		System.out.println("size" + sizeSanPham);
		return "/web/size";
	}

	@GetMapping(value = "/sp-chitiet", produces = "application/json")
	@ResponseBody
	public Optional<SanphamVaChitiet> getSizeSanphamVaChitiet(@RequestParam("id") Long id, ModelMap model) {
		return this.sanphamvachitietService.findBySanphamVaChitietId(id);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String viewAdd(ModelMap model, HttpSession session, @RequestParam("id") Long id,
			@RequestParam("quantity") Integer quantity) {
		HashMap<Long, CartDTO> cartItems = (HashMap<Long, CartDTO>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			cartItems = new HashMap<>();
			session.setAttribute("myCartItems", cartItems);
		}

		SanphamVaChitiet product = this.sanphamvachitietService.findBySanphamId(id).get();
		System.out.println("product" + product);
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

		System.out.println("giohang" + cartItems);
		session.setAttribute("myCartTotal", totalPrice(cartItems));
		System.out.println("tong: " + totalPrice(cartItems));
		session.setAttribute("myCartNum", cartItems.size());
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
		return "/web/thanhtoan";
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
			billDetail.setBills(bill_id);
			billDetail.setSanphamvachitiet(entry.getValue().getProduct());
			billDetail.setBilldetail_quantity(entry.getValue().getQuantity());
			billDetail.setBilldetail_price(entry.getValue().getQuantity()
					* (entry.getValue().getProduct().getGiatien() - entry.getValue().getProduct().getGiamgia()));
			billDetail.setBilldetail_sale(entry.getValue().getProduct().getGiamgia());
			billDetail.setBilldetail_pay(billdetail_pay);
			billDetail.setBilldetail_status((Integer) 0);
			billDetailService.insertBillDetail(billDetail);
		}
		cartItems = new HashMap<>();
		session.setAttribute("myCartItems", cartItems);
		session.setAttribute("myCartTotal", 0);
		session.setAttribute("myCartNum", 0);

		return "redirect:/web/damua";
	}
//	@PostMapping("/saveCheckout")
//	public String saveCheckout(HttpSession session, @ModelAttribute("bills") Bills bills) {
//		HashMap<Long, CartDTO> cartItems = (HashMap<Long, CartDTO>) session.getAttribute("myCartItems");
//		if (cartItems == null) {
//			cartItems = new HashMap<>();
//		}

//		bills.setBill_id(ThreadLocalRandom.current().nextLong(0, new Long("9000000000000000")));
//		Xuly.giaiMd5(bills.getBill_password());
//		bills.setBill_date(new Timestamp(new Date().getTime()));
//		bills.setBill_status((Integer) 0);
//		billService.insertBill(bills);
//
//		for (Map.Entry<Long, CartDTO> entry : cartItems.entrySet()) {
//			BillDetail billDetail = new BillDetail();
//		//	billDetail.setBilldetail_id(ThreadLocalRandom.current().nextLong(0, new Long("9000000000000000")));
//			billDetail.setBills(bills);
//			billDetail.setSanphamvachitiet(entry.getValue().getProduct());
//			billDetail.setBilldetail_quantity(entry.getValue().getQuantity());
//			billDetail.setBilldetail_price(entry.getValue().getQuantity()
//					* (entry.getValue().getProduct().getGiatien() - entry.getValue().getProduct().getGiamgia()));
//			billDetail.setBilldetail_sale(entry.getValue().getProduct().getGiamgia());
//			billDetail.setBilldetail_status(true);
//			billDetailService.insertBillDetail(billDetail);
//		}
//
//		cartItems = new HashMap<>();
//		session.setAttribute("myCartItems", cartItems);
//		session.setAttribute("myCartTotal", 0);
//		session.setAttribute("myCartNum", 0);
//
//		return "redirect:/web/damua";
//	}

	@GetMapping("/damua")
	public String damua() {
		return "/web/damua";
	}
}
