package org.serj.controllers;

import org.serj.domains.Comment;
import org.serj.domains.Note;
import org.serj.domains.User;
import org.serj.repositoryes.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Controller
public class TopicController {

    @Autowired
    private CommentRepository commentRepository;


    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/comments/{id}")
    public String showComments(@PathVariable Integer id, Model model) {
        Iterable<Comment> comments = commentRepository.findAllById(id);
        model.addAttribute("comments", comments);

        return "comments";
    }




    @PostMapping("/new_comment")
    public String add(
            @AuthenticationPrincipal User author,
            @AuthenticationPrincipal Note topic,
            @RequestParam String text,
            Model model,
            @RequestParam("file") MultipartFile file) throws IOException {

        Comment comment = new Comment(text, author, topic);

        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));
            comment.setFilename(resultFileName);
        }

        commentRepository.save(comment);

        Iterable<Comment> comments = commentRepository.findAll();

        model.addAttribute("notes", comments);


        if (author.isAdmin()) {
            model.addAttribute("isAdmin", author.isAdmin());
        }

        return "comments";
    }

}
