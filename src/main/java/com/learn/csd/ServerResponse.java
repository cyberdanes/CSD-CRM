package com.learn.csd;

/**
 * Created by ajaya_000 on 22-01-2015.
 */
public class ServerResponse {
    private String id;
	private String content;

    public String getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setId(String id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
