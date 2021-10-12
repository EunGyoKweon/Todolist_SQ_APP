package com.todo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.*;

import com.todo.service.DbConnect;
import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	public Connection conn;
	private List<TodoItem> list; // TodoItem ��ü���� List�� ������ list

	public TodoList() {
		this.list = new ArrayList<TodoItem>(); // �� list�� ArrayList�̴�.
		this.conn = DbConnect.getConnection();
	}
	
	// item �߰�
	public int addItem(TodoItem t) {		
		
		String sql="insert into todolistSQL (title, memo, category, current_date, due_date, friend, material)"+"values(?,?,?,?,?,?,?);";
		PreparedStatement pstmt;
		
		int count=0;
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,t.getTitle());
			pstmt.setString(2,t.getDesc());
			pstmt.setString(3,t.getCategory());
			pstmt.setString(4,t.getCurrent_date());
			pstmt.setString(5,t.getDue_date());
			pstmt.setString(6,t.getFriend());
			pstmt.setString(7,t.getMaterial());
			count=pstmt.executeUpdate();
			pstmt.close();	
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	// item title �ߺ�üũ
		public int isDuplicate(String title) {
			int count=0;
			PreparedStatement pstmt;
			try {
				String sql = "SELECT count(id) FROM todolistSQL WHERE title=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				ResultSet rs=pstmt.executeQuery();
				count = rs.getInt("count(id)");
				pstmt.close();	
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return count;
		}

	// item ����
	public int deleteItem(int index) {
		String sql="delete from todolistSQL where id=?;";
		PreparedStatement pstmt;
		int count=0;
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);
			count=pstmt.executeUpdate();
			pstmt.close();	
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// �Ϸ�� item üũǥ�� �־��ֱ�
	public ArrayList<TodoItem> findItem(int num) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		String sql="SELECT * FROM todolistSQL where id = ?;";
		PreparedStatement pstmt;
		int count=num;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, count);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				title += "[V]";
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String friend = rs.getString("friend");
				String material = rs.getString("material");
						
				TodoItem t = new TodoItem(category, title, description, due_date, friend, material);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			pstmt.close();	
					
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// �Ϸ�� item üũǥ�� �־��ֱ�
		public ArrayList<TodoItem> findItem2(int num) {
			ArrayList<TodoItem> list = new ArrayList<TodoItem>();
			String sql="SELECT * FROM todolistSQL where id = ?;";
			PreparedStatement pstmt;
			int count=num;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, count);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					int id=rs.getInt("id");
					String category = rs.getString("category");
					String title = rs.getString("title");
					String description = rs.getString("memo");
					String due_date = rs.getString("due_date");
					String current_date = rs.getString("current_date");
					String friend = rs.getString("friend");
					String material = rs.getString("material");
							
					TodoItem t = new TodoItem(category, title, description, due_date, friend, material);
					t.setId(id);
					t.setCurrent_date(current_date);
					list.add(t);
				}
				pstmt.close();	
						
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
	
	// üũǥ�� ����
		public int remove_check(TodoItem t) {
			String sql="update todolistSQL set title=?,memo=?, category=?, current_date=?, due_date=?, friend=?, material=?"+"where id = ?;";
			PreparedStatement pstmt;
			int count=0;
			try {
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,t.getTitle());
				pstmt.setString(2,t.getDesc());
				pstmt.setString(3,t.getCategory());
				pstmt.setString(4,t.getCurrent_date());
				pstmt.setString(5,t.getDue_date());
				pstmt.setString(6,t.getFriend());
				pstmt.setString(7,t.getMaterial());
				pstmt.setInt(8, t.getId());
				count=pstmt.executeUpdate();
				pstmt.close();	
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return count;
		}

		
		// item üũǥ�� ����
		public int editItem2(TodoItem t) {
			String sql="update todolistSQL set title=?,memo=?, category=?, current_date=?, due_date=?, friend=?, material=?"+"where id = ?;";
			PreparedStatement pstmt;
			int count=0;
			try {
				
				pstmt = conn.prepareStatement(sql);
				String str = t.getTitle().substring(0,t.getTitle().length()-3);
				pstmt.setString(1,str);
				pstmt.setString(2,t.getDesc());
				pstmt.setString(3,t.getCategory());
				pstmt.setString(4,t.getCurrent_date());
				pstmt.setString(5,t.getDue_date());
				pstmt.setString(6,t.getFriend());
				pstmt.setString(7,t.getMaterial());
				pstmt.setInt(8, t.getId());
				count=pstmt.executeUpdate();
				pstmt.close();	
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return count;
		}

	// item ����
	public int editItem(TodoItem t) {
		String sql="update todolistSQL set title=?, memo=?, category=?, current_date=?, due_date=?, friend=?, material=?"+"where id = ?;";
		PreparedStatement pstmt;
		int count=0;
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,t.getTitle());
			pstmt.setString(2,t.getDesc());
			pstmt.setString(3,t.getCategory());
			pstmt.setString(4,t.getCurrent_date());
			pstmt.setString(5,t.getDue_date());
			pstmt.setString(6,t.getFriend());
			pstmt.setString(7,t.getMaterial());
			pstmt.setInt(8,t.getId());
			count=pstmt.executeUpdate();
			pstmt.close();	
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	// �ߺ������ʰ� ī�װ� ���
	public ArrayList<String> getCategories(){
		ArrayList<String> list = new ArrayList<String>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT DISTINCT category FROM todolistSQL";
			ResultSet rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				String category = rs.getString("category");
				list.add(category);
			}
			stmt.close();	
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// ī�װ����� keyword ã��
		public ArrayList<TodoItem> getListCategory(String keyword){
			ArrayList<TodoItem> list = new ArrayList<TodoItem>();
			PreparedStatement pstmt;
			try {
				String sql = "SELECT * FROM todolistSQL WHERE category = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, keyword);
				ResultSet rs=pstmt.executeQuery();
				
				while(rs.next()) {
					int id=rs.getInt("id");
					String category = rs.getString("category");
					String title = rs.getString("title");
					String description = rs.getString("memo");
					String due_date = rs.getString("due_date");
					String current_date = rs.getString("current_date");
					String friend = rs.getString("friend");
					String material = rs.getString("material");
					
					TodoItem t = new TodoItem(category, title, description, due_date, friend, material);
					t.setId(id);
					t.setCurrent_date(current_date);
					list.add(t);
				}
				pstmt.close();	
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		
		// ���� �ش��ϴ� item ã��
		public ArrayList<TodoItem> month(String month){
					ArrayList<TodoItem> list = new ArrayList<TodoItem>();
					PreparedStatement pstmt;
		
					try {
						String sql="SELECT * FROM todolistSQL";
						pstmt = conn.prepareStatement(sql);
						ResultSet rs=pstmt.executeQuery();
						
						while(rs.next()) {
							int id=rs.getInt("id");
							String category = rs.getString("category");
							String title = rs.getString("title");
							String description = rs.getString("memo");
							String due_date = rs.getString("due_date");
							String current_date = rs.getString("current_date");
							String friend = rs.getString("friend");
							String material = rs.getString("material");
							String str = due_date.substring(5,7);
							int str_int = Integer.parseInt(str);
							int month_int = Integer.parseInt(month);
							if(str_int==month_int) {
								TodoItem t = new TodoItem(category, title, description, due_date, friend, material);
								t.setId(id);
								t.setCurrent_date(current_date);
								list.add(t);
							}
							else {
								
							}
						}
						pstmt.close();	
						
					}catch(SQLException e) {
						e.printStackTrace();
					}
					return list;
				}
	
	// keyword �����ϴ� item ã��
	public ArrayList<TodoItem> getList(String keyword) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		keyword = "%"+keyword+"%";
		try {
			String sql = "SELECT * FROM todolistSQL WHERE title like ? or memo like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int id=rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String friend = rs.getString("friend");
				String material = rs.getString("material");
				
				TodoItem t = new TodoItem(category, title, description, due_date, friend, material);
				
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			pstmt.close();	
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// ����
	public ArrayList<TodoItem> getOrderedList(String orderby, int ordering){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt=conn.createStatement();
			String sql="SELECT * FROM todolistSQL ORDER BY "+ orderby;
	
			if(ordering==0) {
				sql+=" desc";
			}
		
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id=rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String friend = rs.getString("friend");
				String material = rs.getString("material");
				
				TodoItem t = new TodoItem(category, title, description, due_date, friend, material);
				
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();	
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	// ��ü list�� ������ return
	public ArrayList<TodoItem> getList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt=conn.createStatement();
			String sql="SELECT * FROM todolistSQL";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				int id=rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String friend = rs.getString("friend");
				String material = rs.getString("material");
				
				TodoItem t = new TodoItem(category, title, description, due_date, friend, material);
				
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	// üũ�Ϸ�� list�� ������ return
		public ArrayList<TodoItem> getList2() {
			ArrayList<TodoItem> list = new ArrayList<TodoItem>();
			Statement stmt;
			try {
				stmt=conn.createStatement();
				String sql="SELECT * FROM todolistSQL WHERE title LIKE '%[V]'";
				ResultSet rs=stmt.executeQuery(sql);
				while(rs.next()) {
					int id=rs.getInt("id");
					String category = rs.getString("category");
					String title = rs.getString("title");
					String description = rs.getString("memo");
					String due_date = rs.getString("due_date");
					String current_date = rs.getString("current_date");
					String friend = rs.getString("friend");
					String material = rs.getString("material");
					
					TodoItem t = new TodoItem(category, title, description, due_date, friend, material);
				
					t.setId(id);
					t.setCurrent_date(current_date);
					list.add(t);
				}
				stmt.close();			
			}catch(SQLException e){
				e.printStackTrace();
			}
			return list;
		}
	
	// item �� ������ return
	public int getCount() {
		Statement stmt;
		int count=0;
		try {
			stmt=conn.createStatement();
			String sql="SELECT count(id) FROM todolistSQL;";
			ResultSet rs=stmt.executeQuery(sql);
			rs.next();
			count=rs.getInt("count(id)");
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
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
	
	// Ư���� item�� ��ġ(����)�� return
	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}

}
