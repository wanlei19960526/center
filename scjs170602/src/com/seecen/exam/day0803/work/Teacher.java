package com.seecen.exam.day0803.work;

/**
 * 老师管理系统类(有7个方法,6个菜单功能)
 * @scjs170602
 * @author 【万磊】
 * @2017年8月3日
 */
public class Teacher {
	String[] names = new String[100];

	/**
	 * 打印菜单
	 */
	public void printMenu() {
		System.out.println("*******欢迎来到老师管理系统*******");
		System.out.println("\t1.添加老师");
		System.out.println("\t2.删除老师");
		System.out.println("\t3.修改老师");
		System.out.println("\t4.全局查找老师");
		System.out.println("\t5.索引查找老师");
		System.out.println("\t6.显示所有老师");
		System.out.println("*****************************");
	}

	/**
	 * 添加老师
	 * 
	 * @param name
	 *            老师名
	 * @return 添加成功返回true，失败返回false
	 */
	public boolean add(String name) {
		for (int i = 0; i < names.length; i++) {
			if (names[i] == null) {
				names[i] = name;
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据老师名(字母忽略大小写)查找
	 * 
	 * @param name
	 *            老师名
	 * @return 查到了返回名字所在的索引值，否则返回-1
	 */
	public int findByName(String name) {
		for (int i = 0; i < names.length; i++) {
			if (name.equalsIgnoreCase(names[i])) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * 在开始索引和结束索引之间,根据老师名(字母忽略大小写)查找
	 * @param startIndex 开始索引
	 * @param endIndex 结束索引
	 * @param name 老师名
	 * @return 查到了返回名字所在的索引值，否则返回-1
	 */
	public int findByIndex(int startIndex,int endIndex,String name){
		for (int i = startIndex - 1; i < endIndex; i++) {
			if (name.equalsIgnoreCase(names[i])) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * 根据姓名删除老师信息
	 * 
	 * @param name
	 *            老师名
	 * @return 删除成功返回true，否则返回false
	 */
	public boolean deleteName(String name) {
		if (findByName(name) == -1) {
			return false;
		} else {
			// 将数组中查找得到的索引值的位置清空
			names[findByName(name)] = null;
			return true;
		}
	}

	/**
	 * 根据新名字修改旧姓名,如果旧姓名存在就修改，否则不修改
	 * 
	 * @param oldName
	 *            旧名字
	 * @param newName
	 *            新名字
	 * @return 修改成功返回true，否则返回false
	 */
	public boolean updateName(String oldName, String newName) {
		if (findByName(oldName) == -1) {
			return false;
		} else {
			names[findByName(oldName)] = newName;
			return true;
		}
	}

	/**
	 * 显示所有老师的信息
	 */
	public void showAll() {
		for (String str : names) {
			System.out.print(str == null ? "" : str + "  ");
		}
	}
}
