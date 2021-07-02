package org.serj.domains;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String text;

    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User author;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="note_id")
    private Note topic;


    public Comment() {
    }

    public Comment(String text, User author, Note topic) {
        this.text = text;
        this.author = author;
        this.topic = topic;
    }

    public String getAuthorName(){
        return author!= null?author.getName() : "<none>";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Note getTopic() {
        return topic;
    }

    public void setTopic(Note topic) {
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
