package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("======== [ TodoList ��ɾ� ���� ] ========");
        System.out.println("1. item �߰� - add ");
        System.out.println("2. item ���� - del ");
        System.out.println("3. item ����  - edit ");
        System.out.println("4. ��ü item�� ���� - ls ");
        System.out.println("5. item ����� ���� - ls_name_asc ");
        System.out.println("6. item ���񿪼� ���� - ls_name_desc ");
        System.out.println("7. item �����ð��� ���� - ls_date ");
        System.out.println("8. item �����ð����� ���� - ls_date_desc");
        System.out.println("9. ����� ���뿡�� Ű���� ã�� - find <Ű����>");
        System.out.println("10. ī�װ����� Ű���� ã�� - find_cate <Ű����>");
        System.out.println("11. ī�װ� ���� ��� - ls_cate");
        System.out.println("12. ���� - exit ");
        System.out.println();
    }
    
    public static void prompt() {
    	System.out.print("������ �ұ��? : ");
    }
}
