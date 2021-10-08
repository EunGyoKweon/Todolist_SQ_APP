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
	
	// item ���� �߰�
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== [ item �߰� ] ==========\n");
		
		System.out.print("[ ī�װ� ] : "); // ī�װ� �Է¹���.
		category = sc.next();  
		
		sc.nextLine(); // ������ �Է��ϰ� enterġ�°� ����
		
		System.out.print("[ ���� ] : ");
		title = sc.next();  // ������ �ܾ�� �Է¹���.
		
		// ���� �ߺ�üũ
		if (list.isDuplicate(title)) {
			System.out.println("");
			System.out.printf("[ ��� ] item�� ������ �ߺ��� �� �����ϴ�.");
			System.out.println("");
			return;
		}
		
		sc.nextLine(); // ������ �Է��ϰ� enterġ�°� ����
		
		System.out.print("[ ���� ] : ");
		desc = sc.nextLine().trim(); // ������ line���� �Է¹��� & trim => �¿���� ����
	
		
		System.out.print("[ �������� ] : ");
		due_date = sc.nextLine().trim(); // ������ line���� �Է¹��� & trim => �¿���� ����
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
		System.out.println("[ �˸� ] : item�� ���������� �߰��Ǿ����ϴ�.");
		System.out.println("=======================================\n");
	}

	// item ����
	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n========== [ item ���� ] ==========\n");
		System.out.print("������ item�� ��ȣ�� �Է��ϼ��� : ");
		int item_num = sc.nextInt();
		
		TodoItem item = l.getitem(item_num);
		
		String cate = item.getCategory();
		String title = item.getTitle();
		String desc = item.getDesc();
		String due = item.getDue_date();
		String time = item.getCurrent_date();
		
		System.out.println(item_num + ". " + "[ " + cate + " ] / " + "[ ���� ] : " + title + " / [ ���� ] : " + desc + " / " + due + " / [ item �����ð� ] : " + time);
		System.out.print("[ �˸� ] : �� �׸��� �����Ͻðڽ��ϱ�? (y/n) : ");
		String answer = sc.next();
		
		if(answer.equals("y")) {
			l.deleteItem(item);
			System.out.println("[ �˸� ] item�� ���������� �����Ǿ����ϴ�.");
		}
		else if(answer.equals("n")) {
			System.out.println("[ �˸� ] item ���� ��� ");
		}
		else {
			System.out.println("[ ��� ] y �Ǵ� n�� �Է����ּ���.");
		}
	}


	// item update (edit)
	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n" + "========== [ item ���� ] ==========\n");
		System.out.print("������ �׸��� ��ȣ�� �Է��ϼ��� : ");
		int num = sc.nextInt();
		
		
		System.out.print("item�� ���ο� ī�װ��� �Է��ϼ��� : ");
		String new_category = sc.next().trim();
		
//		if (!l.isDuplicate(title)) {
//			System.out.println("[ ��� ] " + title + "�̶�� ������ ���� item�� ���������ʽ��ϴ�.");
//			System.out.println("=======================================\n");
//			return;
//		}
		
		sc.nextLine(); // enterŰ ����

		System.out.print("item�� ���ο� ������ �Է��ϼ��� : ");
		String new_title = sc.next().trim(); // �ܾ������ �Է¹���.
		if (l.isDuplicate(new_title)) {
			System.out.println("[ ��� ] ������ �ߺ��� �� �����ϴ�!");
			System.out.println();
			return;
		}
		
		sc.nextLine(); // enterŰ ����
		
		System.out.print("item�� ���ο� ������ �Է��ϼ��� : ");
		String new_description = sc.nextLine().trim(); // line���� �Է¹���.
		
		System.out.print("item�� ���ο� �������ڸ� �Է��ϼ��� : ");
		String new_due_date = sc.nextLine().trim(); // line���� �Է¹���.
		
		TodoItem item = l.getitem(num);
		l.deleteItem(item);
		
		TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
		l.addItem(t);
		
		System.out.println("[ �˸� ] item�� ���������� ������Ʈ �Ǿ����ϴ�.");
		System.out.println("");
	}

	// item�� ��� ���
	public static void listAll(TodoList l) {
		int num = 0, num2 = 1;
		System.out.println("===================================== [ ��ü item ��� ] =====================================");
		for (TodoItem i : l.getList()) {
			num++;
		}
		System.out.println("[ ��ü ���, �� " + num + "�� ]");
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
			System.out.println("<<<<<<<<  " + count + "���� item�� ���������� �ҷ��Խ��ϴ�." + "  >>>>>>>>");
			
		}catch(FileNotFoundException e) {
			// e.printStackTrace();
			System.out.println();
			System.out.println("todolist.txt ������ �����ϴ�.");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
