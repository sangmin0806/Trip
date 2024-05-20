package com.ssafy.board.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.FileInfoDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.member.model.MemberDto;
import com.ssafy.util.PageNavigation;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/article")
public class BoardController {

//	private final String UPLOAD_PATH = "/upload";
	
	@Value("${file.path}")
	private String uploadPath;
	
	@Value("${file.path.upload-images}")
	private String uploadImagePath;
	
	@Value("${file.path.upload-files}")
	private String uploadFilePath;

//	@Autowired
//	private ServletContext servletContext;

	private final BoardService boardService;

	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}

	@GetMapping("/write")
	public String write(@RequestParam Map<String, String> map, Model model) {
		log.debug("write call parameter {}", map);
		model.addAttribute("pgno", map.get("pgno"));
		model.addAttribute("key", map.get("key"));
		model.addAttribute("word", map.get("word"));
		return "board/write";
	}

	@PostMapping("/write")
    public String write(@RequestBody BoardDto boardDto, HttpSession session) throws Exception {
        log.debug("Received boardDto : {}", boardDto);

        // 세션에서 사용자 정보를 가져와서 DTO에 사용자 ID 설정
//        MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//        boardDto.setUserId(memberDto.getUserId());

        // 게시글을 데이터베이스에 저장하는 로직 호출
        boardService.writeArticle(boardDto);

        // 성공적으로 게시글을 등록했다는 메시지 반환
        return "Article posted successfully";
    }


	@GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam Map<String, String> map) throws Exception {
        log.debug("list parameter pgno : {}", map.get("pgno"));
        
        List<BoardDto> list = boardService.listArticle(map);
        PageNavigation pageNavigation = boardService.makePageNavigation(map);
        
        Map<String, Object> response = new HashMap<>();
        response.put("articles", list);
        response.put("navigation", pageNavigation);
        response.put("pgno", map.get("pgno"));
        response.put("key", map.get("key"));
        response.put("word", map.get("word"));
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@GetMapping("/view")
    public ResponseEntity<Map<String, Object>> view(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> params) throws Exception {
        log.debug("view articleNo : {}", articleNo);
        boardService.updateHit(articleNo);
        BoardDto boardDto = boardService.getArticle(articleNo);
        log.debug("view articleData : {}", boardDto);
        Map<String, Object> response = new HashMap<>();
        response.put("articles", boardDto);
        response.put("pgno", params.get("pgno"));
        response.put("key", params.get("key"));
        response.put("word", params.get("word"));
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@GetMapping("/modify")
    public ResponseEntity<Map<String, Object>> getModifyArticle(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> params) throws Exception {
        log.debug("modify articleNo : {}", articleNo);
        BoardDto boardDto = boardService.getArticle(articleNo);

        Map<String, Object> response = new HashMap<>();
        response.put("article", boardDto);
        response.put("pgno", params.get("pgno"));
        response.put("key", params.get("key"));
        response.put("word", params.get("word"));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/modify")
    public ResponseEntity<?> modifyArticle(@RequestBody BoardDto boardDto) throws Exception {
        log.debug("modify boardDto : {}", boardDto);
        boardService.modifyArticle(boardDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

	@DeleteMapping("/delete")
	public String delete(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map,
			RedirectAttributes redirectAttributes) throws Exception {
		log.debug("delete articleNo : {}", articleNo);
//		boardService.deleteArticle(articleNo, servletContext.getRealPath(UPLOAD_PATH));
		boardService.deleteArticle(articleNo, uploadPath);
		redirectAttributes.addAttribute("pgno", map.get("pgno"));
		redirectAttributes.addAttribute("key", map.get("key"));
		redirectAttributes.addAttribute("word", map.get("word"));
		return "redirect:/article/list";
	}

	@GetMapping("/download")
	public ModelAndView downloadFile(@RequestParam("sfolder") String sfolder, @RequestParam("ofile") String ofile,
			@RequestParam("sfile") String sfile) {
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		fileInfo.put("sfolder", sfolder);
		fileInfo.put("ofile", ofile);
		fileInfo.put("sfile", sfile);
		return new ModelAndView("fileDownLoadView", "downloadFile", fileInfo);
	}

}
