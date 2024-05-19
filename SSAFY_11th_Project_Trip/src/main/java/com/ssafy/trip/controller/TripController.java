package com.ssafy.trip.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.trip.model.TripDto;
import com.ssafy.trip.model.TripListDto;
import com.ssafy.trip.model.service.TripService;

import com.ssafy.util.PageNavigation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/trip")
public class TripController {
	private final TripService tripService;

	public TripController(TripService tripService) {
		super();
		this.tripService = tripService;
	}
	@GetMapping("/location")
	public ResponseEntity<?> location(@RequestParam Map<String, Object> map) throws Exception {
		String input = (String) map.get("input");
		if (input != null  && !input.isEmpty()) {
	        String[] keywords = input.split("\\s+");
	        map.put("keywords", keywords);
	    }
		log.debug("map : {}", map);
		Map<String, Object> info = tripService.locationInfo(map);
	    log.debug("list : {}", info);
	    return new ResponseEntity<>(info, HttpStatus.OK);
	}
	@GetMapping("/list")
	public ResponseEntity<?> list(@RequestParam Map<String, Object> map) throws Exception {
		String input = (String) map.get("input");
		if (input != null  && !input.isEmpty()) {
	        String[] keywords = input.split("\\s+");
	        map.put("keywords", keywords);
	    }
		log.debug("map : {}", map);
	    List<TripDto> list = tripService.listTripInfo(map);
	    log.debug("list : {}", list);
	    return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/planLists")
	public ResponseEntity<?> getPlanLists(@RequestParam Map<String, Object> map,HttpSession session) throws Exception {
		map.put("userId", session.getAttribute("userId"));
		log.debug("map : {}", map);
	    List<TripListDto> list = tripService.getPlanLists(map);
	    log.debug("list : {}", list);
	    return new ResponseEntity<>(list, HttpStatus.OK);
	}
	@GetMapping("/planLists/{id}")
	public ResponseEntity<?> getPlan(@PathVariable int id) throws Exception {
		List<TripDto> list = tripService.listTripInfoById(id);
	    return new ResponseEntity<>(list, HttpStatus.OK);
	}
	@PostMapping("/planLists/{id}")
	public ResponseEntity<?> deletePlan(@PathVariable int id) throws Exception {
		int success = tripService.deletePlan(id);
		if(success!=0) {
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	@PostMapping("/planLists")
	public ResponseEntity<?> registerPlan(@RequestBody Map<String, Object> map, HttpSession session) throws Exception {
		map.put("userId", session.getAttribute("userId"));
		System.out.println(map);
		int success = tripService.registerPlan(map);
		if(success!=0) {
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
//	@GetMapping("/view")
//	public String view(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map, Model model)
//			throws Exception {
//		log.debug("view articleNo : {}", articleNo);
//		BoardDto boardDto = boardService.getArticle(articleNo);
//		boardService.updateHit(articleNo);
//		model.addAttribute("article", boardDto);
//		model.addAttribute("pgno", map.get("pgno"));
//		model.addAttribute("key", map.get("key"));
//		model.addAttribute("word", map.get("word"));
//		return "board/view";
//	}

//	@GetMapping("/modify")
//	public String modify(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map, Model model)
//			throws Exception {
//		log.debug("modify articleNo : {}", articleNo);
//		BoardDto boardDto = boardService.getArticle(articleNo);
//		model.addAttribute("article", boardDto);
//		model.addAttribute("pgno", map.get("pgno"));
//		model.addAttribute("key", map.get("key"));
//		model.addAttribute("word", map.get("word"));
//		return "board/modify";
//	}
//
//	@PostMapping("/modify")
//	public String modify(BoardDto boardDto, @RequestParam Map<String, String> map,
//			RedirectAttributes redirectAttributes) throws Exception {
//		log.debug("modify boardDto : {}", boardDto);
//		boardService.modifyArticle(boardDto);
//		redirectAttributes.addAttribute("pgno", map.get("pgno"));
//		redirectAttributes.addAttribute("key", map.get("key"));
//		redirectAttributes.addAttribute("word", map.get("word"));
//		return "redirect:/article/list";
//	}
//
//	@GetMapping("/delete")
//	public String delete(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map,
//			RedirectAttributes redirectAttributes) throws Exception {
//		log.debug("delete articleNo : {}", articleNo);
////		boardService.deleteArticle(articleNo, servletContext.getRealPath(UPLOAD_PATH));
//		boardService.deleteArticle(articleNo, uploadPath);
//		redirectAttributes.addAttribute("pgno", map.get("pgno"));
//		redirectAttributes.addAttribute("key", map.get("key"));
//		redirectAttributes.addAttribute("word", map.get("word"));
//		return "redirect:/article/list";
//	}
}
