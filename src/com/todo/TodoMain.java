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
		boolean quit = false;
		
		Menu.displaymenu();  // 메뉴 출력
		do {
			
			Menu.prompt();
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

			case "ls_name":
				System.out.println("[ 알림 ] : 제목순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"title",1);
				break;

			case "ls_name_desc":
				System.out.println("[ 알림 ] : 제목역순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"title",0);
				break;
				
			case "ls_date":
				System.out.println("[ 알림 ] : 마감기한이 얼마남지 않은 순서대로 정렬하였습니다.");
				TodoUtil.listAll(l,"due_date",1);
				break;

			case "exit":
				quit = true;
				System.out.println("[ 알림 ] : 정상적으로 저장되었습니다.");
				break;
			
			case "help":
				Menu.displaymenu();
				break;
				
			case "comp":
				int comp = Integer.parseInt(st.nextToken());
				TodoUtil.completeItem(l,comp);
				break;
				
			case "ls_comp":
				TodoUtil.listAll2(l);
				break;
				
			case "find":
				String find = st.nextToken();
				TodoUtil.findList(l,find);
				break;			
			
			case "find_cate":
				String f = st.nextToken();
				TodoUtil.findCateList(l,f);
				break;
				
			case "ls_date_desc":
				System.out.println("[ 알림 ] : 마감기한이 많이 남은 순서대로 정렬하였습니다.");
				TodoUtil.listAll(l,"due_date",0);
				break;
				
			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;
				
			case "del_comp":
				int del_comp = Integer.parseInt(st.nextToken());
				TodoUtil.del_comp(l,del_comp);
				break;
				
			case "mul_del":
				while(st.hasMoreTokens()) {
					int mul_del = Integer.parseInt(st.nextToken());
					TodoUtil.deleteMulti(l,mul_del);
				}
				break;
				
			case "mul_check":
				while(st.hasMoreTokens()) {
					int mul_comp = Integer.parseInt(st.nextToken());
					TodoUtil.completeItem(l,mul_comp);
				}
				break;
				
			case "month":
				int month = Integer.parseInt(st.nextToken());
				if(month<1 || month>12) {
					System.out.println("[ 알림 ] : 잘못된 월을 입력하셨습니다.");
				}
				else {
					String str_month = Integer.toString(month);
					TodoUtil.find_monthItem(l,str_month);
				}
				
				break;
				
			default:
				System.out.println("[ 알림 ] : 정확한 명령어를 입력하세요. (도움말 - help)");
				break;
			}
		} while (!quit);
	}
}
