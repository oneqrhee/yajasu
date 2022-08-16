package com.example.yaja.controller;

import com.example.yaja.dto.PostRequestDto;
import com.example.yaja.dto.PostResponseDto;
import com.example.yaja.dto.Response;
import com.example.yaja.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post")
    public List<Response> readAllPost(){
        return postService.readAllPost();
    }

    @GetMapping("/post/{id}")
    public PostResponseDto readPost(@PathVariable Long id){
        return postService.readPost(id);
    }

    @PostMapping("/post")
    public Response createPost(@RequestBody PostRequestDto postRequestDto){
        return postService.createPost(postRequestDto);
    }

    @PutMapping("/post/{id}")
    public Response updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, @RequestParam String password){
        return postService.updatePost(id, postRequestDto, password);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id, @RequestParam String password){
        return postService.deletePost(id, password);
    }
}
