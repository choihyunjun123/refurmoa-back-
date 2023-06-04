package com.highfive.refurmoa.post.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.highfive.refurmoa.post.dto.reponse.PostResponseDTO;
import com.highfive.refurmoa.post.dto.request.PostReadCountResquestDTO;
import com.highfive.refurmoa.post.dto.request.PostRequestDTO;
import com.highfive.refurmoa.post.dto.request.PostWriteDTO;
import com.highfive.refurmoa.post.dto.request.UserlikeRequestDTO;
import com.highfive.refurmoa.post.service.PostServiceImpl;
import com.highfive.refurmoa.prod.DTO.request.ProdFileDTO;
import com.highfive.refurmoa.prod.service.ProductServiceImpl;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostServiceImpl postServiceImpl;
    private final ProductServiceImpl productServiceImpl;
    public PostController(PostServiceImpl postServiceImpl,ProductServiceImpl productServiceImpl) {
        this.postServiceImpl = postServiceImpl;
        this.productServiceImpl=productServiceImpl;
    }
  
    // 찜 등록/취소
    @PostMapping("/like")
    public void userlikeupdate(@RequestBody UserlikeRequestDTO userlikeDTO) {
        postServiceImpl.userlikeupdate(userlikeDTO);
    }

    // 판매글 목록 가져오기
    @PostMapping("")
    public Page<PostResponseDTO> getPostList(@RequestBody PostRequestDTO postRequestDTO) {
        return postServiceImpl.getPostList(postRequestDTO);
    }

    // 조회수 증가
    @PostMapping("/readcount")
    public void readCount(@RequestBody PostReadCountResquestDTO postReadCountResquestDTO) {
        postServiceImpl.readCount(postReadCountResquestDTO);
    }
    private int prodNum;
    @PostMapping("/write")
    public int PostWrite(@RequestParam(value="main_image",required = false) MultipartFile mainImg,@RequestParam(value="detailFile") MultipartFile detailFile,PostWriteDTO postDto) throws IllegalStateException, IOException  {
    	prodNum=postServiceImpl.PostWrite(mainImg,detailFile,postDto);
    	return prodNum;
    }
    @PostMapping("/post/file")
	public int upload(@RequestBody MultipartFile[] uploadfiles) throws IOException {
       
		int prod_num = prodNum;
		String[] tmp=new String[]{"","",""};
		for (int i=0;i<uploadfiles.length;i++) {
			File defect = new File("prod\\"+UUID.randomUUID().toString().replaceAll("-", "")+".jpg");
			uploadfiles[i].transferTo(defect);
			tmp[i]=defect.toString();
		}
		ProdFileDTO dto= new ProdFileDTO(prod_num,tmp[0],tmp[1],tmp[2]);
		productServiceImpl.insertFile(dto);
		return 1;
    }

}
