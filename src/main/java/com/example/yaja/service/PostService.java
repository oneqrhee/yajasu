package com.example.yaja.service;

import com.example.yaja.dto.PostRequestDto;
import com.example.yaja.dto.PostResponseDto;
import com.example.yaja.dto.Response;
import com.example.yaja.entity.Comment;
import com.example.yaja.entity.Post;
import com.example.yaja.repository.CommentRepository;
import com.example.yaja.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
    }

    @Transactional(readOnly = true)
    public List<Response> readAllPost(){
        List<Post> posts = postRepository.findAllByOrderByModifiedAt();
        List<Response> responseList = new ArrayList<>();
        for(Post post : posts){
            responseList.add(toResponse(post));
        }
        return responseList;
    }

    @Transactional(readOnly = true)
    public PostResponseDto readPost(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."));

        return new PostResponseDto(post);
    }

    @Transactional
    public Response createPost(PostRequestDto postRequestDto){
        String title = postRequestDto.getTitle();
        String author = postRequestDto.getAuthor();
        String content = postRequestDto.getContent();
        String password = postRequestDto.getPassword();
        if(title.isEmpty() || author.isEmpty() || content.isEmpty() || password.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "모든 항목을 최소 1자 이상 입력하세요.");
        }

        Post post = new Post(title, author, content, password);

        Post savedPost = postRepository.save(post);

        return toResponse(savedPost);
    }

    @Transactional(readOnly = true)
    protected boolean checkPassword(Long id, String password){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."));
        return !post.getPassword().equals(password);
    }

    @Transactional
    public Response updatePost(Long id, PostRequestDto postRequestDto, String password){
        if(checkPassword(id, password)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호를 확인하세요");
        }
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."));
        post.update(postRequestDto);

        return toResponse(post);
    }

    @Transactional
    public ResponseEntity<?> deletePost(Long id, String password){
        if(checkPassword(id, password)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호를 확인하세요");
        }
        postRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    protected Response toResponse(Post post){
        Long id = post.getId();
        String title = post.getTitle();
        String author = post.getAuthor();
        String content = post.getContent();
        LocalDateTime createdAt = post.getCreatedAt();
        LocalDateTime modifiedAt = post.getModifiedAt();

        return new Response(id, title, author, content, createdAt, modifiedAt);
    }
}
