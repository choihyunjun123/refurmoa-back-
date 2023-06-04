package com.highfive.refurmoa.post.service;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.highfive.refurmoa.post.dto.reponse.PostResponseDTO;
import com.highfive.refurmoa.post.dto.request.PostReadCountResquestDTO;
import com.highfive.refurmoa.post.dto.request.PostRequestDTO;
import com.highfive.refurmoa.post.dto.request.PostWriteDTO;
import com.highfive.refurmoa.post.dto.request.UserlikeRequestDTO;

public interface PostService {
  
    void userlikeupdate(UserlikeRequestDTO userlikeDTO); // 찜 등록/삭제
    // 판매글 목록 가져오기
    Page<PostResponseDTO> getPostList(PostRequestDTO postRequestDTO);
    // 조회수 증가
    void readCount(PostReadCountResquestDTO postReadCountResquestDTO);
    
    public int PostWrite(MultipartFile mainImg,MultipartFile detailFile,PostWriteDTO prodDto) throws IOException;
  
}
