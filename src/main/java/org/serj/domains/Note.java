package org.serj.domains;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String text;

    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User author;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    private String filename;

    /*private Date postReg = new Date();

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "note_exercise", joinColumns = @JoinColumn(name="note_id"))
    @ElementCollection(targetClass = Exercise.class, fetch = FetchType.EAGER)
    private Set<Exercise> exercises;
    private int user_id;*/
    public Note() {
    }

    public Note(String text, String tag, User author) {
        this.text = text;
        this.tag = tag;
        this.author = author;
    }

    public String getAuthorName(){
        return author!= null?author.getName() : "<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
