package com.jayce.designpattern.composition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jayce Tang
 * @version 1.0
 * @date 2019/11/7 18:06
 * @description 组合模式
 * 将对象组合成树形结构以表示"部分-整体"的层次结构。组合模式使得用户对单个对象和组合对象的使用具有一致性。
 */
public class Composition {
    public static void main(String[] args) {

        Student studentLeader = new Student("小明","学生会主席");

        Student committeeMember = new Student("小刚","学生会委员");

        Student student = new Student("小红","学生");

        committeeMember.add(student);
        studentLeader.add(committeeMember);

        System.out.println("-"+studentLeader);
        studentLeader.get().forEach(sl->{
            System.out.println("--"+sl);
            sl.get().forEach(cm->{
                System.out.println("---"+cm);
            });
        });
    }
}

class Student{
    private String name;

    private String position;

    private List<Student> students;

    public Student(String name, String position) {
        this.name = name;
        this.position = position;
        students = new ArrayList();
    }

    public void add(Student student){
        students.add(student);
    }

    public void remove(Student student){
        students.remove(student);
    }

    public List<Student> get(){
        return students;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", position=" + position + "]";
    }
}