package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem { // �� item���� 3���� member�� �������ִ�.
	private int id;
    private String title;  // ����
    private String desc;  // ����
    private String current_date;  // item�� �����Ǵ� �ð�
    private String category;  // item ī�װ� 
    private String due_date;  // item ī�װ� 
    private String friend; // item ��������
    private String material; // item ��������

    public TodoItem(String category, String title, String desc, String due_date, String friend, String material){ 
    	this.category = category;
    	this.title = title;
        this.desc = desc;
        this.due_date = due_date;
        this.friend=friend;
        this.material=material;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date = f.format(new Date());  // �ڵ����� ����
    } 
    
    public void SetItem(String category, String title, String desc, String time, String due_date){ 
    	this.category = category;
    	this.title = title;
        this.desc = desc;
        this.due_date = due_date;
        this.current_date = time;
    } 
    
    // getter, setter
    
    public void setId(int id) {
    	this.id=id;
    }
    
    public String getFriend() {
		return friend;
	}


	public void setFriend(String friend) {
		this.friend = friend;
	}


	public String getMaterial() {
		return material;
	}


	public void setMaterial(String material) {
		this.material = material;
	}


	public int getId() {
    	return id;
    }
    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
	@Override
	public String toString() {
		return id + ". " + "[ " + category + " ], " + "[ ���� ]:" + title + ", [ ���� ]:" + desc + " <<" + due_date + ">> [ item �����ð� ]:" + current_date + ", [ �Բ��ϴ� ��� ]:" + friend + ", [ �غ� ]:" + material ;	
	}
    
}
