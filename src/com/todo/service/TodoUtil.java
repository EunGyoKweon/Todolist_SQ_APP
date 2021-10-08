package com.todo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	// item 새로 추가
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== [ item 추가 ] ==========\n");
		
		System.out.print("[ 카테고리 ] : "); // 카테고리 입력받음.
		category = sc.next();  
		
		sc.nextLine(); // 제목을 입력하고 enter치는거 제거
		
		System.out.print("[ 제목 ] : ");
		title = sc.next();  // 제목은 단어로 입력받음.
		
		// 제목 중복체크
		if (list.isDuplicate(title)) {
			System.out.println("");
			System.out.printf("[ 경고 ] item의 제목은 중복될 수 없습니다.");
			System.out.println("");
			return;
		}
		
		sc.nextLine(); // 제목을 입력하고 enter치는거 제거
		
		System.out.print("[ 내용 ] : ");
		desc = sc.nextLine().trim(); // 내용은 line으로 입력받음 & trim => 좌우공백 제거
	
		
		System.out.print("[ 마감일자 ] : ");
		due_date = sc.nextLine().trim(); // 내용은 line으로 입력받음 & trim => 좌우공백 제거
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
		System.out.println("[ 알림 ] : item이 정상적으로 추가되었습니다.");
		System.out.println("=======================================\n");
	}

	// item 삭제
	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n========== [ item 삭제 ] ==========\n");
		System.out.print("삭제할 item의 번호를 입력하세요 : ");
		int item_num = sc.nextInt();
		
		TodoItem item = l.getitem(item_num);
		
		String cate = item.getCategory();
		String title = item.getTitle();
		String desc = item.getDesc();
		String due = item.getDue_date();
		String time = item.getCurrent_date();
		
		System.out.println(item_num + ". " + "[ " + cate + " ] / " + "[ 제목 ] : " + title + " / [ 내용 ] : " + desc + " / " + due + " / [ item 생성시간 ] : " + time);
		System.out.print("[ 알림 ] : 위 항목을 삭제하시겠습니까? (y/n) : ");
		String answer = sc.next();
		
		if(answer.equals("y")) {
			l.deleteItem(item);
			System.out.println("[ 알림 ] item이 정상적으로 삭제되었습니다.");
		}
		else if(answer.equals("n")) {
			System.out.println("[ 알림 ] item 삭제 취소 ");
		}
		else {
			System.out.println("[ 경고 ] y 또는 n을 입력해주세요.");
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
		
//		if (!l.isDuplicate(title)) {
//			System.out.println("[ 경고 ] " + title + "이라는 제목을 가진 item이 존재하지않습니다.");
//			System.out.println("=======================================\n");
//			return;
//		}
		
		sc.nextLine(); // enter키 제거

		System.out.print("item의 새로운 제목을 입력하세요 : ");
		String new_title = sc.next().trim(); // 단어단위로 입력받음.
		if (l.isDuplicate(new_title)) {
			System.out.println("[ 경고 ] 제목은 중복될 수 없습니다!");
			System.out.println();
			return;
		}
		
		sc.nextLine(); // enter키 제거
		
		System.out.print("item의 새로운 내용을 입력하세요 : ");
		String new_description = sc.nextLine().trim(); // line으로 입력받음.
		
		System.out.print("item의 새로운 마감일자를 입력하세요 : ");
		String new_due_date = sc.nextLine().trim(); // line으로 입력받음.
		
		TodoItem item = l.getitem(num);
		l.deleteItem(item);
		
		TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
		l.addItem(t);
		
		System.out.println("[ 알림 ] item이 정상적으로 업데이트 되었습니다.");
		System.out.println("");
	}

	// item들 모두 출력
	public static void listAll(TodoList l) {
		int num = 0, num2 = 1;
		System.out.println("===================================== [ 전체 item 목록 ] =====================================");
		for (TodoItem i : l.getList()) {
			num++;
		}
		System.out.println("[ 전체 목록, 총 " + num + "개 ]");
		num = 0;
		
		for (TodoItem item : l.getList()) {
			System.out.println(num2 + ". " + item.toString());
			num2++;
		}
		num2 = 0;
		System.out.println("============================================================================================");
		System.out.println();
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			
			Writer w = new FileWriter(filename);
			
			for(TodoItem item: l.getList()) {
				w.write(item.toSaveString());
	        }
			w.close();
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		try {			
			BufferedReader in = new BufferedReader(new FileReader(filename));
			
			String str;
			int count = 0;
			
			
			while((str=in.readLine()) != null) {
				
				StringTokenizer st = new StringTokenizer(str,"##");
				String category = st.nextToken();
				String title = st.nextToken();
				String des = st.nextToken();
				String due_date = st.nextToken();
				String time = st.nextToken();
				
				TodoItem t = new TodoItem(category, title, des, due_date);
				t.setCurrent_date(time);
				
				l.addItem(t);
				count ++;
			}
			
			in.close();
			System.out.println("<<<<<<<<  " + count + "개의 item을 정상적으로 불러왔습니다." + "  >>>>>>>>");
			
		}catch(FileNotFoundException e) {
			// e.printStackTrace();
			System.out.println();
			System.out.println("todolist.txt 파일이 없습니다.");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
