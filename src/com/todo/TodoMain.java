package com.todo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		
		TodoUtil.loadList(l, "todolist.txt"); // file load
		
		Menu.displaymenu();  // 메뉴 출력
		do {
			
			Menu.prompt();
			isList = false; // do while문을 실행할 때 isList가 true가 되는 과정이 있을 것임.
			String str = sc.nextLine();
			
			StringTokenizer st = new StringTokenizer(str," ");
			String choice = st.nextToken();
			
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				System.out.println("제목순으로 정렬하였습니다.");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("제목역순으로 정렬하였습니다.");
				isList = true;  // 정렬을 완료하고나면 isList변수 값을 true로 설정
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("item 생성 순서대로 정렬하였습니다.");
				isList = true;
				break;

			case "exit":
				quit = true;
				System.out.println("정상적으로 저장되었습니다.");
				break;
			
			case "help":
				Menu.displaymenu();
				break;
				
			case "find":
				String find = st.nextToken();
				l.find_keyword(find);
				break;			
			
			case "find_cate":
				String f = st.nextToken();
				l.find_cate(f);
				break;
				
			case "ls_date_desc":
				l.reverseByDate();
				isList = true;
				System.out.println("item 생성역순서대로 정렬하였습니다.");
				break;
				
			case "ls_cate":
				l.cate();
				break;
				
			default:
				System.out.println("정확한 명령어를 입력하세요. (도움말 - help)");
				break;
			}
			
			if(isList) {
				l.listAll(); 
				System.out.println(); 
				// isList가 true일 경우 즉, List가 정렬이 완료되면 list에 있는 item들을 모두 출력
			}
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
