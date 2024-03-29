package com.jayce.designpattern.responsibility;

/**
 * @author Jayce Tang
 * @version 1.0
 * @date 2019/11/18 17:06
 * @description  责任链模式
 * 顾名思义，责任链模式（Chain of Responsibility Pattern）为请求创建了一个接收者对象的链。
 * 这种模式给予请求的类型，对请求的发送者和接收者进行解耦。这种类型的设计模式属于行为型模式。
 * 在这种模式中，通常每个接收者都包含对另一个接收者的引用。如果一个对象不能处理该请求，那么它会把相同的请求传给下一个接收者，依此类推。
 */
public class Responsibility {

    public static void main(String[] args) {


        /*
         * 通过条件判断是否能够处理，符合就行处理，否则就转交给下一个进行处理
         *
         */
        String name = "xiaobai";
        String something = "去聚餐";
        String something2 = "去旅游";
        Leader leader1 =new Supervisor(name, something);
        Leader leader2 =new BranchManager(name, something);
        Leader leader3 =new GeneralManager(name, something);
        leader1.setLeader(leader2);
        leader2.setLeader(leader3);
        leader1.handler(1);

        Leader leader4 =new Supervisor(name, something2);
        Leader leader5 =new BranchManager(name, something2);
        Leader leader6 =new GeneralManager(name, something2);
        leader4.setLeader(leader5);
        leader5.setLeader(leader6);
        leader4.handler(0);



    }
}

abstract class Leader{


    protected Leader leader;

    protected void setLeader(Leader leader){
        this.leader=leader;
    }

    protected Leader getLeader(){
        return leader;
    }

    abstract void handler(int  level);
}

//主管
class Supervisor extends Leader{
    private String name;
    private String something;
    public Supervisor(String name,String something) {
        this.name=name;
        this.something=something;
    }

    @Override
    void handler(int level) {
        //如果级别在自己的处理范围之内
        if(level>1){
            System.out.println("主管处理了  "+name+"所述的<"+something+">事情!");
        }else{
            System.out.println("主管未能处理  "+name+"所述的<"+something+">事情!转交给上级!");
            getLeader().handler(level);
        }
    }
}

//部门经理
class BranchManager extends Leader{
    private String name;
    private String something;
    public BranchManager(String name,String something) {
        this.name=name;
        this.something=something;
    }

    @Override
    void handler(int level) {
        //如果级别在自己的处理范围之内
        if(level>0){
            System.out.println("部门经理处理了  "+name+"所述的<"+something+">事情!");
        }else{
            System.out.println("部门经理未能处理  "+name+"所述的<"+something+">事情!转交给上级!");
            getLeader().handler(level);
        }
    }
}

//总经理
class GeneralManager extends Leader{
    private String name;
    private String something;
    public GeneralManager(String name,String something) {
        this.name=name;
        this.something=something;
    }

    @Override
    void handler(int level) {
        //如果级别在自己的处理范围之内
        if(level>-1){
            System.out.println("总经理处理了  "+name+"所述的<"+something+">事情!");
        }else{
            System.out.println("总经理未能处理  "+name+"所述的<"+something+">事情!转交给上级!");
            getLeader().handler(level);
        }
    }
}