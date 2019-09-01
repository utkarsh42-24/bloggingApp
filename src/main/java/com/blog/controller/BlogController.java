package com.blog.controller;

import com.blog.models.Blog;
import com.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/blog")
public class BlogController {


    @Autowired
    BlogRepository blogRepository;

    /** Create */
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Blog createBlog(@RequestBody Blog blog){
        return blogRepository.save(blog);
    }


    /** Read */
    @GetMapping("/get/{id}")
    public Blog getBlog(@PathVariable Long id){
        return blogRepository.findById(id).get();
    }

    @GetMapping("/get/all")
    public Iterable<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }


    /** Update */
    @PutMapping("/update/{id}")
    public Blog updateBlog(@PathVariable Long id, @RequestBody Blog blog ){
        if(!id.equals(blog.getBlogID())){  // id mismatch
            return null;
        }

        Blog blog1 = blogRepository.findById(id).get();
        if( blog1 == null ) {  // not found
            return null;
        }

        // do the update
        blog1.setDescription(blog.getDescription());
        return blogRepository.save(blog1);
    }


    /** Delete */
    @DeleteMapping("/delete/{id}")
    public void DeleteBlog(@PathVariable Long id){
        Blog blog = blogRepository.findById(id).get();
        if(blog==null){   // not found
            return;
        }
        blogRepository.delete(blog);
    }


    @DeleteMapping("/delete/all")
    public void DeleteAllBlogs(){
        long entries = blogRepository.count();
        if(entries==0){
            return;
        }
        blogRepository.deleteAll();
    }

}
