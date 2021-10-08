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
		
		Menu.displaymenu();  // �޴� ���
		do {
			
			Menu.prompt();
			isList = false; // do while���� ������ �� isList�� true�� �Ǵ� ������ ���� ����.
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
				System.out.println("��������� �����Ͽ����ϴ�.");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("���񿪼����� �����Ͽ����ϴ�.");
				isList = true;  // ������ �Ϸ��ϰ��� isList���� ���� true�� ����
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("item ���� ������� �����Ͽ����ϴ�.");
				isList = true;
				break;

			case "exit":
				quit = true;
				System.out.println("���������� ����Ǿ����ϴ�.");
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
				System.out.println("item ������������� �����Ͽ����ϴ�.");
				break;
				
			case "ls_cate":
				l.cate();
				break;
				
			default:
				System.out.println("��Ȯ�� ��ɾ �Է��ϼ���. (���� - help)");
				break;
			}
			
			if(isList) {
				l.listAll(); 
				System.out.println(); 
				// isList�� true�� ��� ��, List�� ������ �Ϸ�Ǹ� list�� �ִ� item���� ��� ���
			}
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
