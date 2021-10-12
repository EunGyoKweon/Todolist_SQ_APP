package com.todo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	// item find
		public static void find_monthItem(TodoList l, String str_month) {
			int count=0;
			for(TodoItem item : l.month(str_month)) {
				System.out.println(item.toString());
				count++;
			}
			System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
			System.out.println();
		}
	
	// item find
	public static void findList(TodoList l, String keyword) {
		int count=0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}
	
	// item 새로 추가
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date, friend, material;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== [ item 추가 ] ==========\n");
		
		System.out.print("[ 카테고리 ] : "); // 카테고리 입력받음.
		category = sc.next();  
		
		sc.nextLine(); // 제목을 입력하고 enter치는거 제거
		
		System.out.print("[ 제목 ] : ");
		title = sc.next();  // 제목은 단어로 입력받음.
		
		if(list.isDuplicate(title)>0) {
			System.out.println("[ 알림 ] : 제목은 중복될 수 없습니다! ");
			System.out.println("");
			return;
		}
		
		sc.nextLine(); // 제목을 입력하고 enter치는거 제거
		
		System.out.print("[ 내용 ] : ");
		desc = sc.nextLine().trim(); // 내용은 line으로 입력받음 & trim => 좌우공백 제거
	
		
		System.out.print("[ 마감일자 ] : ");
		due_date = sc.nextLine().trim(); // 내용은 line으로 입력받음 & trim => 좌우공백 제거
		
		System.out.print("[ 함께하는 사람 ] : ");
		friend = sc.nextLine().trim(); // 내용은 line으로 입력받음 & trim => 좌우공백 제거
		
		System.out.print("[ 준비물 ] : ");
		material = sc.nextLine().trim(); // 내용은 line으로 입력받음 & trim => 좌우공백 제거
		
		TodoItem t = new TodoItem(category, title, desc, due_date, friend, material);
		if(list.addItem(t)>0) {
			System.out.println("[ 알림 ] : item이 정상적으로 추가되었습니다.");
			System.out.println("=======================================\n");
		}
	}

	// item 삭제
	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n========== [ item 삭제 ] ==========\n");
		System.out.print("삭제할 item의 번호를 입력하세요 : ");
		int index = sc.nextInt();
		
		System.out.print("[ 알림 ] : 아이템을 정말로 삭제하시겠습니까? (y/n) : ");
		String answer = sc.next();
		
		if(answer.equals("y")) {
			if(l.deleteItem(index)>0) {
				System.out.println("[ 알림 ] item이 정상적으로 삭제되었습니다.");
				System.out.println("");
			}
	
		}
		else if(answer.equals("n")) {
			System.out.println("[ 알림 ] item 삭제 취소 ");
		}
		else {
			System.out.println("[ 경고 ] y 또는 n을 입력해주세요.");
		}
	}
	
	// item 삭제
		public static void deleteMulti(TodoList l, int num) {
			
			if(l.deleteItem(num)>0) {
				System.out.println("[ 알림 ] item이 정상적으로 삭제되었습니다.");
				System.out.println("");
			}
		}


	// item update (edit)
	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n" + "========== [ item 수정 ] ==========\n");
		System.out.print("수정할 항목의 번호를 입력하세요 : ");
		int num = sc.nextInt();
		
		
		System.out.print("item의 새로운 카테고리를 입력하세요 : ");
		String new_category = sc.next().trim();
		
		sc.nextLine(); // enter키 제거

		System.out.print("item의 새로운 제목을 입력하세요 : ");
		String new_title = sc.next().trim(); // 단어단위로 입력받음.
		
		sc.nextLine(); // enter키 제거
		
		System.out.print("item의 새로운 내용을 입력하세요 : ");
		String new_description = sc.nextLine().trim(); // line으로 입력받음.
		
		System.out.print("item의 새로운 마감일자를 입력하세요 : ");
		String new_due_date = sc.nextLine().trim(); // line으로 입력받음.
		
		System.out.print("함께할 사람들을 입력하세요 : ");
		String new_friend = sc.nextLine().trim(); // line으로 입력받음.
		
		System.out.print("준비물을 입력하세요 : ");
		String new_material = sc.nextLine().trim(); // line으로 입력받음.
		
		TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date, new_friend, new_material);
		t.setId(num);
		if(l.editItem(t)>0) {
			System.out.println("[ 알림 ] item이 정상적으로 업데이트 되었습니다.");
			System.out.println("");
		}		
	}
	
	// 완료한 item 체크 삭제하기
			public static void del_comp(TodoList l, int num) {
				for(TodoItem item : l.findItem2(num)) {
					String str = item.getTitle().substring(item.getTitle().length()-3,item.getTitle().length());
					String str1 = "[V]";
					if(str.equals(str1)) {
						if(l.editItem2(item)>0) {
							
							System.out.println("[ 알림 ] 완료체크를 삭제하였습니다.");
							System.out.println("");
						}
						else {
							System.out.println("[ 알림 ] 오류");
							System.out.println("");
						}
					}
					else {
						System.out.println("[ 오류 ] 완료된 item이 아닙니다.");
						System.out.println("");
					}
					
				}
				
			}	
		
	// 카테고리 나열
	public static void listCateAll(TodoList l) {
		int count=0;
		System.out.println(" ");
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.println(" ");
		System.out.println("=======================================");
		System.out.printf("[ 알림 ] 총 %d개의 카테고리가 등록되어있습니다.\n", count);
		System.out.println("=======================================");
		System.out.println(" ");
	}
	
	// 카테고리 찾기
	public static void findCateList(TodoList l, String cate) {
		int count=0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("[ 알림 ] 총 %d개의 카테고리가 등록되어있습니다.\n", count);
		System.out.println();
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.println();
		System.out.println("===================================");
		System.out.printf("[ 알림 ] 전체 목록 -> 총 %d개", l.getCount());
		System.out.println();
		System.out.println("===================================");
		System.out.println();
		for(TodoItem item : l.getOrderedList(orderby,ordering)) {
			System.out.println(item.toString());
		}
		System.out.println();
	}
	

	// item들 모두 출력
	public static void listAll(TodoList l) {
	
		System.out.println("===================================== [ 전체 item 목록 ] =====================================");
		for(TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
		System.out.println("============================================================================================");
		System.out.println();
	}	
	
	// 완료한 item들 모두 출력
		public static void listAll2(TodoList l) {
		
			System.out.println("===================================== [ 완료한 item 목록 ] =====================================");
			for(TodoItem item : l.getList2()) {
				System.out.println(item.toString());
			}
			System.out.println("============================================================================================");
			System.out.println();
		}	
	
	// 완료한 item 체크하기
		public static void completeItem(TodoList l, int num) {
			for(TodoItem item : l.findItem(num)) {
				String str = item.getTitle().substring(item.getTitle().length()-3,item.getTitle().length());
				String str1 = "[V]";
				if(str.equals(str1)) {
					System.out.println("[ 알림 ] 이미 체크가 된 항목입니다.");
					System.out.println("");					
				}
				else {
					if(l.editItem(item)>0) {
						System.out.println("[ 알림 ] 체크를 완료하였습니다.");
						System.out.println("");
						}
					else {
						System.out.println("[ 알림 ] 오류");
						System.out.println("");
					}
				}
			}
		}	
}
