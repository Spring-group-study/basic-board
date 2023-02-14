package com.study.board.dto;

import javax.validation.constraints.NotBlank;

public class PostDto {

    public static class Post {
        Long id;

        String author;

        @NotBlank(message = "제목은 공백이 아니여야 합니다.")
        String title;

        String content;

        public Long getId() {
            return id;
        }
        public String getAuthor() {
            return author;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Post(Long id, String author, String title, String content) {
            this.id = id;
            this.author = author;
            this.title = title;
            this.content = content;
        }
    }

    public static class Patch {

        @NotBlank(message = "제목은 공백이 아니여야 합니다.")
        String title;

        String content;

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Patch(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }

    public static class Response {
        String author;
        String title;
        String content;

        public String getAuthor() {
            return author;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public Response(String author, String title, String content) {
            this.author = author;
            this.title = title;
            this.content = content;
        }
    }

}
