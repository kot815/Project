package com.example.demo.bean;

public class NotepadBean {
    private  String id;
    private String notepadContent;
    private String notepadTime;

    public String getId() {
        return id;
    }

    public String getNotepadContent() {
        return notepadContent;
    }

    public String getNotepadTime() {
        return notepadTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNotepadContent(String notepadContent) {
        this.notepadContent = notepadContent;
    }

    public void setNotepadTime(String notepadTime) {
        this.notepadTime = notepadTime;
    }
}
