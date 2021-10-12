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
			System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
			System.out.println();
		}
	
	// item find
	public static void findList(TodoList l, String keyword) {
		int count=0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
	}
	
	// item ���� �߰�
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date, friend, material;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== [ item �߰� ] ==========\n");
		
		System.out.print("[ ī�װ� ] : "); // ī�װ� �Է¹���.
		category = sc.next();  
		
		sc.nextLine(); // ������ �Է��ϰ� enterġ�°� ����
		
		System.out.print("[ ���� ] : ");
		title = sc.next();  // ������ �ܾ�� �Է¹���.
		
		if(list.isDuplicate(title)>0) {
			System.out.println("[ �˸� ] : ������ �ߺ��� �� �����ϴ�! ");
			System.out.println("");
			return;
		}
		
		sc.nextLine(); // ������ �Է��ϰ� enterġ�°� ����
		
		System.out.print("[ ���� ] : ");
		desc = sc.nextLine().trim(); // ������ line���� �Է¹��� & trim => �¿���� ����
	
		
		System.out.print("[ �������� ] : ");
		due_date = sc.nextLine().trim(); // ������ line���� �Է¹��� & trim => �¿���� ����
		
		System.out.print("[ �Բ��ϴ� ��� ] : ");
		friend = sc.nextLine().trim(); // ������ line���� �Է¹��� & trim => �¿���� ����
		
		System.out.print("[ �غ� ] : ");
		material = sc.nextLine().trim(); // ������ line���� �Է¹��� & trim => �¿���� ����
		
		TodoItem t = new TodoItem(category, title, desc, due_date, friend, material);
		if(list.addItem(t)>0) {
			System.out.println("[ �˸� ] : item�� ���������� �߰��Ǿ����ϴ�.");
			System.out.println("=======================================\n");
		}
	}

	// item ����
	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n========== [ item ���� ] ==========\n");
		System.out.print("������ item�� ��ȣ�� �Է��ϼ��� : ");
		int index = sc.nextInt();
		
		System.out.print("[ �˸� ] : �������� ������ �����Ͻðڽ��ϱ�? (y/n) : ");
		String answer = sc.next();
		
		if(answer.equals("y")) {
			if(l.deleteItem(index)>0) {
				System.out.println("[ �˸� ] item�� ���������� �����Ǿ����ϴ�.");
				System.out.println("");
			}
	
		}
		else if(answer.equals("n")) {
			System.out.println("[ �˸� ] item ���� ��� ");
		}
		else {
			System.out.println("[ ��� ] y �Ǵ� n�� �Է����ּ���.");
		}
	}
	
	// item ����
		public static void deleteMulti(TodoList l, int num) {
			
			if(l.deleteItem(num)>0) {
				System.out.println("[ �˸� ] item�� ���������� �����Ǿ����ϴ�.");
				System.out.println("");
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
		
		sc.nextLine(); // enterŰ ����

		System.out.print("item�� ���ο� ������ �Է��ϼ��� : ");
		String new_title = sc.next().trim(); // �ܾ������ �Է¹���.
		
		sc.nextLine(); // enterŰ ����
		
		System.out.print("item�� ���ο� ������ �Է��ϼ��� : ");
		String new_description = sc.nextLine().trim(); // line���� �Է¹���.
		
		System.out.print("item�� ���ο� �������ڸ� �Է��ϼ��� : ");
		String new_due_date = sc.nextLine().trim(); // line���� �Է¹���.
		
		System.out.print("�Բ��� ������� �Է��ϼ��� : ");
		String new_friend = sc.nextLine().trim(); // line���� �Է¹���.
		
		System.out.print("�غ��� �Է��ϼ��� : ");
		String new_material = sc.nextLine().trim(); // line���� �Է¹���.
		
		TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date, new_friend, new_material);
		t.setId(num);
		if(l.editItem(t)>0) {
			System.out.println("[ �˸� ] item�� ���������� ������Ʈ �Ǿ����ϴ�.");
			System.out.println("");
		}		
	}
	
	// �Ϸ��� item üũ �����ϱ�
			public static void del_comp(TodoList l, int num) {
				for(TodoItem item : l.findItem2(num)) {
					String str = item.getTitle().substring(item.getTitle().length()-3,item.getTitle().length());
					String str1 = "[V]";
					if(str.equals(str1)) {
						if(l.editItem2(item)>0) {
							
							System.out.println("[ �˸� ] �Ϸ�üũ�� �����Ͽ����ϴ�.");
							System.out.println("");
						}
						else {
							System.out.println("[ �˸� ] ����");
							System.out.println("");
						}
					}
					else {
						System.out.println("[ ���� ] �Ϸ�� item�� �ƴմϴ�.");
						System.out.println("");
					}
					
				}
				
			}	
		
	// ī�װ� ����
	public static void listCateAll(TodoList l) {
		int count=0;
		System.out.println(" ");
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.println(" ");
		System.out.println("=======================================");
		System.out.printf("[ �˸� ] �� %d���� ī�װ��� ��ϵǾ��ֽ��ϴ�.\n", count);
		System.out.println("=======================================");
		System.out.println(" ");
	}
	
	// ī�װ� ã��
	public static void findCateList(TodoList l, String cate) {
		int count=0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("[ �˸� ] �� %d���� ī�װ��� ��ϵǾ��ֽ��ϴ�.\n", count);
		System.out.println();
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.println();
		System.out.println("===================================");
		System.out.printf("[ �˸� ] ��ü ��� -> �� %d��", l.getCount());
		System.out.println();
		System.out.println("===================================");
		System.out.println();
		for(TodoItem item : l.getOrderedList(orderby,ordering)) {
			System.out.println(item.toString());
		}
		System.out.println();
	}
	

	// item�� ��� ���
	public static void listAll(TodoList l) {
	
		System.out.println("===================================== [ ��ü item ��� ] =====================================");
		for(TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
		System.out.println("============================================================================================");
		System.out.println();
	}	
	
	// �Ϸ��� item�� ��� ���
		public static void listAll2(TodoList l) {
		
			System.out.println("===================================== [ �Ϸ��� item ��� ] =====================================");
			for(TodoItem item : l.getList2()) {
				System.out.println(item.toString());
			}
			System.out.println("============================================================================================");
			System.out.println();
		}	
	
	// �Ϸ��� item üũ�ϱ�
		public static void completeItem(TodoList l, int num) {
			for(TodoItem item : l.findItem(num)) {
				String str = item.getTitle().substring(item.getTitle().length()-3,item.getTitle().length());
				String str1 = "[V]";
				if(str.equals(str1)) {
					System.out.println("[ �˸� ] �̹� üũ�� �� �׸��Դϴ�.");
					System.out.println("");					
				}
				else {
					if(l.editItem(item)>0) {
						System.out.println("[ �˸� ] üũ�� �Ϸ��Ͽ����ϴ�.");
						System.out.println("");
						}
					else {
						System.out.println("[ �˸� ] ����");
						System.out.println("");
					}
				}
			}
		}	
}
