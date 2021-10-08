package com.todo.dao;

import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	private List<TodoItem> list; // TodoItem 객체들을 List로 가지는 list

	public TodoList() {
		this.list = new ArrayList<TodoItem>(); // 이 list는 ArrayList이다.
	}
	
	// item 추가
	public void addItem(TodoItem t) {
		list.add(t);
	}

	// item 삭제
	public void deleteItem(TodoItem t) {
		list.remove(t);
	}

	// item 수정
	void editItem(TodoItem t, TodoItem updated) {
		int index = list.indexOf(t);
		list.remove(index);
		list.add(updated);
	}

	// 전체 list를 가지고 return
	public ArrayList<TodoItem> getList() {
		return new ArrayList<TodoItem>(list);
	}

	// item name을 기준으로 sort
	public void sortByName() {
		Collections.sort(list, new TodoSortByName());
		System.out.println();

	}
	
	// 원하는 순서의 item return
	public TodoItem getitem(int n) {
		return list.get(n-1);
	}
	
	// list item들 모두 출력
	public void listAll() {
		System.out.println("\n"
				+ "[ 전체 item 목록 ]\n");
		for (TodoItem myitem : list) {
			System.out.println("[ " + myitem.getCategory() + " ] / " + "[ 제목 ] : " + myitem.getTitle() + " / [ 내용 ] : " + myitem.getDesc() + " / " + myitem.getDue_date() + " / [ item 생성시간 ] : " + myitem.getCurrent_date());
		}
	}
	
	// list item들 역순 출력
	public void reverseList() {
		Collections.reverse(list);
		System.out.println();
	}
	
	// item 생성시간에 따라서 sort
	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
		System.out.println();
		
	}
	
	// item 생성시간 반대로 sort
	public void reverseByDate() {
		Collections.sort(list, new TodoSortByDate());
		Collections.reverse(list);
		System.out.println();
	}

	// 특정한 item의 위치(순서)를 return
	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}
	
	// item 제목 중복체크
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
				
				System.out.println( num + ". [ " + item.getCategory() + " ] / " + "[ 제목 ] : " +item.getTitle() + " / [ 내용 ] : " + item.getDesc() + " / " + item.getDue_date() + " / [ item 생성시간 ] : " + item.getCurrent_date());
				count++;
			}
			if(item.getDesc().contains(key)) {
				System.out.println( num + ". [ " + item.getCategory() + " ] / " + "[ 제목 ] : " +item.getTitle() + " / [ 내용 ] : " + item.getDesc() + " / " + item.getDue_date() + " / [ item 생성시간 ] : " + item.getCurrent_date());
				count++;
			}
			num++;
		}
		
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
		System.out.println();
		System.out.println("===================================================================================================================================================================================================");
		System.out.println();
	}
	
	public void cate() {
		int count = 0;
		String cat = "카테고리 : ";
		
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
		System.out.println("총 " + count + "개의 카테고리가 등록되어 있습니다.");
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
				System.out.println( num + ". [ " + item.getCategory() + " ] / " + "[ 제목 ] : " +item.getTitle() + " / [ 내용 ] : " + item.getDesc() + " / " + item.getDue_date() + " / [ item 생성시간 ] : " + item.getCurrent_date());
				count++;
			}
			num++;
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
		System.out.println();
		System.out.println("===================================================================================================================================================================================================");
		System.out.println();
	}
}
