package com.todo.dao;

import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	private List<TodoItem> list; // TodoItem ��ü���� List�� ������ list

	public TodoList() {
		this.list = new ArrayList<TodoItem>(); // �� list�� ArrayList�̴�.
	}
	
	// item �߰�
	public void addItem(TodoItem t) {
		list.add(t);
	}

	// item ����
	public void deleteItem(TodoItem t) {
		list.remove(t);
	}

	// item ����
	void editItem(TodoItem t, TodoItem updated) {
		int index = list.indexOf(t);
		list.remove(index);
		list.add(updated);
	}

	// ��ü list�� ������ return
	public ArrayList<TodoItem> getList() {
		return new ArrayList<TodoItem>(list);
	}

	// item name�� �������� sort
	public void sortByName() {
		Collections.sort(list, new TodoSortByName());
		System.out.println();

	}
	
	// ���ϴ� ������ item return
	public TodoItem getitem(int n) {
		return list.get(n-1);
	}
	
	// list item�� ��� ���
	public void listAll() {
		System.out.println("\n"
				+ "[ ��ü item ��� ]\n");
		for (TodoItem myitem : list) {
			System.out.println("[ " + myitem.getCategory() + " ] / " + "[ ���� ] : " + myitem.getTitle() + " / [ ���� ] : " + myitem.getDesc() + " / " + myitem.getDue_date() + " / [ item �����ð� ] : " + myitem.getCurrent_date());
		}
	}
	
	// list item�� ���� ���
	public void reverseList() {
		Collections.reverse(list);
		System.out.println();
	}
	
	// item �����ð��� ���� sort
	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
		System.out.println();
		
	}
	
	// item �����ð� �ݴ�� sort
	public void reverseByDate() {
		Collections.sort(list, new TodoSortByDate());
		Collections.reverse(list);
		System.out.println();
	}

	// Ư���� item�� ��ġ(����)�� return
	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}
	
	// item ���� �ߺ�üũ
	public Boolean isDuplicate(String title) {
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}
	
	public void find_keyword(String key) {
		int count = 0, num = 1;
		
		System.out.println("===================================================================================================================================================================================================");
		System.out.println();
		
		for (TodoItem item : list) {

			if(item.getTitle().contains(key)) {
				
				System.out.println( num + ". [ " + item.getCategory() + " ] / " + "[ ���� ] : " +item.getTitle() + " / [ ���� ] : " + item.getDesc() + " / " + item.getDue_date() + " / [ item �����ð� ] : " + item.getCurrent_date());
				count++;
			}
			if(item.getDesc().contains(key)) {
				System.out.println( num + ". [ " + item.getCategory() + " ] / " + "[ ���� ] : " +item.getTitle() + " / [ ���� ] : " + item.getDesc() + " / " + item.getDue_date() + " / [ item �����ð� ] : " + item.getCurrent_date());
				count++;
			}
			num++;
		}
		
		System.out.println("�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
		System.out.println();
		System.out.println("===================================================================================================================================================================================================");
		System.out.println();
	}
	
	public void cate() {
		int count = 0;
		String cat = "ī�װ� : ";
		
		for (TodoItem item : list) {
			if(cat.contains(item.getCategory())) {
				continue;
			}
			else {
				cat += item.getCategory();
				cat += " / ";
				count++;
			}
		}
		
		System.out.println("=============================================================================================================");
		System.out.println();
		System.out.println(cat.substring(0, cat.length()-2));
		System.out.println("�� " + count + "���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.");
		System.out.println();
		System.out.println("=============================================================================================================");
		System.out.println();
	}
	
	public void find_cate(String f) {
		int count = 0, num = 1;
		
		System.out.println("===================================================================================================================================================================================================");
		System.out.println();
		
		for (TodoItem item : list) {

			if(item.getCategory().contains(f)) {
				System.out.println( num + ". [ " + item.getCategory() + " ] / " + "[ ���� ] : " +item.getTitle() + " / [ ���� ] : " + item.getDesc() + " / " + item.getDue_date() + " / [ item �����ð� ] : " + item.getCurrent_date());
				count++;
			}
			num++;
		}
		System.out.println("�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
		System.out.println();
		System.out.println("===================================================================================================================================================================================================");
		System.out.println();
	}
}
