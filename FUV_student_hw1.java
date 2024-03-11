/* Homework 1
 * Name: Le Thi Diem My
 * Student Id: 220050
 */
package com.gradescope.cs201;
public class FUV_student_hw1{
    private String first_name;
    private String last_name;
    private String student_id;
    public FUV_student_hw1(String fuv_email){
        String [] divided_parts=fuv_email.split("[.@]");
        if (divided_parts.length!=7 || divided_parts[0].isEmpty()|| divided_parts[1].isEmpty()||divided_parts[3].isEmpty()||!divided_parts[2].matches("\\d{6}")){
            throw new IllegalArgumentException();
        }else{
            this.first_name=divided_parts[0];
            this.last_name=divided_parts[1];
            this.student_id=divided_parts[2];
        }          
    }
    public String get_first_name(){
        return first_name;
    }
    public String get_last_name(){
        return last_name;
    }
    public String get_student_id(){
        return student_id;
    }
public static void main(String[] args){
    try{
        FUV_student_hw1 student= new FUV_student_hw1("linh.huynh.1456@student.fulbright.edu.vn");
        System.out.println(student.get_first_name());
        System.out.println(student.get_last_name());
        System.out.println(student.student_id);
    }
    catch(IllegalArgumentException e){
        System.out.println(e);
    }

    }    
}

